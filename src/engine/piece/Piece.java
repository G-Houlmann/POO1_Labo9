package engine.piece;

import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.move.Move;
import engine.rule.Rule;
import engine.util.Vector;

public abstract class Piece implements ChessView.UserChoice {
    private int firstMoveTurn;
    private PlayerColor color;
    private Board board;
    private Vector position;
    protected Rule[] rules;

    public Piece(Board board, PlayerColor color, Vector position) {
        this.color = color;
        this.board = board;
        this.position = position;
    }

    /** 
     * @return Le type de la pièce
     */
    public abstract PieceType getPieceType();

    /**
     * @return La position de la pièce
     */
    public Vector getPosition() {
        return position;
    }

    /** 
     * Déplace la pièce
     * @param newPos Nouvelle position de la pièce sur le board
     */
    public void move(Vector newPos){
        position = newPos;
        // Retour à une position antérieure
        if (board.getTurn() <= firstMoveTurn) {
            firstMoveTurn = 0;
        } else if(!hasMoved()) {
            this.firstMoveTurn = board.getTurn();
        }
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
     * @return Le board sur lequel est la pièce
     */
    public Board getBoard(){
        return board;
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

    /**
     * Supprime la pièce du plateau auquel elle est associée
     */
    public void removeFromBoard() {
        board.removePiece(this);
    }

    @Override
    public String textValue() {
        return getClass().getName();
    }
}