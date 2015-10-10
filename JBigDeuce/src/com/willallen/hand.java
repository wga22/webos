package com.willallen;

import java.util.Vector;

public class hand
{
	private int index3Diamond;
	private Vector vecHand;
	private boolean lead;

	public hand(card[] paramArrayOfcard)
	{
		this.vecHand = new Vector();
		this.lead = false;
		for (int i = 0; i < paramArrayOfcard.length; i++)
		{
			if ((paramArrayOfcard[i].getSuit() == 4) && (paramArrayOfcard[i].getValue() == 3))
			{
				this.lead = true;
				this.index3Diamond = i;
			}
			this.vecHand.addElement(paramArrayOfcard[i]);
		}
	}

	private card[] computerSelectFiveCards(boolean paramBoolean)
	{
		card[] arrayOfcard;
		if (paramBoolean == true)
		{
			arrayOfcard = computerSelectPairCombn(4);
			if (arrayOfcard == null)
				arrayOfcard = computerSelectPairCombn(3);
			if (arrayOfcard == null)
				arrayOfcard = computerSelectFlush();
			if (arrayOfcard == null)
				arrayOfcard = computerSelectStraight();
		} else
		{
			arrayOfcard = computerSelectStraight();
			if (arrayOfcard == null)
				arrayOfcard = computerSelectFlush();
			if (arrayOfcard == null)
				arrayOfcard = computerSelectPairCombn(3);
			if (arrayOfcard == null)
				arrayOfcard = computerSelectPairCombn(4);
		}
		if (arrayOfcard != null)
			return arrayOfcard;
		return null;
	}

	private card[] computerSelectFlush()
	{
		Vector localVector1 = new Vector();
		Vector localVector2 = new Vector();
		Vector localVector3 = new Vector();
		Vector localVector4 = new Vector();

		if (this.vecHand.size() >= 5)
		{
			int j;
			for (int i = 0; i < this.vecHand.size(); i++)
			{
				j = ((card) this.vecHand.elementAt(i)).getSuit();
				int k = ((card) this.vecHand.elementAt(i)).getValue();
				int m = 0;
				int n = 0;
				int i1 = 0;

				if (k == 1)
					k = 14;
				if (k == 2)
					k = 15;
				if (j == 1)
				{
					while ((m < localVector1.size()) && (i1 == 0))
					{
						n = ((card) localVector1.elementAt(m)).getValue();
						if (n == 1)
							n = 14;
						if (n == 2)
							n = 15;
						if (k <= n)
						{
							localVector1.insertElementAt((card) this.vecHand.elementAt(i), m);
							i1 = 1;
						} else
						{
							m++;
						}
					}
					if (i1 != 0)
						continue;
					localVector1.addElement((card) this.vecHand.elementAt(i));
				} else if (j == 2)
				{
					while ((m < localVector2.size()) && (i1 == 0))
					{
						n = ((card) localVector2.elementAt(m)).getValue();
						if (n == 1)
							n = 14;
						if (n == 2)
							n = 15;
						if (k <= n)
						{
							localVector2.insertElementAt((card) this.vecHand.elementAt(i), m);
							i1 = 1;
						} else
						{
							m++;
						}
					}
					if (i1 != 0)
						continue;
					localVector2.addElement((card) this.vecHand.elementAt(i));
				} else if (j == 3)
				{
					while ((m < localVector3.size()) && (i1 == 0))
					{
						n = ((card) localVector3.elementAt(m)).getValue();
						if (n == 1)
							n = 14;
						if (n == 2)
							n = 15;
						if (k <= n)
						{
							localVector3.insertElementAt((card) this.vecHand.elementAt(i), m);
							i1 = 1;
						} else
						{
							m++;
						}
					}
					if (i1 != 0)
						continue;
					localVector3.addElement((card) this.vecHand.elementAt(i));
				} else
				{
					if (j != 4)
						continue;
					while ((m < localVector4.size()) && (i1 == 0))
					{
						n = ((card) localVector4.elementAt(m)).getValue();
						if (n == 1)
							n = 14;
						if (n == 2)
							n = 15;
						if (k <= n)
						{
							localVector4.insertElementAt((card) this.vecHand.elementAt(i), m);
							i1 = 1;
						} else
						{
							m++;
						}
					}
					if (i1 != 0)
						continue;
					localVector4.addElement((card) this.vecHand.elementAt(i));
				}
			}
			card[] arrayOfcard;
			if (localVector1.size() >= 5)
			{
				arrayOfcard = new card[5];
				for (j = 0; j < 5; j++)
				{
					arrayOfcard[j] = ((card) localVector1.elementAt(j));
				}

				removeSelCards(5, arrayOfcard);
				return arrayOfcard;
			}
			if (localVector2.size() >= 5)
			{
				arrayOfcard = new card[5];
				for (j = 0; j < 5; j++)
				{
					arrayOfcard[j] = ((card) localVector2.elementAt(j));
				}
				removeSelCards(5, arrayOfcard);
				return arrayOfcard;
			}
			if (localVector3.size() >= 5)
			{
				arrayOfcard = new card[5];
				for (j = 0; j < 5; j++)
				{
					arrayOfcard[j] = ((card) localVector3.elementAt(j));
				}
				removeSelCards(5, arrayOfcard);
				return arrayOfcard;
			}
			if (localVector4.size() >= 5)
			{
				arrayOfcard = new card[5];
				for (j = 0; j < 5; j++)
				{
					arrayOfcard[j] = ((card) localVector4.elementAt(j));
				}
				removeSelCards(5, arrayOfcard);
				return arrayOfcard;
			}
			return null;
		}

		return null;
	}

