package Dominoes;

import Dominoes.Visualization.DominoDisplay;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

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

    // reverses the domino
    public void flip()
    {
      int tempNum = leftNum;
      leftNum = rightNum;
      rightNum = tempNum;
      display.flip();
    }

    public int getLeft() {return leftNum;}
    public int getRight() {return rightNum;}

    public void display(int player)
    {display.display(x, y, player, pickedUp);}
    public void display(int player, double scalingFactor)
    {display.display(x, y, player, pickedUp, scalingFactor);}

    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
    public int getX() {return x;}
    public int getY() {return y;}

    public void pickUp() {pickedUp = true;}
    public void putDown() {pickedUp = false;}
}
