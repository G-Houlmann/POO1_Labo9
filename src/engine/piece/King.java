package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.core.Board;
import engine.rule.CastlingRule;
import engine.rule.LinearRule;
import engine.rule.Rule;
import engine.util.Direction;
import engine.util.Vector;

public class King extends Piece {
   public King(Board board, PlayerColor color, Vector position, 
               Direction kingSideCastlingDirection) {
        super(board, color, position);
        rules = new Rule[] {
            new LinearRule(this, board, new Vector(0, 1), 1),
            new LinearRule(this, board, new Vector(1, 0), 1),
            new LinearRule(this, board, new Vector(1, 1), 1),
            new LinearRule(this, board, new Vector(1, -1), 1),
            new CastlingRule(this, board, kingSideCastlingDirection, 
                kingSideCastlingDirection.getDirectionVector().multiply(3)),
            new CastlingRule(this, board, kingSideCastlingDirection.opposite(),
                kingSideCastlingDirection.getDirectionVector().multiply(-4))
        };
    }

    public PieceType getPieceType(){
        return PieceType.KING;
    }
}