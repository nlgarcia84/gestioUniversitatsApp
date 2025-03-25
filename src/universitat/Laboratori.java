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

    // Mètode constructor
    public Laboratori(String codi, int numeroLaboratori, double costPerDia, int capacitat) {
        super(codi, numeroLaboratori, costPerDia);
        this.capacitat = capacitat;
    }

    // Mètodes accessors exclusius de AulaInformatica, la resta els hereta de Aula
    public int getCapacitat() {
        return this.capacitat;
    }

    public void setCapacitat(int capacitat) {
        this.capacitat = capacitat;
    }

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

    @Override
    // Implementació del mètode updateUnitatUniversitat de la Interficie
    // UnitatUniversitat
    public void updateUnitatUniversitat() {
        super.updateUnitatUniversitat();
        System.out.println("\nCost de manteniment: " + this.costManteniment() + " EUR");
        System.out.println("\nCapacitat del Laboratori: " + this.getCapacitat());
        System.out.println("Entra el nou valor de la capacitat:");
        capacitat = Integer.parseInt(DADES.nextLine());
    }

    @Override
    // Implementació del mètode showUnitatUniversitat de la Interficie
    // UnitatUniversitat
    public void showUnitatUniversitat() {
        super.showUnitatUniversitat();
        System.out.println("\nCapacitat: " + this.getCapacitat());
    }

    @Override
    // Implementació del mètode declarat a la clase pare Aula.java
    public double costManteniment() {
        return (double) (this.getCostPerDia() * 0.35) + (this.getCapacitat() * 0.10);
    }
}
