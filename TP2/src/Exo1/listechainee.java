package Exo1;

public class listechainee {
	//Question 1
	
    // Tête de la liste
    private Noeud tete;

    // Constructeur pour initialiser une liste vide
    public listechainee() {
        this.tete = null;
    }

    // Méthode pour ajouter un élément à la fin de la liste (de manière récursive)
    public void ajouter(int valeur) {
        tete = ajouterRecursivement(tete, valeur);
    }

    // Méthode récursive pour ajouter un élément
    private Noeud ajouterRecursivement(Noeud noeud, int valeur) {
        if (noeud == null) {
            return new Noeud(valeur);
        } else {
            noeud.suivant = ajouterRecursivement(noeud.suivant, valeur);
            return noeud;
        }
    }

    // Méthode pour afficher les éléments de la liste
    public void afficher() {
        afficherRecursivement(tete);
    }

    // Méthode récursive pour afficher les éléments
    private void afficherRecursivement(Noeud noeud) {
        if (noeud != null) {
            System.out.print(noeud.valeur + " ");
            afficherRecursivement(noeud.suivant);
        }
    }

    // Méthode pour vérifier si la liste est vide
    public boolean estVide() {
        return tete == null;
    }

    
    //Question 2
    // Méthode pour insérer un élément au début de la liste
    public void ajouterAuDebut(int valeur) {
        Noeud nouveauNoeud = new Noeud(valeur);
        nouveauNoeud.suivant = tete; // Le nouveau noeud pointe vers l'ancienne tête
        tete = nouveauNoeud; // La tête est mise à jour pour être le nouveau noeud
    }
    
    //Question 3
    // Méthode pour trouver l'avant-dernier noeud
    public Noeud trouverAvantDernier() {
        // Vérifier si la liste est vide ou ne contient qu'un seul élément
        if (tete == null || tete.suivant == null) {
            return null; 
        }

        // Parcourir la liste pour trouver l'avant-dernier noeud
        Noeud courant = tete;
        while (courant.suivant != null && courant.suivant.suivant != null) {
            courant = courant.suivant;
        }
        
        return courant; // Le noeud courant est l'avant-dernier
    }
    
    //Question 4
    // Méthode pour inverser les éléments de la liste
    public void inverser() {
        Noeud precedent = null;
        Noeud courant = tete;
        Noeud suivant = null;

        while (courant != null) {
            // Sauvegarder le nœud suivant
            suivant = courant.suivant;

            // Inverser le lien du nœud courant
            courant.suivant = precedent;

            // Avancer les pointeurs
            precedent = courant;
            courant = suivant;
        }

        // Mettre à jour la tête de la liste
        tete = precedent;
    }
    
    //Question 5
 // Méthode pour mettre à jour la valeur d'un nœud donné 
    public boolean mettreAjour(int ancienneValeur, int nouvelleValeur) {
        Noeud courant = tete;

        // Parcourir la liste pour trouver le nœud avec l'ancienne valeur
        while (courant != null) {
            if (courant.valeur == ancienneValeur) {
                // Mise à jour de la valeur du nœud
                courant.valeur = nouvelleValeur;
                return true; // Mise à jour réussie
            }
            courant = courant.suivant;
        }
        return false; // Nœud non trouvé
    }
    
    public void inverserValeurs(int valeur1, int valeur2) {
        Noeud courant = tete;

        // Parcourir la liste pour trouver et inverser les valeurs
        while (courant != null) {
            if (courant.valeur == valeur1) {
                courant.valeur = valeur2;
            } else if (courant.valeur == valeur2) {
                courant.valeur = valeur1;
            }
            courant = courant.suivant;
        }
    }
    
    //Ajout supplémentaire de delete pour completer la méthodologie CRUD
    // Méthode pour supprimer un nœud par sa valeur
    public boolean supprimer(int valeur) {
        Noeud courant = tete;
        Noeud precedent = null;

        // Parcourir la liste pour trouver le nœud à supprimer
        while (courant != null) {
            if (courant.valeur == valeur) {
                // Si le nœud à supprimer est la tête de la liste
                if (precedent == null) {
                    tete = courant.suivant; // Déplacer la tête au nœud suivant
                } else {
                    // Sinon, sauter le nœud courant
                    precedent.suivant = courant.suivant;
                }
                return true; // Suppression réussie
            }
            // Avancer dans la liste
            precedent = courant;
            courant = courant.suivant;
        }
        return false; // Nœud avec la valeur donnée non trouvé
    }
    
    //ajout du calcul du temps pour la question 8 
    // Mesurer le temps de la méthode inverserValeurs
    public long mesurerTempsInversion(int valeur1, int valeur2) {
        long debut = System.nanoTime();
        inverserValeurs(valeur1, valeur2);
        long fin = System.nanoTime();
        long duree = fin - debut;
        System.out.println("Temps d'exécution  : " + duree + " ns");
        return duree;
    }
    // Test
    public static void main(String[] args) {
    	listechainee liste = new listechainee();
        liste.ajouter(1);
        liste.ajouter(2);
        liste.ajouter(3);
        liste.ajouter(4);
        liste.afficher();
        liste.ajouterAuDebut(5);
        System.out.println('\n');
        liste.afficher();
        System.out.println('\n');
        Noeud avantDernier = liste.trouverAvantDernier();
        System.out.println(avantDernier.valeur + " est l'avant dernier noeud");
        System.out.println('\n');
        liste.inverser();
        System.out.println("Liste après inversion : ");
        liste.afficher() ;
        liste.inverserValeurs(4,1);
        System.out.println('\n');
        liste.afficher();
        System.out.println('\n');
        liste.supprimer(5);
        liste.afficher();
    }
}
