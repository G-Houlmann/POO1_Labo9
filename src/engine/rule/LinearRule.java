package engine.rule;

import engine.Board;
import engine.piece.Piece;
import engine.util.Vector;

public class LinearRule extends Rule {
    private final Vector direction;
    private final int range;

    public LinearRule(Piece piece, Board board, Vector direction, int range) {
        super(piece, board);
        this.direction = direction;
        this.range = range;
    }

    public LinearRule(Piece piece, Board board, Vector direction) {
        this(piece, board, direction, Integer.MAX_VALUE);
    }

    /**
     * Calcul la distance depuis la position de la pièce jusqu'à la 
     * case cible. 
     * 
     * Retourne le rapport entre le vecteur direction et le vecteur fromTo
     * 
     * Si la case n'est pas atteignable, c'est à dire  si les deux vecteurs 
     * ne sont pas colinéaires ou s'ils ne sont pas colinéaires d'un facteur 
     * entier , alors la fonction retourne 0.
     * @param to
     * @return
     */
    private int computeDistance(Vector to) {
        double distance = 0;
        Vector fromTo = to.substract(piece.getPosition());

        if (direction.isCollinear(fromTo)) {
            if (direction.getX() == 0) {
                distance = (double) fromTo.getY() / (double) direction.getY();
            } else {
                distance = (double) fromTo.getX() / (double) direction.getX();
            }
            distance = distance == (int) distance ? distance : 0;
        }

        return (int) distance;
    }

    @Override
    public boolean check(Vector to) {
        int distance = computeDistance(to);
        int sign = distance < 0 ? -1 : 1;
        distance = sign * distance;

        if (distance == 0 || distance > range) {
            return false;
        }
        
        for (int i = 1; i <= distance; ++i) {
            Vector position = piece.getPosition().add(direction.multiply(i * sign));
            // Si une case sur le chemin est occupée
            // ou si la case de destination est occupée par une pièce de même couleur
            if ((i < distance && board.hasPieceAt(position))
            || (board.hasPieceAt(position) && 
            board.getPieceAt(position).getColor() == piece.getColor())) {
                return false;
            }
        }

        return true;
    }
}