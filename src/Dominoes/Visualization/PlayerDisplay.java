package Dominoes.Visualization;

  import Dominoes.Display;
  import Dominoes.Domino;
  import javafx.scene.canvas.GraphicsContext;

  import java.util.ArrayList;

//***********************************
// Ryan Hughes
//
// This class handles the logic to visually display
// the player's hand. It gets passed the hand and an integer
// determining whether to display the hand face up or
// face down.
//***********************************

public class PlayerDisplay
{
  private GraphicsContext gc;

  /**
   * This is the only defined constructor for the PlayerDisplay
   * @param gc the GraphicsContext used to display the graphic
   *           elements of the player's hand
   */
  public PlayerDisplay(GraphicsContext gc)
  {
    this.gc = gc;
  }

  /**
   * This method updates the visual display of the player's hand
   * @param dominoes the ArrayList of Dominoes to be displayed
   * @param player an integer indicating the player, 1 is a human
   *               player which will display the dominoes face up,
   *               anything else will displyay the dominoes face down.
   */
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

  /**
   * A simple getter
   * @return GraphicsContext gc that is used to display objects on the canvas
   */
  public GraphicsContext getGC() {return gc;}
}
