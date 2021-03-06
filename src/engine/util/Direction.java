package engine.util;

/**
 * Symbolise une direction dans un système d'axes classique,
 * l'axe des abcisses va de gauche à droite et celui des ordonnées
 * de bas en haut
 */
public enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private Vector v;

    private Direction(int dx, int dy) {
        v = new Vector(dx, dy);
    }

    /**
     * @return un vecteur directeur et unitaire correspondant à la 
     * direction
     */
    public Vector getDirectionVector() {
        return v;
    }

    /**
     * @return Un vecteur normal et unitaire correspondant à la 
     * direction
     */
    public Vector getNormalVector() {
        return v.getNormalVector();
    }

    /**
     * @return La direction opposée
     */
    public Direction opposite() {
        switch(this) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
            default:
                return LEFT;
        }
    }
}