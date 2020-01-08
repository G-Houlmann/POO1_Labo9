package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.Direction;
import engine.Vector;
import engine.rule.DiagonalTakenRule;
import engine.rule.DoubleForwardRule;
import engine.rule.EnPassantRule;
import engine.rule.ForwardRule;
import engine.rule.Rule;

public class Pawn extends Piece {
    public Pawn(Board board, PlayerColor color, Vector position, Direction direction) {
        super(board, color, position);
        rules = new Rule[] {
            new ForwardRule(this, board, direction),
            new DoubleForwardRule(this, board, direction),
            new EnPassantRule(this, board, direction),
            new DiagonalTakenRule(this, board, direction)};
    }

    @Override
    public boolean canBeTakenEnPassant() {
        return true;
    }

    public PieceType getPieceType(){
        return PieceType.PAWN;
    }
}