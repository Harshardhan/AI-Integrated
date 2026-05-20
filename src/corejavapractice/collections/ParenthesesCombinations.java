package corejavapractice.collections;


import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ParenthesesCombinations {

    static class Node {
        String current;
        int open;
        int close;

        Node(String current, int open, int close) {
            this.current = current;
            this.open = open;
            this.close = close;
        }
    }

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        Stack<Node> stack = new Stack<>();

        // Push the initial state
        stack.push(new Node("", 0, 0));

        while (!stack.isEmpty()) {
            Node node = stack.pop();

            // If the current string is complete, add it to the result
            if (node.current.length() == n * 2) {
                result.add(node.current);
                continue;
            }

            // Add an open parenthesis if the count is less than n
            if (node.open < n) {
                stack.push(new Node(node.current + "(", node.open + 1, node.close));
            }

            // Add a close parenthesis if it won't break the balance
            if (node.close < node.open) {
                stack.push(new Node(node.current + ")", node.open, node.close + 1));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int n = 3; // Example input
        List<String> combinations = generateParenthesis(n);
        System.out.println("All combinations of well-formed parentheses:");
        for (String combination : combinations) {
            System.out.println(combination);
        }
    }
}
