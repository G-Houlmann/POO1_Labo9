package engine.rule;

import engine.Board;
import engine.move.CastlingMove;
import engine.move.Move;
import engine.piece.Piece;
import engine.util.Direction;
import engine.util.Vector;

public class CastlingRule extends Rule {
    private final Direction direction;
    private final Vector secondPiecePosition;
    

    public CastlingRule(Piece piece, Board board, Direction direction, Vector secondPiecePosition) {
        super(piece, board);
        this.direction = direction;
        this.secondPiecePosition = secondPiecePosition;
    }

    @Override
    public boolean check(Vector to) {
        Vector destination = direction.getDirectionVector().multiply(2);
        Vector absoluteSecondPiecePosition = getPiece().getPosition().add(secondPiecePosition);
        
        /* Conditions de base :
         * - La case sur laquelle souhaite se rendre la pièce est bien une case où l'on peut roquer
         * - La pièce n'a pas encore bougé
         * - Une seconde pièce se trouve en position adéquate pour effectuer le roque
         * - Cette pièce est utilisable pour roquer
         * - Cette pièce n'a pas encore bougé
         */
        if (!destination.equals(to.substract(getPiece().getPosition()))
        || getPiece().hasMoved()
        || !getBoard().hasPieceAt(absoluteSecondPiecePosition)
        || !getBoard().getPieceAt(absoluteSecondPiecePosition).canBeUsedtoCastle()
        || getBoard().getPieceAt(absoluteSecondPiecePosition).hasMoved()) {
            return false;
        }

        // Vérifie que les cases sur le trajet sont vide et non attaquées
        Vector v = getPiece().getPosition().add(direction);
        while (v.substract(getPiece().getPosition()).squareNorm() < secondPiecePosition.squareNorm()) {
            if (getBoard().hasPieceAt(v) 
            || (getBoard().isAttacked(v, getPiece().getColor()) && v.substract(getPiece().getPosition()).squareNorm() <= destination.squareNorm())) {
                return false;
            }
            v = v.add(direction);
        }

        return true;
    }

    @Override
    public Move createMove(Vector to) {
        return new CastlingMove(getPiece().getPosition(),
            to, 
            getPiece(), 
            getBoard().getPieceAt(getPiece().getPosition().add(secondPiecePosition)),
            to.substract(direction));
    }
}