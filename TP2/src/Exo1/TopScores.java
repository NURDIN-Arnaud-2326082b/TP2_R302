package Exo1;

import java.util.*;

public class TopScores {
    private static final int MAX_SCORES = 10;
    private MyLinkedList<Integer> scores;

    public TopScores() {
        this.scores = new MyLinkedList<>();
    }

    // Method to add a score
    public void add(int score) {
        MyLinkedList<Integer>.Node current = scores.head;
        MyLinkedList<Integer>.Node newNode = scores.new Node(score); // Use MyLinkedList's Node class

        if (scores.size < MAX_SCORES) {
            // If the list has less than 10 elements, insert directly
            if (current == null) {
                scores.add(0,score); // Add as the first element if the list is empty
                return;
            }

            // Insert in descending order
            while (current != null && current.next != null && current.next.value >= score) {
                current = current.next;
            }

            if (current == null || current.value < score) {
                scores.add(0,score); // Insert at the head if the new score is the highest
            } else {
                // Insert after the current node
                newNode.next = current.next;
                newNode.prev = current;
                if (current.next != null) {
                    current.next.prev = newNode;
                }
                current.next = newNode;
                if (newNode.next == null) {
                    scores.tail = newNode;
                }
                scores.size++;
            }
        } else {
            // If the list has 10 elements, compare with the lowest score
            if (score > scores.tail.value) {
                // Remove the lowest score
                removeLowest();
                add(score);
            }
        }
    }

    // Method to remove the lowest score
    private void removeLowest() {
        if (scores.tail != null) {
            scores.remove(scores.tail.value); // Assuming MyLinkedList has a remove method
        }
    }

    // Method to get the scores
    public List<Integer> getScores() {
        List<Integer> result = new ArrayList<>();
        MyLinkedList<Integer>.Node current = scores.head;
        while (current != null) {
            result.add(current.value);
            current = current.next;
        }
        return result;
    }

    // Main method for testing
    public static void main(String[] args) {
        TopScores topScores = new TopScores();
        topScores.add(50);
        topScores.add(20);
        topScores.add(30);
        topScores.add(40);
        topScores.add(60);
        topScores.add(70);
        topScores.add(80);
        topScores.add(90);
        topScores.add(100);
        topScores.add(110);
        topScores.add(120);

        System.out.println("Top Scores: " + topScores.getScores());
        
        topScores.add(200);
        
        System.out.println("Top Scores: " + topScores.getScores());

        topScores.add(5);
        
        System.out.println("Top Scores: " + topScores.getScores());

    }
}
