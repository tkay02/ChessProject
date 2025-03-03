package src.interfaces;

/**
 * An interface for classes that prompts the user to select
 * a piece and displays a list of valid moves for that piece.
 * @author Joseph Oladeji, Levi Sweat, Nolan Flinchum (100%), Thomas Kay
 */
public interface ShowMovesIF {
    
    public String showMoves(BoardIF board, int turn, String player1, String player2);

}
