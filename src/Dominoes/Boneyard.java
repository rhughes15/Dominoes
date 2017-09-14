package Dominoes;

import Dominoes.Visualization.BoneyardDisplay;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Boneyard
{
  private ArrayList<Domino> boneyard;
  private BoneyardDisplay display;

  public Boneyard(GraphicsContext gc)
  {
    display = new BoneyardDisplay(gc);
    Image[] pics = new Image[7];
    for(int i = 0; i < 7; i++) pics[i] = new Image("dom" + i + ".png");
    boneyard = new ArrayList<>();
    for(int i = 0; i <= 6; i++)
      for(int j = 0; j <= i; j++)
        boneyard.add(new Domino(0, 0, i, j, gc, pics[i], pics[j]));
  }

  public Domino getDomino()
  {
    if(!boneyard.isEmpty()) return boneyard.remove((int)(Math.random() * boneyard.size()));
    return new EndDomino();
  }

  public boolean isEmpty() { return boneyard.isEmpty(); }

  public void display() { display.display(); }
}
