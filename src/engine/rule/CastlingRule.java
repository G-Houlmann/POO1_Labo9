package engine.rule;

import engine.Board;
import engine.Direction;
import engine.Move;
import engine.Vector;
import engine.piece.Piece;

public class CastlingRule extends Rule {
    private final Direction direction;
    private final Vector secondPiecePosition;
    

    public CastlingRule(Piece piece, Board board, Direction direction, Vector secondPiecePosition) {
        super(piece, board);
        this.direction = direction;
        this.secondPiecePosition = secondPiecePosition;
    }

    @Override
    public boolean check(Vector to) {
        Vector destination = direction.getDirectionVector().multiply(2);
        
        if (!destination.equals(to)
        || piece.hasMoved()
        || !board.hasPieceAt(secondPiecePosition)
        || !board.getPieceAt(secondPiecePosition).canBeUsedtoCastle()
        || !board.getPieceAt(secondPiecePosition).hasMoved()) {
            return false;
        }

        Vector v = piece.getPosition();
        while (v.squareNorm() < secondPiecePosition.squareNorm()) {
            v = v.add(direction);
            if (board.hasPieceAt(v) 
            || (board.isAttacked(v, piece.getColor()) && v.squareNorm() <= destination.squareNorm())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Move createMove(Vector to) {
        // TODO Auto-generated method stub
        return null;
    }
}