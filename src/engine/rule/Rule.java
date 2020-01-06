package engine.rule;

import engine.Board;
import engine.Move;
import engine.Vector;
import engine.piece.Piece;

abstract class Rule {
    Piece piece;
    Board board;

    public Rule(Piece piece, Board board) {
        this.piece = piece;
        this.board = board;
    }

    /**
     * Vérifie que le mouvement est possible selon la règle
     * @param to Case cible supposée dans les limites du plateau
     * @return true/false
     */
    public abstract boolean check(Vector to);

    /**
     * Génère un nouveau mouvement correspondant à la règle depuis la
     * case actuelle de piece jusqu'à la case cible
     * @param to Case cible supposée atteignable par un mouvement valide
     *          (il est donc vivement conseillé d'appeler check au préalable)
     * @return Un mouvement
     */
    public abstract Move createMove(Vector to);
}