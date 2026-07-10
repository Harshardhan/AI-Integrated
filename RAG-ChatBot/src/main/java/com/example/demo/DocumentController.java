package com.example.demo;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/document")
public class DocumentController {

	private final DocumentService service;

	public DocumentController(DocumentService service) {
		this.service = service;
	}

	@GetMapping("/verify")
	public String verify() {

		service.verifyPdf();

		return "Verified";
	}

	@GetMapping("/read")
	public String read() {

		service.loadPdf();

		return "Read";
	}

	@GetMapping("/search")
	public List<String> search(@RequestParam String question) {

		List<Document> docs = service.search(question);

		return docs.stream().map(Document::getText).toList();
	}


}