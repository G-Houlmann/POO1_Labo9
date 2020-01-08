package engine.rule;

import engine.Board;
import engine.move.Move;
import engine.move.PromotionMove;
import engine.piece.Piece;
import engine.util.Direction;
import engine.util.Vector;

/**
 * Représente un mouvement d'une case dans une direction donnée,
 * comme le mouvement de base d'un pion.
 * 
 * La case de destination doit être libre.
 */
public class ForwardRule extends OneWayRule {
    private int promotionYValue;

    public ForwardRule(Piece piece, Board board, Direction direction, int promotionYValue) {
        super(piece, board, direction);
    }

    @Override
    public boolean check(Vector to) {
        return to.equals(piece.getPosition().add(direction))
            && !board.hasPieceAt(to);
    }

    @Override
    public Move createMove(Vector to) {
        if (piece.getPosition().getY() == promotionYValue) {
            return new PromotionMove(piece.getPosition(), to, piece);
        } else {
            return super.createMove(to);
        }
    }
}