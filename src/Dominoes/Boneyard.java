package Dominoes;

import Dominoes.Visualization.BoneyardDisplay;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

//***********************************
// Ryan Hughes
//
// This class handles the creation of all dominoes for
// the game and holds all the extras that are not currently
// in either player's hand. getDomino should be used to draw
// dominoes during the game
//***********************************
public class Boneyard
{
  private ArrayList<Domino> boneyard;
  private BoneyardDisplay display;

  /**
   * The constructor creates all the dominoes that
   * will be used in the game
   * @param gc the GraphicsContext that will be used by the
   *           BoneyardDisplay to display the boneyard
   */
  public Boneyard(GraphicsContext gc)
  {
    display = new BoneyardDisplay(gc);
    Image[] pics = new Image[7];
    for(int i = 0; i < 7; i++) pics[i] = new Image("dom" + i + ".png");
    boneyard = new ArrayList<>();
    for(int i = 0; i <= 6; i++)
      for(int j = 0; j <= i; j++)
        boneyard.add(new Domino(0, 0, i, j, gc, pics[i], pics[j]));
  }

  /**
   * This method should be used to "draw" dominoes during the game
   * @return a random domino from the boneyard
   */
  public Domino getDomino()
  {
    if(!boneyard.isEmpty()) return boneyard.remove((int)(Math.random() * boneyard.size()));
    return new EndDomino();
  }

  public boolean isEmpty() { return boneyard.isEmpty(); }

  public void display() { display.display(); }
}
