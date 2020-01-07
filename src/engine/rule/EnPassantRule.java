package engine.rule;

import engine.Board;
import engine.Direction;
import engine.Move;
import engine.Vector;
import engine.piece.Piece;

public class EnPassantRule extends OneWayRule {
    public EnPassantRule(Piece piece, Board board, Direction direction) {
        super(piece, board, direction);
    }

    /**
     * Vérifie que la pièce à la position position est bien éligible
     * à une prise en passant
     * @param position
     * @return true/false
     */
    private boolean checkEnPassantOnPiece(Vector position) {
        Piece p = board.getPieceAt(position);
        return p.canBeTakenEnPassant()
            && p.getColor() != piece.getColor()
            && p.getFirstMoveTurn() == board.getTurn() - 1
            && position.add(direction.getDirectionVector().multiply(2))
                .equals(board.getHistory().getLast().getFrom());
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