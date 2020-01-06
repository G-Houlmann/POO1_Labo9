package engine;

/**
 * Symbolise une direction dans un système d'axes classique,
 * l'axe des abcisses va de gauche à droite et celui des ordonnées
 * de bas en haut
 */
public enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(0,1);

    private Vector v;

    private Direction(int dx, int dy) {
        v = new Vector(dx, dy);
    }

    /**
     * @return un vecteur directeur correspondant à la 
     * direction
     */
    public Vector getDirectionVector() {
        return v;
    }
}