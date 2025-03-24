/*
 * Classe que defineix una universitat, que es defineix pel seu nom,
 * la seva ubicació i un array de campus.
 */

package universitat;

/**
 *
 * @author fgarin
 */
public class Universitat implements UnitatUniversitat {

    private String nomUniversitat;
    private String ubicacioSeu;
    private Campus[] campus = new Campus[5];
    private int pCampus = 0; // Primera posició buida de l'array de campus

    public Universitat(String nomUniversitat, String ubicacioSeu) {
        this.nomUniversitat = nomUniversitat;
        this.ubicacioSeu = ubicacioSeu;
    }

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

    public static Universitat addUniversitat() {
        String nomUniversitat, ubicacioSeu;

        System.out.println("\nNom de la universitat:");
        nomUniversitat = UnitatUniversitat.DADES.nextLine();
        System.out.println("\nUbicació de la seu:");
        ubicacioSeu = UnitatUniversitat.DADES.nextLine();

        return new Universitat(nomUniversitat, ubicacioSeu);
    }

    @Override
    public void updateUnitatUniversitat() {
        System.out.println("\nNom de la universitat: " + nomUniversitat);
        System.out.println("\nEntra el nou nom:");
        nomUniversitat = UnitatUniversitat.DADES.nextLine();
        System.out.println("\nUbicació de la seu: " + ubicacioSeu);
        System.out.println("\nEntra la nova ubicació:");
        ubicacioSeu = UnitatUniversitat.DADES.nextLine();
    }

    public double costMantenimentTotal() {
        double costTotal = 0;

        for (int i = 0; i < pCampus; i++) {
            costTotal += campus[i].costManteniment();
        }

        return costTotal;
    }

    @Override
    public void showUnitatUniversitat() {
        System.out.println("\nLes dades de la universitat " + nomUniversitat + " són: ");
        System.out.println("\nUbicació: " + ubicacioSeu);
        System.out.println("\nCost de manteniment total: " + costMantenimentTotal() + " EUR");
    }

    public void addCampus() {
        Campus nouCampus = Campus.addCampus();

        if (selectCampus(nouCampus.getNomCampus()) == -1) {
            campus[pCampus] = nouCampus;
            pCampus++;
        } else {
            System.out.println("\nEl campus ja existeix");
        }
    }

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
        // Comprobem si existeix el campus seleccionat
        int indexCampus = selectCampus(null);
        // Si el campus indicat existeix, llavors accedim a ell i seleccionem l'aula
        // pasant el tipus i el codi
        if (indexCampus != -1) {
            int indexAula = campus[indexCampus].selectAula(1, null);
            // Si troba l'aula l'actualitza
            if (indexAula != -1) {
                campus[indexCampus].getAula()[indexAula].updateUnitatUniversitat();
            } else {
                // Si no existeix l'aula retorna per terminal que no existeix
                System.out.println("\nL'aula estàndard no existeix");
            }
        } else {
            // Si no existeix el campus retorna per terminal que no existeix
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
            int indexAulaInformatica = campus[indexCampus].selectAula(2, null);

            if (indexAulaInformatica != -1) {
                campus[indexCampus].getAula()[indexAulaInformatica].updateUnitatUniversitat();
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
            int indexLaboratori = campus[indexCampus].selectAula(3, null);

            if (indexLaboratori != -1) {
                campus[indexCampus].getAula()[indexLaboratori].updateUnitatUniversitat();
            } else {
                System.out.println("\nEl laboratori no existeix");
            }
        } else {
            System.out.println("\nEl campus no existeix");
        }
    }
}
