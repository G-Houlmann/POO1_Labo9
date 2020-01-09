package engine.rule;

import engine.Board;
import engine.piece.Piece;
import engine.util.Direction;

/**
 * Représente les mouvements restreints à une direction,
 * comme ceux des pions
 */
public abstract class OneWayRule extends Rule {
    private Direction direction;

    OneWayRule(Piece piece, Board board, Direction direction) {
        super(piece, board);
        this.direction = direction;
    }

    /**
     * @return La direction
     */
    Direction getDirection() {
        return direction;
    }
}