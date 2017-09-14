package Dominoes.Visualization;

import Dominoes.Display;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class DominoDisplay
{
  private GraphicsContext gc;
  private Image leftImage;
  private Image rightImage;

  /**
   * The only constructor of the class used to display a
   * Domino
   * @param gc the GraphicsContext with which the Domino will be drawn
   * @param leftImage the Image that will be printed on the left side
   *                  of the Domino
   * @param rightImage the Image that will be printed on the right
   *                   side of the Domino
   */
  public DominoDisplay(GraphicsContext gc, Image leftImage, Image rightImage)
  {
    this.gc = gc;
    this.leftImage = leftImage;
    this.rightImage = rightImage;
  }

  /**
   * This is the normal display call, it will display the Dominoes
   * at a normal size by calling the other display function with a
   * scaling factor of 1
   * @param x the x coordinate at which the Domino will be displayed
   * @param y the y coordinate at which the Domino will be displayed
   * @param player an int denoting which player this domino belongs to,
   *               player 1 will display the dominoes face up, any other
   *               player will have their dominoes displayed face down
   * @param pickedUp a boolean denoting whether the domino is currently in
   *                 the air, a value of true will display a shadow around
   *                 the domino, making it look picked up
   */
  public void display(int x, int y, int player, boolean pickedUp)
  {
    display(x, y, player, pickedUp, 1);
  }

  /**
   * This is the main display function for drawing the dominoes. It takes
   * all the same parameters as the other function but also takes a scaling factor
   * to scale the size of the domino. This scaling factor currently works for the dominoes,
   * but it was not fully implemented on the board in the time given so it is not used
   * any where in the program
   * @param x
   * @param y
   * @param player
   * @param pickedUp
   * @param scalingFactor
   */
  public void display(int x, int y, int player, boolean pickedUp, double scalingFactor)
  {
    if(player == 1)
    {
      gc.drawImage(scale(leftImage, (int)(Display.DOMINO_WIDTH * scalingFactor),
                   (int)(Display.DOMINO_HEIGHT * scalingFactor), true),
                   x + 1, y + 1);
      gc.drawImage(scale(rightImage, (int)(Display.DOMINO_WIDTH * scalingFactor),
                   (int)(Display.DOMINO_HEIGHT * scalingFactor), true),
                   x + ((Display.DOMINO_WIDTH * scalingFactor) / 2) + 1, y);
    }
    else
    {
      gc.drawImage(scale(new Image("dom0.png"), (int)(Display.DOMINO_WIDTH * scalingFactor),
                   (int)(Display.DOMINO_HEIGHT * scalingFactor), true),
                   x + 1, y + 1);
      gc.drawImage(scale(new Image("dom0.png"), (int)(Display.DOMINO_WIDTH * scalingFactor),
                   (int)(Display.DOMINO_HEIGHT * scalingFactor), true),
                   x + (Display.DOMINO_WIDTH / 2) + 1, y);
    }

    gc.setStroke(Color.BLACK);
    if(pickedUp) gc.setEffect(new DropShadow(5, 5, 5, Color.BLACK));
    gc.strokeRoundRect(x, y, Display.DOMINO_WIDTH * scalingFactor, Display.DOMINO_HEIGHT * scalingFactor,
      Display.DOMINO_ARC_WIDTH, Display.DOMINO_ARC_HEIGHT);
    gc.setEffect(null);
    gc.strokeLine(x + (Display.DOMINO_WIDTH * scalingFactor) / 2, y, x +
      ((Display.DOMINO_WIDTH * scalingFactor) / 2), y + (Display.DOMINO_HEIGHT * scalingFactor));
  }

  /**
   * This method takes no parameters and reverses the images
   * on the domino
   */
  public void flip()
  {
    Image tempImage = leftImage;
    leftImage = rightImage;
    rightImage = tempImage;
  }

  /**
   * credit: stack overflow user: James_D
   * This method is used to scale the images within th dominoes which
   * did not end up being utilized in the final program due to time constraints
   * @param source the Image to be scaled
   * @param targetWidth the width to scale the image to, must not be negative
   * @param targetHeight the height to scale the image to, must not be negative
   * @param preserveRatio a boolean indicating whether the aspect ratio of the
   *                      source image should be preserved
   * @return a scaled version of the source Image
   */
  private Image scale(Image source, int targetWidth, int targetHeight, boolean preserveRatio)
  {
    ImageView imageView = new ImageView(source);
    imageView.setPreserveRatio(preserveRatio);
    imageView.setFitWidth(targetWidth);
    imageView.setFitHeight(targetHeight);
    return imageView.snapshot(null, null);
  }
}
