package corejavapractice.collections;

import java.util.*;

public class MinimumGeneticMutation {
    public static int minMutation(String startGene, String endGene, String[] bank) {
        // Gene bank as a HashSet for fast lookup
        Set<String> geneBank = new HashSet<>(Arrays.asList(bank));
        if (!geneBank.contains(endGene)) {
            return -1; // If endGene is not in the bank, mutation is impossible
        }

        // Characters representing possible mutations
        char[] geneChars = {'A', 'C', 'G', 'T'};

        // BFS setup
        Queue<String> queue = new LinkedList<>();
        queue.offer(startGene);
        Set<String> visited = new HashSet<>();
        visited.add(startGene);

        int mutations = 0;

        // BFS to find the shortest path
        while (!queue.isEmpty()) {
            int size = queue.size();

            // Process all nodes at the current level
            for (int i = 0; i < size; i++) {
                String currentGene = queue.poll();
                if (currentGene.equals(endGene)) {
                    return mutations;
                }

                // Generate all possible valid mutations
                char[] currentGeneArray = currentGene.toCharArray();
                for (int j = 0; j < currentGeneArray.length; j++) {
                    char originalChar = currentGeneArray[j];

                    for (char c : geneChars) {
                        if (c == originalChar) {
                            continue; // Skip if no mutation
                        }

                        currentGeneArray[j] = c;
                        String mutatedGene = new String(currentGeneArray);

                        // If the mutation is valid and not visited, process it
                        if (geneBank.contains(mutatedGene) && !visited.contains(mutatedGene)) {
                            queue.offer(mutatedGene);
                            visited.add(mutatedGene);
                        }
                    }

                    // Restore the original character
                    currentGeneArray[j] = originalChar;
                }
            }

            // Increment mutations count after processing the current level
            mutations++;
        }

        // If we exhaust the queue and don't find the endGene
        return -1;
    }

    public static void main(String[] args) {
        // Example input
        String startGene = "AACCGGTT";
        String endGene = "AACCGGTA";
        String[] bank = {"AACCGGTA", "AACCGCTA", "AAACGGTA"};

        int result = minMutation(startGene, endGene, bank);
        if (result != -1) {
            System.out.println("Minimum number of mutations: " + result);
        } else {
            System.out.println("Mutation is not possible.");
        }
    }

}
