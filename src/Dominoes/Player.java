package Dominoes;

import Dominoes.Visualization.PlayerDisplay;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

//***********************************
// Ryan Hughes
//
// This class represents a player. It gives most of
// the functionality for the player, but lets the
// children define the takeTurn and draw methods.
// The player holds the board and boneyard and makes
// calls to display all of the visual elements
//***********************************

public abstract class Player
{
  protected ArrayList<Domino> hand;
  protected Boneyard bones;
  protected Board board;
  protected PlayerDisplay display;

  /**
   * Constructor
   * @param bones the boneyard that will be used
   * @param board the board that will be used
   * @param gc the GraphicsContext used by all the visual elements to paint on the canvas
   */
  public Player(Boneyard bones, Board board, GraphicsContext gc)
  {
    this.bones = bones;
    this.board = board;
    hand = new ArrayList<>();
    display = new PlayerDisplay(gc);
  }

  public abstract void takeTurn();
  public abstract void draw();

  public Domino play(int d) { return hand.remove(d); }
  public void draw(Domino d) {hand.add(d);}

  public void printHand(int player)
  {
    display.update(hand, player);
  }
  public void printBones() {bones.display();}
  public void printBoard() {board.printBoard();}
  public boolean emptyBones() { return bones.isEmpty(); }

  // returns the index of the domino that can be played
  // if any of the dominos can be played on the integer its passed
  public int canPlayOn(int boardNum) {
    for (Domino d : hand)
      if (d.getLeft() == boardNum || d.getRight() == boardNum)
        return hand.indexOf(d);
    return -1;
  }

  public boolean isEmpty() { return hand.isEmpty(); }

  public ArrayList<Domino> getHand() { return hand; }
  public Board getBoard() {return board;}
  public GraphicsContext getGC() {return display.getGC();}
}
