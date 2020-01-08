package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.rule.LinearRule;
import engine.rule.Rule;
import engine.util.Vector;

public class Bishop extends Piece {
    public Bishop(Board board, PlayerColor color, Vector position) {
        super(board, color, position);
        rules = new Rule[] {
            new LinearRule(this, board, new Vector(1, 1)),
            new LinearRule(this, board, new Vector(1, -1))
        };
    }

    public PieceType getPieceType(){
        return PieceType.BISHOP;
    }
}