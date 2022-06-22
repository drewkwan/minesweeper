package vttp.minesweeper.app;

import java.util.Random;
import java.util.Scanner;

public class Minesweeper {

    private int fieldHidden[][] = new int[10][10];
    private int fieldVisible[][] = new int[10][10]; //the game needs a player board and a visible board


    
    public boolean playMove() { //play move scan the row and number
        Scanner sc = new Scanner(System.in);
        
        /*System.out.println("\nEnter Row Number: "); //split this into two inputs (?) 
        int i = sc.nextInt();
        System.out.println("Enter Column Number");
        int j = sc.nextInt();
        */
        System.out.println("\nWhere would you like to dig? Input as row, col: 4,1");
        String input = sc.nextLine();
        String inputReplaced = input.replace(",", " ");
        String[] numbers = inputReplaced.split(" ");

        String row = numbers[0];
        String column = numbers[1];
        int i = Integer.parseInt(row);
        int j = Integer.parseInt(column);
        //Change these into where would you like to dig -> completed

        if(i < 0 || i >9 || j<0 || j>9 || fieldVisible[i][j] != 0) { //if row column is oout of bounds throw invalid input. play again 
            System.out.println("\nIncorrect Input!");
            return true;
        }
        
        if(fieldHidden[i][j] == 100) { //if mine, boom
            displayHidden();
            System.out.print("BOOM! You stepped on a mine and died :( \n GAME OVER LOL\n");
            return false;
        }

        else if (fieldHidden[i][j] == 0) { //if field hidden has no mines around, fixvisible
            fixVisible(i, j);
        }
        else {
            fixNeighbours(i, j); //otherwise call this
        }
        displayHidden();
        return true;
        

    }

    public void fixVisible(int i, int j) {
        fieldVisible[i][j] = 50; //if there are no mines, or it is 0
        if (i != 0) {  //and if i is not top row
            fieldVisible[i -1][j] = fieldHidden[i-1][j]; //reveal the one up 
            if (fieldVisible[i-1][j]==0) { //and if it is not revealed
                fieldVisible[i-1][j] = 50; //
            }
            if (j != 0) { 
                fieldVisible[i-1][j-1] = fieldHidden[i-1][j-1]; //reveal the one left
                if(fieldVisible[i-1][j-1] == 0)
                    fieldVisible[i-1][j-1] = 50; //again if no mines
                
            }
        }
        if (i != 9) {
            fieldVisible[i+1][j] = fieldHidden[i+1][j];
            if(fieldVisible[i+1][j]==0)
                fieldVisible[i+1][j] = 50;
            if(j != 9) {
                fieldVisible[i+1][j+1] = fieldHidden[i+1][j+1];
                if(fieldVisible[i+1][j+1]==0)
                    fieldVisible[i+1][j+1] = 50;
            }
        }
        if(j!=0)
        {
            fieldVisible[i][j-1]=fieldHidden[i][j-1];
            if(fieldVisible[i][j-1]==0) 
                fieldVisible[i][j-1] = 50;
            if(i!=9)
            {
                fieldVisible[i+1][j-1]=fieldHidden[i+1][j-1];
                if(fieldVisible[i+1][j-1]==0) fieldVisible[i+1][j-1] = 50;
            }
        }
        if(j!=9)
        {
            fieldVisible[i][j+1]=fieldHidden[i][j+1];
            if(fieldVisible[i][j+1]==0)
                 fieldVisible[i][j+1] = 50;
            if(i!=0)
            {
                fieldVisible[i-1][j+1]=fieldHidden[i-1][j+1];
                if(fieldVisible[i-1][j+1]==0) fieldVisible[i-1][j+1] = 50;
            }
        }
    }

