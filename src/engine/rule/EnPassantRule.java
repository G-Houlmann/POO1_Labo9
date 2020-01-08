package engine.rule;

import engine.Board;
import engine.move.Move;
import engine.piece.Piece;
import engine.util.Direction;
import engine.util.Vector;

public class EnPassantRule extends DiagonalRule {
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
        Vector backSquare = to.substract(direction);

        return checkPosition(to)
            && board.hasPieceAt(backSquare)
            && checkEnPassantOnPiece(backSquare);
    }

    @Override
    public Move createMove(Vector to) {
        return new Move(piece.getPosition(), to, piece, board.getPieceAt(to.substract(direction)));
    }
}