package engine.piece;

import chess.PlayerColor;
import engine.Board;
import engine.Move;
import engine.Vector;
import engine.rule.Rule;

public abstract class Piece {
    private int firstMoveTurn;
    private PlayerColor color;
    private Board board; // Inutile ?
    private Vector position;
    protected Rule[] rules;

    protected Piece(Board board, PlayerColor color, Vector position) {
        this.color = color;
        this.board = board;
        this.position = position;
    }

    /**
     * @return La position de la pièce
     */
    public Vector getPosition() {
        return position;
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
     * Retourne un mouvement permettant d'atteindre la destination to.
     * 
     * Si la case n'est pas atteignable avec l'ensemble de règles de 
     * la pièce, un mouvement invalide est retourné.
     * @param to Case de destination
     * @return Un mouvement
     */
    public Move createMove(Vector to) {
        for (Rule rule : rules) {
            if (rule.check(to)) {
                return rule.createMove(to);
            }
        }

        return new Move();
    }

    /**
     * @return true s'il est possible de prendre la pièce en passant,
     * false sinon
     */
    public boolean canBeTakenEnPassant() {
        return false;
    }

    /**
     * @return true s'il est possible d'utiliser la pièce pour effectuer un roque',
     * false sinon
     */
    public boolean canBeUsedtoCastle() {
        return false;
    }
}