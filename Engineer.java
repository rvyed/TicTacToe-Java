import java.util.Random;

public class Engineer {
    // Declare and initialize a char variable token to '0'
    private char token = '0';

    // Declare a boolean variable hardMode
    private boolean hardMode;

    // Declare and initialize an instance of the Random class
    private Random random = new Random();

    // Define the constructor for the Engineer class, which takes a boolean argument
    public Engineer(boolean hardMode) {
        // Initialize the hardMode instance variable with the value passed to the constructor
        this.hardMode = hardMode;
    }

    // Define a method to make a move on the game board
    public void makeMove(GameBoard board, int playerLastRow, int playerLastCol) {
        // If hardMode is true, make a move based on the player's last move
        if(hardMode) {
            // Recursive method to decide on the best course of action
            executeMoveWithStrategy(board, playerLastRow, playerLastCol);
        } else {
            // If hardMode is false, make a random move
            calculateRandomMove(board);
        }
    }

    // Method to strategically decide where to place the token based on the player's last move
    private void executeMoveWithStrategy(GameBoard board, int playerLastRow, int playerLastCol) {
        // If the player's last position is empty, place the token there
        if (board.isPositionEmpty(playerLastRow, playerLastCol)) {
            board.placeToken(playerLastRow, playerLastCol, this.token);
            return;
        }

        // If the player's last position isn't empty, make a random move
        calculateRandomMove(board);
    }

    // Method to calculate a random move and place the token on the board
    private void calculateRandomMove(GameBoard board) {
        // Get the size of the board
        int size = board.getSize();
        int row, col;

        // Calculate the coordinates of the new position
        int[] coordinates = getEmptyPosition(board, size);

        // Get the row and column values from the coordinates array
        row = coordinates[0];
        col = coordinates[1];

        // Place the token at the calculated position
        board.placeToken(row, col, this.token);
    }

    // Method to get an empty position on the board
    private int[] getEmptyPosition(GameBoard board, int size) {
        int row, col;
        // Continue generating random row and column values until an empty position is found
        do {
            row = random.nextInt(size);
            col = random.nextInt(size);
        } while(!board.isPositionEmpty(row, col));

        // Return the row and column values as an array
        return new int[] {row, col};
    }

    // Method to get the token character
    public char getToken() {
        // Return the token
        return this.token;
    }
}
