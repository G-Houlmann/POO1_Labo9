package engine.rule;

import engine.Board;
import engine.piece.Piece;
import engine.util.Direction;
import engine.util.Vector;

/**
 * Implémente un déplacement double, comme celui d'un pion.
 * Les conditions sont :
 *  - La pièce n'a pas encore bougé
 *  - Aucune pièce n'est présente sur la case de destination
 *  - La case de destination est bien 2 cases après celle de la position actuelle,
 *    dans la bonne direction
 *  - La case entre la pièce et la case de destination est libre
 */
public class DoubleForwardRule extends OneWayRule {
    public DoubleForwardRule(Piece piece, Board board, Direction direction) {
        super(piece, board, direction);
    }

    @Override
    public boolean check(Vector to) {
        return !getPiece().hasMoved()
            && !getBoard().hasPieceAt(to)
            && getPiece().getPosition().add(getDirection(), 2).equals(to)
            && !getBoard().hasPieceAt(getPiece().getPosition().add(getDirection()));
    }
}