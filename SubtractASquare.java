import java.util.Scanner;

// Subtract A Square is a game in which 2 players take it in turns to subtract-
// a square number from 3 heaps of coins until none remain.
// The winner of the game is the person who takes the last coin remaining.

public class SubtractASquare{
    public static void main(String[] args) {

        // Variable Initialisation:
        final int heapSize = 13;
        // Setting the heap size of all 3 of the heaps to 13;
        int heap1 = heapSize;
        int heap2 = heapSize;
        int heap3 = heapSize;
        final int three = 3;
        String playerWinner = null;
        String currentPlayer = "Player 1";
        int loops = 0; // Keeps track of which player is playing.
        // array to allow looping between the players with one block of code.
        String[] players = {"Player 1", "Player 2"}; 
        // Stops the game when true, meaning there is a winner:
        boolean winner = false;
        String lastPlayer = null;
        int firstPick = 0;
        String error = null;
        // the heap selected by the player for that round;
        int heap = 0;
        boolean skip = false;
        boolean player1Skip = false;
        boolean player2Skip = false;
        String legalError = "Sorry that's not a legal number of coins for that heap.";
        String legalCoins = "Sorry that's not a legal number of coins for that heap.";
        Scanner input = new Scanner(System.in); // creating the scanner object.

        do {
            lastPlayer = currentPlayer;
            skip = false;
            // resetting the next position to 0 as there is not more that 2 elements in the array;
            if (loops == 2){
                loops = 0;
            }
            currentPlayer = players[loops];
            // Checks that there are still coins remaining and shows the winner and exits the loop;
            if (heap1 == 0 && heap2 == 0 && heap3 == 0){
                winner = true;
                playerWinner = lastPlayer;
            }else {
                // The below if statement handles heap selection;
                if (error != "coinSelection"){
                    if (error == null){
                        System.out.println("Remaining coins: "+heap1+", "+heap2+", "+heap3);
                    }
                    System.out.print(currentPlayer + ": choose a heap: ");
                    String latestInput = input.nextLine().replaceAll("\\s", "").trim();
                    // checks if the user enters skip, It checks they have not used skip before;
                    if (latestInput.equalsIgnoreCase("skip")){
                        if (currentPlayer == "Player 1"){
                            if (player1Skip){
                                System.out.println("Sorry you have used your skip.");
                                error = "heapSelection";
                            }else {
                                loops = loops + 1;
                                skip = true;
                                player1Skip = true;
                                error = "heapSelection";
                            }
                        }else {
                            if (player2Skip){
                                System.out.println("Sorry you have used your skip.");
                                error = "heapSelection";
                            }else {
                                loops = loops + 1;
                                skip = true;
                                player2Skip = true;
                                error = "heapSelection";
                            }
                        }
                    }else if (latestInput.matches("^-?[0-9]\\d*(\\.\\d+)?$")){
                        heap = Integer.parseInt(latestInput);
                        error = null;
                    }else {
                        System.out.println("Sorry you must enter an integer or skip.");
                        error = "heapSelection";
                    }
                }
                // The below if statement handles the coin selection;
                if (heap <= three && heap >= 1 && error != "heapSelection" && !skip){
                    // This checks that the loop the user selected is not empty;
                    if ((heap==1&&heap1==0)||(heap==2&&heap2==0)||(heap==three&&heap3==0)){
                        System.out.println("Sorry, that's not a legal heap choice.");
                        error = "emptyHeap";
                    }else {
                        error = null;
                        System.out.print("Now choose a square number of coins: ");
                        // removes whitespaces from the input, then checks that the string -
                        // includes a number to prevent an exception;
                        String sanitised = input.nextLine().trim();
                        if (sanitised.matches("^-?[0-9]\\d*(\\.\\d+)?$")) {
                            firstPick = Integer.parseInt(sanitised);
                            // this checks that the number inputted is a square number;
                            final int x = (int) Math.sqrt(firstPick);
                            if (Math.pow(x, 2) == firstPick){
                                if (heap == 1){
                                    if (firstPick > heap1){
                                        System.out.println(legalError);
                                        error = "coinSelection";
                                    }else {
                                        heap1 = heap1 - firstPick;
                                        loops = loops + 1;
                                    }
                                }else if (heap == 2){
                                    if (firstPick > heap2){
                                        System.out.println(legalError);
                                        error = "coinSelection";
                                    }else {
                                        heap2 = heap2 - firstPick;
                                        loops = loops + 1;
                                    }
                                }else if (heap == three){
                                    if (firstPick > heap3){
                                        System.out.println(legalError);
                                        error = "coinSelection";
                                    }else {
                                        heap3 = heap3 - firstPick;
                                        loops = loops + 1;
                                    }
                                }
                            }else {
                                System.out.println(legalCoins);
                                error = "coinSelection";
                            }
                        }else {
                            System.out.println("Sorry you must enter an integer.");
                            error = "coinSelection";
                        }
                    }
                }else if (error != "heapSelection" && !skip && error != "coinSelection"){
                    System.out.println("Sorry, that's not a legal heap choice.");
                    error = "heapSelection";
                }
            }
        } while (!winner);
        System.out.println(playerWinner + " wins!");
    }
}
