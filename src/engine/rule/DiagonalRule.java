package engine.rule;

import engine.Board;
import engine.piece.Piece;
import engine.util.Direction;
import engine.util.Vector;

public abstract class DiagonalRule extends OneWayRule {
    public DiagonalRule(Piece piece, Board board, Direction direction) {
        super(piece, board, direction);
    }

    /**
     * Vérifie que la position de destination est accessible
     * @param to Un vecteur
     * @return true/false
     */
    protected boolean checkPosition(Vector to) {
        Vector normal = direction.getNormalVector();
        Vector base = piece.getPosition().add(direction);

        return base.add(normal).equals(to)
            || base.substract(normal).equals(to);
    }
}