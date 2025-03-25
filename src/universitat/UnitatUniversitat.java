package universitat;

import java.util.Scanner;

public interface UnitatUniversitat {
  // Aquest atribut substituirà a l’atribut constant DADES de les diferents
  // classes que han d’implementar aquesta interfície
  public final static Scanner DADES = new Scanner(System.in);

  // Comuna a totes les clases
  public abstract void updateUnitatUniversitat();

  public abstract void showUnitatUniversitat();
}