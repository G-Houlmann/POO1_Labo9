package engine;

import java.util.LinkedList;

public class History {
    private LinkedList<Move> moves;

    public History() {
        moves = new LinkedList<>();
    }

    /**
     * @return true si l'historique est vide, false sinon
     */
    public boolean isEmpty() {
        return moves.isEmpty();
    }

    /**
     * Ajoute un mouvement à l'historique
     * @param m Un mouvement
     */
    public void add(Move m) {
        moves.add(m);
    }

    /**
     * @return Le dernier mouvement joué
     * @throws RuntimeException si l'historique est vide
     */
    public Move getLast() {
        if (isEmpty())
            throw new RuntimeException("Empty history");
    
        return moves.getLast();
    }
}