	private card computerSelectOneCard(boolean paramBoolean)
	{
		Vector localVector = new Vector();
		card localcard;
		for (int i = 0; i < this.vecHand.size(); i++)
		{
			int j = ((card) this.vecHand.elementAt(i)).getValue();
			int k = ((card) this.vecHand.elementAt(i)).getSuit();
			if (j == 1)
				j = 14;
			if (j == 2)
				j = 15;

			int m = 0;
			int n = 0;
			int i1 = 0;
			while ((n < localVector.size()) && (m == 0))
			{
				i1 = ((card) localVector.elementAt(n)).getValue();
				if (i1 == 1)
					i1 = 14;
				if (i1 == 2)
					i1 = 15;
				if (((j == i1) && (k > ((card) localVector.elementAt(n)).getSuit())) || (j < i1))
				{
					localVector.insertElementAt((card) this.vecHand.elementAt(i), n);
					m = 1;
				} else
				{
					n++;
				}
			}
			if (m != 0)
				continue;
			localVector.addElement((card) this.vecHand.elementAt(i));
		}
		this.vecHand.removeAllElements();
		this.vecHand = ((Vector) localVector.clone());
		if (this.vecHand.size() != 0)
		{
			if (paramBoolean == true)
			{
				localcard = (card) this.vecHand.lastElement();
				this.vecHand.removeElementAt(this.vecHand.size() - 1);
				return localcard;
			}
			localcard = (card) this.vecHand.firstElement();
			this.vecHand.removeElementAt(0);
			return localcard;
		}

		return null;
	}

	private card[] computerSelectPairCombn(int paramInt)
	{
		int j = 0;
		int k = 0;
		if (this.vecHand.size() <= 5)
			return null;

		Vector localVector1 = new Vector();
		for (int i = 0; i < this.vecHand.size(); i++)
		{
			j = ((card) this.vecHand.elementAt(i)).getValue();
			k = ((card) this.vecHand.elementAt(i)).getSuit();
			if (j == 1)
				j = 14;
			if (j == 2)
				j = 15;

			int m = 0;
			int n = 0;
			int i1 = 0;
			while ((n < localVector1.size()) && (m == 0))
			{
				i1 = ((card) localVector1.elementAt(n)).getValue();
				if (i1 == 1)
					i1 = 14;
				if (i1 == 2)
					i1 = 15;
				if (((j == i1) && (k > ((card) localVector1.elementAt(n)).getSuit())) || (j < i1))
				{
					localVector1.insertElementAt((card) this.vecHand.elementAt(i), n);
					m = 1;
				} else
				{
					n++;
				}
			}
			if (m != 0)
				continue;
			localVector1.addElement((card) this.vecHand.elementAt(i));
		}
		this.vecHand.removeAllElements();
		this.vecHand = ((Vector) localVector1.clone());

		Vector localVector2 = new Vector();
		Vector localVector3 = new Vector();
		while (j < this.vecHand.size() - 1)
		{
			if (((card) this.vecHand.elementAt(j)).getValue() == ((card) this.vecHand.elementAt(j + 1)).getValue())
			{
				if ((j + 2 < this.vecHand.size())
						&& (((card) this.vecHand.elementAt(j + 1)).getValue() == ((card) this.vecHand.elementAt(j + 2))
								.getValue()))
				{
					localVector3.addElement((card) this.vecHand.elementAt(j));
					localVector3.addElement((card) this.vecHand.elementAt(j + 1));
					localVector3.addElement((card) this.vecHand.elementAt(j + 2));
					this.vecHand.removeElementAt(j);
					this.vecHand.removeElementAt(j);
					this.vecHand.removeElementAt(j);
					j = k;
				} else
				{
					localVector2.addElement((card) this.vecHand.elementAt(j));
					localVector2.addElement((card) this.vecHand.elementAt(j + 1));
					this.vecHand.removeElementAt(j);
					this.vecHand.removeElementAt(j);
					j = k;
				}
			} else
			{
				j++;
				k = j;
			}
		}
		card[] arrayOfcard = null;

		if (this.vecHand.size() != 0)
		{
			if ((paramInt == 2) && (localVector2.size() >= 4))
			{
				this.vecHand.addAll(localVector2);
				this.vecHand.addAll(localVector3);
				return arrayOfcard;
			}
			int i2;
			int i3;
			if ((paramInt == 3) && (localVector3.size() >= 3) && (localVector2.size() >= 2))
			{
				arrayOfcard = new card[5];
				i2 = 0;
				i3 = 0;
				for (i2 = 0; i2 < 3; i2++)
					arrayOfcard[i2] = ((card) localVector3.elementAt(i2));
				for (i2 = 3; i2 < localVector3.size(); i2++)
					this.vecHand.addElement((card) localVector3.elementAt(i2));
				for (i3 = 0; i3 < 2; i3++)
					arrayOfcard[(i3 + 3)] = ((card) localVector2.elementAt(i3));
				for (i3 = 2; i3 < localVector2.size(); i3++)
					this.vecHand.addElement((card) localVector2.elementAt(i3));
				return arrayOfcard;
			}
			if ((paramInt == 4) && (localVector2.size() >= 4))
			{
				i2 = 0;
				i3 = 0;
				int i4 = 0;
				arrayOfcard = new card[5];
				while ((i2 + 4 < localVector2.size()) && (i4 == 0))
					if (((card) localVector2.elementAt(i2)).getValue() == ((card) localVector2.elementAt(i2 + 2))
							.getValue())
					{
						arrayOfcard[0] = ((card) localVector2.elementAt(i2));
						arrayOfcard[1] = ((card) localVector2.elementAt(i2 + 1));
						arrayOfcard[2] = ((card) localVector2.elementAt(i2 + 2));
						arrayOfcard[3] = ((card) localVector2.elementAt(i2 + 3));
						arrayOfcard[4] = ((card) this.vecHand.firstElement());
						this.vecHand.removeElementAt(0);
						for (i3 = 4; i3 < localVector2.size(); i3++)
							this.vecHand.addElement((card) localVector2.elementAt(i3));
						i4 = 1;
					} else
					{
						i2 += 2;
					}
				if (i4 == 1)
					return arrayOfcard;
				this.vecHand.addAll(localVector2);
				this.vecHand.addAll(localVector3);
				return null;
			}
			this.vecHand.addAll(localVector2);
			this.vecHand.addAll(localVector3);
			return null;
		}

		this.vecHand.addAll(localVector2);
		this.vecHand.addAll(localVector3);
		return null;
	}

