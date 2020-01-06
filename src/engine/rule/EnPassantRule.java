package engine.rule;

import chess.PieceType;
import engine.Board;
import engine.Direction;
import engine.History;
import engine.Move;
import engine.Vector;
import engine.piece.Piece;

public class EnPassantRule extends OneWayRule {
    private History history;

    public EnPassantRule(Piece piece, Board board, Direction direction, History history) {
        super(piece, board, direction);
        this.history = history;
    }

    /**
     * Vérifie que la pièce à la position position est bien éligible
     * à une prise en passant
     * @param position
     * @return true/false
     */
    private boolean checkEnPassantOnPiece(Vector position) {
        Piece p = board.getPieceAt(position);
        return p.getType() == PieceType.PAWN
            && p.getColor() != piece.getColor()
            && p.getFirstMoveTurn() == board.getTurn() - 1
            && position.manhattanDistance(history.getLast().getFrom()) == 2;
            
    }

    @Override
    public boolean check(Vector to) {
        Vector backSquare = to.add(direction.getDirectionVector().multiply(-1));

        return board.hasPieceAt(backSquare)
            && checkEnPassantOnPiece(backSquare);
    }

    @Override
    public Move createMove(Vector to) {
        // TODO Auto-generated method stub
        return null;
    }
}