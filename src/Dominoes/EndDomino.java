package Dominoes;

//***********************************
// Ryan Hughes
//
// This class is a special domino used to indicate
// that the boneyard is empty to the rest of the program
// without breaking the program
//***********************************

public class EndDomino extends Domino
{
  public EndDomino() { super(0, 0, 0, 0, null, null, null); }
}