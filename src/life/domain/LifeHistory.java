package life.domain;

public class LifeHistory {
  private Gen current;
  private LifeHistory prev;

  public LifeHistory(Gen gen) {
    current = gen;
  }

  public void evolve() {
	LifeHistory newRegistro = new LifeHistory(current);
	newRegistro.prev = this.prev;
	this.prev = newRegistro;
	this.current = current.next();
  }

  public void undo() {
    this.current = prev.current;
	this.prev = prev.prev;
  }

  public Gen current() {
   return current;
  }

  public int generations() {
	boolean endFound = false;
	int count = 0;
	LifeHistory prevObservando = prev;
	while(!endFound) {
		if(prevObservando == null) {
			count++;
			endFound = true;
		}
		else {
			count++;
			prevObservando = prevObservando.prev;
		}
	}
	return count;
  }

  public boolean endOfGame() {
    return current.equals(current.next());
  }
}
