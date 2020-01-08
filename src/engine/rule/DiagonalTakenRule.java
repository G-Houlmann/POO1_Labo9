package engine.rule;

import engine.Board;
import engine.piece.Piece;
import engine.util.Direction;
import engine.util.Vector;

/**
 * Symbolise une prise en diagonale de distance 1, comme
 * celle applicable par un pion.
 * 
 * Le mouvement doit s'effectuer dans une direction donnée.
 */
public class DiagonalTakenRule extends DiagonalRule {
    public DiagonalTakenRule(Piece piece, Board board, Direction direction) {
        super(piece, board, direction);
    }

    @Override
    public boolean check(Vector to) {
        return board.hasPieceAt(to)
            && board.getPieceAt(to).getColor() != piece.getColor()
            && checkPosition(to);
    }
}