package engine.move;

import engine.piece.Piece;
import engine.util.Vector;

public class PromotionMove extends Move {
    private Piece newPiece;

    public PromotionMove(Vector from, Vector to, Piece piece, Piece newPiece){
        super(from, to, piece);
        this.newPiece = newPiece;

    }

    public Piece getNewPiece(){
        return newPiece;
    }
}