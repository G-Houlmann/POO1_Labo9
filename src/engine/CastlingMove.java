package engine;

import engine.piece.Piece;

public class CastlingMove extends Move {
    private Boolean castling;
    private Move towerMove;

    public CastlingMove(Vector from, Vector to, Piece piece){
        super(from, to, piece);
        castling = true;
        towerMove = new Move();

    }

    public Move getTowerMove(){
        return towerMove;
    }
}