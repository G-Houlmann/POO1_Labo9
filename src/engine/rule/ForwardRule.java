package engine.rule;

import engine.core.Board;
import engine.piece.Piece;
import engine.util.Direction;
import engine.util.Vector;

/**
 * Représente un mouvement d'une case dans une direction donnée,
 * comme le mouvement de base d'un pion.
 * 
 * La case de destination doit être libre.
 */
public class ForwardRule extends PromotionRule {

    public ForwardRule(Piece piece, Board board, Direction direction, 
                       Vector promotionLinePosition) {
        super(piece, board, direction, promotionLinePosition);
    }

    @Override
    public boolean check(Vector to) {
        return to.equals(getPiece().getPosition().add(getDirection()))
            && !getBoard().hasPieceAt(to);
    }
}