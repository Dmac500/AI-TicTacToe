import java.util.ArrayList;
import java.util.*;
import java.util.Scanner;

/**
 * This class is used to read in a state of a tic tac toe board. It creates a MinMax object and passes the state to it. What returns is a list 
 * of possible moves for the player X that have been given min/max values by the method findMoves. The moves that can result in a win or a 
 * tie for X are printed out with the method printBestMoves()
 * 
 * @author Mark Hallenbeck
 *
 * Copyright© 2014, Mark Hallenbeck, All Rights Reservered.
 *
 */
public class AI_MinMax {
	
	private String[] init_board;
	
	private ArrayList<Node> movesList;
	
	AI_MinMax(String[] gameboard )
	{
		init_board = gameboard;
		
		if(init_board.length != 9)
		{
			System.out.println("You have entered an invalid state for tic tac toe, exiting......");
			System.exit(-1);
		}
		
		MinMax sendIn_InitState = new MinMax(init_board);
		
		movesList = sendIn_InitState.findMoves();
		
		printBestMoves();
	}
	
	
	
	/**
	 * goes through a node list and prints out the moves with the best result for player X
	 * checks the min/max function of each state and only recomends a path that leads to a win or tie
	 */
	private void printBestMoves()
	{
		System.out.print("\n\nThe moves list is: < ");
		
		for(int x = 0; x < movesList.size(); x++)
		{
			Node temp = movesList.get(x);
			
			if(temp.getMinMax() == 10 || temp.getMinMax() == 0)
			{
				System.out.print(temp.getMovedTo() + " ");
			}
		}
		
		System.out.print(">");
	}
	 int returnBestMoves()
	{
		System.out.print("\n\nThe moves list is: < ");
		ArrayList<Integer> RandomBestMoves = new ArrayList<Integer>();
		ArrayList<Integer> RandomOKMoves = new ArrayList<Integer>();
		ArrayList<Integer> RandomNotToGoodMoves = new ArrayList<Integer>();
		
		for(int x = 0; x < movesList.size(); x++)
		{
			Node temp = movesList.get(x);
			
			if(temp.getMinMax() == 10 )
			{
				RandomBestMoves.add(temp.getMovedTo());
			}
			else if( temp.getMinMax() == 0)
			{
				RandomOKMoves.add(temp.getMovedTo());
			}
			else if (temp.getMinMax() == -10)
			{
				RandomNotToGoodMoves.add(temp.getMovedTo());
			}
			
		}
		 Collections.shuffle(RandomBestMoves); 
		 Collections.shuffle(RandomOKMoves); 
		 Collections.shuffle(RandomNotToGoodMoves); 
		 
		 if(RandomBestMoves.isEmpty() == false)
		 {
			 return RandomBestMoves.get(0);
		 }
		 else if(RandomOKMoves.isEmpty() == false)
		 {
			 return RandomOKMoves.get(0);
		 }
		 else if(RandomNotToGoodMoves.isEmpty() == false)
		 {
			 return RandomNotToGoodMoves.get(0);
		 }
		 else {
		return -1;
		 }
	}


	

}
