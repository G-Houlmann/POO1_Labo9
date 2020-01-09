package engine.rule;

import engine.Board;
import engine.move.Move;
import engine.piece.Piece;
import engine.util.Direction;
import engine.util.Vector;

public class EnPassantRule extends OneWayRule implements DiagonalRule {
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
        Piece p = getBoard().getPieceAt(position);
        
        return p.canBeTakenEnPassant()
            && p.getColor() != getPiece().getColor()
            && p.getFirstMoveTurn() == getBoard().getTurn() - 1
            && position.add(getDirection().getDirectionVector().multiply(2))
                .equals(getBoard().getHistory().getLast().getFrom());
    }

    @Override
    public boolean check(Vector to) {
        Vector backSquare = to.substract(getDirection());

        return checkPosition(getPiece().getPosition(), to, getDirection())
            && getBoard().hasPieceAt(backSquare)
            && checkEnPassantOnPiece(backSquare);
    }

    @Override
    public Move createMove(Vector to) {
        return new Move(getPiece().getPosition(), to, getPiece(), getBoard().getPieceAt(to.substract(getDirection())));
    }
}