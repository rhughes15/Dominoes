package Dominoes;

import javafx.scene.canvas.GraphicsContext;

import java.util.Scanner;

public class HumanPlayer extends Player
{
  public HumanPlayer(Boneyard bones, Board board, GraphicsContext gc){super(bones, board, gc); }

  public void takeTurn()
  {
    // This is an abstract method that needs to be
    // overridden based on the structure, but was implemented
    // with other functions/classes so isn't used by the
    // human player.
  }

  public void draw()
  {
    Domino draw = bones.getDomino();
    int position = hand.size();
    int newX = ((position + 1) * Display.DOMINO_HBUFFER) +
      (position * Display.DOMINO_WIDTH);
    int newY = Display.SCREEN_HEIGHT - Display.HAND_HEIGHT + Display.DOMINO_VBUFFER;
    draw.setX(newX);
    draw.setY(newY);
    hand.add(draw);
  }
}
