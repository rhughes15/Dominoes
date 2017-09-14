package Dominoes;

import Dominoes.Visualization.DominoDisplay;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

//***********************************
// Ryan Hughes
//
// This class handles the internal, logical representation
// of the dominoes that are used to play the game
//***********************************

public class Domino
{
    private int leftNum;
    private int rightNum;
    private int x, y;
    private DominoDisplay display;
    private boolean pickedUp;

    public Domino(int x, int y, int left, int right, GraphicsContext gc, Image leftImage, Image rightImage)
    {
      this.x = x;
      this.y = y;
      leftNum = left;
      rightNum = right;
      pickedUp = false;
      display = new DominoDisplay(gc, leftImage, rightImage);
    }

  /**
   * This method is overridden so that the Dominoes can be used
   * in complex data structures
   * @param obj the domino to compare to this
   * @return true if the dominoes have the same values, regardless
   * of order
   */
    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Domino)
        {
            Domino dom = (Domino)obj;
            if(leftNum == dom.leftNum && rightNum == dom.rightNum) return true;
            else if (leftNum == dom.rightNum && rightNum == dom.leftNum) return true;
        }
        return false;
    }

  /**
   * This method reverses the order of the numbers on the domino.
   */
  public void flip()
    {
      int tempNum = leftNum;
      leftNum = rightNum;
      rightNum = tempNum;
      display.flip();
    }

    public int getLeft() {return leftNum;}
    public int getRight() {return rightNum;}

  /**
   * This method is used to display the domino at its
   * current x and y coordinates
   * @param player 1 will display the domino face up, anything
   *               else will display it face down
   */
  public void display(int player)
    {display.display(x, y, player, pickedUp);}

  /**
   * This method is just like the other display method, but
   * it adds the capability of using a scaling factor to scale the
   * size of the dominoes. This works for individual dominoes, but
   * was not debugged well enough by the deadline to be implemented on
   * the board.
   * @param player 1 will display the domino face up, anything
   *               else will display it face down
   * @param scalingFactor a double from 0 to 1 representing the
   *                      percentage of the original size of the domino
   *                      to scale the domino to. 0 = 0%, 1 = 100%
   */
    public void display(int player, double scalingFactor)
    {display.display(x, y, player, pickedUp, scalingFactor);}

    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
    public int getX() {return x;}
    public int getY() {return y;}

    public void pickUp() {pickedUp = true;}
    public void putDown() {pickedUp = false;}
}
