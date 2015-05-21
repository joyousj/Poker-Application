package poker;

import java.util.Arrays;
import java.util.Scanner;



public class Game 
{

  private final int HAND_SIZE = 5;
	private int again = 1;
	
	// instantiate Deck and Players
	Scanner scan = new Scanner(System.in);
	Deck deck = new Deck();
	//Deck deck2 = new Deck();
	Players player = new Players("Player");
	Players dealer = new Players("Dealer");
	Card[] hand;
	//Card[] hand2;
	
	
	
	// plays the game
	public void play()
	{
		while (again == 1)
		{
			// fill deck
			deck.fillDeck();
			
			// shuffle
			deck.shuffle();
			
			
			// player draws
			hand = player.draw(deck);
			//hand2 = dealer.draw(deck);
			
			// sort hand		
			Arrays.sort(hand);
			//Arrays.sort(hand2);
			
			// player redraws
			this.checkHand();
			hand = this.redraw();//swapping old card for new one if desired 
			//hand2 = this.redraw();//let's me redraw but does not print dealer's hand
			
			// display hand again
			//this.makeHand(); //<--- TA ! un-comment this and change makeHand()
			System.out.println();
			this.checkHand();
			
			// sort hand		
			Arrays.sort(hand);
			
			// evaluate the hand
			System.out.println();
			this.evaluate();
		
			// play again?
			this.again();
		}
		System.out.println("Thanks for playing! =]");
	}
	
	// makes a hand (for TA; testing purposes)
	/*public void makeHand()
	{
		hand[0].rank = 1;
		hand[1].rank = 2;
		hand[2].rank = 3;
		hand[3].rank = 4;
		hand[4].rank = 5;
		
		hand[0].suit = 1;
		hand[1].suit = 2;
		hand[2].suit = 3;
		hand[3].suit = 4;
		hand[4].suit = 1;
		
		
	}*/
	
	// tells player cards in hand
	public void checkHand()
	{
		for (int handCounter = 0; handCounter < HAND_SIZE; handCounter++)
		{
			this.display(hand[handCounter]);
		}
	}
	
	// asks if player wants to redraw
	public Card[] redraw()
	{
		for (int counter = 0; counter < 5; counter++)
		{
			System.out.print("Redraw card " + (counter + 1) + "?" +
					" (1 for yes, 0 for no)");
			int answer = scan.nextInt();
			if (answer == 1)
			{
				hand[counter] = player.redraw(counter, deck);
			}
		}
		deck.refreshDeckPosition();
		return hand;
	}
	
	
	// evaluates the hand
	public void evaluate()
	{
		if (this.royalFlush() == 1)
		{
			System.out.println("You have a royal flush!");
		}
		else if (this.straightFlush() == 1)
		{
			System.out.println("You have a straight flush!");
		}
		else if (this.fourOfaKind() == 1)
		{
			System.out.println("You have four of a kind!");
		}
		else if (this.fullHouse() == 1)
		{
			System.out.println("You have a full house!");
		}
		else if (this.flush() == 1)
		{
			System.out.println("You have a flush!");
		}
		else if (this.straight() == 1)
		{
			System.out.println("You have a straight!");
		}
		else if (this.triple() == 1)
		{
			System.out.println("You have a triple!");
		}
		else if (this.twoPairs() == 1)
		{
			System.out.println("You have two pairs!");
		}
		else if (this.pair() == 1)
		{
			System.out.println("You have a pair!");
		}
		else
		{
			int highCard = this.highCard();
			System.out.println("Your highest card is " + highCard);
		}
	}
	
	// checks for a royal flush
	public int royalFlush()
	{
		if (hand[0].rank == 1 && hand[1].rank == 10 && hand[2].rank == 11 &&
				hand[3].rank == 12 && hand[4].rank == 13)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	
	// checks for a straight flush
	public int straightFlush()
	{
		for (int counter = 1; counter < 5; counter++)
		{
			if (hand[0].suit != hand[counter].suit)
			{
				return 0;
			}
		}
		for (int counter2 = 1; counter2 < 5; counter2++)
		{
			if (hand[counter2 - 1].rank != (hand[counter2].rank - 1))
			{
				return 0;
			}
				
		}
		return 1;
		
	}
	
	// checks for four of a kind
	public int fourOfaKind()
	{
		if (hand[0].rank != hand[3].rank && hand[1].rank != hand[4].rank)
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}
	
	// checks for full house
	public int fullHouse()
	{
		int comparison = 0;
		for (int counter = 1; counter < 5; counter++)
		{
			if (hand[counter - 1].rank == hand[counter].rank)
			{
				comparison++;
			}
		}
		if (comparison == 3)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	
	// checks for flush
	public int flush()
	{
		for (int counter = 1; counter < 5; counter++)
		{
			if (hand[0].suit != hand[counter].suit)
			{
				return 0;
			}
		}
		return 1;
	}
	
	// check for straight
	public int straight()
	{
		for (int counter2 = 1; counter2 < 5; counter2++)
		{
			if (hand[counter2 - 1].rank != (hand[counter2].rank - 1))
			{
				return 0;
			}
				
		}
		return 1;
	}
	
	// checks for triple
	public int triple()
	{
		if (hand[0].rank == hand[2].rank || hand[2].rank == hand[4].rank)
		{
			return 1;
		}
		return 0;
	}
	
	// checks for two pairs
	public int twoPairs()
	{
		int check = 0;
		for(int counter = 1; counter < 5; counter++)
		{
			if (hand[counter - 1].rank == hand[counter].rank)
			{
				check++;
			}
		}
		if (check == 2)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	
	// check for pair
	public int pair()
	{
		int check = 0;
		for(int counter = 1; counter < 5; counter++)
		{
			if (hand[counter - 1].rank == hand[counter].rank)
			{
				check++;
			}
		}
		if (check == 1)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	
	// find highest card
	public int highCard()
	{
		int highCard = 0;
		for (int counter = 0; counter < 5; counter++)
		{
			if (hand[counter].rank > highCard)
			{
				highCard = hand[counter].rank;
			}
		}
		return highCard;
	}
	
	// asks user if they want to play again
	public void again()
	{
		System.out.print("Play again? (1 for yes, 0 for no)");
		again = scan.nextInt();
	}
	
	// generates string for each card in hand
	public void display(Card card)
	{
		if (card.rank == 1)
		{
			System.out.print("A");
		}
		if (card.rank == 2)
		{
			System.out.print("2");
		}
		if (card.rank == 3)
		{
			System.out.print("3");
		}
		if (card.rank == 4)
		{
			System.out.print("4");
		}
		if (card.rank == 5)
		{
			System.out.print("5");
		}
		if (card.rank == 6)
		{
			System.out.print("6");
		}
		if (card.rank == 7)
		{
			System.out.print("7");
		}
		if (card.rank == 8)
		{
			System.out.print("8");
		}
		if (card.rank == 9)
		{
			System.out.print("9");
		}
		if (card.rank == 10)
		{
			System.out.print("10");
		}
		if (card.rank == 11)
		{
			System.out.print("J");
		}
		if (card.rank == 12)
		{
			System.out.print("Q");
		}
		if (card.rank == 13)
		{
			System.out.print("K");
		}
		if (card.suit == 1)
		{
			System.out.print("S");
			System.out.println();
		}
		if (card.suit == 2)
		{
			System.out.print("H");
			System.out.println();
		}
		if (card.suit == 3)
		{
			System.out.print("D");
			System.out.println();
		}
		if (card.suit == 4)
		{
			System.out.print("C");
			System.out.println();
		}
		
	}
}
