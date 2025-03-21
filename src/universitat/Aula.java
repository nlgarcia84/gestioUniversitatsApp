package universitat;

/**
 * @author Norman Leyva
 */
public abstract class Aula implements UnitatUniversitat {

  private String codi;
  private int numeroAula;
  private double costPerDia;

  // Métode constructor
  public Aula(String codi, int numeroAula, double costPerDia) {
    this.codi = codi;
    this.numeroAula = numeroAula;
    this.costPerDia = costPerDia;
  }

  // Métodes accesssors
  public String getCodi() {
    return codi;
  }

  public void setCodi(String codi) {
    this.codi = codi;
  }

  public int getNumeroAula() {
    return numeroAula;
  }

  public void setNumeroAula(int numeroAula) {
    this.numeroAula = numeroAula;
  }

  public double getCostPerDia() {
    return costPerDia;
  }

  public void setCostPerDia(double costPerDia) {
    this.costPerDia = costPerDia;
  }

  @Override
  public void updateUnitatUniversitat() {
    System.out.println("\nCodi de l'aula: " + this.getCodi());
    System.out.println("\nEntra el nou valor del codi d'aula: ");
    codi = DADES.nextLine();

    System.out.println("\nNúmero de l'aula: " + this.getNumeroAula());
    System.out.println("\nEntra el nou valor del número d'aula: ");
    numeroAula = Integer.parseInt(DADES.nextLine());

    System.out.println("\nCost per dia de l'aula: " + this.getCostPerDia());
    System.out.println("\nEntra nou valor del cost per dia de l'aula: ");
    costPerDia = Double.parseDouble(DADES.nextLine());
  };

  @Override
  public void showUnitatUniversitat() {
    System.out.println("\nLes dades de l'aula estàndard amb codi " + this.getCodi() + " són: ");
    System.out.println("\nNúmero de l'aula: " + this.getNumeroAula());
    System.out.println("\nCost per dia de l'aula: " + this.getCostPerDia());
  };

}