package engine.rule;

import engine.core.Board;
import engine.move.Move;
import engine.move.PromotionMove;
import engine.piece.Piece;
import engine.util.Direction;
import engine.util.Vector;

abstract class PromotionRule extends OneWayRule {
    private Vector promotionLinePosition;

    public PromotionRule(Piece piece, Board board, Direction direction, 
                         Vector promotionLinePosition) {
        super(piece, board, direction);
        this.promotionLinePosition = promotionLinePosition;
    }
    
    /**
     * @param to Case de destination
     * @return true si la pièce est promue en arrivant sur cette case,
     *          false sinon
     */
    boolean isPromoted(Vector to) {
        return to.substract(promotionLinePosition)
            .isCollinear(getDirection().getNormalVector());
    }

    @Override
    public Move createMove(Vector to) {
        if (isPromoted(to)) {
            return new PromotionMove(getPiece().getPosition(), to, getPiece(),
                                     getBoard().getPieceAt(to));
        } else {
            return super.createMove(to);
        }
    }
}