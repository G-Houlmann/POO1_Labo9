package engine;

public class History {
    /**
     * @return true si l'historique est vide, false sinon
     */
    public boolean isEmpty() {
        // TODO
        return true;
    }

    /**
     * @return Le dernier mouvement joué
     * @throws RuntimeException si l'historique est vide
     */
    public Move getLast() {
        // TODO 
        if (false)
            throw new RuntimeException("Empty history");
        return new Move();
    }
}