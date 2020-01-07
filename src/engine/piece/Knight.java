package engine.piece;

import chess.PlayerColor;
import engine.Board;
import engine.Vector;
import engine.rule.LinearRule;
import engine.rule.Rule;

public class Knight extends Piece {
    Knight(Board board, PlayerColor color, Vector position) {
        super(board, color, position);
        rules = new Rule[] {
            new LinearRule(this, board, new Vector(1, 2), 1),
            new LinearRule(this, board, new Vector(1, -2), 1),
            new LinearRule(this, board, new Vector(2, 1), 1),
            new LinearRule(this, board, new Vector(2, -1), 1)
        };
    }
}