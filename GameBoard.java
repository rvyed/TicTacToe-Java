
/**
 * Author: Raed LNU
 * 
 * The `GameBoard` class is designed to represent the game's playing grid. It uses a two-dimensional character array `board` to simulate the game board, where each cell represents a slot on the grid. At initialization, each cell is set to '.', an indication of an unoccupied slot. The `placeToken` method is utilized to place a player's marker on the game board, incorporating a pre-validation step using the `validate` method to ensure the move is legitimate and within the rules of the game.
 *
 * The class presented challenges in managing edge cases, such as out-of-bounds moves and the placement of invalid tokens. These were efficiently handled using exception handling mechanisms within the `validate` method. The class further includes the `checkForWin` and `checkForWinDirection` methods to handle the detection of win conditions across rows, columns, and diagonals. The `checkForTie` method is employed to identify a stalemate scenario when all slots are filled without a winner.
 *
 * The `displayBoard` method is integral to visualizing the current game state. Thorough testing, involving multiple game scenarios, ensured the methods behaved as expected under various conditions. This class serves as the central hub of the game, effectively maintaining the game state and validating player actions.
 */


public class GameBoard {
    // The game board represented as a 2D array
    private char[][] board;

    // The size of the game board
    private int size;

    // Constructor for the game board
    public GameBoard(int size) {
        // Initialize the size
        this.size = size;

        // Initialize the board
        this.board = new char[size][size];

        // Fill the board with initial value '.'
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = '.';
            }
        }
    }

    // Method to place a token on the board
    public void placeToken(int row, int col, char token) {
        // Validate the row, col, and token
        validate(row, col, token);

        // Place the token
        board[row][col] = token;
    }

    // Method to validate the row, col, and token
    private void validate(int row, int col, char token) {
        // Check for out-of-bounds move
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new RuntimeException("Out-of-bounds move. Please try again.");
        }

        // Check for invalid token
        if (token != '+' && token != '0') {
            throw new RuntimeException("Invalid token. Please try again.");
        }

        // Check for already occupied position
        if (board[row][col] != '.') {
            throw new RuntimeException("Position already occupied. Please try again.");
        }
    }

    // Method to check if a position is empty
    public boolean isPositionEmpty(int row, int col) {
        return board[row][col] == '.';
    }

    // Method to get the size of the board
    public int getSize() {
        return size;
    }

    // Method to display the board
    public void displayBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print((board[i][j] == '\u0000' ? '.' : board[i][j]) + "   ");
            }
            System.out.println();
        }
    }

    // Method to check for win in any direction
    public int checkForWinDirection(Player player) {
        char token = player.getToken();

        // Check rows and columns for win
        for (int i = 0; i < size; i++) {
            if (checkForWin(token, i, 0, 0, 1) || checkForWin(token, 0, i, 1, 0)) {
                return 1;
            }
        }

        // Check diagonals for win
        return (checkForWin(token, 0, 0, 1, 1) || checkForWin(token, 0, size - 1, 1, -1)) ? 1 : 0;
    }

    // Helper method to check for win in a specific direction
    private boolean checkForWin(char token, int startRow, int startCol, int dirRow, int dirCol) {
        for (int i = 0; i < size; i++) {
            if (board[startRow + dirRow * i][startCol + dirCol * i] != token) {
                return false;
            }
        }
        return true;
    }

    // Method to check for a tie
    public boolean checkForTie() {
        // Iterate over the board
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // If any position is empty, it's not a tie
                if (board[i][j] == '.') {
                    return false;
                }
            }
        }
        // If all positions are occupied, it's a tie
        return true;
    }
}

