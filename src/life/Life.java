package life;

import life.domain.Gen;
import life.domain.LifeHistory;
import life.ui.LifeController;
import life.ui.LifeView;
import java.util.Scanner;
import java.io.*;

/**
 * Responsabilidades:
 *
 * - Leer el fichero de configuración cuyo nombre ha sido pasado por
 *   parámetro y crear una generación con dicho patrón.
 * - Crear una triada MVC y ejecutar tick sobre el controlador cada
 *   250 segundos.
 */
public class Life {
  /**
   * Función que dado un nombre de fichero lo abre y lee
   * una generación de acuerdo a los requisitos.
   */
  public static final Gen readConfig(String filename) {
	try {
		java.io.File archivo = new java.io.File(filename);
		java.util.Scanner scan = new java.util.Scanner(archivo);
		Gen genRead = new Gen(scan.nextInt());
		while (scan.hasNextInt()) {
			int x = scan.nextInt();
			int y = scan.nextInt();
			genRead.set(x,y,true);
		}
		return genRead;
	}
	catch(Exception e) {
		return null;
	}	
  }

  public static final void main(String[] args) {
    if (args.length != 1) {
      System.err.println("Error de uso: el programa Life necesita un fichero como argumento");
      return;
    }

    Gen pattern = readConfig(args[0]);

    if (pattern == null)
      return;

    // MVC
    LifeHistory m = new LifeHistory(pattern);
    LifeView v = new LifeView(m);
    LifeController c = new LifeController(m , v);

    // Main loop
    while (true) {
      c.tick();
      try {
        Thread.sleep(250);
      }
      catch (InterruptedException e) {}
    }
  }
}
