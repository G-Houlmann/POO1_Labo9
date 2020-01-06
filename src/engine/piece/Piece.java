package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.Vector;

public abstract class Piece {
    private int firstMoveTurn;
    private PlayerColor color;
    private PieceType type;

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

    /**
     * @return La couleur de la pièce
     */
    public PlayerColor getColor() {
        return color;
    }

    /**
     * @return Le numéro du tour au premier mouvement, 0 si
     * la pièce n'a pas encore bougé.
     */
    public int getFirstMoveTurn() {
        return firstMoveTurn;
    }

    /**
     * @return Le type de la pièce
     */
    public PieceType getType() {
        return type;
    }
}