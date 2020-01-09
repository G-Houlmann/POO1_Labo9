package engine.move;

import chess.ChessView;
import engine.piece.Bishop;
import engine.piece.Knight;
import engine.piece.Piece;
import engine.piece.Queen;
import engine.piece.Rook;
import engine.util.Vector;

public class PromotionMove extends Move {

    public PromotionMove(Vector from, Vector to, Piece piece){
        super(from, to, piece);
    }

    public PromotionMove(Vector from, Vector to, Piece piece, Piece taken) {
        super(from, to, piece, taken);
    }

    @Override
    public void apply(ChessView view) {
        Piece p = null;
        while(p == null)
            p = view.askUser("Promotion !", "Choose a new piece:",
                (Piece) new Knight(getPiece().getBoard(), getPiece().getColor(), getTo()),
                (Piece) new Rook(getPiece().getBoard(), getPiece().getColor(), getTo()),
                (Piece) new Bishop(getPiece().getBoard(), getPiece().getColor(), getTo()),
                (Piece) new Queen(getPiece().getBoard(), getPiece().getColor(), getTo()));
        p.getBoard().removePiece(getPiece());
        p.getBoard().addPiece(p);
        view.removePiece(getFrom().getX(), getFrom().getY());
        view.putPiece(p.getPieceType(), p.getColor(), getTo().getX(), getTo().getY());
    }
}