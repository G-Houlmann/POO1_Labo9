package engine.move;

import engine.piece.Piece;
import engine.util.Vector;

public class CastlingMove extends Move {
    private Move secondPieceMove;

    public CastlingMove(Vector from, Vector to, Piece piece, Piece secondPiece, Vector secondMove){
        super(from, to, piece);
        secondPieceMove = new Move(secondPiece.getPosition(), secondMove, secondPiece);
    }

    @Override
    public Boolean apply() {
        Boolean kingMoveLegal = super.apply();
        return kingMoveLegal && secondPieceMove.apply();
    }

    @Override
    public Move reverse() {
        secondPieceMove.reverse();
        return super.reverse();
    }
}