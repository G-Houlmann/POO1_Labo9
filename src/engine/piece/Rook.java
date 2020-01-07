package engine.piece;

import chess.PlayerColor;
import engine.Board;
import engine.Vector;
import engine.rule.LinearRule;
import engine.rule.Rule;

public class Rook extends Piece {
    Rook(Board board, PlayerColor color, Vector position) {
        super(board, color, position);
        rules = new Rule[] {
            new LinearRule(this, board, new Vector(0, 1)),
            new LinearRule(this, board, new Vector(1, 0))
        };
    }

    @Override
    public boolean canBeUsedtoCastle() {
        return true;
    }
}