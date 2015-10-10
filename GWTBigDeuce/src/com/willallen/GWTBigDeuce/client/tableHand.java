package com.willallen.GWTBigDeuce.client;

import java.io.PrintStream;
import java.util.Vector;

public class tableHand
{
	public static final int ONECARD = 0;
	public static final int ONEPAIR = 1;
	public static final int TWOPAIR = 2;
	public static final int STRAIGHT = 3;
	public static final int FLUSH = 4;
	public static final int FULLHOUSE = 5;
	public static final int FOURAKIND = 6;
	public static final int STRAIGHTFLUSH = 7;
	public static final int ROYALFLUSH = 8;
	private card[] cd;
	private int noCard;
	private int highestValue;
	private int highestSuit;
	private int combination;

	public tableHand(card[] paramArrayOfcard)
	{
		int i = paramArrayOfcard.length;
		this.cd = new card[i];
		for (int j = 0; j < i; j++)
			this.cd[j] = paramArrayOfcard[j];
		this.noCard = i;

		if (i == 1)
		{
			this.noCard = 1;
			this.highestValue = paramArrayOfcard[0].getValue();
			this.highestSuit = paramArrayOfcard[0].getSuit();
			this.combination = 0;
		} else
		{
			int[] arrayOfInt1;
			int[] arrayOfInt2;
			int[] arrayOfInt3;
			if (i == 2)
			{
				arrayOfInt1 = new int[1];
				arrayOfInt2 = new int[1];
				arrayOfInt3 = new int[1];

				if (pair(paramArrayOfcard, arrayOfInt1, arrayOfInt2, arrayOfInt3) == true)
				{
					this.noCard = 2;
					this.highestValue = arrayOfInt1[0];
					this.highestSuit = arrayOfInt2[0];
					this.combination = arrayOfInt3[0];
				}
			} else if (i == 5)
			{
				arrayOfInt1 = new int[1];
				arrayOfInt2 = new int[1];
				arrayOfInt3 = new int[1];

				if (pairCombn(paramArrayOfcard, arrayOfInt1, arrayOfInt2, arrayOfInt3) == true)
				{
					this.noCard = 5;
					this.highestValue = arrayOfInt1[0];
					this.highestSuit = arrayOfInt2[0];
					this.combination = arrayOfInt3[0];
				} else
				{
					int[] arrayOfInt4 = new int[1];
					int[] arrayOfInt5 = new int[1];
					int[] arrayOfInt6 = new int[1];
					int[] arrayOfInt7 = new int[1];
					int[] arrayOfInt8 = new int[1];
					int[] arrayOfInt9 = new int[1];
					boolean bool1 = straight(paramArrayOfcard, arrayOfInt7, arrayOfInt8, arrayOfInt9);
					boolean bool2 = flush(paramArrayOfcard, arrayOfInt4, arrayOfInt5, arrayOfInt6);

					if (bool1 == true)
					{
						if (bool2 == true)
						{
							this.noCard = 5;
							this.highestValue = arrayOfInt7[0];
							this.highestSuit = arrayOfInt5[0];
							this.combination = 7;
						} else
						{
							this.noCard = 5;
							this.highestValue = arrayOfInt7[0];
							this.highestSuit = arrayOfInt8[0];
							this.combination = arrayOfInt9[0];
						}
					} else if (bool2 == true)
					{
						this.noCard = 5;
						this.highestValue = arrayOfInt4[0];
						this.highestSuit = arrayOfInt5[0];
						this.combination = arrayOfInt6[0];
					}
				}
			} else
			{
				System.out.println("MUST BE 1, 2 OR 5 CARDS!!!");
			}
		}
	}

	public static boolean flush(card[] paramArrayOfcard, int[] paramArrayOfInt1, int[] paramArrayOfInt2,
			int[] paramArrayOfInt3)
	{
		int i = paramArrayOfcard[0].getSuit();
		int j = paramArrayOfcard[0].getValue();
		if ((i == paramArrayOfcard[1].getSuit()) && (i == paramArrayOfcard[2].getSuit())
				&& (i == paramArrayOfcard[3].getSuit()) && (i == paramArrayOfcard[4].getSuit()))
		{
			if (j != 2)
				for (int k = 1; k <= 4; k++)
				{
					int m = paramArrayOfcard[k].getValue();
					if (m == 2)
					{
						j = 2;
						break;
					}
					if (m == 1)
					{
						j = 1;
					} else
					{
						if ((m <= j) || (j == 1))
							continue;
						j = m;
					}
				}
			paramArrayOfInt1[0] = j;
			paramArrayOfInt2[0] = i;
			paramArrayOfInt3[0] = 4;
			return true;
		}
		return false;
	}

