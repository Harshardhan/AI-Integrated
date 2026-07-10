package com.example.demo;

import java.util.List;

import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

	private static final Logger logger = LoggerFactory.getLogger(DocumentService.class);

	private final ResourceLoader resourceLoader;
	private final VectorStore vectorStore;
	private final AIClient aiClient;

	public DocumentService(ResourceLoader resourceLoader, VectorStore vectorStore, AIClient aiClient) {
		this.resourceLoader = resourceLoader;
		this.vectorStore = vectorStore;
		this.aiClient = aiClient;
	}

	/**
	 * Verify that the PDF exists.
	 */
	public void verifyPdf() {

		Resource resource = resourceLoader.getResource("classpath:documents/Leave Policy.pdf");

		logger.info("Exists       : {}", resource.exists());
		logger.info("Filename     : {}", resource.getFilename());

		try {
			logger.info("URI          : {}", resource.getURI());
			logger.info("Readable     : {}", resource.isReadable());
			logger.info("Content Size : {} bytes", resource.contentLength());
		} catch (Exception e) {
			logger.error("Error reading PDF", e);
		}
	}

	/**
	 * Run ONLY ONCE to load the PDF into PGVector.
	 */
	public void loadPdf() {

		logger.info("Reading PDF...");

		PagePdfDocumentReader pdfReader = new PagePdfDocumentReader(
				resourceLoader.getResource("classpath:documents/Leave Policy.pdf"));

		List<Document> documents = pdfReader.get();

		logger.info("Pages Found : {}", documents.size());

		/*
		 * Smaller chunks improve semantic search accuracy.
		 */
		TokenTextSplitter splitter = TokenTextSplitter.builder().withChunkSize(800).withMinChunkSizeChars(300)
				.withMinChunkLengthToEmbed(10).withMaxNumChunks(10000).build();
		List<Document> chunks = splitter.apply(documents);

		logger.info("Chunks Created : {}", chunks.size());

		logger.info("Saving chunks into PGVector...");

		vectorStore.add(chunks);

		logger.info("Vector Store Updated Successfully.");
	}

	/**
	 * Search the Vector Database.
	 */
	public List<Document> search(String question) {

		logger.info("Question : {}", question);

		SearchRequest request = SearchRequest.builder().query(question).topK(5).similarityThreshold(0.60).build();

		List<Document> results = vectorStore.similaritySearch(request);

		logger.info("Retrieved {} documents", results.size());

		int count = 1;

		for (Document doc : results) {

			logger.info("------------------------------------");
			logger.info("Document {}", count++);
			logger.info("Score : {}", doc.getScore());
			logger.info(doc.getText());
		}

		return results;
	}
	
	public String askQuestion(String question) {

	    List<Document> docs = search(question);

	    String context = docs.stream()
	            .map(Document::getText)
	            .collect(Collectors.joining("\n\n"));

	    RagRequest ragRequest = new RagRequest(question, context);

	    RagResponse response = aiClient.rag(ragRequest);

	    return response.getAnswer();
	}
}