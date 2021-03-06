package engine.util;

public class Vector {
    private int x;
    private int y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return La composante x
     */
    public int getX() {
        return x;
    }

    /**
     * @return La composante y
     */
    public int getY() {
        return y;
    }

    /**
     * Ajoute un vecteur unitaire dans la direction donnée
     * @param d Une direction
     * @return Un nouveau vecteur
     */
    public Vector add(Direction d) {
        return add(d.getDirectionVector());
    }

    /**
     * Ajoute n fois un vecteur unitaire dans la direction donnée
     * @param d Une direction
     * @param n Un facteur
     * @return Un nouveau vecteur
     */
    public Vector add(Direction d, int n) {
        return add(d.getDirectionVector().multiply(n));
    }

    /**
     * @param that Un vecteur
     * @return L'addition des deux vecteurs
     */
    public Vector add(Vector that) {
        return new Vector(this.x + that.x, this.y + that.y);
    }

    /**
     * @param that Un vecteur
     * @return La soustraction des deux vecteurs
     */
    public Vector substract(Vector that) {
        return new Vector(this.x - that.x, this.y - that.y);
    }

    /**
     * Ajoute un vecteur unitaire dans la direction donnée
     * @param d Une direction
     * @return Un nouveau vecteur
     */
    public Vector substract(Direction d) {
        return substract(d.getDirectionVector());
    }

    /**
     * Multiple un vecteur par un scalaire
     * @param scalar Un nombre
     * @return Un nouveau vecteur
     */
    public Vector multiply(int scalar) {
        return new Vector(scalar * x, scalar * y);
    }

    /**
     * @return Un vecteur normal
     */
    public Vector getNormalVector() {
        return new Vector(y, -x);
    }

    /**
     * @param that Un vecteur
     * @return true si les vecteurs sont colinéaires, false sinon
     */
    public boolean isCollinear(Vector that) {
        return this.x * that.y == this.y * that.x;
    }

    /**
     * @return Le carré de la norme du vecteur
     */
    public long squareNorm() {
        return x * x + y * y;
    }

    @Override
    public boolean equals(Object that) {
        return getClass() == that.getClass()
            && this.x == ((Vector) that).x
            && this.y == ((Vector) that).y;
    }

    public Vector inverser() {
        return new Vector(y, x);
    }
}