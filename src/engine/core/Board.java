package engine.core;

import java.util.LinkedList;

import chess.PieceType;
import chess.PlayerColor;
import engine.move.Move;
import engine.piece.King;
import engine.piece.Piece;
import engine.util.Vector;

public class Board {
    private int turn;
    private LinkedList<Piece> pieces;
    private History history;


    public Board(){
        pieces = new LinkedList<Piece>();
        history = new History();
        turn = 1;
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
     * @return true si une pièce se trouve à l'emplacement indiqué, false sinon.
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
        return pieces.stream().filter((piece) -> piece.getPosition()
        .equals(v)).findAny().orElse(null);
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
     * Ajoute un mouvement à l'historique
     * @param move Un mouvement
     */
    public void addToHistory(Move move) {
        history.add(move);
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
     * @return true si une pièce de la couleur color peut être menacée dans la
     *         case position, false sinon
     */
    public boolean isAttacked(Vector position, PlayerColor color) {
        color = color == PlayerColor.BLACK ? PlayerColor.WHITE : PlayerColor.BLACK;
        for (Piece piece : pieces) {
            if (piece.getColor() == color && piece.createMove(position).isValid()){
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
            .filter(piece -> piece.getPieceType() == PieceType.KING && 
            piece.getColor() == color).findAny().orElse(null);
        return isAttacked(k.getPosition(), color);
            
    }
}