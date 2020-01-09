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
    Piece getPiece(){
        return piece;
    }

    /**
     * @return La case de départ du mouvement
     */
    public Vector getFrom() {
        return from;
    }

    /**
     * @return La case d'arrivée du mouvement
     */
    public Vector getTo() {
        return to;
    }

    
    /** 
     * @return True si le mouvement est valide (la case visée est atteignable).
     *          False sinon.
     */
    public Boolean isValid(){
        return validity;
    }

    
    /** 
     * Applique l'inverse du mouvement pour l'annuler
     */
    public void reverse(){
        piece.move(from);
        if(taken.isPresent()){
            piece.getBoard().addPiece(taken.get());
        }
    }

    
    /** 
     * Applique le mouvement, vérifie si il crée un échec allié. Si c'est le cas,
     * Applique le mouvement inverse pour annuler les changements.
     * @return True si le mouvement s'est appliqué sans problème, false si il a
     * du être annulé.
     */
    public Boolean apply(){
        if (taken.isPresent()) {
            taken.get().removeFromBoard();
        }
        piece.move(to);
        return true;
    }

    /**
     * Applique un mouvement à une vue
     * @param view
     */
    public void apply(ChessView view) {
        if (taken.isPresent()) {
            view.removePiece(taken.get().getPosition().getX(), taken.get().getPosition().getY());
        }
        view.removePiece(from.getX(), from.getY());
        view.putPiece(piece.getPieceType(), piece.getColor(), to.getX(), to.getY());
    }
}