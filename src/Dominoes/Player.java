package Dominoes;

import Dominoes.Visualization.PlayerDisplay;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public abstract class Player
{
  protected ArrayList<Domino> hand;
  protected Boneyard bones;
  protected Board board;
  protected PlayerDisplay display;

  public Player(Boneyard bones, Board board, GraphicsContext gc)
  {
    this.bones = bones;
    this.board = board;
    hand = new ArrayList<>();
    display = new PlayerDisplay(gc);
  }

  public abstract void takeTurn();

  public Domino play(int d) { return hand.remove(d); }
  public abstract void draw();
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
