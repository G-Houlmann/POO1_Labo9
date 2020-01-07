package engine;

import java.util.Optional;
import engine.piece.Piece;

public class Move {
    Vector from;
    Vector to;
    Piece piece;
    Optional<Piece> taken;
    Boolean validity;

    public Move(){

    }

    public Move(Vector from, Vector to, Piece piece){
        this.from = from;
        this.to = to;
        this.piece = piece;
    }

    /**
     * @return La case de départ du mouvement
     */
    public Vector getFrom() {
        return from;
    }

    public Move reverse(){
        return new Move();
    }

    public Boolean apply(){
        return true;
    }
}