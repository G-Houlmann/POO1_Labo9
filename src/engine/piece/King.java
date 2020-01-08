package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.Direction;
import engine.Vector;
import engine.rule.CastlingRule;
import engine.rule.LinearRule;
import engine.rule.Rule;

public class King extends Piece {
   public King(Board board, PlayerColor color, Vector position) {
        super(board, color, position);
        rules = new Rule[] {
            new LinearRule(this, board, new Vector(0, 1), 1),
            new LinearRule(this, board, new Vector(1, 0), 1),
            new CastlingRule(this, board, Direction.RIGHT, new Vector(3, 0)),
            new CastlingRule(this, board, Direction.LEFT, new Vector(-4, 0))
        };
    }

    public PieceType getPieceType(){
        return PieceType.KING;
    }
}