    public void fixNeighbours(int i, int j)
    {
        Random random = new Random();
        int x = random.nextInt()%4; //if x 

        fieldVisible[i][j] = fieldHidden[i][j];

        if(x==0)
        {
            if(i!=0)
            {
                if(fieldHidden[i-1][j]!=100)
                {
                    fieldVisible[i-1][j] = fieldHidden[i-1][j];
                    if(fieldVisible[i-1][j]==0)  fieldVisible[i-1][j] = 50;
                }
            }
            if(j!=0)
            {
                if(fieldHidden[i][j-1]!=100)
                {
                    fieldVisible[i][j-1] = fieldHidden[i][j-1];
                    if(fieldVisible[i][j-1]==0)  fieldVisible[i][j-1] = 50;
                }

            }
            if(i!=0 && j!=0)
            {
                if(fieldHidden[i-1][j-1]!=100)
                {
                    fieldVisible[i-1][j-1] = fieldHidden[i-1][j-1];
                    if(fieldVisible[i-1][j-1]==0)  fieldVisible[i-1][j-1] = 50;
                }

            }
        }
        else if(x==1)
        {
            if(i!=0)
            {
                if(fieldHidden[i-1][j]!=100)
                {
                    fieldVisible[i-1][j] = fieldHidden[i-1][j];
                    if(fieldVisible[i-1][j]==0)  fieldVisible[i-1][j] = 50;
                }
            }
            if(j!=9)
            {
                if(fieldHidden[i][j+1]!=100)
                {
                    fieldVisible[i][j+1] = fieldHidden[i][j+1];
                    if(fieldVisible[i][j+1]==0)  fieldVisible[i][j+1] = 50;
                }

            }
            if(i!=0 && j!=9)
            {
                if(fieldHidden[i-1][j+1]!=100)
                {
                    fieldVisible[i-1][j+1] = fieldHidden[i-1][j+1];
                    if(fieldVisible[i-1][j+1]==0)  fieldVisible[i-1][j+1] = 50;
                }
            }
        }
        else if(x==2)
        {
            if(i!=9)
            {
                if(fieldHidden[i+1][j]!=100)
                {
                    fieldVisible[i+1][j] = fieldHidden[i+1][j];
                    if(fieldVisible[i+1][j]==0)  fieldVisible[i+1][j] = 50;
                }
            }
            if(j!=9)
            {
                if(fieldHidden[i][j+1]!=100)
                {
                    fieldVisible[i][j+1] = fieldHidden[i][j+1];
                    if(fieldVisible[i][j+1]==0)  fieldVisible[i][j+1] = 50;
                }

            }
            if(i!=9 && j!=9)
            {
                if(fieldHidden[i+1][j+1]!=100)
                {
                    fieldVisible[i+1][j+1] = fieldHidden[i+1][j+1];
                    if(fieldVisible[i+1][j+1]==0)  fieldVisible[i+1][j+1] = 50;
                }
            }
        }
        else
        {
            if(i!=9)
            {
                if(fieldHidden[i+1][j]!=100)
                {
                    fieldVisible[i+1][j] = fieldHidden[i+1][j];
                    if(fieldVisible[i+1][j]==0)  fieldVisible[i+1][j] = 50;
                }
            }
            if(j!=0)
            {
                if(fieldHidden[i][j-1]!=100)
                {
                    fieldVisible[i][j-1] = fieldHidden[i][j-1];
                    if(fieldVisible[i][j-1]==0) 
                    fieldVisible[i][j-1] = 50;
                }

            }
            if(i!=9 && j!=0)
            {
                if(fieldHidden[i+1][j-1]!=100)
                {
                    fieldVisible[i+1][j-1] = fieldHidden[i+1][j-1];
                    if(fieldVisible[i+1][j-1]==0)  fieldVisible[i+1][j-1] = 50;
                }
            }
        }
    }
    