	public card[] getCards()
	{
		return this.cd;
	}

	public int getCombination()
	{
		return this.combination;
	}

	public int getHighestSuit()
	{
		return this.highestSuit;
	}

	public int getHighestValue()
	{
		return this.highestValue;
	}

	public int getNoCard()
	{
		return this.noCard;
	}

	public static boolean pair(card[] paramArrayOfcard, int[] paramArrayOfInt1, int[] paramArrayOfInt2,
			int[] paramArrayOfInt3)
	{
		if (paramArrayOfcard[0].getValue() == paramArrayOfcard[1].getValue())
		{
			paramArrayOfInt1[0] = paramArrayOfcard[0].getValue();
			paramArrayOfInt2[0] = (paramArrayOfcard[0].getSuit() < paramArrayOfcard[1].getSuit() ? paramArrayOfcard[0]
					.getSuit() : paramArrayOfcard[1].getSuit());
			paramArrayOfInt3[0] = 1;
			return true;
		}
		return false;
	}

	public static boolean pairCombn(card[] paramArrayOfcard, int[] paramArrayOfInt1, int[] paramArrayOfInt2,
			int[] paramArrayOfInt3)
	{
		Vector<card> localVector1 = new Vector<card>();
		Vector<card> localVector2 = new Vector<card>();
		Vector<card> localVector3 = new Vector<card>();
		localVector3.addElement(paramArrayOfcard[0]);
		int j;
		int k;
		int m;
		int n;
		for (int i = 1; i < 5; i++)
		{
			j = 0;
			k = 0;
			m = 0;
			n = paramArrayOfcard[i].getValue();
			if (n == 1)
				n = 14;
			if (n == 2)
				n = 15;
			while ((j < localVector3.size()) && (m == 0))
			{
				k = ((card) localVector3.elementAt(j)).getValue();
				if (k == 1)
					k = 14;
				if (k == 2)
					k = 15;
				if (n < k)
				{
					localVector3.insertElementAt(paramArrayOfcard[i], j);
					m = 1;
				} else if (n == k)
				{
					if (localVector1.isEmpty())
					{
						localVector1.addElement(paramArrayOfcard[i]);
						localVector1.addElement((card) localVector3.elementAt(j));
						localVector3.removeElementAt(j);
					} else if (localVector2.isEmpty())
					{
						localVector2.addElement(paramArrayOfcard[i]);
						localVector2.addElement((card) localVector3.elementAt(j));
						localVector3.removeElementAt(j);
					}
					m = 1;
				} else
				{
					j++;
				}
			}
			if (m != 0)
				continue;
			localVector3.addElement(paramArrayOfcard[i]);
		}

		if ((!localVector1.isEmpty()) && (!localVector2.isEmpty()))
		{
			if (((card) localVector3.elementAt(0)).getValue() == ((card) localVector1.elementAt(0)).getValue())
			{
				paramArrayOfInt1[0] = ((card) localVector1.elementAt(0)).getValue();
				j = ((card) localVector3.elementAt(0)).getSuit();
				for (k = 0; k < localVector1.size(); k++)
				{
					m = ((card) localVector1.elementAt(k)).getSuit();
					if (m >= j)
						continue;
					j = m;
				}
				paramArrayOfInt2[0] = j;
				paramArrayOfInt3[0] = 5;
			} else if (((card) localVector3.elementAt(0)).getValue() == ((card) localVector2.elementAt(0)).getValue())
			{
				paramArrayOfInt1[0] = ((card) localVector2.elementAt(0)).getValue();
				j = ((card) localVector3.elementAt(0)).getSuit();
				for (k = 0; k < localVector2.size(); k++)
				{
					m = ((card) localVector2.elementAt(k)).getSuit();
					if (m >= j)
						continue;
					j = m;
				}
				paramArrayOfInt2[0] = j;
				paramArrayOfInt3[0] = 5;
			} else if (((card) localVector1.elementAt(0)).getValue() == ((card) localVector2.elementAt(0)).getValue())
			{
				paramArrayOfInt1[0] = ((card) localVector1.elementAt(0)).getValue();
				paramArrayOfInt2[0] = 1;
				paramArrayOfInt3[0] = 6;
			} else
			{
				j = ((card) localVector1.elementAt(0)).getValue();
				k = ((card) localVector2.elementAt(0)).getValue();
				if ((j == 1) || (j == 2))
				{
					paramArrayOfInt1[0] = j;
					m = ((card) localVector1.elementAt(0)).getSuit();
					n = ((card) localVector1.elementAt(1)).getSuit();
					paramArrayOfInt2[0] = (m < n ? m : n);
				} else if ((k == 1) || (k == 2))
				{
					paramArrayOfInt1[0] = k;
					m = ((card) localVector2.elementAt(0)).getSuit();
					n = ((card) localVector2.elementAt(1)).getSuit();
					paramArrayOfInt2[0] = (m < n ? m : n);
				} else if (j > k)
				{
					paramArrayOfInt1[0] = j;
					m = ((card) localVector1.elementAt(0)).getSuit();
					n = ((card) localVector1.elementAt(1)).getSuit();
					paramArrayOfInt2[0] = (m < n ? m : n);
				} else
				{
					paramArrayOfInt1[0] = k;
					m = ((card) localVector2.elementAt(0)).getSuit();
					n = ((card) localVector2.elementAt(1)).getSuit();
					paramArrayOfInt2[0] = (m < n ? m : n);
				}
				paramArrayOfInt3[0] = 2;
			}
			return true;
		}
		return false;
	}

