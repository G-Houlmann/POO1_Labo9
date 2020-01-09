import chess.ChessController;
import chess.ChessView;
import chess.views.console.ConsoleView;
import engine.Chess;

class ConsoleLauncher{
    public static void main(String[] args) {
        // 1. Création du contrôleur pour gérer le jeu d’échec
        ChessController controller = new Chess(); // Instancier un ChessController
        // 2. Création de la vue
        ChessView view = new ConsoleView(controller); // mode console
        // 3 . Lancement du programme.
        controller.start(view);
        }
}