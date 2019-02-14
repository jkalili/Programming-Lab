/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  HighRoll.java
 *  Purpose       :  HW 02
 *  Author        :  Jason Kalili
 *  Date          :  2019-02-14
 *  Description   :  A program that creates sets of dice for the user to roll based on input on command
 *                   line. Implements a Textual User Interface (TUI) and uses methods from Die.java and
 *                   DiceSet.java.
 *  Notes         :  I had some help with this because I could not figure it out for the life of me but I understood
 *                   and wrote most of the code myself!
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
 import java.io.IOException;
 import java.util.Scanner;
 import java.util.Arrays;

 public class HighRoll {

   public static void main(String[] args) {

     int highScore = 0;
     String[] options = {"1", "2", "3", "4", "5"};
     String optionList = "Please Choose an Option: \n Option 1: Roll All Dice \n Option 2: Roll A Single Die \n Option 3: Calculate Score \n Option 4: Save As High Score \n Option 5: Display High Score \n Option 6: Enter Q To Quit";
   	 System.out.println( optionList ); //when first running program it prompts you

     Scanner sc1 = new Scanner(System.in);
   	 String input1 = sc1.nextLine();
   	 System.out.println("Enter the number of dice you'd like your set to have: "); //once you choose an option you get propmted to input your variables

     Scanner sc2 = new Scanner(System.in);
     String input2 = sc2.nextLine();
     System.out.println("Enter the number of sides you'd like to have on each of your dice (must be at least 4): "); //same as above

     Scanner sc3 = new Scanner(System.in);
     String input3 = sc3.nextLine();
   	while ( Arrays.asList(options).contains(input1) ) {
   	    DiceSet ds = new DiceSet( Integer.valueOf(input2), Integer.valueOf(input3) );
   	    if( input1.equals(options[0])) {
   	        ds.roll();
   	        System.out.println("The rolls from this set are: " + ds.toString() + "\n \n \n Please choose an Option: " + optionList);
   	        Scanner sc4 = new Scanner(System.in);
   	        input1 = sc4.nextLine();
   	    }

        if( input1.equals(options[1])) {
   	        System.out.println("Please indicate which die you'd like to roll: from 0 to " + (Integer.valueOf(input2)));
   	        Scanner sc5 = new Scanner(System.in);
   	        String input5 = sc5.nextLine();
            int result = Integer.parseInt(input5);
   	        System.out.println("The result of this roll is " + ds.rollIndividual(result) + "\n \n \n " + "Please choose an Option: " + optionList );
   	        Scanner sc6 = new Scanner(System.in);
   	        input1 = sc6.nextLine();
   	    }

        if( input1.equals(options[2])) {
   	        System.out.println("The total of the rolls in this set is: " + ds.sum() + " \n \n \n Please choose an Option: " + optionList);
   	        Scanner sc7 = new Scanner(System.in);
   	        input1 = sc7.nextLine();
   	    }

        if( input1.equals(options[3])) {
   	        highScore = ds.sum();
   	        System.out.println("Your High Score " + ds.sum() + " Has Been Saved." + "\n \n \n Please choose an Option: " + optionList);
   	        Scanner sc4 = new Scanner(System.in);
   	        input1 = sc4.nextLine();
   	    }

        if( input1.equals(options[4])) {
   	        highScore = ds.sum();
   	        System.out.println("Your High Score is: " + highScore + "\n \n \n Please choose an Option: " + optionList);
   	        Scanner sc4 = new Scanner(System.in);
   	        input1 = sc4.nextLine();
   	   }
   	 }
   }
 }
