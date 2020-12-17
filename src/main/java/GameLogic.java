import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class GameLogic {
	
	ArrayList<Character> gameBoard;
	ExecutorService ex;
	String player1; // This is X
	String player2;// This is O
	int NumOfGames;
	private Consumer<ArrayList<Character>> callback;
	String Winner;
	
	
			
    GameLogic(String Play1,String play2, int NumGames,Consumer<ArrayList<Character>> call){ // this builds the board 
    player1 = Play1;
	player2 = play2;
	NumOfGames= NumGames;
	callback = call;

	//System.out.println(NumOfGames);
    	gameBoard = new ArrayList<>(Arrays.asList('b','b','b','b','b','b','b','b','b')); // make the board
		 ex = Executors.newFixedThreadPool(5);
	    Rungame();
	}
    
    public String getWinner() //  return the winner
	 {
    	return Winner;
	 }    
 public void Rungame() // run the game on a tread
   {
	 Future<Integer> future =  ex.submit(new playGame());// ik this need a future
	  

   }
   class playGame implements Callable<Integer>// This is going to play the game on the thread
   {
	playGame()
	{
		
	}
	 void Reset(ArrayList<Character> g) // Reset the board 
	 {
		 for(int i = 0;i < 9 ;i++)
		 {
			 g.set(i,'b');
		 }
	 }
	 Boolean CalcTheWin(Character Symbol) // all the posiable ways to in tictacToe
	 {
		 
		 if(gameBoard.get(0).equals(Symbol)&&gameBoard.get(1).equals(Symbol)&&gameBoard.get(2).equals(Symbol))
		 {
			 return true;
		 }
		 if(gameBoard.get(3).equals(Symbol)&&gameBoard.get(4).equals(Symbol)&&gameBoard.get(5).equals(Symbol))
		 {
			 return true;
		 }
		 if(gameBoard.get(6).equals(Symbol)&&gameBoard.get(7).equals(Symbol)&&gameBoard.get(8).equals(Symbol))
		 {
			 return true;
		 }
		 

		 if(gameBoard.get(0).equals(Symbol)&&gameBoard.get(3).equals(Symbol)&&gameBoard.get(6).equals(Symbol))
		 {
			 return true;
		 }
		 if(gameBoard.get(1).equals(Symbol)&&gameBoard.get(4).equals(Symbol)&&gameBoard.get(7).equals(Symbol))
		 {
			 return true;
		 }
		 if(gameBoard.get(2).equals(Symbol)&&gameBoard.get(5).equals(Symbol)&&gameBoard.get(8).equals(Symbol))
		 {
			 return true;
		 }
		 

		 if(gameBoard.get(0).equals(Symbol)&&gameBoard.get(4).equals(Symbol)&&gameBoard.get(8).equals(Symbol))
		 {
			 return true;
		 }
		 if(gameBoard.get(2).equals(Symbol)&&gameBoard.get(4).equals(Symbol)&&gameBoard.get(6).equals(Symbol))
		 {
			 return true;
		 }
		 
		 return false;
	 }
	 
	
	 public void whoWon(Character symbol) // to see who wom easyier 
	 {
		 
		 if(symbol.equals('X'))
		 {
			Winner = "Player1";
		 }
		 else if(symbol.equals('O'))
		 {
			 Winner = "Player2";
		 }
		 else
		 {
			 Winner = " ";
		 }
		 
	 }
	 
	   Character turn = 'X';
	   public Integer call() throws Exception 
	   {
		    
		if (player1 == "novice" || player1 == "advanced" && player2 == "novice"||player2 =="advanced") // this will just pick random spots until a tie or a win
		{
			   
			   int  val = 0; 
	   for(int i = 0; i <  NumOfGames ;i++) {
		   Winner ="";
		   Reset(gameBoard); // reset the game for each game
		   	
		  for(int iter = 0; iter < 9; iter++) {
		   		
				Future<Integer> future = ex.submit(new BlindPlaying(gameBoard, turn));
			  
				try {
				Integer index = future.get();
					gameBoard.set(index , turn);
					
			    
				val = index;
				}//end of the try
				catch(Exception e){System.out.println(e.getMessage());}
				
				if(turn.equals('X')) {
					turn = 'O';
				}//end of the if 
				else {
					turn = 'X';
				}// end of the else
				callback.accept(gameBoard);
				
				if (CalcTheWin('X'))
				{
					System.out.print("Player 1 wins");
					whoWon('X');
					
					 break;
				}
				else if(CalcTheWin('O'))
				{
					whoWon('O');
					
					System.out.print("Player 2 wins");
				 break;
				}
				Thread.sleep(1000);
			}// end of for loop playing the game
		  		
			
			
			Thread.sleep(1000);
			
		   }// end of for loop of the number of games 
	      ex.shutdown();

			return val;
			
			
			
		}
		else if(player1.equals("expert") && !player2.equals("expert")) // runs MinMax for player one 
		{
			
			   int  val = 0; 
			   for(int i = 0; i <  NumOfGames ;i++) {
				   Winner ="";
				   Reset(gameBoard); // reset the game for each game
				   	
				  for(int iter = 0; iter < 9; iter++) {
				   		
						Future<Integer> future = ex.submit(new BlindPlaying(gameBoard, turn));
						Future<Integer> Future =  ex.submit(new ALgroathim(gameBoard, turn));
						try {
						//Integer index = future.get();
							//gameBoard.set(index , turn);
							Integer index;
							if(turn.equals('X')) {
								 index = Future.get();
								gameBoard.set(index -1, turn);
								
							}//end of the if 
							else {
								 index =future.get();
								gameBoard.set(index , turn);
							
							}
					    
						val = index;
						}//end of the try
						catch(Exception e){System.out.println(e.getMessage());}
						
						if(turn.equals('X')) {
							turn = 'O';
						}//end of the if 
						else {
							
							turn = 'X';
						}// end of the else
						callback.accept(gameBoard);
						
						if (CalcTheWin('X'))
						{
							System.out.print("Player 1 wins");
							whoWon('X');
							
							 break;
						}
						else if(CalcTheWin('O'))
						{
							whoWon('O');
							
							System.out.print("Player 2 wins");
						 break;
						}
						Thread.sleep(1000);
					}// end of for loop playing the game
				  		
					
					
					Thread.sleep(1000);
					
				   }// end of for loop of the number of games 
			      ex.shutdown();
				//Thread.sleep(1000);
//				System.out.println("\n" + "player: pop"  + " chooses index: "+val);
					return val;
					
					
			
			
			
			
		}
		else if(!player1.equals("expert") && player2.equals("expert"))// runs MinMax for player Two
		{
			
			   int  val = 0; 
			   for(int i = 0; i <  NumOfGames ;i++) {
				   Winner ="";
				   Reset(gameBoard); // reset the game for each game
				   	
				  for(int iter = 0; iter < 9; iter++) {
				   		
						Future<Integer> future = ex.submit(new BlindPlaying(gameBoard, turn));
						Future<Integer> Future =  ex.submit(new ALgroathim(gameBoard, turn));
						try {
						//Integer index = future.get();
							//gameBoard.set(index , turn);
							Integer index;
							if(turn.equals('O')) {
								 index = Future.get();
								gameBoard.set(index -1, turn);
								
							}//end of the if 
							else {
								 index =future.get();
								gameBoard.set(index , turn);
							
							}
					    
						val = index;
						}//end of the try
						catch(Exception e){System.out.println(e.getMessage());}
						
						if(turn.equals('X')) {
							turn = 'O';
						}//end of the if 
						else {
							
							turn = 'X';
						}// end of the else
						callback.accept(gameBoard);
						
						if (CalcTheWin('X')) // checks for winner
						{
							System.out.print("Player 1 wins");
							whoWon('X');
							
							 break;
						}
						else if(CalcTheWin('O'))
						{
							whoWon('O');
							
							System.out.print("Player 2 wins");
						 break;
						}
						Thread.sleep(1000);
					}// end of for loop playing the game
				  		
					
					
					Thread.sleep(1000);
					
				   }// end of for loop of the number of games 
			      ex.shutdown();
				//Thread.sleep(1000);
//				System.out.println("\n" + "player: pop"  + " chooses index: "+val);
					return val;
		}
					
		else {
		   
		   
		   int  val = 0; 
   for(int i = 0; i <  NumOfGames ;i++) {
	   	
	   Reset(gameBoard); // reset the game for each game
	   	
	  for(int iter = 0; iter < 9; iter++) {
	   		
			Future<Integer> future = ex.submit(new ALgroathim(gameBoard, turn));
		  
			try {
			Integer index = future.get();
				if (index == -1)
				{
				Random r = new Random();
	    		val = r.nextInt(9);
				}
				gameBoard.set(index - 1, turn);
				System.out.println(gameBoard.get(0)+ " "+gameBoard.get(1)+" "+gameBoard.get(2));
				System.out.println(gameBoard.get(3)+ " "+gameBoard.get(4)+" "+gameBoard.get(5));
				System.out.println(gameBoard.get(6)+ " "+gameBoard.get(7)+" "+gameBoard.get(8));
		    
			val = index;
			}//end of the try
			catch(Exception e){System.out.println(e.getMessage());}
			
			if(turn.equals('X')) {
				turn = 'O';
			}//end of the if 
			else {
				turn = 'X';
			}// end of the else
			callback.accept(gameBoard);
			Thread.sleep(1000);
			if (CalcTheWin('X'))
			{
				System.out.print("Player 1 wins");
				whoWon('X');
				
				 break;
			}
			else if(CalcTheWin('O'))
			{
				whoWon('O');
				
				System.out.print("Player 2 wins");
			 break;
			}
		
			
		}// end of for loop playing the game
	
		
		
		Thread.sleep(1000);
		
	   }// end of for loop of the number of games 
      ex.shutdown();
	//Thread.sleep(1000);
//	System.out.println("\n" + "player: pop"  + " chooses index: "+val);
		return val;
		
	   }
	   }
   } 

    class ALgroathim implements Callable<Integer> //  this is the min max  ALgroathim
    { //  this is going to predicted the next move
    	AI_MinMax nextMove;
    	Character move;
    	ArrayList<Character> Board;
    	ALgroathim (ArrayList<Character> g ,Character t )
    	{
    		Board = g;
    		this.move = t;
    		if(move.equals('O'))
    		{
    			Board = flipSigns(Board);
    		}
    		String[] board = CharToString(Board);
    		
    		nextMove = new AI_MinMax(board);
    	}
    	ArrayList<Character> flipSigns(ArrayList<Character> g) // this is to compinsate for that the algerithm only works for x
    	{
    		for(int i = 0; i < 9; i++)
    		{
    			if(g.get(i).equals('X'))
    			{
    				g.set(i, 'O');
    			}
    			else if(g.get(i).equals('O'))
    			{
    				g.set(i, 'X');
    			}
    		
    		}
    		
    		return g;
    	}
    	
    	String[] CharToString(ArrayList<Character> p )
    	{
    	
    	String[] stringArr = new String[9];
    		
    	for(int i = 0; i < 9;i++  ) // im sad that java dont have a auto 
    	{
    		stringArr[i]= String.valueOf(p.get(i));
    	
    	}
    	
    	
    	
    	 return stringArr;
    	}
    	public Integer call() throws Exception {
    		if(move.equals('O'))
    		{
    			Board = flipSigns(Board);
    		}
    		
    		return nextMove.returnBestMoves();
		}
		
    }
    class BlindPlaying implements Callable<Integer>{ // runs random picking BlindPlaying

    	ArrayList<Character> board = new ArrayList<Character>();
    	Character move;
    	
    	BlindPlaying(ArrayList<Character> game, Character move){
    		board = game;
    		this.move = move;
    	}
    	@Override
    	public Integer call() throws Exception {
    		// TODO Auto-generated method stub
    		boolean bool = true;
    		Integer val = 0;
    		
    		
    		while(bool) {
    			
    		Random r = new Random();
    		val = r.nextInt(9);
    		
    		if(board.get(val) == 'X' || board.get(val) == 'O') {
    				bool = true;
    				
    			}
    		else {bool = false;}	
    		}
    		
    		Thread.sleep(1000);
    		
    		return val;
    	}
    	
    }
}
    	
 





	
