/*
 * Classe que defineix una aula d'informàtica d'una universitat. 
 * Es defineix pel seu codi, número d'aula, el seu cost per dia i el tamany de l'aula en metres quadrats.
 */
package universitat;

import java.util.Scanner;

/**
 *
 * @author fgarin
 */
public class AulaInformatica extends Aula {
    private final static Scanner DADES = new Scanner(System.in);

    // Atributs propis
    private double areaEnMetresQuadrats;

    /*
     * TODO CONSTRUCTOR
     *
     * Nom del mètode: AulaInformatica
     *
     * Paràmetres: valors per tots els atributs de la classe.
     *
     * Accions:
     * - Assignar als atributs corresponents els valors passats com a paràmetres.
     */
    public AulaInformatica(String codi, int numeroAula, double costPerDia, double areaEnMetresQuadrats) {
        super(codi, numeroAula, costPerDia);
        this.areaEnMetresQuadrats = areaEnMetresQuadrats;
    }

    /*
     * TODO Heu d'implementar tots els mètodes accessors possibles.
     */

    public double getAreaEnMetresQuadrats() {
        return this.areaEnMetresQuadrats;
    }

    public void setAreaEnMetresQuadrats(double areaEnMetresQuadrats) {
        this.areaEnMetresQuadrats = areaEnMetresQuadrats;
    }

    /*
     * TODO
     *
     * Nom del mètode: addAulaInformatica
     *
     * Paràmetres: cap
     * 
     * Accions:
     * - Demanar a l'usuari les dades per consola per crear una nova aula de
     * informatica. Les dades a
     * demanar són les que necessita el constructor.
     *
     * Retorn: Objecte AulaInformatica creat.
     */
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

    /*
     * TODO
     *
     * Nom del mètode: updateAulaInformatica
     *
     * Paràmetres: cap
     *
     * Accions:
     * - Demanar a l'usuari que introdueixi les noves dades de l'aula de informatica
     * i modificar els
     * atributs corresponents d'aquesta aula.
     * - Li heu de mostrar a l'usuari els valors dels atributs abans de
     * modificar-los.
     *
     * Retorn: cap
     */
    public void updateAulaInformatica() {
        super.updateUnitatUniversitat();
        System.out.println("\nÀrea en metres quadrats de l'aula de informatica: " + this.getAreaEnMetresQuadrats());
        System.out.println("Entra el nou valor de l'àrea en metres quadrats de l'aula de informatica:");
        areaEnMetresQuadrats = Double.parseDouble(DADES.nextLine());
    }

    /*
     * TODO
     *
     * Nom del mètode: showAulaInformatica
     * 
     * Accions:
     * - Mètode per imprimir les dades de l'aula de informatica i el seu cost de
     * manteniment.
     *
     * Retorn: cap
     */
    public void showAulaInformatica() {
        super.showUnitatUniversitat();
        System.out.println("\nCost de manteniment: " + this.costManteniment() + " EUR");
    }

    /*
     * TODO
     *
     * Nom del mètode: costManteniment
     *
     * Paràmetres: cap
     *
     * Accions:
     * - Mètode que retornarà el cost de manteniment de l'aula.
     * - El cost de manteniment per dia és un 30% del cost per dia de l'aula + un 5%
     * de la seva àrea
     *
     * Retorn: cost de manteniment de l'aula (double).
     */
    public double costManteniment() {
        return (this.getCostPerDia() * 0.3) + (this.getAreaEnMetresQuadrats() * 0.05);
    }
}
