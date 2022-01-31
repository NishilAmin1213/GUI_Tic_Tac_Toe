package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Arrays;
//Note, variables that are being used throught the class must be outside of the constuctor
public class TicTacToeWindow extends JFrame {

    static char Checker(char[][] dat){
        //check for 3 rows, 3 columns, 2 diagonals
        for(int row=0;row<3;row++){//loops through the inner arrays
            if((dat[row][0] == dat[row][1] && dat[row][1] == dat[row][2])&&(dat[row][0] != ' ')){
                return dat[row][0];
            }else if((dat[0][row] == dat[1][row] && dat[1][row] == dat[2][row]) && (dat[0][row] != ' ')){
                return dat[0][row];
            }
        }
        if((dat[0][0] == dat[1][1] && dat[1][1] == dat[2][2]) && (dat[0][0] != ' ')){
            return dat[0][0];
        }
        if((dat[0][2] == dat[1][1] && dat[1][1] == dat[2][0]) && (dat[2][0] != ' ')){
            return dat[0][2];
        }
        return 'N';
    }

    private int turn = 1;     //this can be incremented each valid turn the tok%2 returns 1 or 0, 1 = x, 0 = o
    private char[][] dat = new char[][]{{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}}; //2d array for holding data in the board
    private JButton[] buttons = new JButton[9];

    public TicTacToeWindow(){

        setSize(500,500);
        setTitle("TIC TAC TOE :) ");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container content = this.getContentPane();
        //use a grid layout manager
        content.setLayout(new GridLayout(3,3));

        //array of values and other variable declaration
        //String[] dat = new String[9];
        //set all values of array to ""
        //for(int j=0;j<9;j++){
        //    dat[j] = "";
        //} alternate method of defining the array
        System.out.println(Arrays.toString(dat));

        // create and setup all buttons
        for(int i=0;i<9; i++){
            //all 9 JButtons are different entities and are not deleted after the loop ends
            // because the content pane has references to them --->
            JButton myButton = new JButton(""); //          |
            final int index = i;//final = constant variable       |
            content.add(myButton); //<-----------------------------
            myButton.setFont(new Font(myButton.getFont().getName(),myButton.getFont().getStyle(), 40));
            buttons[i] = myButton;

            myButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    char counter;
                    System.out.println(index); //action performed has access to index, because we have created the anonymous class
                    // in the same loop sas the button and the index, it has access to it
                    if(turn %2 == 1){
                        counter = 'X';
                        setTitle("PLAYER O TURN");
                        System.out.println("PLAYER X GO");
                    }else{
                        counter = 'O';
                        setTitle("PLAYER X TURN");
                        System.out.println("PLAYER O GO");
                    }
                    int row = index%3;
                    int col = index/3;
                    if(dat[row][col] != ' '){
                        setTitle("TRY AGAIN, INVALID MOVE");
                        System.out.println("PLACE TAKEN, TRY AGAIN");
                    }else{
                        //place token at dat[i]
                        dat[row][col] = counter;
                        myButton.setText(counter+"");
                        turn++; //to change person to next go
                        char winner = Checker(dat);
                        System.out.println(winner);
                        if(winner=='O' || winner=='X'){
                            int option = JOptionPane.showOptionDialog(
                                    TicTacToeWindow.this,
                                    "Player "+winner+" has Won! \n Do you want to play again",
                                    "GAME COMPLETED",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    null,
                                    null);
                            if(option == JOptionPane.YES_OPTION){
                                //change turn to 1, set board to spaces, and set button title to number
                                turn = 1;
                                dat = new char[][]{{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
                                for(int j=0;j<9;j++){
                                    buttons[j].setText("");
                                }
                            }else{
                                System.exit(0);
                            }
                        }else if(turn == 10){

                        }
                    }
                }
            });
        }
    //create 2d or 1d array to represent whats on the board, change this when buttons are pressed as we know the index in the array
    // tell the button what text should be displayed
        setVisible(true);
    }
}


/*
Program Minesweeper
grid, with random mined or empty spaces
click on empty space, shows all connected things that are spaces
click on mine, lost game
right click to label mines

COMPLETE OUTER LOOP WITH ANOTHER PARAMETER IN HASKELL REPLIT
 */