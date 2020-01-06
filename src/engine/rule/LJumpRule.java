package engine.rule;

import engine.Board;
import engine.Move;
import engine.Vector;
import engine.piece.Piece;

public class LJumpRule extends Rule {
    static final Vector BASE_MOVES[] = {new Vector(1, 2), new Vector(2, 1)};

    public LJumpRule(Piece piece, Board board) {
        super(piece, board);
    }

    @Override
    public boolean check(Vector to) {
        for (Vector v : BASE_MOVES) {
            for (int direction = 0; direction < 4; ++direction) {
                if (v.equals(to)
                    && (!board.hasPieceAt(v) || board.getPieceAt(v).getColor() != piece.getColor())) {
                    return true;
                }

                v = v.getNormalVector();
            }
        }
        return false;
    }

    @Override
    public Move createMove(Vector to) {
        // TODO Auto-generated method stub
        return null;
    }
}