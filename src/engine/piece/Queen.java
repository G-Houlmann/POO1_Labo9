package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.core.Board;
import engine.rule.LinearRule;
import engine.rule.Rule;
import engine.util.Vector;

public class Queen extends Piece {
    public Queen(Board board, PlayerColor color, Vector position) {
        super(board, color, position);
        rules = new Rule[] {
            new LinearRule(this, board, new Vector(0, 1)),
            new LinearRule(this, board, new Vector(1, 0)),
            new LinearRule(this, board, new Vector(1, 1)),
            new LinearRule(this, board, new Vector(1, -1))
        };
    }

    public PieceType getPieceType(){
        return PieceType.QUEEN;
    }
}