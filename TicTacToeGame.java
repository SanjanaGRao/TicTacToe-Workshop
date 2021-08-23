package TicTacToeWorkshop;
import java.util.Scanner;

public class TicTacToeGame {
	
	static Scanner sc = new Scanner(System.in);
	private static char[] board = new char[10];
	private static char userOption;
	private static char computerOption;
	private static int indexNumber;
	
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
	}
	
	/* UseCase 3 - Method to display the board
	 * For loop helps in displaying the available spaces for the user to make a move
	 */
	public static void showBoard()
	{
		System.out.println("\n------------- ");
		for(int i = 1 ; i< board.length ; i+=3)
		{
			System.out.println("| " + board[i] + " | " + board[i+1] + " | " + board[i+2] + " |");
			System.out.println("------------- ");
		}
		
	}
	
	/*
	 * UseCase 4 - Ability of the user to make desired location in the board
	 * In this function, we ask the user the input as to where he wants to place his X or O
	 * @param indexNumber is used for the user to enter his input number.
	 * The 'if' condition checks for a valid index number.
	 */
	public static void userIndexValue()
	{
		
		System.out.println("Enter an index number where you want to place your input: ");
		indexNumber = sc.nextInt(); 
		if(indexNumber < 1 || indexNumber > 9)
		{
			System.out.println("Please enter a valid index number from 1-9. ");
		}
		sc.close();
	}
	
	public static void main(String[] args) {
		
		System.out.println("Welcome to Tic Tac Toe \n");
		createBoard();		
		chooseOption();
		showBoard();
		userIndexValue();
	}

}
