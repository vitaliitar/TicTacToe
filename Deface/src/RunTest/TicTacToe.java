package RunTest;
import java.util.*;
import java.io.*;

public class TicTacToe implements AllMethods{
	
    public CellVal[][] board = new CellVal[3][3];
    private CellVal turn = CellVal.EMPTY;
    private TermState endState = TermState.UNKNOWN;
    private int move = -1;
 
    
    TicTacToe() {
    	displayMenu();
    	Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        if (choice == 1)
            turn = CellVal.X;
        else
            turn = CellVal.O;
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                board[i][j] = CellVal.EMPTY;
        }
       // in.close();
    }
    
    public void displayMenu(){
      
        System.out.println("You are X and the computer is O, - is a free space");
        System.out.println("Press 1 and you'll be the first or 2 then computer will start a game");
       
    }
    
    public int getUserMove(){
        boolean valid = false;
        Scanner in = new Scanner(System.in);
        while(!valid) {
            System.out.print("Enter your move: ");
            this.move = in.nextInt();
            this.move--;  //to account for 0 start in array
            if (move < 0 || move > 8)
                System.out.println("You can only move to spots 1-9");
            else if (this.board[move/3][move%3] != CellVal.EMPTY)
                System.out.println("That space is taken. Try again");
            else
                valid = true;
        }
      //  in.close();
        
        return move;
    }
    
    public int getCompMove() {
        System.out.println("Let me think...");
        TicTacNode node = new TicTacNode(this.board);
        node = node.findMax();
        return node.move; 
    }
    
    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            System.out.print("\t\t");
            for (int j = 0; j < 3; j++) {
                if (this.board[i][j] == CellVal.X) 
                    System.out.print("X ");
                else if (this.board[i][j] == CellVal.O)
                    System.out.print("O ");
                else
                    System.out.print("- ");
            }
            System.out.print("\n");
        }
    }
    
    public void printConclusion(TermState s) {

    	String res = "";
    	try (FileReader reader = new FileReader("C://Users//User//Downloads//text.txt");){
   		 int c;
            while((c=reader.read())!=-1){
                 res +=(char)c;
               // System.out.print((char)c);
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	int[] mas = new int[3];int i = 0;
    	   for (String retval : res.split(" ")) {
    	        // System.out.println("I'm here");
    	         mas[i] = Integer.parseInt(retval);
    	         i++;
    	      }
    	
    	
    	

        if (s == TermState.WIN) {
            System.out.println(" AI win.");
       
            mas[0]++;
            String h1= "";
            h1 += Integer.toString(mas[0]);
            h1 += " ";
            h1 += Integer.toString(mas[1]);
            h1 += " ";
            h1 += Integer.toString(mas[2]);
            
            
         	try (FileWriter writer = new FileWriter("C://Users//User//Downloads//text.txt", false)){
        		String ss = h1;
        		writer.write(ss);
        		
    		} catch (IOException e) {
    			System.out.println("Error");
    			e.printStackTrace();
    		}
         	System.out.println("Result l/w/d"+ h1);
            
       
            
        }
        else if (s == TermState.LOSE) {
            System.out.println("Oh, no! You have defeated me.");
            mas[1]++;
            
            String h1= "";
            h1 += Integer.toString(mas[0]);
            h1 += " ";
            h1 += Integer.toString(mas[1]);
            h1 += " ";
            h1 += Integer.toString(mas[2]);
            
            
         	try (FileWriter writer = new FileWriter("C://Users//User//Downloads//text.txt", false)){
        		String ss = h1;
        		writer.write(ss);
        		
    		} catch (IOException e) {
    			System.out.println("Error");
    			e.printStackTrace();
    		}
        	System.out.println("Result l/w/d"+ h1);
            
           // fw.close();
            
        }
        else {
            System.out.println("It's a draw! Good Game.");
            mas[2]++;
            
            String h1= "";
            h1 += Integer.toString(mas[0]);
            h1 += " ";
            h1 += Integer.toString(mas[1]);
            h1 += " ";
            h1 += Integer.toString(mas[2]);
            
            
         	try (FileWriter writer = new FileWriter("C://Users//User//Downloads//text.txt", false)){
        		String ss = h1;
        		writer.write(ss);
        		
    		} catch (IOException e) {
    			System.out.println("Error");
    			e.printStackTrace();
    		}
            
        	System.out.println("Result l/w/d"+ h1);
         //   fw.close();
            
        }
    }
    
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
       // game.displayMenu();
        if (game.turn == CellVal.O)
            System.out.print("Computer has");
        else
            System.out.print("You have");
        System.out.print(" been chosen to go first\n");
        
        while (game.endState == TermState.UNKNOWN) {
            int move;
            
            //user turn
            if (game.turn == CellVal.X) {
                move = game.getUserMove();  
                game.board[move/3][move%3] = game.turn;
                game.turn = CellVal.O; //switch turns
            }
            
            //computer's turn
            else {
                move = game.getCompMove();
                System.out.println("Move is: "+(move+1));
                game.board[move/3][move%3] = game.turn;
                game.turn = CellVal.X; //switch turns
            }
            
            game.printBoard();
            TicTacNode now = new TicTacNode(game.board);
            game.endState = now.terminalTest(game.board);
        } 
        
        //game is over
        game.printConclusion(game.endState);
        System.out.println("Thanks for playing. Goodbye!");
    }

   
}