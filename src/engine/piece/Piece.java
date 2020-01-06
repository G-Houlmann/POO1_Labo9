package engine.piece;

import engine.Vector;

public abstract class Piece {
    private int firstMoveTurn;

    /**
     * @return La position de la pièce
     */
    public Vector getPosition() {
        // TODO
        return new Vector(0,0);
    }

    /**
     * @return true si la pièce a déjà effectué un mouvement,
     * false sinon.
     */
    public boolean hasMoved() {
        return firstMoveTurn > 0;
    }
}