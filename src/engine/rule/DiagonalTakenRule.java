package engine.rule;

import engine.Board;
import engine.move.Move;
import engine.move.PromotionMove;
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
    private int promotionYValue;
    public DiagonalTakenRule(Piece piece, Board board, Direction direction, int promotionYValue) {
        super(piece, board, direction);
        this.promotionYValue = promotionYValue;
    }

    @Override
    public boolean check(Vector to) {
        return board.hasPieceAt(to)
            && board.getPieceAt(to).getColor() != piece.getColor()
            && checkPosition(to);
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