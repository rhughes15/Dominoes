package Dominoes;

import javafx.scene.input.MouseEvent;

public class PlayerListener
{
  private Player player;
  private Domino pressed;
  private int pressedIndex;
  private Controller controller;

  public PlayerListener(Player player, Controller controller)
  {
    this.player = player;
    this.controller = controller;
  }

  //if the click is on a domino, the domino gets picked up
  public void handlePress(MouseEvent mouseEvent) //LEFT OFF HERE: need to finish implementing event handling, animation from the computer, comments
  {
    Object[] hand = player.getHand().toArray();
    double clickX = mouseEvent.getX();
    double clickY = mouseEvent.getY();

    for(int i = 0; i < hand.length; i++)
    {
      double difx = clickX - ((Domino)hand[i]).getX();
      double dify = clickY - ((Domino)hand[i]).getY();
      if(difx > 0 && difx < Display.DOMINO_WIDTH &&
         dify > 0 && dify < Display.DOMINO_HEIGHT)
      {
        ((Domino)hand[i]).pickUp();
        pressed = (Domino)hand[i];
        pressedIndex = i;
        player.printHand(1);
        player.play(player.getHand().indexOf(pressed));
      }
    }

    double difx = clickX - Display.BONEYARD_X;
    double dify = clickY - Display.BONEYARD_Y;
    if(difx > 0 && difx < Display.BONEYARD_WIDTH && // boneyard was clicked
       dify > 0 && dify < Display.BONEYARD_HEIGHT)
    {
      player.draw();
      player.printHand(1);
    }
  }

  // drags the domino around wherever the mouse goes and
  // repaints everything to get rid of the trail
  public void handleDrag(MouseEvent mouseEvent)
  {
    if(pressed != null)
    {
      pressed.setX((int) mouseEvent.getX());
      pressed.setY((int) mouseEvent.getY());
      player.printHand(1);
      player.printBoard();
      player.printBones();
      pressed.display(1);
    }
  }

  // will add the domino to the board if it can and
  // add it back to the hand if it's not on the board
  public void handleRelease(MouseEvent mouseEvent)
  {
    if(pressed != null)
    {
      pressed.putDown();
      int leftx = player.getBoard().getLeftx();
      int lefty = player.getBoard().getLefty();
      int rightx = player.getBoard().getRightx();
      int righty = player.getBoard().getRighty();
      if (isCloseEnough(pressed.getX(), leftx, pressed.getY(), lefty))
      {
        if (player.getBoard().placeLeft(pressed))
        {
          pressed = null;
          controller.run();
        }
        else
        {
          player.draw(pressed);
          pressed = null;
          pressedIndex = -1;
        }
      }
      else if (isCloseEnough(pressed.getX(), rightx, pressed.getY(), righty))
      {
        if (player.getBoard().placeRight(pressed))
        {
          pressed = null;
          controller.run();
        }
        else
        {
          player.draw(pressed);
          pressed = null;
          pressedIndex = -1;
        }
      }
      else
      {
        pressed.setX(((pressedIndex + 1) * Display.DOMINO_HBUFFER) +
          (pressedIndex * Display.DOMINO_WIDTH));
        pressed.setY(Display.SCREEN_HEIGHT - Display.HAND_HEIGHT + Display.DOMINO_VBUFFER);
        player.draw(pressed);
        pressed = null;
        pressedIndex = -1;
      }
    }
    player.printBoard();
    player.printHand(1);
    player.printBones();
  }

  private boolean isCloseEnough(int x1, int x2, int y1, int y2)
  {
    if(Math.abs(x2 - x1) < Display.DOMINO_WIDTH &&
       Math.abs(y2 - y1) < Display.DOMINO_HEIGHT)
      return true;
    return false;
  }
}
