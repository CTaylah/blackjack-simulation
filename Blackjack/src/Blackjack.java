import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("How many games would you like to simulate?");
		int rounds = input.nextInt();
		
		int ties = 0;
		int wins = 0;
		int losses = 0;
		int blackjack = 0;
		int playerBusts = 0;
		int record = 0;
		int counter = 0;
		int recordL = 0;
		int counterL = 0;
		for(int n = 1; n <= rounds; n++) {
			System.out.println("Game #" + n);
			String winner = simulation();
			if (winner == "PlayerBlackjack") {
				wins++;
				blackjack++;
			}
			else if (winner == "Dealer-PlayerBust") {
				losses++;
				playerBusts++;
			}
			else if (winner == "Player") wins++;
			else if(winner == "Dealer") losses++;
			else if(winner == "Tie") ties++;
			else if(winner == "THERE'S BEEN A DISASTOR") System.out.println("There was an issue...");
			
			if(winner == "PlayerBlackjack" || winner == "Player") {
				counter++;
				if(checkRecord(counter, record)) {
					record = counter;
				}
			}
			else {
				counter = 0;
			}
			
			if(winner == "Dealer") {
				counterL++;
				if(checkRecord(counterL, recordL)) {
					recordL = counterL;
				}
			}
			
			else counterL = 0;
		}
		
		outputStats(rounds, ties,wins,losses, blackjack, playerBusts, record, recordL);
	}
	
	
	public static String simulation() {
		int sum = 0;
		String winner = "FRICK ITS NOT WORKING";
		String card1, card2, card3 = "", card4 = "", card5 = "";
		int cardV1, cardV2, cardV3 = 0, cardV4 = 0, cardV5 = 0;
		
		boolean playerBust = false;
		boolean playerBlackjack;
		boolean dealerBlackjack;
		
		cardV1 = getHand();
		cardV2 = getHand();
		card1 = assignName(cardV1);
		card2 = assignName(cardV2);
		cardV1 = adjustValue(card1, cardV1);
		cardV2 = adjustValue(card2, cardV2);
		
		playerBlackjack = isBlackjack(cardV1, cardV2);
		
		sum = cardV1 + cardV2;
		if(sum == 22) cardV1 = 1;
		sum = cardV1 + cardV2;
		
		
		int cardCount = 2;
		while(sum < 17) {
			if(cardV3 == 0) cardV3 = getHand();
			else if(cardV4 == 0) cardV4 = getHand();
			else if(cardV5 == 0) cardV5 = getHand();
			card3 = assignName(cardV3);
			card4 = assignName(cardV4);
			card5 = assignName(cardV5);
			
			cardV1 = adjustValue(card1, cardV1);
			cardV2 = adjustValue(card2, cardV2);
			cardV3 = adjustValue(card3, cardV3);
			cardV4 = adjustValue(card4, cardV4);
			cardV5 = adjustValue(card5, cardV5);
			
			sum = cardV1 + cardV2 + cardV3 + cardV4 + cardV5;
			cardCount++;
			if(sum > 21) {
				if(card1 == "Ace") cardV1 = 1;
				else if(card2 == "Ace") cardV2 = 1;
				else if(card3 == "Ace") cardV3 = 1;
				else if(card4 == "Ace") cardV4 = 1;
				else if(card5 == "Ace") cardV5 = 1;
				else playerBust = true;
			}
				sum = cardV1 + cardV2 + cardV3 + cardV4 + cardV5;
				
				if(!playerBust && cardCount == 5) {
					break;
				}
			}
		
		String card1d, card2d, card3d = "", card4d = "", card5d = "";
		int cardV1d, cardV2d, cardV3d = 0, cardV4d = 0, cardV5d = 0;
		int sumd = 0;
		boolean dealerBust = false;
		
		//HAHA COPY AND PASTE GO BRRRRRRRRRRRR
		
		cardV1d = getHand();
		cardV2d = getHand();
		card1d = assignName(cardV1d);
		card2d = assignName(cardV2d);
		cardV1d = adjustValue(card1d, cardV1d);
		cardV2d = adjustValue(card2d, cardV2d);
		
		dealerBlackjack = isBlackjack(cardV1d, cardV2d);
		
		sumd = cardV1d + cardV2d;
		if(sumd == 22) cardV1d = 1;
		sumd = cardV1d + cardV2d;

		if(fiveCards(cardCount, playerBust)) {}
		else {
			while(sumd < 17) {
				if(cardV3d == 0) cardV3d = getHand();
				else if(cardV4d == 0) cardV4d = getHand();
				else if(cardV5d == 0) cardV5d = getHand();
				card3d = assignName(cardV3d);
				card4d = assignName(cardV4d);
				card5d = assignName(cardV5d);
				
				cardV1d = adjustValue(card1d, cardV1d);
				cardV2d = adjustValue(card2d, cardV2d);
				cardV3d = adjustValue(card3d, cardV3d);
				cardV4d = adjustValue(card4d, cardV4d);
				cardV5d = adjustValue(card5d, cardV5d);
				
				sumd = cardV1d + cardV2d + cardV3d + cardV4d + cardV5d;
				
				if(sumd > 21)
					if(card1d == "Ace") cardV1d = 1;
					else if(card2d == "Ace") cardV2d = 1;
					else if(card3d == "Ace") cardV3d = 1;
					else if(card4d == "Ace") cardV4d = 1;
					else if(card5d == "Ace") cardV5d = 1;
					else dealerBust = true;
					sumd = cardV1d + cardV2d + cardV3d + cardV4d + cardV5d;
					
					if(cardV5d > 0) {
						break;
				}

			}
		}
		
		//End of copy and paste 4 dealer hand pog

		printPlayerHand(card1, card2, card3, card4, card5);
		System.out.println("Dealer hand: \t" + card1d + " " + card2d + " " + card3d + " "+ card4d + " "+ card5d);
		
		
		if(fiveCards(cardCount, playerBust)) winner = "Player";
		else if(playerBlackjack) winner = "PlayerBlackjack";
		else if(playerBust) winner = "Dealer-PlayerBust";
		else if(dealerBust) winner = "Player";
		else if(sum == sumd) winner = "Tie";
		else if(sum > sumd) winner = "Player";
		else if (sum < sumd) winner = "Dealer";
		else winner = "THERE'S BEEN A DISASTOR";
		
		if(winner == "PlayerBlackjack") {
			printPlayerHand(card1, card2, card3, card4, card5);
			System.out.println("Blackjack! Player wins!");
		}
		
		else if(fiveCards(cardCount, playerBust)) {
			System.out.println("Five card win! Player wins!");
			printPlayerHand(card1, card2, card3, card4, card5);
		}
		
		else if(winner == "Tie") {
			printPlayerHand(card1, card2, card3, card4, card5);
			printDealerHand(card1d, card2d, card3d, card4d, card5d);
			System.out.println("Player has " + sum + ". Dealer has " + sumd + ". It's a tie");
		}
		
		else if(winner == "Player" && dealerBust) {
			printPlayerHand(card1, card2, card3, card4, card5);
			printDealerHand(card1d, card2d, card3d, card4d, card5d);
			System.out.println("Dealer busts! Player wins");
		}
		
		else if(winner == "Player") {
			printPlayerHand(card1, card2, card3, card4, card5);
			printDealerHand(card1d, card2d, card3d, card4d, card5d);
			System.out.println("Player has " + sum + ". Dealer has " + sumd + ". Player wins!");

		}
		else if(winner == "Dealer") {
			printPlayerHand(card1, card2, card3, card4, card5);
			printDealerHand(card1d, card2d, card3d, card4d, card5d);
			System.out.println("Player has " + sum + ". Dealer has " + sumd + ". Dealer wins");

		}
		
		
		return winner;
	}
	
	public static String assignName(int card) {
		if(card == 1)
		return "Ace";
		else if(card == 0)
				return "";
		else if(card == 2)
			return "Two";
		else if(card == 3)
			return "Three";
		else if(card == 4)
			return "Four";
		else if(card == 5)
			return "Five";
		else if(card == 6)
			return "Six";
		else if(card == 7)
			return "Seven";
		else if(card == 8)
			return "Eight";
		else if(card == 9)
			return "Nine";
		else if(card == 10)
			return "Ten";
		else if(card == 11)
			return "Jack";
		else if(card == 12)
			return "Queen";
		else if(card == 13)
			return "King";
		else return "Card Error";
	}
 	
	public static int getHand() {
		return (int)(Math.random() * 13) + 1;
	}
	
	public static int adjustValue(String card, int cardV) {
		if (card == "Jack" || card == "Queen" || card == "King")
			return 10;
		else if (card == "Ace")
			return 11;
		else
			return cardV;
	}
	
	public static boolean isBlackjack(int cardV1, int cardV2) {
		if(cardV1 + cardV2 == 21)
		return true;
		else return false;
	}
	
	public static boolean fiveCards(int cardCount, boolean playerBust) {
		if(cardCount == 5 && !playerBust) return true;
		else return false;
	}
	
	public static void printPlayerHand(String card1, String card2, String card3, String card4, String card5) {
		System.out.println("Player hand: \t" + card1 + " " + card2 + " " + card3 + " "+ card4 + " "+ card5);

	}
	
	public static void printDealerHand(String card1, String card2, String card3, String card4, String card5) {
		System.out.println("Player hand: \t" + card1 + " " + card2 + " " + card3 + " "+ card4 + " "+ card5);
	}
	public static void outputStats(int hands, int ties, int wins, int losses, int blackjack, int playerBusts, int record, int recordL) {
		boolean wins5 = record >= 5;
		boolean losses5 = recordL >= 5;
		
		System.out.println("#######################");
		System.out.println("Hands Played \t" + hands);
		System.out.println("Player wins \t" + wins);
		System.out.println("Player losses \t" + losses);
		System.out.println("Ties \t" + ties);
		System.out.println("Player Blackjacks \t" + blackjack);
		System.out.println("Longest Win Streak \t" + record);
		System.out.println("Longest Losing Streak \t" + recordL);
		System.out.println("Player Busts \t" + playerBusts);
		System.out.println("5 Win Achievement \t" + wins5);
		System.out.println("5 Loss Achievement \t" + losses5);
		System.out.println("Winning Percentage \t" + calculatePercentage(hands, losses, ties) + "%");
	}
   
	public static boolean checkRecord(int current, int longest)
    {
        return current > longest;
    }
	
	public static double calculatePercentage(int hands, int wins, int ties) {
		double x = (double)wins / hands;
		x = (x * 10000);
		x = x / 100;
		return x;
	}
	
}
