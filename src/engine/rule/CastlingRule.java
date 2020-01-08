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
        Vector absoluteSecondPiecePosition = piece.getPosition().add(secondPiecePosition);
        
        if (!destination.equals(to.substract(piece.getPosition()))
        || piece.hasMoved()
        || !board.hasPieceAt(absoluteSecondPiecePosition)
        || !board.getPieceAt(absoluteSecondPiecePosition).canBeUsedtoCastle()
        || board.getPieceAt(absoluteSecondPiecePosition).hasMoved()) {
            System.out.println("Conditions bases non remplies");
            return false;
        }

        Vector v = piece.getPosition().add(direction);
        while (v.substract(piece.getPosition()).squareNorm() < secondPiecePosition.squareNorm()) {
            if (board.hasPieceAt(v) 
            || (board.isAttacked(v, piece.getColor()) && v.substract(piece.getPosition()).squareNorm() <= destination.squareNorm())) {
                System.out.println("Pièce sur le chemin ou échec");
                return false;
            }
            v = v.add(direction);
        }

        return true;
    }

    @Override
    public Move createMove(Vector to) {
        // TODO Auto-generated method stub
        return null;
    }
}