	private card[] computerSelectStraight()
	{
		Vector localVector = new Vector();
		int j = 0;
		int k = 0;
		int m = 0;
		int n = 0;
		int i1;
		for (int i = 0; i < this.vecHand.size(); i++)
		{
			j = ((card) this.vecHand.elementAt(i)).getValue();
			k = ((card) this.vecHand.elementAt(i)).getSuit();
			if (j == 1)
				j = 14;
			if (j == 2)
				j = 15;

			m = 0;
			n = 0;
			i1 = 0;
			while ((n < localVector.size()) && (m == 0))
			{
				i1 = ((card) localVector.elementAt(n)).getValue();
				if (i1 == 1)
					i1 = 14;
				if (i1 == 2)
					i1 = 15;
				if (((j == i1) && (k > ((card) localVector.elementAt(n)).getSuit())) || (j < i1))
				{
					localVector.insertElementAt((card) this.vecHand.elementAt(i), n);
					m = 1;
				} else
				{
					n++;
				}
			}
			if (m != 0)
				continue;
			localVector.addElement((card) this.vecHand.elementAt(i));
		}
		this.vecHand.removeAllElements();
		this.vecHand = ((Vector) localVector.clone());

		if (this.vecHand.size() >= 5)
		{
			j = 0;
			k = 0;
			m = 0;
			while ((j + 4 < this.vecHand.size()) && (m == 0))
				if ((((card) this.vecHand.elementAt(j)).getValue() == ((card) this.vecHand.elementAt(j + 1)).getValue() - 1)
						&& (((card) this.vecHand.elementAt(j + 1)).getValue() == ((card) this.vecHand.elementAt(j + 2))
								.getValue() - 1)
						&& (((card) this.vecHand.elementAt(j + 2)).getValue() == ((card) this.vecHand.elementAt(j + 3))
								.getValue() - 1)
						&& (((card) this.vecHand.elementAt(j + 3)).getValue() == ((card) this.vecHand.elementAt(j + 4))
								.getValue() - 1))
				{
					m = 1;
					k = j;
				} else
				{
					j++;
				}
			if (m == 0)
				return null;

			card[] arrayOfcard = new card[5];
			for (n = 0; n < 5; n++)
				arrayOfcard[n] = ((card) this.vecHand.elementAt(k + n));
			for (i1 = 0; i1 < 5; i1++)
				this.vecHand.removeElementAt(k);
			return arrayOfcard;
		}

		return null;
	}

	private card[] computerSelectStraightFlush()
	{
		int[] arrayOfInt1 = new int[1];
		int[] arrayOfInt2 = new int[1];
		int[] arrayOfInt3 = new int[1];

		card[] arrayOfcard = computerSelectStraight();
		if (arrayOfcard != null)
		{
			if (tableHand.flush(arrayOfcard, arrayOfInt1, arrayOfInt2, arrayOfInt3) == true)
				return arrayOfcard;
			return null;
		}
		return null;
	}

	private card[] computerSelectTwoCards(boolean paramBoolean)
	{
		Vector<card> localVector = new Vector<card>();
		int j = 0, k = 0, m = 0, n = 0;
		for (int i = 0; i < this.vecHand.size(); i++)
		{
			j = ((card) this.vecHand.elementAt(i)).getValue();
			k = ((card) this.vecHand.elementAt(i)).getSuit();
			if (j == 1)
				j = 14;
			if (j == 2)
				j = 15;

			m = 0;
			n = 0;
			int i1 = 0;
			while ((n < localVector.size()) && (m == 0))
			{
				i1 = ((card) localVector.elementAt(n)).getValue();
				if (i1 == 1)
					i1 = 14;
				if (i1 == 2)
					i1 = 15;
				if (((j == i1) && (k > ((card) localVector.elementAt(n)).getSuit())) || (j < i1))
				{
					localVector.insertElementAt((card) this.vecHand.elementAt(i), n);
					m = 1;
				} else
				{
					n++;
				}
			}
			if (m != 0)
				continue;
			localVector.addElement((card) this.vecHand.elementAt(i));
		}

		if (paramBoolean == false)
		{
			j = 0;
			while ((j < this.vecHand.size() - 1) && (n == 0))
				if (((card) this.vecHand.elementAt(j)).getValue() == ((card) this.vecHand.elementAt(j + 1)).getValue())
				{
					n = 1;
					k = j;
					m = j + 1;
				} else
				{
					j++;
				}
			if (n == 0)
			{
				return null;
			}

			card[] arrayOfcard = new card[2];
			arrayOfcard[0] = ((card) this.vecHand.elementAt(k));
			arrayOfcard[1] = ((card) this.vecHand.elementAt(m));
			this.vecHand.removeElementAt(k);
			this.vecHand.removeElementAt(k);
			return arrayOfcard;
		}

		j = this.vecHand.size() - 1;
		while ((j >= 1) && (n == 0))
			if (((card) this.vecHand.elementAt(j)).getValue() == ((card) this.vecHand.elementAt(j - 1)).getValue())
			{
				n = 1;
				k = j;
				m = j - 1;
			} else
			{
				j--;
			}
		if (n == 0)
			return null;

		card[] arrayOfcard = new card[2];
		arrayOfcard[0] = ((card) this.vecHand.elementAt(k));
		arrayOfcard[1] = ((card) this.vecHand.elementAt(m));
		this.vecHand.removeElementAt(m);
		this.vecHand.removeElementAt(m);
		return arrayOfcard;
	}

