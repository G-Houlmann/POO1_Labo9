import chess.ChessController;
import chess.ChessView;
import chess.views.gui.GUIView;
import engine.Chess;

class launcher{
    public static void main(String[] args) {
        // 1. Création du contrôleur pour gérer le jeu d’échec
        ChessController controller = new Chess(); // Instancier un ChessController
        // 2. Création de la vue
        ChessView view = new GUIView(controller); // mode GUI
        // = new ConsoleView(controller); // mode Console
        // 3 . Lancement du programme.
        controller.start(view);
        }
}

