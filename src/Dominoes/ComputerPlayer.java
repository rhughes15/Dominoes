package Dominoes;

import javafx.scene.canvas.GraphicsContext;

public class ComputerPlayer extends Player
{
  public ComputerPlayer(Boneyard bones, Board board, GraphicsContext gc)
  { super(bones, board, gc); }

  // current ai:
  // if it can play on the left, it will
  // if not, and it can play on the right, it will
  // if not, it will draw and start the process over
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
