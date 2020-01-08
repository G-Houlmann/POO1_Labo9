package engine.rule;

import engine.Board;
import engine.move.Move;
import engine.piece.Piece;
import engine.util.Vector;

public abstract class Rule {
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
    public Move createMove(Vector to) {
        if (board.hasPieceAt(to)) 
            return new Move(piece.getPosition(), to, piece, board.getPieceAt(to));
        else
            return new Move(piece.getPosition(), to, piece);
    }
}