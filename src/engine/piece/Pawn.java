package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.core.Board;
import engine.rule.DiagonalTakenRule;
import engine.rule.DoubleForwardRule;
import engine.rule.EnPassantRule;
import engine.rule.ForwardRule;
import engine.rule.Rule;
import engine.util.Direction;
import engine.util.Vector;

public class Pawn extends Piece {

    public Pawn(Board board, PlayerColor color, Vector position, 
                Direction direction, Vector promotionLinePosition) {
        super(board, color, position);
        rules = new Rule[] {
            new ForwardRule(this, board, direction, promotionLinePosition),
            new DoubleForwardRule(this, board, direction),
            new EnPassantRule(this, board, direction),
            new DiagonalTakenRule(this, board, direction, promotionLinePosition)};
    }

    @Override
    public boolean canBeTakenEnPassant() {
        return true;
    }

    public PieceType getPieceType(){
        return PieceType.PAWN;
    }
}