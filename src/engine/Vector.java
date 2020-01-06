package engine;

public class Vector {
    private int x;
    private int y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
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
     * @param that Un vecteur
     * @return L'addition des deux vecteurs
     */
    public Vector add(Vector that) {
        return new Vector(this.x + that.x, this.y + that.y);
    }
}