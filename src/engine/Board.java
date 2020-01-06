package engine;

import engine.piece.Piece;

public class Board {
    private int turn;

    /**
     * @param v Un vecteur
     * @return true si une pièce se trouve à l'emplacement indiqué,
     *          false sinon ou si v est en-dehors des limites du
     *          plateau.
     */
    public boolean hasPieceAt(Vector v) {
        // TODO
        return true;
    }

    /**
     * @param v Une position supposée dans les limites du plateau
     * @return La pièce présente à la position v ou null si aucune
     *          ne s'y trouve
     */
    public Piece getPieceAt(Vector v) {
        // TODO
        return new Piece() {};
    }

    /**
     * @return Le numéro du tour en cours
     */
    public int getTurn() {
        return turn;
    }
}