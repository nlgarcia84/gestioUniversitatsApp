package universitat;

import java.util.Scanner;

public interface UnitatUniversitat {
  public final static Scanner DADES = new Scanner(System.in);

  // Comuna a totes les clases
  public abstract void updateUnitatUniversitat();

  public abstract void showUnitatUniversitat();
}