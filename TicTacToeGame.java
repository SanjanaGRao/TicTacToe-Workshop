package TicTacToeWorkshop;
import java.util.Scanner;

public class TicTacToeGame {
	
	private static char[] board = new char[10];
	private static char userOption;
	private static char computerOption;
	
	//UseCase 1 -Creating a board and initializing
	public static void createBoard() 
	{
		
		for(int i = 1; i<board.length ; i++)
		{
			board[i] = ' ';			
		}
	}
	
	/*UseCase 2 - To allow a player to choose X or O
	 * @param userOption is allows the user to choose if he wants to play as X and O.
	 * @param computerOption automatically allows it to choose the other team opposite to what user has selected.
	 * the 'if...else' condition automatically allocates computer option
	 */
	public static void chooseOption()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter if you want to choose X or O: ");
		userOption = sc.next().charAt(0); 
		if(userOption=='X' || userOption=='x')
		{
			userOption = 'X';
			computerOption = 'O';
			System.out.println("Player Team: " + userOption);
			System.out.println("Computer Team: " + computerOption);
		}
		else {
			userOption = 'O';
			computerOption = 'X';
			System.out.println("Player Team: " + userOption);
			System.out.println("Computer Team: " + computerOption);
		}
		sc.close();
	}
	
	//Method to display the board
	public static void showBoard()
	{
		System.out.println("\n------------- ");
		for(int i = 1 ; i<10 ; i+=3)
		{
			System.out.println("| " + board[i] + " | " + board[i+1] + " | " + board[i+2] + " |");
			System.out.println("------------- ");
		}
		
	}
	
	public static void main(String[] args) {
		
		System.out.println("Welcome to Tic Tac Toe \n");
		createBoard();		
		chooseOption();
		showBoard();
	}

}
