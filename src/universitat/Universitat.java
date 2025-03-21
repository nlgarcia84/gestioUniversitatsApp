/*
 * Classe que defineix una universitat, que es defineix pel seu nom,
 * la seva ubicació i un array de campus.
 */

package universitat;

import java.util.Scanner;

/**
 *
 * @author fgarin
 */
public class Universitat {
    private final static Scanner DADES = new Scanner(System.in);

    private String nomUniversitat;
    private String ubicacioSeu;
    private Campus[] campus = new Campus[5];
    private int pCampus = 0; //Primera posició buida de l'array de campus

    /*
     * TODO CONSTRUCTOR
     *
     * Nom del mètode: Universitat
     *
     * Paràmetres: valors per tots els atributs de la classe menys els arrays.
     *
     * Accions:
     * - Assignar als atributs corresponents els valors passats com a paràmetres.
     */
    
    public Universitat(String nomUniversitat, String ubicacioSeu) {
        this.nomUniversitat = nomUniversitat;
        this.ubicacioSeu = ubicacioSeu;
    }

    /**
     * TODO Heu d'implementar tots els mètodes accessors possibles.
     */
    
    public String getNomUniversitat() {
        return nomUniversitat;
    }

    public void setNomUniversitat(String nomUniversitat) {
        this.nomUniversitat = nomUniversitat;
    }

    public String getUbicacioSeu() {
        return ubicacioSeu;
    }

    public void setUbicacioSeu(String ubicacioSeu) {
        this.ubicacioSeu = ubicacioSeu;
    }

    public Campus[] getCampus() {
        return this.campus;
    }

    public void setCampus(Campus[] campus) {
        this.campus = campus;
    }

    public int getpCampus() {
        return pCampus;
    }

    public void setpCampus(int pCampus) {
        this.pCampus = pCampus;
    }
    

    /**
     * TODO
     *
     * Nom del mètode: addUniversitat
     *
     * Paràmetres: cap
     * 
     * Accions:
     * - Demanar a l'usuari les dades per consola per crear una nova universitat.
     *   Les dades a demanar són les que necessita el constructor.
     * 
     * Retorn: Objecte Universitat creat.
     */
    public static Universitat addUniversitat() {
        String nomUniversitat, ubicacioSeu;

        System.out.println("\nNom de la universitat:");
        nomUniversitat = DADES.nextLine();
        System.out.println("\nUbicació de la seu:");
        ubicacioSeu = DADES.nextLine();

        return new Universitat(nomUniversitat, ubicacioSeu);
    }

    /**
     * TODO
     *
     * Nom del mètode: updateUniversitat
     * 
     * Paràmetres: cap
     * 
     * Accions:
     * - Demanar a l'usuari que introdueixi les noves dades de la universitat i
     *   modificar els atributs corresponents d'aquesta universitat. 
     *   Els únics atributs que modificarem
     *   són els que hem inicialitzat en el constructor amb els paràmetres passats.
     * - Li heu de mostrar a l'usuari els valors dels atributs abans de modificar-los.
     * 
     * Retorn: cap
     */
    public void updateUniversitat() {
        System.out.println("\nNom de la universitat: " + nomUniversitat);
        System.out.println("\nEntra el nou nom:");
        nomUniversitat = DADES.nextLine();
        System.out.println("\nUbicació de la seu: " + ubicacioSeu);
        System.out.println("\nEntra la nova ubicació:");
        ubicacioSeu = DADES.nextLine();
    }

    /**
     * TODO
     *
     * Nom del mètode: costManteniment
     *  
     * Paràmetres: cap
     * 
     * Accions:
     * - Calcular cost de manteniment dels campus de la universitat a partir dels costos de manteniment
     *   de tots els campus de la universitat.
     * 
     * Retorn: Cost de manteniment total de la universitat (double).
     */
    public double costManteniment() {
        double costTotal = 0;

        for (int i = 0; i < pCampus; i++) {
            costTotal += campus[i].costManteniment();
        }

        return costTotal;
    }

