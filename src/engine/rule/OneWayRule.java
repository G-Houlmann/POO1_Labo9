package engine.rule;

import engine.Board;
import engine.Direction;
import engine.piece.Piece;

public abstract class OneWayRule extends Rule {
    protected Direction direction;

    OneWayRule(Piece piece, Board board, Direction direction) {
        super(piece, board);
        this.direction = direction;
    }
}