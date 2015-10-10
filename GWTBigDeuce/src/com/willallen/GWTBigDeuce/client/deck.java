package com.willallen.GWTBigDeuce.client;
import java.util.Random;
import java.util.Vector;

public class deck
{
  public static final int SPADE = 1;
  public static final int HEART = 2;
  public static final int CLUB = 3;
  public static final int DIAMOND = 4;
  public static final int JACK = 11;
  public static final int QUEEN = 12;
  public static final int KING = 13;
  public static final int ACE = 1;
  private Vector vecDeck;
  private Random ran;
  private static final int DECKSIZE = 52;

  public deck()
  {
    this.vecDeck = new Vector();
    for (int i = 1; i <= 4; i++)
      for (int j = 1; j <= 13; j++)
        this.vecDeck.addElement(new card(i, j));
    this.ran = new Random();
  }

  public int getDeckSize()
  {
    return this.vecDeck.size();
  }

  public card getNext() {
    if (hasMoreCard() == false)
    {
      int i = this.ran.nextInt() >> 26;
      int j = i < 0 ? i ^ 0xFFFFFFFF : i;
      j %= this.vecDeck.size();
      card localcard = (card)this.vecDeck.elementAt(j);
      this.vecDeck.removeElementAt(j);
      return localcard;
    }return null;
  }

  public card[] getNextN(int paramInt) {
    card[] arrayOfcard = new card[paramInt];
    if (hasMoreCard() == false) {
      for (int i = 0; i < paramInt; i++)
      {
        int j = this.ran.nextInt() >> 26;
        int k = j < 0 ? j ^ 0xFFFFFFFF : j;
        k %= this.vecDeck.size();
        arrayOfcard[i] = ((card)this.vecDeck.elementAt(k));
        this.vecDeck.removeElementAt(k);
      }
      return arrayOfcard;
    }return null;
  }

  public boolean hasMoreCard()
  {
    return this.vecDeck.isEmpty();
  }
}