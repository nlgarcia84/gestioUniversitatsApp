/*
 * Classe que defineix una aula estàndard d'una universitat. 
 * Es defineixen pel seu codi, número d'aula i el seu cost per dia.
 */
package universitat;

/**
 *
 * @author fgarin
 */
public class AulaEstandard extends Aula {

    /*
     * TODO CONSTRUCTOR
     *
     * Nom del mètode: AulaEstandard
     * 
     * Paràmetres: valors per tots els atributs de la classe.
     *
     * Accions:
     * - Assignar als atributs corresponents els valors passats com a paràmetres.
     */
    public AulaEstandard(String codi, int numeroAula, double costPerDia) {
        super(codi, numeroAula, costPerDia);
    }

    /*
     * TODO Heu d'implementar tots els mètodes accessors possibles.
     */

    /*
     * TODO
     *
     * Nom del mètode: addAulaEstandard
     *
     * Accions:
     * - Demanar a l'usuari les dades per consola per crear una nova
     * AulaEstandard. Les dades a demanar són les que necessita
     * el constructor.
     *
     * Retorn: Objecte AulaEstandard creat.
     */
    public static AulaEstandard addAulaEstandard() {

        System.out.println("\nCodi de l'aula: ");
        String codi = DADES.nextLine();

        System.out.println("\nNúmero de l'aula: ");
        int numeroAula = Integer.parseInt(DADES.nextLine());

        System.out.println("\nCost per dia de l'aula: ");
        double costPerDia = Double.parseDouble(DADES.nextLine());

        return new AulaEstandard(codi, numeroAula, costPerDia);
    }

    /*
     * TODO
     *
     * Nom del mètode: updateAulaEstandard
     * 
     * Paràmetres: cap
     * 
     * Accions:
     * - Demanar a l'usuari que introdueixi les noves dades de l'aula actual i
     * modificar els atributs corresponents d'aquesta aula.
     * - Li heu de mostrar a l'usuari els valors dels atributs abans de
     * modificar-los.
     * 
     * Retorn: cap
     */
    @Override
    public void updateUnitatUniversitat() {
        super.updateUnitatUniversitat();
    }

    /*
     * TODO
     *
     * Nom del mètode: showAulaEstandard
     * 
     * Paràmetres: cap
     * 
     * Accions:
     * - Mètode per mostrar les dades de l'aula actual i el seu cost de manteniment.
     * 
     * Retorn: cap
     */
    public void showUnitatUniversitat() {
        super.showUnitatUniversitat();
    }

    /*
     * TODO
     *
     * Nom del mètode: costManteniment
     * 
     * Paràmetres: cap
     * 
     * Accions:
     * - Mètode que calcula el cost de manteniment del AulaEstandard actual
     * a partir del seu cost per dia.
     * El cost de manteniment per dia és un 20% del cost per dia de l'aula
     * 
     * Retorn: cost de manteniment de l'aula (double).
     */
    @Override
    public double costManteniment() {
        return (this.getCostPerDia() * 0.2);
    }
}
