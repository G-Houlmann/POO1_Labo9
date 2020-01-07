package engine;

import chess.ChessController;
import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;

public class Chess implements ChessController {
    private final int GRID_SIZE = 8; //TODO globalisation


    private final int ROOK_DIST = 0;
    private final int KNIGHT_DIST = 1;
    private final int BISHOP_DIST = 2;
    private final int QUEEN_DIST = 3;
    private final int KING_DIST = 4;
    private final int PAWN_HEIGHT = 1;

    private ChessView view;
    

    @Override
    public void newGame() {
        // TODO Auto-generated method stub

    }

    private void putSet(PlayerColor color, int pawnsRow, int piecesRow){
        //put pawns
        for(int i = 0; i < GRID_SIZE; ++i){
            view.putPiece(PieceType.PAWN, color, i, pawnsRow);
        }

        //put rooks
        view.putPiece(PieceType.ROOK, color, ROOK_DIST, piecesRow);
        view.putPiece(PieceType.ROOK, color, GRID_SIZE - 1 - ROOK_DIST, piecesRow);

        //put knights
        view.putPiece(PieceType.KNIGHT, color, KNIGHT_DIST, piecesRow);
        view.putPiece(PieceType.KNIGHT, color, GRID_SIZE - 1 - KNIGHT_DIST, piecesRow);

        //put bishops
        view.putPiece(PieceType.BISHOP, color, BISHOP_DIST, piecesRow);
        view.putPiece(PieceType.BISHOP, color, GRID_SIZE - 1 - BISHOP_DIST, piecesRow);

        //put queen and king
        view.putPiece(PieceType.QUEEN, color, QUEEN_DIST, piecesRow);
        view.putPiece(PieceType.KING, color, KING_DIST, piecesRow);
    }

    private void fillBoard(ChessView view){
        putSet(PlayerColor.WHITE, PAWN_HEIGHT, 0);
        putSet(PlayerColor.BLACK, GRID_SIZE - 1 - PAWN_HEIGHT, GRID_SIZE-1);
    }

    @Override
    public void start(ChessView view) {
        // TODO Auto-generated method stub
        view.startView();
        this.view = view;
        fillBoard(view);
    }

    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        // TODO Auto-generated method stub
        view.removePiece(fromX, fromY);
        view.putPiece(PieceType.PAWN, PlayerColor.WHITE, toX, toY);
        return false;
    }
}