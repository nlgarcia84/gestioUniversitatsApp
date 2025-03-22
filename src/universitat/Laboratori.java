/*
 * Classe que defineix un Laboratori d'una universitat.
 * Es defineix pel seu codi, numeroLaboratori, cost per dia i la seva capacitat en nombre de persones.
 */
package universitat;

/**
 *
 * @author fgarin
 */
public class Laboratori extends Aula {

    private int capacitat;

    /*
     * TODO CONSTRUCTOR
     *
     * Nom del mètode: Laboratori
     *
     * Paràmetres: valors per tots els atributs de la classe.
     *
     * Accions:
     * - Assignar als atributs corresponents els valors passats com a paràmetres.
     */
    public Laboratori(String codi, int numeroLaboratori, double costPerDia, int capacitat) {
        super(codi, numeroLaboratori, costPerDia);
        this.capacitat = capacitat;
    }

    /*
     * TODO Heu d'implementar tots els mètodes accessors possibles.
     */

    public int getCapacitat() {
        return this.capacitat;
    }

    public void setCapacitat(int capacitat) {
        this.capacitat = capacitat;
    }

    /*
     * TODO
     *
     * Nom del mètode: addLaboratori
     *
     * Paràmetres: cap
     *
     * Accions:
     * - Demanar les dades per consola per crear un Laboratori.
     * Les dades a demanar són les que necessita
     * el constructor.
     * 
     * Retorn: Objecte Laboratori creat.
     */
    public static Laboratori addLaboratori() {
        System.out.println("\nCodi del Laboratori: ");
        String codi = DADES.nextLine();

        System.out.println("\nNúmero del Laboratori: ");
        int numeroLaboratori = Integer.parseInt(DADES.nextLine());

        System.out.println("\nCost per dia del Laboratori: ");
        double costPerDia = Integer.parseInt(DADES.nextLine());

        System.out.println("\nCapacitat del Laboratori: ");
        int capacitat = Integer.parseInt(DADES.nextLine());

        return new Laboratori(codi, numeroLaboratori, costPerDia, capacitat);
    }

    /*
     * TODO
     *
     * Nom del mètode: updateLaboratori
     *
     * Paràmetres: cap
     *
     * Accions:
     * - Demanar a l'usuari que introdueixi les noves dades del Laboratori i
     * modificar els
     * atributs corresponents d'aquest Laboratori.
     * - Li heu de mostrar a l'usuari els valors dels atributs abans de
     * modificar-los.
     *
     * Retorn: cap
     */
    public void updateLaboratori() {
        super.updateUnitatUniversitat();
        System.out.println("\nCost de manteniment: " + this.costManteniment() + " EUR");
        System.out.println("\nCapacitat del Laboratori: " + this.getCapacitat());
        System.out.println("Entra el nou valor de la capacitat:");
        capacitat = Integer.parseInt(DADES.nextLine());
    }

    /*
     * TODO
     *
     * Nom del mètode: showLaboratori
     *
     * Paràmetres: cap
     *
     * Accions:
     * - Mètode per mostrar les dades del Laboratori actual i el cost del seu
     * manteniment.
     * 
     * Retorn: cap
     */
    public void showLaboratori() {
        super.showUnitatUniversitat();
        System.out.println("\nCapacitat: " + this.getCapacitat());
    }

    /*
     * TODO
     *
     * Nom del mètode: costManteniment
     *
     * Paràmetres: cap
     *
     * Accions:
     * - Mètode que retornarà el cost del manteniment del Laboratori actual.
     * - El cost del manteniment per dia és un 35% del cost per dia del laboratori +
     * un 10% de la seva capacitat
     *
     * Retorn: Cost de manteniment de la motocicleta actual (double).
     */
    @Override
    public double costManteniment() {
        return (double) (this.getCostPerDia() * 0.35) + (this.getCapacitat() * 0.10);
    }
}
