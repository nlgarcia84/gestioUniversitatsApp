package universitat;

public class AulaInformatica extends Aula {

    // Hereta atributs d'Aula i afegim la propia areaEnMetresQuadrats
    private double areaEnMetresQuadrats;

    // Mètode constructor
    public AulaInformatica(String codi, int numeroAula, double costPerDia, double areaEnMetresQuadrats) {
        super(codi, numeroAula, costPerDia);
        this.areaEnMetresQuadrats = areaEnMetresQuadrats;
    }

    // Mètodes accessors exclusius de AulaInformatica, la resta els hereta de Aula
    public double getAreaEnMetresQuadrats() {
        return this.areaEnMetresQuadrats;
    }

    public void setAreaEnMetresQuadrats(double areaEnMetresQuadrats) {
        this.areaEnMetresQuadrats = areaEnMetresQuadrats;
    }

    // Mètodes de classe
    public static AulaInformatica addAulaInformatica() {
        System.out.println("\nCodi de l'aula de informatica: ");
        String codi = DADES.nextLine();

        System.out.println("\nNúmero Aula de informatica: ");
        int numeroAula = Integer.parseInt(DADES.nextLine());

        System.out.println("\nCost per dia de l'aula de informatica: ");
        double costPerDia = Double.parseDouble(DADES.nextLine());

        System.out.println("\nÀrea en metres quadrats de l'aula de informatica: ");
        double areaEnMetresQuadrats = Double.parseDouble(DADES.nextLine());

        return new AulaInformatica(codi, numeroAula, costPerDia, areaEnMetresQuadrats);
    }

    @Override
    // Implementació del mètode updateUnitatUniversitat de la Interficie
    // UnitatUniversitat
    public void updateUnitatUniversitat() {
        super.updateUnitatUniversitat();
        System.out.println("Metres quadrats actuals: " + getAreaEnMetresQuadrats());
        System.out.println("Introdueix els nous metres quadrats");
        areaEnMetresQuadrats = DADES.nextDouble();
        setAreaEnMetresQuadrats(areaEnMetresQuadrats);
    }

    @Override
    // Implementació del mètode showUnitatUniversitat de la Interficie
    // UnitatUniversitat
    public void showUnitatUniversitat() {
        super.showUnitatUniversitat();
    }

    @Override
    // Implementació del mètode declarat a la clase pare Aula.java
    public double costManteniment() {
        return (this.getCostPerDia() * 0.3) + (this.getAreaEnMetresQuadrats() * 0.05);
    }
}
