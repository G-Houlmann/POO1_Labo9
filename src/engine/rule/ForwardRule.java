package engine.rule;

import engine.Board;
import engine.Direction;
import engine.Move;
import engine.Vector;
import engine.piece.Piece;

/**
 * Représente un mouvement d'une case dans une direction donnée,
 * comme le mouvement de base d'un pion.
 * 
 * La case de destination doit être libre.
 */
public class ForwardRule extends OneWayRule {
    public ForwardRule(Piece piece, Board board, Direction direction) {
        super(piece, board, direction);
    }

    @Override
    public boolean check(Vector to) {
        return to.equals(piece.getPosition().add(direction))
            && !board.hasPieceAt(to);
    }

    @Override
    public Move createMove(Vector to) {
        // TODO Auto-generated method stub
        return null;
    }
}