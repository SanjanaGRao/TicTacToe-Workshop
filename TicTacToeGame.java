package TicTacToeWorkshop;
import java.util.Scanner;

public class TicTacToeGame {
	
	private char[] board = new char[10];
	//UseCase 1
	public void createBoard() 
	{
		
		for(int i = 1; i<10 ; i++)
		{
			board[i] = ' ';			
		}
	}
	
	
	public static void main(String[] args) {
		
		TicTacToeGame obj1 = new TicTacToeGame(); //object creation
		obj1.createBoard();
		
		
	}

}
