package engine.rule;

import engine.Board;
import engine.Move;
import engine.Vector;
import engine.piece.Piece;

public abstract class StraightRule extends Rule {
    private static Vector baseVector;

    public StraightRule(Piece piece, Board board) {
        super(piece, board);
    }

    @Override
    public boolean check(Vector to) {
        for (int direction = 0; direction < 4; ++direction) {
            for (int )
        }
    }

    @Override
    public Move createMove(Vector to) {
        // TODO Auto-generated method stub
        return null;
    }
}