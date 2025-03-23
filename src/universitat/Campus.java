/**
 * Classe que defineix un campus. Es defineix pel seu nom,
 * la seva ubicació, un array d'aules estàndard,
 * un array d'aules d'informatica i un array de laboratoris.
 */

package universitat;

/**
 *
 * @author fgarin
 */
public class Campus implements UnitatUniversitat {

    private String nomCampus;
    private String ubicacio;

    private Aula[] aula = new Aula[300];
    private int pAula = 0;

    // Mètode constructor de la clase campus
    public Campus(String nomCampus, String ubicacio) {
        this.nomCampus = nomCampus;
        this.ubicacio = ubicacio;
    }

    // Mètodes accessors
    public String getNomCampus() {
        return nomCampus;
    }

    public void setNomCampus(String nomCampus) {
        this.nomCampus = nomCampus;
    }

    public String getUbicacio() {
        return ubicacio;
    }

    public void setUbicacio(String ubicacio) {
        this.ubicacio = ubicacio;
    }

    public Aula[] getAula() {
        return aula;
    }

    public void setAula(Aula[] aula) {
        this.aula = aula;
    }

    public int getPaula() {
        return pAula;
    }

    // Mètode per afegir un nou campus
    public static Campus addCampus() {
        String nom, ubicacio;

        System.out.println("\nNom del campus: ");
        nom = UnitatUniversitat.DADES.nextLine();
        System.out.println("\nUbicació del campus: ");
        ubicacio = UnitatUniversitat.DADES.nextLine();

        return new Campus(nom, ubicacio);
    }

    // Mètode per actualitzar un campus existent
    @Override
    public void updateUnitatUniversitat() {
        System.out.println("\nNom del campus: " + this.getNomCampus());
        System.out.println("\nEntra el nou nom del campus:");
        this.nomCampus = UnitatUniversitat.DADES.nextLine();
        System.out.println("\nUbicació del campus : " + this.getUbicacio());
        System.out.println("\nEntra la nova ubicació del campus:");
        this.ubicacio = UnitatUniversitat.DADES.nextLine();
    }

    // Mètode calcula el cost de manteniment total del campus entre totes les aules
    public double costManteniment() {

        double costTotal = 0;

        for (int i = 0; i < pAula; i++) {
            costTotal += aula[i].costManteniment();
        }

        return costTotal;

    }

    // Mètode mostra tota la info del campus
    @Override
    public void showUnitatUniversitat() {
        System.out.println("\nLes dades del campus " + this.nomCampus + " són: ");
        System.out.println("\nUbicació: " + this.getUbicacio());
        System.out.println("\nCost de manteniment: " + this.costManteniment() + " EUR");
    }

    // Mètode afegeix una aula estandard a l'array aula
    public void addAulaEstandard() {
        AulaEstandard nouAulaEstandard = AulaEstandard.addAulaEstandard();

        if (selectAula(1, nouAulaEstandard.getCodi()) == -1) {
            aula[pAula] = nouAulaEstandard;
            pAula++;
        } else {
            System.out.println("\nL'aula estàndard ja existeix");
        }
    }

    // Mètode afegeix una aula d'informàtica a l'array aula
    public void addAulaInformatica() {
        AulaInformatica novaAulaInformatica = AulaInformatica.addAulaInformatica();

        if (selectAula(2, novaAulaInformatica.getCodi()) == -1) {
            aula[pAula] = novaAulaInformatica;
            pAula++;
        } else {
            System.out.println("\nL'aula d'informatica ja existeix");
        }
    }

    // Mètode afegeix un Laboratori a l'array aula
    public void addLaboratori() {
        Laboratori novaLaboratori = Laboratori.addLaboratori();

        if (selectAula(3, novaLaboratori.getCodi()) == -1) {
            aula[pAula] = novaLaboratori;
            pAula++;
        } else {
            System.out.println("\nEl laboratori ja existeix");
        }
    }

    // Mètode per seleccionar l'aula
    public int selectAula(int tipusAula, String codi) {

        System.out.println("\n¿Quin tipus d'aula vol seleccionar?");
        System.out.println("\n1=Aula Estandard");
        System.out.println("\n2=Aula Informàtica");
        System.out.println("\n3=Laboratori");
        tipusAula = UnitatUniversitat.DADES.nextInt();
        UnitatUniversitat.DADES.nextLine(); // netegem buffer

        if (codi == null) {
            System.out.println("\nIntrodueixi el codi de l'aula a seleccionar:");
            codi = UnitatUniversitat.DADES.nextLine();
        }

        for (int i = 0; i < pAula; i++) {
            switch (tipusAula) {
                case 1:
                    if (aula[i].getCodi().equals(codi) && aula[i] instanceof AulaEstandard) {
                        return i;
                    }
                    break;
                case 2:
                    if (aula[i].getCodi().equals(codi) && aula[i] instanceof AulaInformatica) {
                        return i;
                    }
                    break;
                case 3:
                    if (aula[i].getCodi().equals(codi) && aula[i] instanceof Laboratori) {
                        return i;
                    }
                default:
                    break;
            }
        }
        return -1;
    }
}