package TicTacToeWorkshop;

public class TicTacToeGame {
	
	private static int length = 10;
	private static char[] board = new char[10];
	
	//UseCase 1 -Creating a board and initializing
	public static void createBoard() 
	{
		
		for(int i = 1; i<length ; i++)
		{
			board[i] = ' ';			
		}
	}
	
	
	public static void main(String[] args) {
		
		System.out.println("Welcome to Tic Tac Toe \n");
		createBoard();
		
		
	}

}