	public card[] computerSelectedCard(boolean[] paramArrayOfBoolean, boolean paramBoolean, int paramInt)
	{
		card[] arrayOfcard;
		if (paramInt == 1)
		{
			arrayOfcard = new card[1];
			arrayOfcard[0] = computerSelectOneCard(paramBoolean);
			if (arrayOfcard[0] != null)
			{
				paramArrayOfBoolean[0] = true;
				return arrayOfcard;
			}
			paramArrayOfBoolean[0] = false;
			return null;
		}
		if (paramInt == 2)
		{
			arrayOfcard = new card[2];
			arrayOfcard = computerSelectTwoCards(paramBoolean);
			if (arrayOfcard != null)
			{
				paramArrayOfBoolean[0] = true;
				return arrayOfcard;
			}
			arrayOfcard = new card[1];
			arrayOfcard[0] = computerSelectOneCard(paramBoolean);
			if (arrayOfcard[0] != null)
			{
				paramArrayOfBoolean[0] = true;
				return arrayOfcard;
			}
			paramArrayOfBoolean[0] = false;
			return null;
		}
		if (paramInt == 5)
		{
			arrayOfcard = new card[5];
			arrayOfcard = computerSelectFiveCards(paramBoolean);
			if (arrayOfcard != null)
			{
				paramArrayOfBoolean[0] = true;
				return arrayOfcard;
			}
			arrayOfcard = new card[2];
			arrayOfcard = computerSelectTwoCards(paramBoolean);
			if (arrayOfcard != null)
			{
				paramArrayOfBoolean[0] = true;
				return arrayOfcard;
			}
			arrayOfcard = new card[1];
			arrayOfcard[0] = computerSelectOneCard(paramBoolean);
			if (arrayOfcard[0] != null)
			{
				paramArrayOfBoolean[0] = true;
				return arrayOfcard;
			}
			paramArrayOfBoolean[0] = false;
			return null;
		}

		paramArrayOfBoolean[0] = false;
		return null;
	}

