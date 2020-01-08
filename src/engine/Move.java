package engine;

import java.util.Optional;

import chess.ChessController;
import engine.piece.Piece;

public class Move {
    private Vector from;
    private Vector to;
    private Piece piece;
    private Optional<Piece> taken;
    private Boolean validity;

    public Move(){
        validity = false;
    }

    public Move(Vector from, Vector to, Piece piece){
        this.from = from;
        this.to = to;
        this.piece = piece;
        this.validity = true;
    }

    
    /** 
     * @return La pièce concernée par le mouvement
     */
    public Piece getPiece(){
        return piece;
    }

    /**
     * @return La case de départ du mouvement
     */
    public Vector getFrom() {
        return from;
    }

    
    /** 
     * @return True si le mouvement est légal, c'est-à-dire qu'il est 
     *          valide (la case visée est atteignable) et qu'il ne cause pas 
     *          d'échecs à sa propre couleur. False sinon.
     */
    public Boolean isLegal(){
        if(validity && !(piece.getBoard().isCheck(piece.getColor()))){
            return true;
        }
        return false;
    }

    
    /** 
     * @return True si le mouvement est valide (la case visée est atteignable).
     *          False sinon.
     */
    public Boolean isValid(){
        return validity;
    }

    
    /** 
     * @return Le mouvement inverse au présent mouvement
     */
    public Move reverse(){
        //TODO gérer la prise
        return new Move(to, from, piece);
    }

    
    /** 
     * Applique le mouvement et ses conséquences (passage au tour suivant, 
     * destruction de l'éventuelle pièce prise...)
     * @param turn Le tour de jeu actuel
     */
    public void apply(int turn){
        /*if(taken.isPresent()){
            piece.getBoard().removePiece(taken.get());
        }*/ //TODO gérer la prise
        piece.move(to, turn);
    }
}