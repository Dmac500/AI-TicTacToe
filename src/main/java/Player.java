

public class Player {

	private int amountOfWins = 0;

	
	public int getWins() // keeps track of wins 
	{
		return amountOfWins;
	}
	public void Addwin()// adds the wins 
	{
		amountOfWins++;
	}
	
	public void reset() // resets it for a new sires of games.
	{
		amountOfWins = 0;
	}
	
}