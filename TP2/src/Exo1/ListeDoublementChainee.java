package Exo1;

public class ListeDoublementChainee {

	//Question 6
	// Tête et queue de la liste
	private NoeudV2 tete;
	private NoeudV2 queue;

	// Constructeur pour initialiser une liste vide
	public ListeDoublementChainee() {
	    this.tete = null;
	    this.queue = null;
	}
    //Insérer un élément au début de la liste
    public void insererAuDebut(int valeur) {
        NoeudV2 nouveauNoeud = new NoeudV2(valeur);
        if (tete == null) {
            tete = nouveauNoeud;
            queue = nouveauNoeud;
        } else {
            nouveauNoeud.suivant = tete;
            tete.precedent = nouveauNoeud;
            tete = nouveauNoeud;
        }
    }

    //Insérer un élément à la fin de la liste
    public void insererALaFin(int valeur) {
        NoeudV2 nouveauNoeud = new NoeudV2(valeur);
        if (queue == null) {
            tete = nouveauNoeud;
            queue = nouveauNoeud;
        } else {
            queue.suivant = nouveauNoeud;
            nouveauNoeud.precedent = queue;
            queue = nouveauNoeud;
        }
    }

    //Afficher la liste
    public void afficher() {
        NoeudV2 courant = tete;
        while (courant != null) {
            System.out.print(courant.valeur + " ");
            courant = courant.suivant;
        }
        System.out.println();
    }

    // Mettre à jour la première occurrence d'un nœud par valeur
    public boolean mettreAjour(int ancienneValeur, int nouvelleValeur) {
        NoeudV2 courant = tete;
        while (courant != null) {
            if (courant.valeur == ancienneValeur) {
                courant.valeur = nouvelleValeur;
                return true;
            }
            courant = courant.suivant;
        }
        return false;
    }

    //  Supprimer la première occurrence d'un nœud par valeur
    public boolean supprimer(int valeur) {
        NoeudV2 courant = tete;

        while (courant != null) {
            if (courant.valeur == valeur) {
                // Si c'est la tête
                if (courant == tete) {
                    tete = courant.suivant;
                    if (tete != null) {
                        tete.precedent = null;
                    } else {
                        queue = null; // La liste devient vide
                    }
                }
                // Si c'est la queue
                else if (courant == queue) {
                    queue = courant.precedent;
                    queue.suivant = null;
                }
                // Si c'est un nœud au milieu
                else {
                    courant.precedent.suivant = courant.suivant;
                    courant.suivant.precedent = courant.precedent;
                }
                return true; // Suppression réussie
            }
            courant = courant.suivant;
        }
        return false; // Nœud non trouvé
    }

    //Question 7 
    public void inverserValeurs(int valeur1, int valeur2) {
        NoeudV2 courant = tete;

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
    
    //Question 8
    // Mesurer le temps de la méthode inverserValeurs
    public long mesurerTempsInversion(int valeur1, int valeur2) {
        long debut = System.nanoTime();
        inverserValeurs(valeur1, valeur2);
        long fin = System.nanoTime();
        long duree = fin - debut;
        System.out.println("Temps d'exécution : " + duree + " ns");
        return duree ;
    }
    
    //Question 9
    // Méthode pour compter le nombre de nœuds
    public int compterNoeuds() {
        if (tete == null) {
            return 0; 
        }

        int count = 1; 
        NoeudV2 courant = tete.suivant;

        while (courant != tete) { // traite le cas d'une liste circulaire
            count++;
            courant = courant.suivant;
            if (courant == null)break;
        }

        return count;
    }
    
    //Question 10 
    // Implémentation de la méthode equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // Les deux références pointent vers le même objet
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false; // Null ou types différents
        }

        ListeDoublementChainee autreListe = (ListeDoublementChainee) obj;
        
        NoeudV2 courant1 = this.tete;
        NoeudV2 courant2 = autreListe.tete;

        if (courant1 == null && courant2 == null) {
            return true; // Les deux listes sont vides
        }
        if (courant1 == null || courant2 == null) {
            return false; // Une liste est vide, l'autre ne l'est pas
        }

        while ((courant1 != null && courant2 != null)||(courant1 != this.tete && courant2 != autreListe.tete)) {
        	if (courant1.valeur != courant2.valeur) {
                return false; 
        	}
        	else if (courant1.suivant == null && courant2.suivant == null) {
        		break ;
        	}
        	  courant1 = courant1.suivant;
              courant2 = courant2.suivant;
        };

        return true; // Vérifie que les deux listes ont le même cycle
    }
    
    //Tests
    public static void main(String[] args) {
        ListeDoublementChainee liste = new ListeDoublementChainee();
        liste.insererAuDebut(3);
        liste.insererAuDebut(2);
        liste.insererAuDebut(1);
        liste.insererALaFin(4);
        liste.insererALaFin(5);
        liste.afficher(); 
        System.out.println('\n');
        liste.supprimer(3);
        liste.afficher(); 
        System.out.println('\n');
        liste.mettreAjour(4, 6);
        liste.afficher(); 
        System.out.println('\n');
        liste.inverserValeurs(1,5);
        liste.afficher();
        System.out.println('\n');
    	listechainee listeTemp = new listechainee();
    	listeTemp.ajouter(1);
    	listeTemp.ajouter(2);
        listeTemp.ajouter(3);
        listeTemp.ajouter(4);
        listeTemp.afficher();
        System.out.println('\n');
        long tempsSimple = listeTemp.mesurerTempsInversion(1, 4);
        long tempsDouble = liste.mesurerTempsInversion(1, 6);
        if(tempsSimple < tempsDouble) {
        	System.out.println("l'inversion de valeur dans une liste chaînée simple est plus rapide");
        }
        else if(tempsSimple > tempsDouble) {
        	System.out.println("l'inversion de valeur dans une liste chaînée double est plus rapide");
        }
        else {
        	System.out.println("les deux fonctions s'équivalent");
        }
        //donc en moyenne, la méhode d'inversion de deux valeurs avec une liste chaînée doubme est deux fois plus rapide qu'avec une liste chaînée simple
        System.out.println('\n');
        int cpt = liste.compterNoeuds();
        System.out.println("il y a "+cpt+" noeuds dans cette liste");
        ListeDoublementChainee liste1 = new ListeDoublementChainee();
        ListeDoublementChainee liste2 = new ListeDoublementChainee();
        ListeDoublementChainee liste3 = new ListeDoublementChainee();

        // Insertion d'éléments dans la première liste
        liste1.insererALaFin(1);
        liste1.insererALaFin(2);
        liste1.insererALaFin(3);

        // Insertion d'éléments dans la deuxième liste
        liste2.insererALaFin(1);
        liste2.insererALaFin(2);
        liste2.insererALaFin(3);

        // Insertion d'éléments dans la troisième liste (liste différente)
        liste3.insererALaFin(1);
        liste3.insererALaFin(2);
        liste3.insererALaFin(4);

        // Affichage des listes
        System.out.print("\nListe 1 : ");
        liste1.afficher();
        System.out.print("\nListe 2 : ");
        liste2.afficher();
        System.out.print("\nListe 3 : ");
        liste3.afficher();

        // Comparaison des listes
        System.out.println('\n');
        System.out.println("Liste 1 égale à Liste 2 : " + liste1.equals(liste2));
        System.out.println('\n');
        System.out.println("Liste 1 égale à Liste 3 : " + liste1.equals(liste3));
    }
}