	public card[] computerSelectedCard(boolean[] paramArrayOfBoolean, boolean paramBoolean, tableHand paramtableHand)
	{
		card[] arrayOfcard1 = paramtableHand.getCards();
		Vector localVector = new Vector();
		int i;
		int j;
		int k;
		int m;
		int n;
		int i1;
		int i2;
		int i3;
		card[] arrayOfcard2;
		if (arrayOfcard1.length == 1)
		{
			i = arrayOfcard1[0].getSuit();
			j = arrayOfcard1[0].getValue();
			if ((j != 2) || (i != 1))
			{
				if (j == 1)
					j = 14;
				if (j == 2)
					j = 15;
				for (k = 0; k < this.vecHand.size(); k++)
				{
					m = ((card) this.vecHand.elementAt(k)).getValue();
					n = ((card) this.vecHand.elementAt(k)).getSuit();
					if (m == 1)
						m = 14;
					if (m == 2)
						m = 15;
					if (((m == j) && (n < i)) || (m > j))
					{
						i1 = 0;
						i2 = 0;
						i3 = 0;
						while ((i2 < localVector.size()) && (i1 == 0))
						{
							i3 = ((card) localVector.elementAt(i2)).getValue();
							if (i3 == 1)
								i3 = 14;
							if (i3 == 2)
								i3 = 15;
							if (((m == i3) && (n > ((card) localVector.elementAt(i2)).getSuit())) || (m < i3))
							{
								localVector.insertElementAt((card) this.vecHand.elementAt(k), i2);
								i1 = 1;
							} else
							{
								i2++;
							}
						}
						if (i1 != 0)
							continue;
						localVector.addElement((card) this.vecHand.elementAt(k));
					}
				}
				if (localVector.size() != 0)
				{
					arrayOfcard2 = new card[1];
					arrayOfcard2[0] = (paramBoolean == true ? (card) localVector.lastElement() : (card) localVector
							.firstElement());
					removeSelCards(1, arrayOfcard2);
					return arrayOfcard2;
				}
				paramArrayOfBoolean[0] = false;
				return null;
			}
			paramArrayOfBoolean[0] = false;
			return null;
		}
		if (arrayOfcard1.length == 2)
		{
			i = arrayOfcard1[0].getSuit() < arrayOfcard1[1].getSuit() ? arrayOfcard1[0].getSuit() : arrayOfcard1[1]
					.getSuit();
			j = arrayOfcard1[0].getValue();

			if (((arrayOfcard1[0].getSuit() != 1) || (arrayOfcard1[0].getValue() != 2))
					&& ((arrayOfcard1[0].getSuit() != 1) || (arrayOfcard1[0].getValue() != 2)))
			{
				if (j == 1)
					j = 14;
				if (j == 2)
					j = 15;
				for (k = 0; k < this.vecHand.size(); k++)
				{
					m = ((card) this.vecHand.elementAt(k)).getValue();
					n = ((card) this.vecHand.elementAt(k)).getSuit();
					if (m == 1)
						m = 14;
					if (m == 2)
						m = 15;
					if (((m == j) && (n < i)) || (m > j))
					{
						i1 = 0;
						i2 = 0;
						i3 = 0;
						while ((i2 < localVector.size()) && (i1 == 0))
						{
							i3 = ((card) localVector.elementAt(i2)).getValue();
							if (i3 == 1)
								i3 = 14;
							if (i3 == 2)
								i3 = 15;
							if (((m == i3) && (n > ((card) localVector.elementAt(i2)).getSuit())) || (m < i3))
							{
								localVector.insertElementAt((card) this.vecHand.elementAt(k), i2);
								i1 = 1;
							} else
							{
								i2++;
							}
						}
						if (i1 != 0)
							continue;
						localVector.addElement((card) this.vecHand.elementAt(k));
					}
				}

				if (localVector.size() >= 2)
				{
					n = 0;
					i1 = 0;
					i2 = 0;
					if (paramBoolean == false)
					{
						m = 0;
						while ((m < localVector.size() - 1) && (i2 == 0))
							if (((card) localVector.elementAt(m)).getValue() == ((card) localVector.elementAt(m + 1))
									.getValue())
							{
								i2 = 1;
								n = m;
								i1 = m + 1;
							} else
							{
								m++;
							}
						if (i2 == 0)
						{
							paramArrayOfBoolean[0] = false;
							return null;
						}
						arrayOfcard2 = new card[2];
						arrayOfcard2[0] = ((card) localVector.elementAt(n));
						arrayOfcard2[1] = ((card) localVector.elementAt(i1));

						removeSelCards(2, arrayOfcard2);
						return arrayOfcard2;
					}

					m = localVector.size() - 1;
					while ((m >= 1) && (i2 == 0))
						if (((card) localVector.elementAt(m)).getValue() == ((card) localVector.elementAt(m - 1))
								.getValue())
						{
							i2 = 1;
							n = m;
							i1 = m - 1;
						} else
						{
							m--;
						}
					if (i2 == 0)
					{
						paramArrayOfBoolean[0] = false;
						return null;
					}
					arrayOfcard2 = new card[2];
					arrayOfcard2[0] = ((card) localVector.elementAt(n));
					arrayOfcard2[1] = ((card) localVector.elementAt(i1));

					removeSelCards(2, arrayOfcard2);
					return arrayOfcard2;
				}

				paramArrayOfBoolean[0] = false;
				return null;
			}
			paramArrayOfBoolean[0] = false;
			return null;
		}
		if (arrayOfcard1.length == 5)
		{
			i = paramtableHand.getCombination();
			int[] arrayOfInt1;
			int[] arrayOfInt2;
			int[] arrayOfInt3;
			int[] arrayOfInt4;
			boolean bool1;
			if (i == 2)
			{
				arrayOfInt1 = new int[1];
				arrayOfInt2 = new int[1];
				arrayOfInt3 = new int[1];
				arrayOfInt4 = new int[1];
				bool1 = tableHand.pairCombn(arrayOfcard1, arrayOfInt1, arrayOfInt3, arrayOfInt4);
				arrayOfcard2 = computerSelectPairCombn(2);
				if (arrayOfcard2 != null)
				{
					bool1 = tableHand.pairCombn(arrayOfcard2, arrayOfInt2, arrayOfInt3, arrayOfInt4);
					if (arrayOfInt1[0] == 1)
						arrayOfInt1[0] = 14;
					if (arrayOfInt1[0] == 2)
						arrayOfInt1[0] = 15;
					if (arrayOfInt2[0] == 1)
						arrayOfInt2[0] = 14;
					if (arrayOfInt2[0] == 2)
						arrayOfInt2[0] = 15;
					if (arrayOfInt2[0] < arrayOfInt1[0])
						arrayOfcard2 = null;
				}
				if (arrayOfcard2 == null)
					arrayOfcard2 = computerSelectStraight();
				if (arrayOfcard2 == null)
					arrayOfcard2 = computerSelectFlush();
				if (arrayOfcard2 == null)
					arrayOfcard2 = computerSelectPairCombn(3);
				if (arrayOfcard2 == null)
					arrayOfcard2 = computerSelectPairCombn(4);
			} else if (i == 3)
			{
				arrayOfInt1 = new int[1];
				arrayOfInt2 = new int[1];
				arrayOfInt3 = new int[1];
				arrayOfInt4 = new int[1];
				bool1 = tableHand.straight(arrayOfcard1, arrayOfInt1, arrayOfInt3, arrayOfInt4);
				arrayOfcard2 = computerSelectStraight();
				if (arrayOfcard2 != null)
				{
					bool1 = tableHand.straight(arrayOfcard2, arrayOfInt2, arrayOfInt3, arrayOfInt4);
					if (arrayOfInt1[0] == 1)
						arrayOfInt1[0] = 14;
					if (arrayOfInt1[0] == 2)
						arrayOfInt1[0] = 15;
					if (arrayOfInt2[0] == 1)
						arrayOfInt2[0] = 14;
					if (arrayOfInt2[0] == 2)
						arrayOfInt2[0] = 15;
					if (arrayOfInt2[0] < arrayOfInt1[0])
						arrayOfcard2 = null;
				}
				if (arrayOfcard2 == null)
					arrayOfcard2 = computerSelectFlush();
				if (arrayOfcard2 == null)
					arrayOfcard2 = computerSelectPairCombn(3);
				if (arrayOfcard2 == null)
					arrayOfcard2 = computerSelectPairCombn(4);
			} else if (i == 4)
			{
				arrayOfInt1 = new int[1];
				arrayOfInt2 = new int[1];
				arrayOfInt3 = new int[1];
				arrayOfInt4 = new int[1];
				bool1 = tableHand.flush(arrayOfcard1, arrayOfInt1, arrayOfInt3, arrayOfInt4);
				arrayOfcard2 = computerSelectFlush();
				if (arrayOfcard2 != null)
				{
					bool1 = tableHand.flush(arrayOfcard2, arrayOfInt2, arrayOfInt3, arrayOfInt4);
					if (arrayOfInt1[0] == 1)
						arrayOfInt1[0] = 14;
					if (arrayOfInt1[0] == 2)
						arrayOfInt1[0] = 15;
					if (arrayOfInt2[0] == 1)
						arrayOfInt2[0] = 14;
					if (arrayOfInt2[0] == 2)
						arrayOfInt2[0] = 15;
					if (arrayOfInt2[0] < arrayOfInt1[0])
						arrayOfcard2 = null;
				}
				if (arrayOfcard2 == null)
					arrayOfcard2 = computerSelectPairCombn(3);
				if (arrayOfcard2 == null)
					arrayOfcard2 = computerSelectPairCombn(4);
			} else if (i == 5)
			{
				arrayOfInt1 = new int[1];
				arrayOfInt2 = new int[1];
				arrayOfInt3 = new int[1];
				arrayOfInt4 = new int[1];
				bool1 = tableHand.pairCombn(arrayOfcard1, arrayOfInt1, arrayOfInt3, arrayOfInt4);
				arrayOfcard2 = computerSelectPairCombn(3);
				if (arrayOfcard2 != null)
				{
					bool1 = tableHand.pairCombn(arrayOfcard2, arrayOfInt2, arrayOfInt3, arrayOfInt4);
					if (arrayOfInt1[0] == 1)
						arrayOfInt1[0] = 14;
					if (arrayOfInt1[0] == 2)
						arrayOfInt1[0] = 15;
					if (arrayOfInt2[0] == 1)
						arrayOfInt2[0] = 14;
					if (arrayOfInt2[0] == 2)
						arrayOfInt2[0] = 15;
					if (arrayOfInt2[0] < arrayOfInt1[0])
						arrayOfcard2 = null;
				}
				if (arrayOfcard2 == null)
					arrayOfcard2 = computerSelectPairCombn(4);
			} else if (i == 6)
			{
				arrayOfInt1 = new int[1];
				arrayOfInt2 = new int[1];
				arrayOfInt3 = new int[1];
				arrayOfInt4 = new int[1];
				bool1 = tableHand.pairCombn(arrayOfcard1, arrayOfInt1, arrayOfInt3, arrayOfInt4);
				arrayOfcard2 = computerSelectPairCombn(4);
				if (arrayOfcard2 != null)
				{
					bool1 = tableHand.pairCombn(arrayOfcard2, arrayOfInt2, arrayOfInt3, arrayOfInt4);
					if (arrayOfInt1[0] == 1)
						arrayOfInt1[0] = 14;
					if (arrayOfInt1[0] == 2)
						arrayOfInt1[0] = 15;
					if (arrayOfInt2[0] == 1)
						arrayOfInt2[0] = 14;
					if (arrayOfInt2[0] == 2)
						arrayOfInt2[0] = 15;
					if (arrayOfInt2[0] < arrayOfInt1[0])
						arrayOfcard2 = null;
				}
				if (arrayOfcard2 == null)
					arrayOfcard2 = computerSelectStraightFlush();
			} else if (i == 7)
			{
				arrayOfInt1 = new int[1];
				arrayOfInt2 = new int[1];
				arrayOfInt3 = new int[1];
				arrayOfInt4 = new int[1];
				int[] arrayOfInt5 = new int[1];
				boolean bool2 = tableHand.straightFlush(arrayOfcard1, arrayOfInt1, arrayOfInt4, arrayOfInt5);
				arrayOfcard2 = computerSelectStraightFlush();
				if (arrayOfcard2 != null)
				{
					bool2 = tableHand.straightFlush(arrayOfcard2, arrayOfInt2, arrayOfInt3, arrayOfInt5);
					if (arrayOfInt3[0] == arrayOfInt4[0])
					{
						if (arrayOfInt1[0] == 1)
							arrayOfInt1[0] = 14;
						if (arrayOfInt1[0] == 2)
							arrayOfInt1[0] = 15;
						if (arrayOfInt2[0] == 1)
							arrayOfInt2[0] = 14;
						if (arrayOfInt2[0] == 2)
							arrayOfInt2[0] = 15;

						return arrayOfInt2[0] > arrayOfInt1[0] ? arrayOfcard2 : null;
					}
				}
			} else
			{
				return null;
			}
			if (arrayOfcard2 == null)
				return null;
			return arrayOfcard2;
		}

		paramArrayOfBoolean[0] = false;
		return null;
	}

