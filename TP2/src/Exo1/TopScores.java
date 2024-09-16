package Exo1;

import java.util.*;

public class TopScores extends MyLinkedList {
    private static final int MAX_SCORES = 10;
    private MyLinkedList<Integer> scores;

    public TopScores() {
        this.scores = new MyLinkedList<>();
    }

    // Méthode pour ajouter un score
    public void add(int score) {
        // Ajouter le score dans la liste en ordre décroissant
        Node current = scores.head;
        Node newNode = new Node(score);

        if (scores.size() < MAX_SCORES) {
            // Si la liste n'a pas encore 10 éléments, insérer directement
            while (current != null && current.next != null && current.next.value >= score) {
                current = current.next;
            }
            if (current == null) {
                scores.add(score);
            } else {
                // Insert after current
                newNode.next = current.next;
                if (current.next != null) {
                    current.next.prev = newNode;
                }
                current.next = newNode;
                newNode.prev = current;
                if (newNode.next == null) {
                    scores.tail = newNode;
                }
                scores.size++;
            }
        } else {
            // La liste a 10 éléments, il faut comparer avec le plus bas score
            if (score > scores.tail.value) {
                // Remplacer le plus bas score
                removeLowest();
                add(score);
            }
        }
    }

    // Méthode pour supprimer le score le plus bas
    private void removeLowest() {
        if (scores.tail != null) {
            scores.remove(scores.tail.value);
        }
    }

    // Méthode pour obtenir les scores
    public List<Integer> getScores() {
        List<Integer> result = new ArrayList<>();
        Node current = scores.head;
        while (current != null) {
            result.add(current.value);
            current = current.next;
        }
        return result;
    }

    // Classe interne Node pour la liste chaînée
    private class Node {
        int value;
        Node next;
        Node prev;

        Node(int value) {
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }

    // Méthode main pour tester
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
    }
}
