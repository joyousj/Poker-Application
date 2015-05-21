package poker;

public class Card implements Comparable<Card>
{
  // I.V.s are suit and rank
	public int suit;
	public int rank;
	
	@Override
	public int compareTo(Card o) 
	{
	     if (this.rank == (o.rank))
	            return 0;
	     else if ((this.rank) > (o.rank))
	            return 1;
	     else
	           return -1;
	}
	

}
/*public class Card {
	private String face;//face of card ("Ace"..)
	private String suit;//suit of card ("Hearts", "Diamonds"..)

	//two argument constructor initializes card's face and suit
	public Card(String cardFace, String cardSuit) {
		face = cardFace;
		suit = cardSuit;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public String toString() {
		return face + " of " + suit;

	}
}
*/
