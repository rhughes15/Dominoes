package Dominoes.Visualization;

import Dominoes.Display;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

//***********************************
// Ryan Hughes
//
// This class handles the display of the boneyard
// on the canvas.
//***********************************

public class BoneyardDisplay
{
  private GraphicsContext gc;
  private Image image;

  /**
   * The constructor for the class to display the Boneyard
   * @param gc the GraphicsContext used to display the Boneyard
   */
  public BoneyardDisplay(GraphicsContext gc)
  {
    this.gc = gc;
    image = new Image("Boneyard.png");
  }

  /**
   * This method displays the Boneyard on the canvas
   * using GraphicsContext gc
   */
  public void display()
  {
    gc.drawImage(image, Display.BONEYARD_X, Display.BONEYARD_Y);
  }
}
