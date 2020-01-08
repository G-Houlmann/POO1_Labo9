package engine.rule;

import engine.Board;
import engine.piece.Piece;
import engine.util.Direction;
import engine.util.Vector;

/**
 * Symbolise une prise en diagonale de distance 1, comme
 * celle applicable par un pion.
 * 
 * Le mouvement doit s'effectuer dans une direction donnée.
 */
public class DiagonalTakenRule extends OneWayRule {
    public DiagonalTakenRule(Piece piece, Board board, Direction direction) {
        super(piece, board, direction);
    }

    /**
     * Vérifie que la position de destination est accessible
     * @param to Un vecteur
     * @return true/false
     */
    private boolean checkPosition(Vector to) {
        Vector normal = direction.getNormalVector();
        Vector base = piece.getPosition().add(direction);

        return base.add(normal).equals(to)
            || base.add(normal.multiply(-1)).equals(to);
    }

    @Override
    public boolean check(Vector to) {
        return board.hasPieceAt(to)
            && board.getPieceAt(to).getColor() != piece.getColor()
            && checkPosition(to);
    }
}