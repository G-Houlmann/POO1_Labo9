package engine.move;

import java.util.Optional;

import chess.ChessView;
import engine.piece.Piece;
import engine.util.Vector;

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
        this(from, to, piece, null);
    }

    public Move(Vector from, Vector to, Piece piece, Piece taken) {
        this.from = from;
        this.to = to;
        this.piece = piece;
        this.validity = true;
        this.taken = Optional.ofNullable(taken);
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
    public Boolean createsAllyCheck(){
        return piece.getBoard().isCheck(piece.getColor());
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
        Vector tmp = from;
        from = to;
        to = tmp;
        return this;
    }

    
    /** 
     * Applique le mouvement, vérifie si il crée un échec allié. Si c'est le cas,
     * Applique le mouvement inverse pour annuler les changements.
     * @return True si le mouvement s'est appliqué sans problème, false si il a
     * du être annulé.
     */
    public Boolean apply(){
        /*if (taken.isPresent()) {
            taken.get().removeFromBoard(); //TODO prise
        }*/
        if(validity){
            piece.move(to);
            if(createsAllyCheck()){
                Move reversed = this.reverse();
                reversed.apply();
                return false;
            }
            return true;
        }
        else{
            return false;
        }
        
    }

    /**
     * Applique un mouvement à une vue
     * @param view
     */
    public void apply(ChessView view) {
        view.removePiece(from.getX(), from.getY());
        view.putPiece(piece.getPieceType(), piece.getColor(), to.getX(), to.getY());
    }
}