	public card[] get3Diamond()
	{
		card[] arrayOfcard = new card[1];
		arrayOfcard[0] = ((card) this.vecHand.elementAt(this.index3Diamond));
		this.vecHand.removeElementAt(this.index3Diamond);
		this.lead = false;
		return arrayOfcard;
	}

	public card[] getHand()
	{
		card[] arrayOfcard = new card[this.vecHand.size()];
		for (int i = 0; i < this.vecHand.size(); i++)
			arrayOfcard[i] = ((card) this.vecHand.elementAt(i));
		return arrayOfcard;
	}

	public int getHandSize()
	{
		return this.vecHand.size();
	}

	public boolean getLead()
	{
		return this.lead;
	}

	public card[] playerSelected3DiamondCard(card[] paramArrayOfcard, boolean[] paramArrayOfBoolean)
	{
		int i = 0;
		int j = 0;

		while ((i < paramArrayOfcard.length) && (j == 0))
		{
			if ((paramArrayOfcard[i].getValue() == 3) && (paramArrayOfcard[i].getSuit() == 4))
				j = 1;
			else
				i++;
		}
		if (j == 0)
			return null;
		return playerSelectedCard(paramArrayOfcard, paramArrayOfBoolean);
	}

	public card[] playerSelectedCard(card[] paramArrayOfcard, boolean[] paramArrayOfBoolean)
	{
		int i = paramArrayOfcard.length;

		card[] arrayOfcard = paramArrayOfcard;

		if (i == 1)
		{
			for (int j = 0; j < this.vecHand.size(); j++)
				removeSelCards(1, arrayOfcard);
			paramArrayOfBoolean[0] = true;
			return arrayOfcard;
		}
		if (i == 2)
		{
			if (arrayOfcard[0].getValue() == arrayOfcard[1].getValue())
			{
				removeSelCards(2, arrayOfcard);
				paramArrayOfBoolean[0] = true;
				return arrayOfcard;
			}
			paramArrayOfBoolean[0] = false;
			return null;
		}
		if (i == 5)
		{
			int[] arrayOfInt1 = new int[1];
			int[] arrayOfInt2 = new int[1];
			int[] arrayOfInt3 = new int[1];
			if ((tableHand.pairCombn(paramArrayOfcard, arrayOfInt1, arrayOfInt2, arrayOfInt3) == true)
					&& (arrayOfInt3[0] == 2))
			{
				paramArrayOfBoolean[0] = false;
				return null;
			}

			if ((tableHand.straight(paramArrayOfcard, arrayOfInt1, arrayOfInt2, arrayOfInt3) == true)
					|| (tableHand.flush(paramArrayOfcard, arrayOfInt1, arrayOfInt2, arrayOfInt3) == true)
					|| (tableHand.pairCombn(paramArrayOfcard, arrayOfInt1, arrayOfInt2, arrayOfInt3) == true))
			{
				removeSelCards(5, arrayOfcard);
				paramArrayOfBoolean[0] = true;
				return arrayOfcard;
			}
			paramArrayOfBoolean[0] = false;
			return null;
		}
		paramArrayOfBoolean[0] = false;
		return null;
	}

