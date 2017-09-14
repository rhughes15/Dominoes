package Dominoes;

import javafx.scene.canvas.GraphicsContext;

//***********************************
// Ryan Hughes
//
// This class was used in the initial implementation to
// handle the player's turn, but that functionality is
// now implemented elsewhere so it only handles the
// human player's drawing dominoes from the boneyard
//***********************************

public class HumanPlayer extends Player
{
  public HumanPlayer(Boneyard bones, Board board, GraphicsContext gc){super(bones, board, gc); }

  /**
   * was necessary to override, but adds no functionality
   */
  public void takeTurn()
  {
    // This is an abstract method that needs to be
    // overridden based on the structure, but was implemented
    // with other functions/classes so isn't used by the
    // human player.
  }

  /**
   * This method handles the logic of the human player drawing
   * from the boneyard
   */
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
