package src.interfaces;

/**
 * An interface for classes that prompts the user to select
 * a piece and displays a list of valid moves for that piece.
 * @author Joseph Oladeji, Levi Sweat (100%), Nolan Flinchum, Thomas Kay
 */
public interface ShowMovesIF {
    
    /**
     * Prompts user to select a piece and then display the list of possible positions avaiable
     * to the specific piece.
     * 
     * @param BoardIF board The board that is used to check for pieces/positions
     * @param int turn The current turn number.
     * @param String player1 The name of player1.
     * @param String player2 The name of player2.
     * @return An empty string.
     */
    public String showMoves(BoardIF board, int turn, String player1, String player2);

}
