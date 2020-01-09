package engine;


import chess.ChessController;
import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import engine.move.Move;
import engine.piece.Bishop;
import engine.piece.King;
import engine.piece.Knight;
import engine.piece.Pawn;
import engine.piece.Piece;
import engine.piece.Queen;
import engine.piece.Rook;
import engine.util.Direction;
import engine.util.Vector;

public class Chess implements ChessController {
    private static final int GRID_SIZE = 8;
    private static final int ROOK_DIST = 0;
    private static final int KNIGHT_DIST = 1;
    private static final int BISHOP_DIST = 2;
    private static final int QUEEN_DIST = 3;
    private static final int KING_DIST = 4;
    private static final int PAWN_HEIGHT = 1;

    private ChessView view;
    private Board board;
    
    private PlayerColor currentPlayer;

    /**
     * Démarre une nouvelle partie
     */
    @Override
    public void newGame() {
        fillBoard(view);
        currentPlayer = PlayerColor.WHITE;
    }

    
    /** 
     * démarre le jeu
     * @param view Vue utilisée pour l'exécution du jeu
     */
    @Override
    public void start(ChessView view) {
        this.view = view;
        view.startView();
        newGame();
    }
    
    /** 
     * Exécute, s'il est légal, un mouvement d'une case à une autre sur le plateau affiché 
     * @param fromX Coordonnée X de départ
     * @param fromY Coordonnée Y de départ
     * @param toX Coordonnée X de destination
     * @param toY Coordonnée Y de destination
     * @return True si l'application du mouvement s'est passée sans problème, false sinon.
     */
    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        Vector from = new Vector(fromX, fromY);
        Vector to = new Vector(toX, toY);

        if (!board.hasPieceAt(from)) {
            return false;
        }

        Piece p = board.getPieceAt(from);
        Move mv = p.createMove(to);

        if(mv.isValid() && p.getColor() == currentPlayer){
            mv.apply();
            if(board.isCheck(p.getColor())){//Le mouvement doit être annulé
                mv.reverse();
                displayCheck();
            } else{
                
                nextTurn();
                board.addToHistory(mv);
                mv.apply(view);
                displayCheck();
                
                return true;
            }
        }
        
        return false;
    }

    /**
     * Affiche le message "Check!" si le joueur actuel est en échec
     */
    private void displayCheck() {
        if (board.isCheck(currentPlayer)) {
            view.displayMessage("Check!");
        }
    }

    /**
     * Passe au tour de jeu suivant
     */
    private void nextTurn(){
        board.nextTurn();
        currentPlayer = currentPlayer == 
        PlayerColor.WHITE? PlayerColor.BLACK : PlayerColor.WHITE;
    }


    /** 
     * Remplit le plateau de jeu d'un joueur sur la vue ainsi que dans l'objet board
     * @param color Couleur des pièces du joueur
     * @param pawnsRow Ligne sur laquelle seront placés les pions du joueur
     * @param piecesRow Ligne sur laquelle seront placées les autres pièces
     * @param direction Direction dans laquelle les pions du joueur pourront avancer
     */
    private void putSet(PlayerColor color, int pawnsRow, int piecesRow, Direction direction, Vector promotionLinePosition){

        //crée les pions
        for(int i = 0; i < GRID_SIZE; ++i){
            view.putPiece(PieceType.PAWN, color, i, pawnsRow);
            board.addPiece(new Pawn(board, color, new Vector(i, pawnsRow), direction, promotionLinePosition));
        }

        //crée les tours
        view.putPiece(PieceType.ROOK, color, ROOK_DIST, piecesRow);
        view.putPiece(PieceType.ROOK, color, GRID_SIZE - 1 - ROOK_DIST, piecesRow);
        board.addPiece(new Rook(board, color, new Vector(ROOK_DIST, piecesRow)));
        board.addPiece(new Rook(board, color, new Vector(GRID_SIZE - 1 - ROOK_DIST, piecesRow)));
        

        //creé les cavaliers
        view.putPiece(PieceType.KNIGHT, color, KNIGHT_DIST, piecesRow);
        view.putPiece(PieceType.KNIGHT, color, GRID_SIZE - 1 - KNIGHT_DIST, piecesRow);
        board.addPiece(new Knight(board, color, new Vector(KNIGHT_DIST, piecesRow)));
        board.addPiece(new Knight(board, color, new Vector(GRID_SIZE - 1 - KNIGHT_DIST, piecesRow)));

        //crée les fous
        view.putPiece(PieceType.BISHOP, color, BISHOP_DIST, piecesRow);
        view.putPiece(PieceType.BISHOP, color, GRID_SIZE - 1 - BISHOP_DIST, piecesRow);
        board.addPiece(new Bishop(board, color, new Vector(BISHOP_DIST, piecesRow)));
        board.addPiece(new Bishop(board, color, new Vector(GRID_SIZE - 1 - BISHOP_DIST, piecesRow)));

        //crée la dame
        view.putPiece(PieceType.QUEEN, color, QUEEN_DIST, piecesRow);
        board.addPiece(new Queen(board, color, new Vector(QUEEN_DIST, piecesRow)));

        //crée le roi
        view.putPiece(PieceType.KING, color, KING_DIST, piecesRow);
        board.addPiece(new King(board, color, new Vector(KING_DIST, piecesRow), Direction.RIGHT));
    }

    
    /** 
     * Remplit le plateau de jeu sur la vue ainsi que dans l'objet board
     * @param view Vue utilisée pour l'exécution du jeu
     */
    private void fillBoard(ChessView view){
        board = new Board();
        putSet(PlayerColor.WHITE, PAWN_HEIGHT, 0, Direction.UP, new Vector(0, GRID_SIZE - 1));
        putSet(PlayerColor.BLACK, GRID_SIZE - 1 - PAWN_HEIGHT, GRID_SIZE-1, Direction.DOWN, new Vector(0,0));
    }
}