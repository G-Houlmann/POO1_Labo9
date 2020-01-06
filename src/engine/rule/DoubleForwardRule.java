package engine.rule;

import engine.Board;
import engine.Direction;
import engine.Vector;
import engine.piece.Piece;

public class DoubleForwardRule extends ForwardRule {
    public DoubleForwardRule(Piece piece, Board board, Direction direction) {
        super(piece, board, direction);
    }

    @Override
    public boolean check(Vector to) {
        
        return super.check(to);
    }
}