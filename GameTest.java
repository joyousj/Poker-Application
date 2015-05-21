package poker;

import java.util.Scanner;

public class GameTest 
{

  public static void main(String[] args) 
	{
		
		// make game
		Game game = new Game();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("What is your name? ");
		String s;
		s = sc.next();
		
		
		new Players(s);
		new Players("Dealer");
		System.out.println(s + "'s hand: ");
		//System.out.println("Dealer's hand: ");
				
		// play game
		game.play();
		
		sc.close();

	}

}
