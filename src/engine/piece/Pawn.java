package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;

public class Pawn extends Piece {
    public Pawn(Board board, PlayerColor color) {
        super(color, PieceType.PAWN, board)
    }
}