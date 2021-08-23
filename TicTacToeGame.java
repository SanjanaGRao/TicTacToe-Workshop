package TicTacToeWorkshop;
import java.util.Scanner;
import java.util.Random;

public class TicTacToeGame {
	
	static Scanner sc = new Scanner(System.in);
	private static char[] board = new char[10];
	private static char userOption;
	private static char computerOption;
	private static int indexNumber;
	public static Random rand = new Random(); // @rand to check if User play's first or Computer using coin tossing
	public static char turnToPlay; //@turnToPlay to check who is playing - the user or the system
	public static int flag = 0; //@param flag is used to monitor if the game is starting first time or not
	
	public static int winnerStatus = 0 ;
	public static int tie = 0 ;
	public static int changeUserTurn = 0 ;
	
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
	}
	
	/*
	 * UseCase 5 - Ability to check for the free space before making the desired move
	 * The'if...else' here checks if the particular index is available or not, if it is available,the user input goes there
	 * If not, message is displayed and the user needs to repeat his turn again.
	 */
	public static void userMove()
	{
		if(board[indexNumber] == ' ' && flag == 1) // this is when the user and computer are playing for the first time
			{
				if(turnToPlay == 'P')
				{
					board[indexNumber] = userOption;
					flag = 0;
				}
				else
				{
					board[indexNumber] = computerOption;
					flag = 0;
				}
			}
		else if(board[indexNumber] == ' ')
		{
			board[indexNumber] = userOption;
		}
		else
			{
				System.out.println("Sorry, Enter a different index number, this index number is not available."); 
				userIndexValue();
				userMove();
			}
	 showBoard();
	}
	
	/* UseCase 6 - Asking if the User would like to do a toss to check who plays first.
	 * If the user chooses Heads i.e, 0, and the @param headOrTail gives the result as 1, user plays
	 * Else,computer starts.
	 * @param flag is set to 1 to indicate that the Computer is starting first
	 */
	public static void tossMethod()
	{
		System.out.println("Choose Head(0) or Tail(1): ");
		int userChoice=sc.nextInt();							
		int headOrTail = rand.nextInt(2);						
		if(userChoice==headOrTail)
		{
			System.out.println("Player's Turn.");
			turnToPlay='P';

		}
		else
		{
			System.out.println("Computer's turn");
			turnToPlay='C';
			indexNumber = rand.nextInt(10);
			flag = 1;
			userMove();
		}
	}
	
	/* UseCase 7 - As player would expect the Tic Tac Toe App to determine after every move the winner or the tie or change the turn
	 * This method prints the Game statistics like the Winner, Tie or not and it helps in Changing the turn 
	 * Swapping is done in order to change the user turns.
	 */
	public static void statistics()
	{
		if(winnerStatus == 0)
			System.out.println("Winner: NA");
		else
			System.out.println("Winner: ");
		System.out.println("Tie Games: " + tie);
		System.out.println("Do you want to change turns? (Y/N):  ");
		char newUserOption = sc.next().charAt(0); 
		if(newUserOption == 'y' || newUserOption == 'Y')
		{
			if(turnToPlay == 'P')
			{
				turnToPlay ='C';
				//Swapping userOption and ComputerOption
				int temp = userOption;
				userOption = computerOption;
				computerOption = userOption;
				showBoard();
			}
			else
			{
				turnToPlay = 'P';
				//Swapping userOption and ComputerOption
				int temp = userOption;
				userOption = computerOption;
				computerOption = userOption;
				showBoard();
			}
		}// end of 1st if
		
	}//end of method statistics
	
	public static void main(String[] args) {
		
		System.out.println("Welcome to Tic Tac Toe \n");
		createBoard();	
		turnToPlay = 'P'; //Assuming the User starts first
		chooseOption();
		showBoard();
		tossMethod();
		userIndexValue();
		userMove();
		statistics();
	}

}
