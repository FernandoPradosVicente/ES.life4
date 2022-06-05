package life.domain;

/**
 * Responsabilidades:
 * - Representar una generación del juego de la vida en un
 *   mundo cuadrado con paredes.
 * - Saber qué celdas están vivas o muertas.
 * - Establecer si una celda está viva o mierta.
 * - Crear la siguiente generación.
 */
public class Gen {
  private boolean[][] cuadro;
	
	public Gen(int lado) {
		cuadro = new boolean[lado][lado];
	}
	
	public int size() {
		return cuadro.length;
	}
	
	public void set(int x, int y, boolean live) {
		cuadro[x][y] = live;
	}
	
	public boolean live(int x, int y) {
		return cuadro[x][y];
	}
	
	public int contarVecinas(int x, int y) {
		int count = 0;
		int i = x-1;												
		while(i <= x+1 && (count < 5)){
			if ((0 <= i) && (i < cuadro.length)){
				for (int j = y-1; j <= y+1; j++){							
					if ((0 <= j) && (j < cuadro.length)){
						if(!((i == x)&&(j == y)) && (cuadro[i][j])){
							count++;
						}
					}	
				}
			}
			i++;
		}
		return count;
	}
	
	public Gen next() {
		Gen nextGen = new Gen(size());
		for (int x = 0; x < cuadro.length; x++){
			for (int y = 0; y < cuadro.length; y++){
				int count = contarVecinas(x,y);
				boolean alive = false;
				if ((cuadro[x][y] && ((count == 2) || (count == 3))) || (!(cuadro[x][y]) && (count == 3))) {
					alive = true;
					}
				nextGen.set(x,y,alive);
			}
		}
		return nextGen;
	}
	
	public boolean equals(Object o) {	
		try {
			Gen oGen = (Gen) o;
			int largo = this.size();
			if (largo == oGen.size()){
				boolean iguales = true;
				int i = 0;
				int j = 0;
				while (i < largo && iguales) {
					if (this.cuadro[i][j] != oGen.cuadro[i][j]) {
						iguales = false;
					}
					else if (j < (largo)-2) {
						j++;	
					}
					else {
					i++;
					j = 0;
					}
				}
				return iguales;
			}
			else {
				return false;
			}
		}
		catch(Exception e) {
			return false;
		}
	}
}
