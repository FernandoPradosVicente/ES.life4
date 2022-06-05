package life.ui;

/**
 * Interfaz para los "escuchadores" de eventos de un objeto LifeView.
 */
public interface LifeViewListener {
  public void pause();
  public void step();
  public void resume();
  public void undo();
  public void quit();
}
