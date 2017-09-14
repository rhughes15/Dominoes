package Dominoes;

import Dominoes.Visualization.BoardDisplay;
import javafx.scene.canvas.GraphicsContext;

import java.util.Deque;
import java.util.LinkedList;

public class Board
{
  private Deque<Domino> board;
  private BoardDisplay display;

  public Board(GraphicsContext gc)
  {
    board = new LinkedList<Domino>();
    display = new BoardDisplay(gc, board);
  }

  // will place a domino in the front if it can be placed there
  // or in the back if it can be placed there
  // it will flip the domino to make sense in the board
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