	public card[] playerSelectedCard(card[] paramArrayOfcard, boolean[] paramArrayOfBoolean, tableHand paramtableHand)
	{
		int handSize = paramArrayOfcard.length;
		int tableHandSize = paramtableHand.getNoCard();
		card[] arrayOfcard1 = paramtableHand.getCards();

		card[] arrayOfcard2 = paramArrayOfcard;

		if (handSize == tableHandSize)
		{
			int k;
			int n;
			int m;
			int i1;
			if (handSize == 1)
			{
				k = arrayOfcard1[0].getValue();
				if (k == 1)
					k = 14;
				if (k == 2)
					k = 15;
				n = arrayOfcard1[0].getSuit();
				m = arrayOfcard2[0].getValue();
				if (m == 1)
					m = 14;
				if (m == 2)
					m = 15;
				i1 = arrayOfcard2[0].getSuit();
				if (((m == k) && (i1 < n)) || (m > k))
				{
					removeSelCards(1, arrayOfcard2);
					paramArrayOfBoolean[0] = true;
					return arrayOfcard2;
				}
				paramArrayOfBoolean[0] = false;
				return null;
			}
			if (handSize == 2)
			{
				k = arrayOfcard1[0].getValue();
				if (k == 1)
					k = 14;
				if (k == 2)
					k = 15;
				n = arrayOfcard1[0].getSuit() < arrayOfcard1[1].getSuit() ? arrayOfcard1[0].getSuit() : arrayOfcard1[1]
						.getSuit();
				m = arrayOfcard2[0].getValue();
				if (m == 1)
					m = 14;
				if (m == 2)
					m = 15;
				i1 = arrayOfcard2[0].getSuit() < arrayOfcard2[1].getSuit() ? arrayOfcard2[0].getSuit()
						: arrayOfcard2[1].getSuit();
				if (((m == k) && (i1 < n)) || (m > k))
				{
					removeSelCards(2, arrayOfcard2);
					paramArrayOfBoolean[0] = true;
					return arrayOfcard2;
				}
				paramArrayOfBoolean[0] = false;
				return null;
			}
			if (handSize == 5)
			{
				int[] arrayOfInt1 = new int[1];
				int[] arrayOfInt2 = new int[1];
				int[] arrayOfInt3 = new int[1];
				int i2 = paramtableHand.getCombination();
				int i3 = 1;

				int[] arrayOfInt4 = new int[1];
				int[] arrayOfInt5 = new int[1];
				int[] arrayOfInt6 = new int[1];
				int i4 = 0;
				if (tableHand.straightFlush(arrayOfcard2, arrayOfInt4, arrayOfInt5, arrayOfInt6) == true)
					i4 = arrayOfInt6[0];
				else if (tableHand.straight(arrayOfcard2, arrayOfInt4, arrayOfInt5, arrayOfInt6) == true)
					i4 = arrayOfInt6[0];
				else if (tableHand.flush(arrayOfcard2, arrayOfInt4, arrayOfInt5, arrayOfInt6) == true)
					i4 = arrayOfInt6[0];
				else if (tableHand.pairCombn(arrayOfcard2, arrayOfInt4, arrayOfInt5, arrayOfInt6) == true)
					i4 = arrayOfInt6[0];
				else
				{
					i3 = 0;
				}
				if (i3 == 1)
				{
					if ((i2 == 2) && (i4 == 2))
						i3 = 0;
					boolean bool;
					if (i2 == 3)
						if (i4 == 3)
						{
							bool = tableHand.straight(arrayOfcard1, arrayOfInt1, arrayOfInt2, arrayOfInt3);
							if (arrayOfInt1[0] == 1)
								arrayOfInt1[0] = 14;
							if (arrayOfInt1[0] == 2)
								arrayOfInt1[0] = 15;
							if (arrayOfInt4[0] == 1)
								arrayOfInt4[0] = 14;
							if (arrayOfInt4[0] == 2)
								arrayOfInt4[0] = 15;
							i3 = arrayOfInt4[0] > arrayOfInt1[0] ? 1 : 0;
							if (arrayOfInt4[0] == arrayOfInt1[0])
								i3 = arrayOfInt5[0] < arrayOfInt2[0] ? 1 : 0;
						} else if (i4 == 2)
						{
							i3 = 0;
						}
					if (i2 == 4)
						if (i4 == 4)
						{
							bool = tableHand.flush(arrayOfcard1, arrayOfInt1, arrayOfInt2, arrayOfInt3);
							i3 = arrayOfInt5[0] < arrayOfInt2[0] ? 1 : 0;
							if (arrayOfInt5[0] == arrayOfInt2[0])
							{
								if (arrayOfInt1[0] == 1)
									arrayOfInt1[0] = 14;
								if (arrayOfInt1[0] == 2)
									arrayOfInt1[0] = 15;
								if (arrayOfInt4[0] == 1)
									arrayOfInt4[0] = 14;
								if (arrayOfInt4[0] == 2)
									arrayOfInt4[0] = 15;
								i3 = arrayOfInt4[0] > arrayOfInt1[0] ? 1 : 0;
							}
						} else if (i4 == 3)
						{
							i3 = 0;
						} else if (i4 == 2)
						{
							i3 = 0;
						}
					if (i2 == 5)
						if (i4 == 5)
						{
							bool = tableHand.pairCombn(arrayOfcard1, arrayOfInt1, arrayOfInt2, arrayOfInt3);
							if (arrayOfInt1[0] == 1)
								arrayOfInt1[0] = 14;
							if (arrayOfInt1[0] == 2)
								arrayOfInt1[0] = 15;
							if (arrayOfInt4[0] == 1)
								arrayOfInt4[0] = 14;
							if (arrayOfInt4[0] == 2)
								arrayOfInt4[0] = 15;
							i3 = arrayOfInt4[0] > arrayOfInt1[0] ? 1 : 0;
							if (arrayOfInt4[0] == arrayOfInt1[0])
								i3 = arrayOfInt5[0] < arrayOfInt2[0] ? 1 : 0;
						} else if (i4 == 4)
						{
							i3 = 0;
						} else if (i4 == 3)
						{
							i3 = 0;
						} else if (i4 == 2)
						{
							i3 = 0;
						}
					if (i2 == 6)
						if (i4 == 6)
						{
							bool = tableHand.pairCombn(arrayOfcard1, arrayOfInt1, arrayOfInt2, arrayOfInt3);
							if (arrayOfInt1[0] == 1)
								arrayOfInt1[0] = 14;
							if (arrayOfInt1[0] == 2)
								arrayOfInt1[0] = 15;
							if (arrayOfInt4[0] == 1)
								arrayOfInt4[0] = 14;
							if (arrayOfInt4[0] == 2)
								arrayOfInt4[0] = 15;
							i3 = arrayOfInt4[0] > arrayOfInt1[0] ? 1 : 0;
							if (arrayOfInt4[0] == arrayOfInt1[0])
								i3 = arrayOfInt5[0] < arrayOfInt2[0] ? 1 : 0;
						} else if (i4 == 5)
						{
							i3 = 0;
						} else if (i4 == 4)
						{
							i3 = 0;
						} else if (i4 == 3)
						{
							i3 = 0;
						} else if (i4 == 2)
						{
							i3 = 0;
						}
					if (i2 == 7)
					{
						if (i4 == 7)
						{
							bool = tableHand.straightFlush(arrayOfcard1, arrayOfInt1, arrayOfInt2, arrayOfInt3);
							i3 = arrayOfInt5[0] < arrayOfInt2[0] ? 1 : 0;
							if (arrayOfInt5[0] == arrayOfInt2[0])
							{
								if (arrayOfInt1[0] == 1)
									arrayOfInt1[0] = 14;
								if (arrayOfInt1[0] == 2)
									arrayOfInt1[0] = 15;
								if (arrayOfInt4[0] == 1)
									arrayOfInt4[0] = 14;
								if (arrayOfInt4[0] == 2)
									arrayOfInt4[0] = 15;
								i3 = arrayOfInt4[0] > arrayOfInt1[0] ? 1 : 0;
							}
						} else
						{
							i3 = 0;
						}
					}
					if (i3 == 1)
					{
						removeSelCards(5, arrayOfcard2);
						return arrayOfcard2;
					}
					return null;
				}
				return null;
			}
			paramArrayOfBoolean[0] = false;
			return null;
		}
		paramArrayOfBoolean[0] = false;
		return null;
	}

	private void removeSelCards(int paramInt, card[] paramArrayOfcard)
	{
		for (int i = 0; i < paramInt; i++)
			for (int j = 0; j < this.vecHand.size(); j++)
			{
				if ((((card) this.vecHand.elementAt(j)).getValue() != paramArrayOfcard[i].getValue())
						|| (((card) this.vecHand.elementAt(j)).getSuit() != paramArrayOfcard[i].getSuit()))
					continue;
				this.vecHand.removeElementAt(j);
				break;
			}
	}

	public void setLead(boolean paramBoolean)
	{
		this.lead = paramBoolean;
	}
}