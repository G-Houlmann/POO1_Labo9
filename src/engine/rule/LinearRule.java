package engine.rule;

import engine.Board;
import engine.Move;
import engine.Vector;
import engine.piece.Piece;

public class LinearRule extends StraightRule {
    public LinearRule(Piece piece, Board board) {
        super(piece, board);
    }

    @Override
    public boolean check(Vector to) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Move createMove(Vector to) {
        // TODO Auto-generated method stub
        return null;
    }
}