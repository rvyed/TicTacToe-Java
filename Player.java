public class Player {
    private char token = '+';
    private int score = 0;

    public Player() { }

    public void makeMove(GameBoard board, int row, int col) {
        // Check for out-of-bounds
        if (row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize()) {
            System.out.println("Error: Out-of-bounds move. Please try again.");
            return;
        }
    
        // Check for already occupied position
        if (!board.isPositionEmpty(row, col)) {
            System.out.println("Error: Position already occupied. Please try again.");
            return;
        }
    
        // Make move
        board.placeToken(row, col, this.token);
    }
    

    public char getToken() {
        return this.token;
    }

    public int getScore() {
        return this.score;
    }

    public void addScore(int increment) {
        this.score += increment;
    }
}
