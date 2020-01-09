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
public class DiagonalTakenRule extends PromotionRule implements DiagonalRule {

    public DiagonalTakenRule(Piece piece, Board board, Direction direction, Vector promotionLinePosition) {
        super(piece, board, direction, promotionLinePosition);
    }

    @Override
    public boolean check(Vector to) {
        return getBoard().hasPieceAt(to)
            && getBoard().getPieceAt(to).getColor() != getPiece().getColor()
            && checkPosition(getPiece().getPosition(), to, getDirection());
    }
}