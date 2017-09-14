package Dominoes.Visualization;

import Dominoes.Display;
import Dominoes.Domino;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class PlayerDisplay
{
  private GraphicsContext gc;

  public PlayerDisplay(GraphicsContext gc)
  {
    this.gc = gc;
  }

  public void update(ArrayList<Domino> dominoes, int player)
  {
    gc.setFill(Display.BACKGROUND_COLOR); // clear the area for the hand
    if(player == 1)
    {
      gc.fillRect(0, Display.SCREEN_HEIGHT - Display.HAND_HEIGHT, Display.SCREEN_WIDTH, Display.HAND_HEIGHT);
    }
    else { gc.fillRect(0, 0, Display.SCREEN_WIDTH, Display.HAND_HEIGHT);}

    for (Domino d : dominoes) {d.display(player);}
  }

  public GraphicsContext getGC() {return gc;}
}
