package Dominoes;

import javafx.scene.canvas.GraphicsContext;

//***********************************
// Ryan Hughes
//
// This class contains the logic for the computer player.
//***********************************
public class ComputerPlayer extends Player
{
  public ComputerPlayer(Boneyard bones, Board board, GraphicsContext gc)
  { super(bones, board, gc); }

  /**
   * This method contains the logic for the computer player.
   * If the computer player can play on the left, it will.
   * If not, and it can play on the right, it will play there.
   * If it can not play on either side, it will draw a domino
   * and start again.
   */
  @Override
  public void takeTurn()
  {
    int play = -1;
    while(play < 0)
    {
      play = canPlayOn(board.getFront());
      if (play >= 0) board.placeLeft(play(play));
      else
      {
        play = canPlayOn(board.getBack());
        if (play >= 0) board.placeRight(play(play));
      }
      if(play < 0)
      {
        Domino d = bones.getDomino();
        if(!(d instanceof  EndDomino)) draw();
        else play = 100;
      }
    }
    display.update(hand, 2);
  }

  /**
   * This method overrides the player's abstract draw method.
   * It handles the logic of drawing a domino for the computer.
   */
  public void draw()
  {
    Domino draw = bones.getDomino();
    int position = hand.size();
    int newX = ((position + 1) * Display.DOMINO_HBUFFER) +
      (position * Display.DOMINO_WIDTH);
    int newY = Display.DOMINO_VBUFFER;
    draw.setX(newX);
    draw.setY(newY);
    hand.add(draw);
  }
}
