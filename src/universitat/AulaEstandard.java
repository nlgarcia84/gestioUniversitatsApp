package universitat;

public class AulaEstandard extends Aula {
    // Les propietats les hereta totes de la clase pare

    // Mètode constructor
    public AulaEstandard(String codi, int numeroAula, double costPerDia) {
        super(codi, numeroAula, costPerDia);
    }

    public static AulaEstandard addAulaEstandard() {

        System.out.println("\nCodi de l'aula: ");
        String codi = DADES.nextLine();

        System.out.println("\nNúmero de l'aula: ");
        int numeroAula = Integer.parseInt(DADES.nextLine());

        System.out.println("\nCost per dia de l'aula: ");
        double costPerDia = Double.parseDouble(DADES.nextLine());

        return new AulaEstandard(codi, numeroAula, costPerDia);
    }

    @Override
    // Implementació del mètode updateUnitatUniversitat de la Interficie
    // UnitatUniversitat
    public void updateUnitatUniversitat() {
        super.updateUnitatUniversitat();
    }

    public void showUnitatUniversitat() {
        super.showUnitatUniversitat();
    }

    @Override

    // Implementació del mètode declarat a la clase pare Aula.java
    public double costManteniment() {
        return (this.getCostPerDia() * 0.2);
    }
}
