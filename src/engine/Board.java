package engine;

import java.util.LinkedList;

import chess.PlayerColor;
import engine.piece.King;
import engine.piece.Piece;

public class Board {
    private int turn;
    private LinkedList<Piece> pieces;
    private History history;


    public Board(){
        pieces = new LinkedList<Piece>();
        turn = 0;
    }

    
    /** 
     * Ajoute une pièce au board
     * @param piece La pièce à ajouter
     */
    public void addPiece(Piece piece){
        pieces.add(piece);
    }

    /** 
     * Incrémente le compteur de tours
     */
    public void nextTurn(){
        turn++;
    }

    /**
     * @param v Un vecteur
     * @return true si une pièce se trouve à l'emplacement indiqué,
     *          false sinon ou si v est en-dehors des limites du
     *          plateau.
     */
    public boolean hasPieceAt(Vector v) {
        return getPieceAt(v) != null;
    }

    /**
     * @param v Une position supposée dans les limites du plateau
     * @return La pièce présente à la position v ou null si aucune
     *          ne s'y trouve
     */
    public Piece getPieceAt(Vector v) {
        return pieces.stream().filter((piece) -> piece.getPosition().equals(v)).findAny().orElse(null);
    }

    /**
     * @param piece La pièce à supprimer de la liste
     */
    public void removePiece(Piece piece){
        pieces.remove(piece);
    }

    /**
     * @return Le numéro du tour en cours
     */
    public int getTurn() {
        return turn;
    }

    /**
     * @return L'historique des mouvements
     */
    public History getHistory() {
        return history;
    }

    /**
     * @param position Case à analyser
     * @param color Une couleur
     * @return true si une pièce de la couleur color peut être menacée dans la case
     *          position, false sinon
     */
    public boolean isAttacked(Vector position, PlayerColor color) {
        color = color == PlayerColor.BLACK ? PlayerColor.WHITE : PlayerColor.BLACK;
        for (Piece piece : pieces) {
            if (piece.getColor() == color && piece.createMove(position).isValid()) {
                return true;
            }
        }

        return false;
    }

    /**
     * @param color Une couleur
     * @return true si le roi de la couleur color est menacé, false sinon
     */
    public boolean isCheck(PlayerColor color) {
        King k = (King) pieces.stream()
            .filter(piece -> piece.getClass() == King.class && piece.getColor() == color)
            .findAny().orElse(null);
        return isAttacked(k.getPosition(), color);
            
    }
}