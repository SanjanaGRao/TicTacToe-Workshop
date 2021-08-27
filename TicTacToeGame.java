package TicTacToeWorkshop;
import java.util.Scanner;
import java.util.Random;

public class TicTacToeGame {
	
	static Scanner sc = new Scanner(System.in);
	private static char[] board = new char[10];
	private static char userOption;
	private static char computerOption;
	private static int indexNumber;
	public static Random rand = new Random(); // @param rand to check if User play's first or Computer using coin tossing
	public static char turnToPlay; //@turnToPlay to check who is playing - the user or the system
	public static int flag = 0; //@param flag is used to monitor if the game is starting first time or not
	public static int count = 0;//@param count is used for monitoring computers move
	
	public static int winnerStatus = 0 ; //@param winnerStatus is used to monitor if the winner has been set or not
	public static int tossWinner = 0 ;//@param tossWinner tells us who won the toss
	public static int changeUserTurn = 0 ;
	public static int[] arrCorners = {1,3,7,9};
	public static int[] arrSides = {2,4,6,8};
	
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
			tossWinner = 1;
			turnToPlay='P';

		}
		else
		{
			System.out.println("Computer's turn");
			tossWinner = 0;
			turnToPlay='C';
			flag = 1;
		}
	}
	
	/* UseCase 7 - As player would expect the Tic Tac Toe App to determine after every move the winner or the tie or change the turn
	 * This method helps in Changing the turn 
	 * Simple 'if' condition is used in order to change the user turns.
	 */
	public static void statistics()
	{
		System.out.println("Do you want to change turns? (Y/N):  ");
		char newUserOption = sc.next().charAt(0); 
		if(newUserOption == 'y' || newUserOption == 'Y')
		{
				if(turnToPlay=='C')
				{
					turnToPlay='P';
					System.out.println("Player's turn to play");
					userIndexValue();
					userMove();
				}
				else
				{
					turnToPlay='C';
					System.out.println("Computer's turn to play");
					computerMove();
				}
			
		}// end of 1st if
		
	}//end of method statistics
	
	/* UseCase 8 - On Computer getting its turn would like the computer to play like a human
	 * UseCase 9 - Next thing to do is check if the Opponent can win then play to block it
	 * The first 9 sets of relational operators and logical operators are statements to check if a system can win
	 * Next 9 set of same statements block the opponent if he/she has a chance of winning
	 * UseCase 10 and 11 - If neither of the players can win, then taking up alternate places like Corners and sides
	 * First, corners are filled and then center is filed and finally the sides are taken into consideration
	 */
	private static void computerMove() {
		//UC8 and UC9 To block user from winning and to play like human
		if(((board[1]==' ')&&(board[2]==board[3] && board[2]==computerOption)||(board[4]==board[7] && board[4]==computerOption)||(board[5]==board[9] && board[5]==computerOption)) ||
		    (board[1]==' ')&&((board[2]==board[3]&&board[2]==userOption)||(board[4]==board[7]&&board[4]==userOption)||(board[5]==board[9]&&board[5]==userOption)))
		{
			board[1] = computerOption;
			
		}
		else if(((board[2]==' ')&&(board[1]==board[3] && board[1]==computerOption)||(board[5]==board[8] && board[8]==computerOption)) ||
				 (board[2]==' ')&&((board[1]==board[3]&&board[3]==userOption)||(board[5]==board[8]&&board[8]==userOption)))
		{
			board[2] = computerOption;
		}
		else if(((board[3]==' ')&&(board[2]==board[1] && board[2]==computerOption)||(board[5]==board[7] && board[5]==computerOption)||(board[6]==board[9] && board[6]==computerOption)) ||
				 (board[3]==' ')&&((board[1]==board[2]&&board[2]==userOption)||(board[6]==board[9]&&board[9]==userOption)||(board[5]==board[7]&&board[7]==userOption)))
		{
			board[3] = computerOption;		
		}
		else if(((board[4]==' ')&&(board[1]==board[7] && board[1]==computerOption)||(board[5]==board[6] && board[5]==computerOption)) ||
				 (board[4]==' ')&&((board[1]==board[7]&&board[7]==userOption)||(board[5]==board[6]&&board[6]==userOption)))
		{
			board[4] = computerOption;	
		}
		else if(((board[5]==' ')&&(board[2]==board[8] && board[2]==computerOption)||(board[4]==board[6] && board[4]==computerOption)||(board[1]==board[9] && board[9]==computerOption)||(board[7]==board[3] && board[3]==computerOption)) ||
				 (board[5]==' ')&&((board[1]==board[9]&&board[1]==userOption)||(board[7]==board[3]&&board[7]==userOption)||(board[2]==board[8]&&board[8]==userOption)||(board[4]==board[6]&&board[6]==userOption)))
				
		{
			board[5] = computerOption;	
		}
		else if(((board[6]==' ')&&(board[9]==board[3] && board[3]==computerOption)||(board[4]==board[5] && board[4]==computerOption)) ||
				 (board[6]==' ')&&((board[9]==board[3]&&board[3]==userOption)||(board[5]==board[4]&&board[4]==userOption)))
				
		{
			board[6] = computerOption;	
		}
		else if(((board[7]==' ')&&(board[1]==board[4] && board[1]==computerOption)||(board[5]==board[3] && board[3]==computerOption)||(board[8]==board[9] && board[9]==computerOption)) ||
				 (board[7]==' ')&&((board[1]==board[4]&&board[4]==userOption)||(board[8]==board[9]&&board[9]==userOption)||(board[5]==board[3]&&board[3]==userOption)))
				
		{
			board[7] = computerOption;	
		}
		else if(((board[8]==' ')&&(board[2]==board[5] && board[2]==computerOption)||(board[9]==board[7] && board[7]==computerOption)) ||
				 (board[8]==' ')&&((board[9]==board[7]&&board[7]==userOption)||(board[2]==board[5]&&board[2]==userOption)))
		{
			board[8] = computerOption;	
		}
		else if(((board[9]==' ')&&(board[6]==board[3] && board[3]==computerOption)||(board[8]==board[7] && board[7]==computerOption)||(board[5]==board[1] && board[5]==computerOption)) ||
				 (board[9]==' ')&&((board[1]==board[5]&&board[5]==userOption)||(board[6]==board[3]&&board[3]==userOption)||(board[8]==board[7]&&board[7]==userOption)))
		{
			board[9] = computerOption;	
		}		
		else
		{
			//UseCase 10 - Checking if corner values are filled or not
			int flag1 = 0;
			for(int i=0;i<4;i++)
			{
				if(board[arrCorners[i]]==' ')
					{
					board[arrCorners[i]]=computerOption;
					flag1=1;
					break;
					}
			}
			//UseCase 11 - Checking for middle spaces and side spaces
			if(flag1==0 )
				{
				if(board[5]==' ')
					board[5]=computerOption;
				else
				{
					
					for(int i=0;i<4;i++)
					{
						if(board[arrSides[i]]==' ')
							{
							board[arrSides[i]]=computerOption;
							break;
							}
					}//end of for
				}
			}//end of if of UC11
		}//end of main else
	}
	
	/* This method defines the conditions for a win
	 * The first 'for loop' is defined to check if there is an winning situation and if there is, it returns a character X or O
	 * The second 'for loop' is to check for a tie,if the match ends in a tie, the method returns character 't' or 'n'.
	 */
		public static char getWinner() {
			String line = null;
			
	        for (int a = 1; a < 10; a++) 
	        {
	           
	            switch (a) {
	            case 1:
	                line = ""+board[1] + board[2] + board[3];
	                break;
	            case 2:
	                line = ""+board[4] + board[5] + board[6];
	                break;
	            case 3:
	                line = ""+board[7] + board[8] + board[9];
	                break;
	            case 4:
	                line = ""+board[1] + board[4] + board[7];
	                break;
	            case 5:
	                line = ""+board[2] + board[5] + board[8];
	                break;
	            case 6:
	                line = ""+board[3] + board[6] + board[9];
	                break;
	            case 7:
	                line = ""+board[1] + board[5] + board[9];
	                break;
	            case 8:
	                line = ""+board[3] + board[5] + board[7];
	                break;
	            }
	        //For X winner
	        if (line.equals("XXX")) {
	            return 'X';
	        }
	          
	        // For O winner
	        else if (line.equals("OOO")) {
	            return  'O';
	        }
	        }
	        int check1;	
	        for (check1 = 1; check1 < 10; check1++) 
	        {
		            if (board[check1]==' ') {
		               break;
		            }
	        } 
	        if(check1==10)
		            return 't';
	        	else
	        		return 'n';
		}
		
		//This is a method created to Identify and display winner
		public static void identifyWinner()
		    {
		    	
		    	
		       char win=getWinner();
		        if(win==userOption)
		        {
		        	System.out.println("\n Congratulations, You win!");
		        	winnerStatus=1;
		        }
		        else if(win==computerOption)
		        	{
		        		System.out.println("\n Computer wins!");
		        		winnerStatus=1;
		        	}
		        else if(win=='t')
		        {
		        	System.out.println("TIE.");
		        	winnerStatus=1;
		        }
		    }
		
	//This method helps in defining the game flow
	public static void gameFlow() {
		createBoard();	
		chooseOption();
		showBoard();
		tossMethod();
		
		while(winnerStatus==0)
		{
			
			if(turnToPlay=='C')
			{
				computerMove();
				showBoard();
				identifyWinner();
				turnToPlay='P';
			}
			else if(turnToPlay=='P') 
			{
				userIndexValue();
				userMove();
				identifyWinner();
				turnToPlay='C';
				
			}
		}
		}

	public static void main(String[] args) {
		
		System.out.println("Welcome to Tic Tac Toe \n");
		gameFlow();
		//Asking the user if wants to play another game - UC13
		System.out.println("Want to start a new game?(Y/N) ");
		char newGame = sc.next().charAt(0); 
		if(newGame=='Y' || newGame=='y')
		{
			winnerStatus=0;
			gameFlow();
		}
		else
			System.exit(0);		
	}

}