    /**
     * TODO
     *
     * Nom del mètode: showUniversitat
     * 
     * Paràmetres: cap
     * 
     * Accions:
     * - Mètode per imprimir les dades de la universitat actual i el cost del seu manteniment.
     * 
     * Retorn: cap
     */
    public void showUniversitat() {
        System.out.println("\nLes dades de la universitat " + nomUniversitat + " són: ");
        System.out.println("\nUbicació: " + ubicacioSeu);
        System.out.println("\nCost de manteniment total: " + costManteniment() + " EUR");
    }

    /**
     * Campus
     * 
     * TODO
     *
     * Nom del mètode: addCampus
     *  
     * Paràmetres: cap
     * 
     * Accions:
     * - Afegeix un nou campus a l'array de campus de la universitat
     *   si aquest no existeix. 
     *   Per afegir-lo heu de fer servir el mètode de la classe Campus escaient
     *   i per comprovar la seva existència el mètode d'aquesta classe que ens
     *   ajuda en aquesta tasca.
     * - Actualitza la posició de l'array de campus si s'afegeix el campus. 
     * - Mostra el missatge "El campus ja existeix" si no s'ha afegit.
     * 
     * Retorn: cap
     */
    public void addCampus() {
        Campus nouCampus = Campus.addCampus();

        if (selectCampus(nouCampus.getNomCampus()) == -1) {
            campus[pCampus] = nouCampus;
            pCampus++;
        } else {
            System.out.println("\nEl campus ja existeix");
        }
    }

    /**
     *
     * Nom del mètode: selectCampus
     *
     * Paràmetres: cap
     * 
     * Accions:
     * - Seleciona un campus de l'array de campus de la universitat
     *   a partir del seu nom.
     * 
     * Retorn: La posició del campus seleccionat o -1 si no existeix.
     */
    public int selectCampus(String nom) {
        if (nom == null) {
            System.out.println("\nNom del campus:");
            nom = DADES.nextLine();
        }

        for (int i = 0; i < pCampus; i++) {
            if (campus[i].getNomCampus().equals(nom)) {
                return i;
            }
        }

        return -1;
    }

    public void addAulaEstandardCampus() {

        int index = selectCampus(null);

        if (index != -1) {
            campus[index].addAulaEstandard();
        } else {
            System.out.println("\nEl campus no existeix");
        }
    }

    public void updateAulaEstandardCampus() {
        int indexCampus = selectCampus(null);

        if (indexCampus != -1) {
            int indexAulaEstandard = campus[indexCampus].selectAulaEstandard(null);

            if (indexAulaEstandard != -1) {
                campus[indexCampus].getAulesEstandard()[indexAulaEstandard].updateAulaEstandard();
            } else {
                System.out.println("\nL'aula estàndard no existeix");
            }
        } else {
            System.out.println("\nEl campus no existeix");
        }
    }

    public void addAulaInformaticaCampus() {
        int index = selectCampus(null);

        if (index != -1) {
            campus[index].addAulaInformatica();
        } else {
            System.out.println("\nEl campus no existeix");
        }
    }

    public void updateAulaInformaticaCampus() {
        int indexCampus = selectCampus(null);

        if (indexCampus != -1) {
            int indexAulaInformatica = campus[indexCampus].selectAulaInformatica(null);

            if (indexAulaInformatica != -1) {
                campus[indexCampus].getAulesInformatica()[indexAulaInformatica].updateAulaInformatica();
            } else {
                System.out.println("\nL'aula d'informàtica no existeix");
            }
        } else {
            System.out.println("\nEl campus no existeix");
        }
    }

    public void addLaboratoriCampus() {
        int index = selectCampus(null);

        if (index != -1) {
            campus[index].addLaboratori();
        } else {
            System.out.println("\nEl campus no existeix");
        }
    }

    public void updateLaboratoriCampus() {
        int indexCampus = selectCampus(null);

        if (indexCampus != -1) {
            int indexLaboratori = campus[indexCampus].selectLaboratori(null);

            if (indexLaboratori != -1) {
                campus[indexCampus].getLaboratoris()[indexLaboratori].updateLaboratori();
            } else {
                System.out.println("\nEl laboratori no existeix");
            }
        } else {
            System.out.println("\nEl campus no existeix");
        }
    }
}
