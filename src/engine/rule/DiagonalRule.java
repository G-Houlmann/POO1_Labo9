package engine.rule;

import engine.util.Direction;
import engine.util.Vector;

interface DiagonalRule  {

    /**
     * Vérifie que la position de destination est accessible par un déplacement
     * en diagonal d'une seul case et faisant avancer la pièce d'une case
     * dans la direction donnée.
     * @param from Position de départ de la pièce
     * @param to Position d'arrivée de la pièce
     * @param direction Direction de déplacement de la pièce
     * @return true/false
     */
    default boolean checkPosition(Vector from, Vector to, Direction direction) {
        Vector normal = direction.getNormalVector();
        Vector base = from.add(direction);

        return base.add(normal).equals(to)
            || base.substract(normal).equals(to);
    }
}