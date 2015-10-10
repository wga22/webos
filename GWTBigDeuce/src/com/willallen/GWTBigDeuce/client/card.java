package com.willallen.GWTBigDeuce.client;


public class card
{
  private int suit;
  private int value;

  public card(int paramInt1, int paramInt2)
  {
    this.suit = paramInt1;
    this.value = paramInt2;
  }

  public int getSuit()
  {
    return this.suit;
  }
  public int getValue() {
    return this.value;
  }
  public void setSuit(int paramInt) {
    this.suit = paramInt;
  }
  public void setValue(int paramInt) {
    this.value = paramInt;
  }

  public void showCard()
  {
    System.out.println("Suit: " + this.suit + "\tValue: " + this.value);
  }
}