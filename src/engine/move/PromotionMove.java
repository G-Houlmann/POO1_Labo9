package engine.move;

import chess.ChessView;
import chess.PlayerColor;
import engine.core.Board;
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
        Board b = getPiece().getBoard();
        PlayerColor color = getPiece().getColor();
        while(p == null)
            p = view.askUser("Promotion !", "Choose a new piece:",
                (Piece) new Knight(b, color, getTo()),
                (Piece) new Rook(b, color, getTo()),
                (Piece) new Bishop(b, color, getTo()),
                (Piece) new Queen(b, color, getTo()));
                
        b.removePiece(getPiece());
        b.addPiece(p);
        view.removePiece(getFrom().getX(), getFrom().getY());
        view.putPiece(p.getPieceType(), color, getTo().getX(), getTo().getY());
    }
}