	public static boolean straight(card[] paramArrayOfcard, int[] paramArrayOfInt1, int[] paramArrayOfInt2,
			int[] paramArrayOfInt3)
	{
		Vector<card> localVector = new Vector<card>();
		localVector.addElement(paramArrayOfcard[0]);
		for (int i = 1; i < 5; i++)
		{
			int j = 0;
			int k = 0;
			int m = 0;
			int n = paramArrayOfcard[i].getValue();
			while ((j < localVector.size()) && (m == 0))
			{
				k = ((card) localVector.elementAt(j)).getValue();
				if ((n < k)
						|| ((n == k) && (paramArrayOfcard[i].getSuit() < ((card) localVector.elementAt(j)).getSuit())))
				{
					localVector.insertElementAt(paramArrayOfcard[i], j);
					m = 1;
				} else
				{
					j++;
				}
			}
			if (m != 0)
				continue;
			localVector.addElement(paramArrayOfcard[i]);
		}

		if ((((card) localVector.elementAt(0)).getValue() == 1) && (((card) localVector.elementAt(1)).getValue() == 10)
				&& (((card) localVector.elementAt(2)).getValue() == 11)
				&& (((card) localVector.elementAt(3)).getValue() == 12)
				&& (((card) localVector.elementAt(4)).getValue() == 13))
		{
			paramArrayOfInt1[0] = 1;
			paramArrayOfInt2[0] = ((card) localVector.elementAt(0)).getSuit();
			paramArrayOfInt3[0] = 3;
			return true;
		}

		if ((((card) localVector.elementAt(0)).getValue() == ((card) localVector.elementAt(1)).getValue() - 1)
				&& (((card) localVector.elementAt(0)).getValue() == ((card) localVector.elementAt(2)).getValue() - 2)
				&& (((card) localVector.elementAt(0)).getValue() == ((card) localVector.elementAt(3)).getValue() - 3)
				&& (((card) localVector.elementAt(0)).getValue() == ((card) localVector.elementAt(4)).getValue() - 4))
		{
			paramArrayOfInt1[0] = ((card) localVector.elementAt(4)).getValue();
			paramArrayOfInt2[0] = ((card) localVector.elementAt(4)).getSuit();
			paramArrayOfInt3[0] = 3;
			return true;
		}
		return false;
	}

	public static boolean straightFlush(card[] paramArrayOfcard, int[] paramArrayOfInt1, int[] paramArrayOfInt2,
			int[] paramArrayOfInt3)
	{
		int[] arrayOfInt1 = new int[1];
		int[] arrayOfInt2 = new int[1];
		int[] arrayOfInt3 = new int[1];
		int[] arrayOfInt4 = new int[1];
		int[] arrayOfInt5 = new int[1];
		int[] arrayOfInt6 = new int[1];
		boolean bool1 = straight(paramArrayOfcard, arrayOfInt4, arrayOfInt5, arrayOfInt6);
		boolean bool2 = flush(paramArrayOfcard, arrayOfInt1, arrayOfInt2, arrayOfInt3);

		if (bool1 == true)
		{
			if (bool2 == true)
			{
				paramArrayOfInt1[0] = arrayOfInt4[0];
				paramArrayOfInt2[0] = arrayOfInt2[0];
				paramArrayOfInt3[0] = 7;
				return true;
			}
			return false;
		}
		return false;
	}
}