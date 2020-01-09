package engine.move;

import chess.ChessView;
import engine.piece.Piece;
import engine.util.Vector;

public class CastlingMove extends Move {
    private Move secondPieceMove;

    public CastlingMove(Vector from, Vector to, Piece piece, Piece secondPiece,
                        Vector secondMove){
        super(from, to, piece);
        secondPieceMove = new Move(secondPiece.getPosition(), secondMove,
                                   secondPiece);
    }

    @Override
    public void apply() {
        super.apply();
        secondPieceMove.apply();
    }

    @Override
    public void apply(ChessView view) {
        secondPieceMove.apply(view);
        super.apply(view);
    }

    @Override
    public void reverse() {
        secondPieceMove.reverse();
        super.reverse();
    }
}