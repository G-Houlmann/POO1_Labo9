package engine.rule;

import engine.Board;
import engine.Direction;
import engine.Move;
import engine.Vector;
import engine.piece.Piece;

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
        return !piece.hasMoved()
            && !board.hasPieceAt(to)
            && piece.getPosition().add(direction, 2).equals(to)
            && !board.hasPieceAt(piece.getPosition().add(direction));
    }

    @Override
    public Move createMove(Vector to) {
        return new Move(piece.getPosition(), to, piece);
    }
}