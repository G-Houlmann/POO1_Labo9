package engine.move;

import chess.ChessView;
import chess.PlayerColor;
import engine.core.Board;
import engine.piece.Piece;
import engine.util.Vector;

public class PromotionMove extends Move {
    private Piece[] promotionCandidates;

    public PromotionMove(Vector from, Vector to, Piece piece, Piece[] promotionCandidates){
        this(from, to, piece, promotionCandidates, null);
    }

    public PromotionMove(Vector from, Vector to, Piece piece, Piece[] promotionCandidates, Piece taken) {
        super(from, to, piece, taken);
        this.promotionCandidates = promotionCandidates;
    }

    @Override
    public void apply(ChessView view) {
        Piece p = null;
        Board b = getPiece().getBoard();
        PlayerColor color = getPiece().getColor();
        while(p == null)
            p = view.askUser("Promotion !", "Choose a new piece:", promotionCandidates);
        b.removePiece(getPiece());
        b.addPiece(p);
        view.removePiece(getFrom().getX(), getFrom().getY());
        view.putPiece(p.getPieceType(), color, getTo().getX(), getTo().getY());
    }
}