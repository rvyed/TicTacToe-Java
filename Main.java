// Main.java written by Prof Luke. Please do not modify this file, but make your code work with this main program.
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Bridge Building Game!");

        int size;
        while (true) {
            System.out.println("Enter the size of the lake:");
            size = scanner.nextInt();
            if (size < 3 || size > 26) {
                System.out.println("The size must be between 3 and 26.");
            } else {
                break;
            }
        }

        int rounds;
        while (true) {
            System.out.println("Enter the number of rounds:");
            rounds = scanner.nextInt();
            if (rounds < 1) {
                System.out.println("The number of rounds must be at least 1.");
            } else {
                break;
            }
        }

        boolean hardMode;
        while (true) {
            System.out.println("Enter the difficulty level (1 for easy, 2 for hard):");
            int difficulty = scanner.nextInt();
            if (difficulty == 1) {
                hardMode = false;
                break;
            } else if (difficulty == 2) {
                hardMode = true;
                break;
            } else {
                System.out.println("The difficulty level must be 1 or 2.");
            }
        }
        scanner.nextLine();

        Player player = new Player();
        Engineer engineer = new Engineer(hardMode);

        for (int round = 0; round < rounds; round++) {
            GameBoard board = new GameBoard(size);
            System.out.println("\nRound: " + (round + 1));

            int playerLastRow = 0;
            int playerLastCol = 0;
            while(true) {
                board.displayBoard();

                int row, col;
                while (true) {
                    System.out.println("Enter your move (row col):");
                    String move = scanner.nextLine().toUpperCase();
                    if (!move.isEmpty()) {
                        // access characters from the input
	                    row = move.charAt(0) - '0';
	                    col = move.charAt(2) - 'A';
	                    if (row < 0 || row >= size || col < 0 || col >= size || !board.isPositionEmpty(row, col)) {
	                        System.out.println("Invalid move. Please enter a valid move.");
	                    } else {
	                        break;
	                    } 
                    }else {
                    	System.out.println("You must enter a valid move.");
                    }
                }

                player.makeMove(board, row, col);
                playerLastRow = row;
                playerLastCol = col;

                int winDirection = board.checkForWinDirection(player);
                if(winDirection != 0) {
                    System.out.println("You've won this round!");
                    int winScore = winDirection == 1 ? 5 : winDirection == 2 ? 7 : 10;
                    player.addScore(winScore + (size > 3 ? size - 3 : 0));
                    break;
                } else if (board.checkForTie()) {
                    System.out.println("The round ended in a tie!");
                    player.addScore(1);
                    break;
                } else {
                    engineer.makeMove(board, playerLastRow, playerLastCol);
                }
            }
        }

        scanner.close();
        System.out.println("Game Over. Your final score is: " + player.getScore());
    }
}