    public void displayVisibile() { //this the current map
        System.out.print("\t ");
        for (int i =0; i<10; i++) {
            System.out.print(" " + i + "  "); //this displays the column spacing
        }
        System.out.print("\n");
        for (int i =0; i<10; i++) {
            System.out.print(i + "\t |"); 
            for (int j=0; j<10; j++) {
                if(fieldVisible[i][j]==0) { //if the value has not been exposed yet, print ?
                    System.out.print("?");
                }
                else if(fieldVisible[i][j] == 50) {
                    System.out.print("0"); //if there are no mines nearby, print 0
                }
                else {
                    System.out.print(fieldVisible[i][j]); //the field visible already prints the count
                }
                System.out.print(" | "); //creates the borders 
            }
            System.out.print("\n"); //start new line when ur i reaches 9
        }
    }

    public boolean checkWin() {
        for (int i=0; i<10; i++) {
            for (int j= 0; j<10; j++) {
                if(fieldVisible[i][j]== 0) {
                    if (fieldHidden[i][j] != 100) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void displayHidden() {
        System.out.print("\t ");
        for(int i=0; i<10; i++)
        {
            System.out.print(" " + i + "  ");
        }
        System.out.print("\n");
        for(int i=0; i<10; i++)
        {
            System.out.print(i + "\t| ");
            for(int j=0; j<10; j++)
            {
                if(fieldHidden[i][j]==0)
                {
                    System.out.print(" ");
                }
                else if(fieldHidden[i][j]==100)
                {
                    System.out.print("X");
                }
                else
                {
                    System.out.print(fieldHidden[i][j]);
                }
                System.out.print(" | ");
            }
            System.out.print("\n");
        }
    }
    
    public void buildHidden()
    {
        for(int i=0; i<10; i++)
        {
            for(int j=0; j<10; j++)
            {
                int cnt=0;
                if(fieldHidden[i][j]!=100) //if there is no mine
                {

                    if(i!=0) //this account for first row
                    {
                        if(fieldHidden[i-1][j]==100) cnt++; //if the one down has mine
                        if(j!=0)
                        {
                            if(fieldHidden[i-1][j-1]==100) cnt++; //if the one left and down has mine
                        }

                    }
                    if(i!=9) //accounts for last row
                    {
                        if(fieldHidden[i+1][j]==100) cnt++; //if there is mine one up
                        if(j!=9) //
                        {
                            if(fieldHidden[i+1][j+1]==100) cnt++; //i
                        }
                    }
                    if(j!=0) //accounts for first column
                    {
                        if(fieldHidden[i][j-1]==100) cnt++; //if 1 left
                        if(i!=9) //accounts for bottom row
                        {
                            if(fieldHidden[i+1][j-1]==100) cnt++; //if one up and one left
                        }
                    }
                    if(j!=9)
                    {
                        if(fieldHidden[i][j+1]==100) cnt++;
                        if(i!=0)
                        {
                            if(fieldHidden[i-1][j+1]==100) cnt++;
                        }
                    }

                    fieldHidden[i][j] = cnt; //display the count
                }
            }
        }

    }
    
    public void setupField (int diff) { //wot is diff
        int var=0;
        while(var != 10) {
            Random random = new Random();
            int i = random.nextInt(10); 
            int j = random.nextInt(10);
            System.out.println("i: " + i + "j: " +j); //this shows where the mines are generated
            fieldHidden[i][j] =100; //fieldHdden = 100 means there's a bomb there. 
            var++;
        }
        buildHidden();
    }
    
    public void startGame() { //not fully certain how this loop works so far
        System.out.println("\n\n ------Welcome to Minesweeper! --------\n");
        setupField(1); //wot this mean

        boolean flag = true;
        while (flag) {
            displayVisibile(); //display the map
            flag = playMove();
            if(checkWin()) {
                displayHidden();
                System.out.println("You win! Congratulations!");
                break;
            }
        }
    }

    public static void main(String[] args) {
        Minesweeper M = new Minesweeper();
        M.startGame();
    }


} //Class ends
//Create exit function 
//loop not rly working