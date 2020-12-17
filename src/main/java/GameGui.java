import javafx.animation.PauseTransition;
import javafx.application.Application;

import javafx.scene.Scene;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class GameGui extends Application {

	HashMap<String, Scene> sceneMap;
	BorderPane startPane;
  //  GridPane TicTacToe ;
	String NumofGames;
	int numGames;
	String player1 ;
	String player2 ;
	ArrayList<String> gameBoard = new ArrayList<String>(9);
	PauseTransition pause = new PauseTransition(Duration.seconds(2));
	PauseTransition pause2 = new PauseTransition(Duration.seconds(10));
 	GameLogic gamePlay;
 	Player playerOne = new Player();
 	Player playerTwo = new Player();
 	String winner = "";
 	int tie = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		 sceneMap = new HashMap<String, Scene>(); //holds the scene
			
			sceneMap.put("start" , createwelcomeScreen(primaryStage)); // this is the load up
			sceneMap.put("Game" , createGameScreen(primaryStage)); // this is the game 
			sceneMap.put("How To play?" ,HowtoPlayScreen(primaryStage)); // this is how you play
			sceneMap.put("WinnerScreen" , createGameScreen(primaryStage)); // sadly coundnt get it to work and ran out of time 
			pause.setOnFinished(e -> primaryStage.setScene(sceneMap.get("Game")));
			pause2.setOnFinished(e -> primaryStage.setScene(sceneMap.get("WinnerScreen")));
			
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	            @Override
	            public void handle(WindowEvent t) {
	                Platform.exit();
	                System.exit(0);
	            }
	        });
			
			primaryStage.setScene(sceneMap.get("start"));
			primaryStage.show();
			
		}
	
	public Scene createwinnerScreen(Stage primaryStage) // this is suppose to be the winner screen 
	{
		Text WINNERTEXT = new Text();
		if( playerOne.getWins() > playerOne.getWins())
		{
			WINNERTEXT.setText("Player One Wins ");
			WINNERTEXT.setStyle(" -fx-font: 40 arial;");
			WINNERTEXT.setFill(Color.RED);
		}
		else if(playerOne.getWins() < playerOne.getWins())
		{
			WINNERTEXT.setText("Player Two Wins ");
			WINNERTEXT.setStyle(" -fx-font: 40 arial;");
			WINNERTEXT.setFill(Color.RED);
		}
		else
		{
			WINNERTEXT.setText("Tie ");
			WINNERTEXT.setStyle(" -fx-font: 40 arial;");
			WINNERTEXT.setFill(Color.RED);
		}
		
		playerOne.reset();
		playerTwo.reset();
		startPane.setCenter(WINNERTEXT);
		pause.play();
		return new Scene(startPane, 1080,720);
	}
	
	public Scene createwelcomeScreen (Stage primaryStage) // start up screen
	{
		Image pic = new Image("background.png");
		Text welcome = new Text(); // welcome text 
		welcome.setText("Welcome to TicTacToe AI v AI ");
		welcome.setStyle(" -fx-font: 40 arial;");
		welcome.setFill(Color.WHITE);
		welcome.setTranslateX(250.0);
		BackgroundSize backSize = new BackgroundSize(
				BackgroundSize.AUTO,
				BackgroundSize.AUTO,
				false,
				false,
				true,
				false); 
		
		
		Background backGround = new Background(new BackgroundImage(
				pic,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				backSize
				)); 
		startPane = new BorderPane();
		primaryStage.setTitle("Welcome to TicTacToe");
		startPane.setTop(welcome);
		startPane.setBackground(backGround);
		
		pause.play();
		return new Scene(startPane, 1080,720);
	}
	

	public int stringToInt(String u)// just to make it easy to get the numbers 
	
	{
		if(u == "One")
		{
			return 1;
		}
		else if(u == "Two")
		{
			return 2;
		}
		else if(u == "Three")
		{
			return 3;
		}
		else if(u == "Four")
		{
			return 4;
		}
		else if(u == "Five")
		{
			return 5;
		}
		else if(u == "Six")
		{
			return 6;
		}
		else if(u == "Seven")
		{
			return 7;
		}
		else if(u == "Eight")
		{
			return 8;
		}
		else if(u == "Nine")
		{
			return 9;
		}
		else if(u == "Ten")
		{
			return 10;
		}
		else
		{
		return 0;
		}
		}
	
	
	public void Addwin(String la) // add the wins 
	{
		if(la.equals("Player1"))
		{
			playerOne.Addwin();
		}
		else if (la.equals("Player2"))
		{
			playerTwo.Addwin();
		}
		else
		{
			;
		}
	}
	public Scene createGameScreen(Stage primaryStage) // this is the game
	{
	    HBox game;
	    HBox Player1;
	    HBox Player2;
	    VBox TheSetter;
	   
       Menu menu1 = new Menu("Menu"); // to get the how to play
		
		MenuItem menuItem1 = new MenuItem("How To Play?");
		menuItem1.setOnAction(e -> {
			primaryStage.setScene(sceneMap.get("How To play?")); 
		});; 
		menu1.getItems().add(menuItem1);
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().add(menu1);
	    BorderPane pane = new BorderPane();
		Text NumOfGames = new Text();
		Text dif1 = new Text();
		Text dif2 = new Text();
		ChoiceBox<String> cb = new ChoiceBox<String>(FXCollections.observableArrayList( // drop boxs to make it look clean
			    "One", "Two", "Three","Four","Five","Six","Seven","Eight","Nine","Ten")
			);
		ChoiceBox<String> play1 = new ChoiceBox<String>(); // drop boxs to make it look clean
		play1.getItems().add("novice");
		play1.getItems().add("advanced");
		play1.getItems().add("expert");
		
			
		
		ChoiceBox<String> play2 = new ChoiceBox<String>(); // drop boxs to make it look clean
		play2.getItems().add("novice");
		play2.getItems().add("advanced");
		play2.getItems().add("expert");
	
		NumOfGames.setText("How many games would you like to play: ");
		NumOfGames.setStyle(" -fx-font: 18 arial;");
		NumOfGames.setFill(Color.BLACK);
		
		dif1.setText("Player one skill level (X) ");
		dif1.setStyle(" -fx-font: 18 arial;");
		dif1.setFill(Color.BLACK);
	
		dif2.setText("Player Two skill level (O)");
		dif2.setStyle(" -fx-font: 18 arial;");
		dif2.setFill(Color.BLACK);
		
		Button start = new Button(); // start button
		start.setText("Start");
		 start.setScaleX(3);
		 start.setScaleY(3);
		 start.setTranslateX(80);
		 start.setTranslateY(50);
		 // this is the game board
		 TextField box1= new TextField("b");
		 TextField box2= new TextField("b");
		 TextField box3= new TextField("b");
		 box1.setScaleX(1.5);
		 box1.setScaleY(1.5);
		 box2.setScaleX(1.5);
		 box2.setScaleY(1.5);
		 box3.setScaleX(1.5);
		 box3.setScaleY(1.5);
		 
		 TextField box4= new TextField("b");
		 TextField box5= new TextField("b");
		 TextField box6= new TextField("b");
		 box4.setScaleX(1.5);
		 box4.setScaleY(1.5);
		 box5.setScaleX(1.5);
		 box5.setScaleY(1.5);
		 box6.setScaleX(1.5);
		 box6.setScaleY(1.5);
		 
		 TextField box7= new TextField("b");
		 TextField box8= new TextField("b");
		 TextField box9= new TextField("b");
		 box7.setScaleX(1.5);
		 box7.setScaleY(1.5);
		 box8.setScaleX(1.5);
		 box8.setScaleY(1.5);
		 box9.setScaleX(1.5);
		 box9.setScaleY(1.5);
		 // orginize the board
		 HBox Row1 = new HBox(10,box1,box2,box3);
		 HBox Row2 = new HBox(10,box4,box5,box6);
		 HBox Row3 = new HBox(10,box7,box8,box9);
		 VBox TicTacToe = new VBox(30,Row1,Row2,Row3);
		TicTacToe.setTranslateY(200);
		 
		 
		HBox winnerBox1;
		HBox winnerBox2;
		VBox WinnerQue;
		
		
		// present the winners
			Text player1T = new Text("Player 1 wins: "); 
			Text player2T = new Text("Player 2 wins: ");
			 player1T.setStyle(" -fx-font: 28 arial;");
			 player2T.setStyle(" -fx-font: 28 arial;");
			TextField amtwins1 = new TextField(String.valueOf(playerOne.getWins()));
			TextField amtwins2 = new TextField(String.valueOf(playerTwo.getWins()));
			amtwins1.setStyle(" -fx-font: 28 arial;");
			amtwins2.setStyle(" -fx-font: 28 arial;");
			winnerBox1 = new HBox(5,player1T,amtwins1);
			winnerBox2 =  new HBox(5,player2T,amtwins2);
			WinnerQue = new VBox(10,winnerBox1,winnerBox2);
		
			
			WinnerQue.setTranslateX(640);
			WinnerQue.setTranslateY(-240);
			// put functions to start
		start.setOnAction(e->{ 
			NumofGames = (String) cb.getValue();
			player1 = (String) play1.getValue();
		    player2 = (String) play2.getValue();
		    
		    playerOne.reset(); // reset the board
		    playerTwo.reset();
		   
			System.out.println();
			System.out.println("Number of Games "+ stringToInt(NumofGames));
			System.out.println("Player 1 "+player1);
			System.out.println("Player 2 "+player2);
			
			gamePlay = new GameLogic(player1,player2,stringToInt(NumofGames),data->{
				Platform.runLater(()->{
					
				box1.setText(String.valueOf(data.get(0)));	
				box2.setText(String.valueOf(data.get(1)));	// this is the updating of the board
				box3.setText(String.valueOf(data.get(2)));	
				box4.setText(String.valueOf(data.get(3)));	
				box5.setText(String.valueOf(data.get(4)));	
				box6.setText(String.valueOf(data.get(5)));	
				box7.setText(String.valueOf(data.get(6)));	
				box8.setText(String.valueOf(data.get(7)));	
				box9.setText(String.valueOf(data.get(8)));	
					
				winner = gamePlay.getWinner();
				Addwin(winner);
				 amtwins1.setText(String.valueOf(playerOne.getWins()));
				 amtwins2.setText(String.valueOf(playerTwo.getWins()));
				
				 
				});
			});
			
			
			start.setText("Play Again");
		});
	   
		Button close = new Button();
		close.setText("Exit");
		close.setScaleX(3);
		close.setScaleY(3);
		close.setTranslateX(50);
		close.setTranslateY(360);
		close.setOnAction(e->{ 
			
			
			primaryStage.close();
		
		 });
		
		game= new HBox(20,NumOfGames,cb);
		Player1= new HBox(20, dif1, play1);
		Player2= new HBox(20, dif2 ,play2);
		TheSetter = new VBox(40,game,Player1,Player2,start, close  );
		 
		// this is the image
		Image pic = new Image("bluemoon.png");
		BackgroundSize backSize = new BackgroundSize(
				BackgroundSize.AUTO,
				BackgroundSize.AUTO,
				false,
				false,
				true,
				false); 
		
		
		Background backGround = new Background(new BackgroundImage(
				pic,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				backSize
				)); 
		pane.setTop(menuBar); 
		pane.setLeft(TheSetter);
		pane.setRight(TicTacToe);
		pane.setBottom(WinnerQue);
		pane.setBackground(backGround);
		
		return new Scene(pane, 1080,720);
	}
	// this is located in the menu and tells you what to do 
	public Scene HowtoPlayScreen (Stage primaryStage)
	{
		Image pic = new Image("BACK.JPG");
		Text welcome = new Text();
		Text message = new Text();
		welcome.setText("How to Play");
		welcome.setStyle(" -fx-font: 40 arial;");
		
		message.setText("First you must pick the number of games you wany to play \n" +
		"Second you will have to pick how skillful you want the you players \n"+ 
			"Then just sit back and enjoy watching AI battle it Out in TICTACTOE\n"+
		"Just wait for the games to play then play again or else bugs ");
		message.setStyle(" -fx-font: 32 arial;");
		message.setFill(Color.BLACK);
		welcome.setFill(Color.BLACK);
		welcome.setTranslateX(425.0);
		message.setTranslateY(90);
		
		
		Button Back = new Button();
		 Back.setText("Back to Action");
		 Back.setScaleX(3);
		 Back.setScaleY(3);
		 Back.setTranslateX(-500);
		 Back.setTranslateY(0.0);
		 Back.setOnAction(e -> {
				primaryStage.setScene(sceneMap.get("Game")); 
			});; 
		BackgroundSize backSize = new BackgroundSize(
				BackgroundSize.AUTO,
				BackgroundSize.AUTO,
				false,
				false,
				true,
				false); 
		
	
		Background backGround = new Background(new BackgroundImage(
				pic,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				backSize
				)); 
		startPane = new BorderPane();
		
		startPane.setTop(welcome);
		startPane.setLeft(message);
		startPane.setBackground(backGround);
		startPane.setCenter(Back);
		pause.play();
		return new Scene(startPane, 1080,720);
	}
	
	
}
		