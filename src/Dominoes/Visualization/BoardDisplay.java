package Dominoes.Visualization;

import Dominoes.Display;
import Dominoes.Domino;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Deque;

//***********************************
// Ryan Hughes
//
// This class is used to visually display the Board.
// It draws on the canvas that utilizes the GraphicsContext
// it receives in the constructor. It displays two yellow
// rectangles where the player can play and displays the
// dominoes in two rows as specified in the problem
//***********************************

public class BoardDisplay
{
  private GraphicsContext gc;
  private int center;
  int leftx, lefty, rightx, righty;
  Deque<Domino> board;

  /**
   * Constructs an object that handles the visualization
   * of the domino board on the canvas with GraphicsContext gc
   * @param gc GraphicsContext used to display the visual elements of the board
   * @param board the board that handles all the data to be displayed
   */
  public BoardDisplay(GraphicsContext gc, Deque<Domino> board)
  {
    this.gc = gc;
    this.board = board;
    center = 0;
    leftx = rightx = (Display.SCREEN_WIDTH / 2) - (Display.DOMINO_WIDTH / 2);
    lefty = righty = Display.SCREEN_HEIGHT / 2;
  }

  /**
   * This method handles all of the calls to GraphicsContext gc
   * to display the dominoes on the board
   */
  public void display()
  {
    gc.setFill(Display.BACKGROUND_COLOR); // clear the background
    gc.fillRect(0, Display.HAND_HEIGHT, Display.SCREEN_WIDTH,
                Display.SCREEN_HEIGHT - (Display.HAND_HEIGHT * 2));

    if(board.size() == 0)  // if there isn't anything on the board, empty yellow square
    {
      gc.setFill(Color.YELLOW);
      gc.fillRoundRect(leftx, lefty, Display.DOMINO_WIDTH, Display.DOMINO_HEIGHT,
        Display.DOMINO_ARC_WIDTH, Display.DOMINO_ARC_HEIGHT);
    }
    else
    {
      //display all the dominoes
      for(Domino d : board) {d.display(1);}

      //display the yellow boxes on either side
      gc.setFill(Color.YELLOW);
      gc.fillRoundRect(leftx, lefty, Display.DOMINO_WIDTH,
        Display.DOMINO_HEIGHT, Display.DOMINO_ARC_WIDTH, Display.DOMINO_ARC_HEIGHT);
      gc.fillRoundRect(rightx, righty, Display.DOMINO_WIDTH,
        Display.DOMINO_HEIGHT, Display.DOMINO_ARC_WIDTH, Display.DOMINO_ARC_HEIGHT);
    }
  }

  /**
   * This method updates all of the visual data stored in
   * the dominoes already on the board when @param domino
   * is added on @param side
   * @param domino the domino that is being added to the board,
   *               must not be null
   * @param side An integer denoting which side of the board
   *             the domino will be placed on. A value less than
   *             0 indicates the left side, a value greater than
   *             0 is the right side
   */
  public void update(Domino domino, int side)
  {
    if(board.size() == 0) //first domino
    {
      domino.setX(leftx);
      domino.setY(lefty);
      board.add(domino);
      rightx += Display.DOMINO_WIDTH / 2;
      righty += Display.DOMINO_HEIGHT;
      leftx -= Display.DOMINO_WIDTH / 2;
      lefty += Display.DOMINO_HEIGHT;
    }
    else if(side < 0) // domino is being added to the left
    {
      domino.setX(leftx);
      domino.setY(lefty);
      board.addFirst(domino);
      int relativePos = 1 - center;
      if(relativePos % 2 == 0) lefty += Display.DOMINO_HEIGHT;
      else lefty -= Display.DOMINO_HEIGHT;
      leftx -= (Display.DOMINO_WIDTH) / 2;
      center++;
    }
    else // domino is being added to the right
    {
      domino.setX(rightx);
      domino.setY(righty);
      board.addLast(domino);
      int relativePos = board.size() - center - 1;
      if(relativePos % 2 == 0) righty += Display.DOMINO_HEIGHT;
      else righty -= Display.DOMINO_HEIGHT;
      rightx += (Display.DOMINO_WIDTH) / 2;
    }
    display();
  }

  /* a list of necessary getters and setters */
  public int getLeftx() {return leftx;}
  public int getLefty() {return lefty;}
  public int getRightx() {return rightx;}
  public int getRighty() {return righty;}
}
