package Dominoes;

import Dominoes.Visualization.BoardDisplay;
import javafx.scene.canvas.GraphicsContext;

import java.util.Deque;
import java.util.LinkedList;
//***********************************
// Ryan Hughes
//
// This class handles the storage and addition of Dominoes
// to the board. It handles all the logic of the board for the
// game and calls its BoardDisplay to display it
//***********************************

public class Board
{
  private Deque<Domino> board;
  private BoardDisplay display;

  public Board(GraphicsContext gc)
  {
    board = new LinkedList<>();
    display = new BoardDisplay(gc, board);
  }

  /**
   * This method will place a domino on the left and return
   * true if it can. If necessary, it will flip the domino to
   * make sense on the board. If the domino can not be placed
   * it will do nothing and return false. This is the method
   * that should be called for the first domino on the board
   * @param d domino to be placed
   * @return true if the domino is placed, false if not
   */
  public boolean placeLeft(Domino d)
  {
    if (board.size() == 0)
    {
      display.update(d, -1);
      return true;
    }
    else
    {
      int front = board.getFirst().getLeft();
      if (front == d.getRight() || front == 0 || d.getRight() == 0)
      {
        display.update(d, -1);
        return true;
      }
      else if (front == d.getLeft() || d.getLeft() == 0)
      {
        d.flip();
        display.update(d, -1);
        return true;
      }
      return false;
    }
  }

  /**
   * This method will place a domino on the right and return
   * true if it can. If necessary, it will flip the domino to
   * make sense on the board. If the domino can not be placed
   * it will do nothing and return false
   * @param d domino to be placed
   * @return true if the domino is placed, false if not
   */
  public boolean placeRight(Domino d)
  {
    int back = board.getLast().getRight();
    if(back == d.getLeft() || d.getLeft() == 0)
    {
      display.update(d, 1);
      return true;
    }
    else if(back == d.getRight() || d.getRight() == 0)
    {
      d.flip();
      display.update(d, 1);
      return true;
    }
    return false;
  }

  /**
   * This method is called to display the board on the canvas
   */
  public void printBoard()
  {
    display.display();
  }

  public int getFront() { return board.getFirst().getLeft();}
  public int getBack() { return board.getLast().getRight(); }
  public int getLeftx() {return display.getLeftx();}
  public int getLefty() {return display.getLefty();}
  public int getRightx() {return display.getRightx();}
  public int getRighty() {return display.getRighty();}
}
