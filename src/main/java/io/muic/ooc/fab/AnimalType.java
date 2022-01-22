package io.muic.ooc.fab;

import java.awt.Color;

public enum AnimalType {
    RABBIT(Rabbit.class, Color.ORANGE, 0.08),
    FOX(Fox.class, Color.BLUE, 0.02);

    private final Class<? extends Animal> animalClass;
    private final Color color;
    private final double spawnProbability;

    AnimalType(Class<? extends Animal> animalClass, Color color, double spawnProbability) {
        this.animalClass = animalClass;
        this.color = color;
        this.spawnProbability = spawnProbability;
    }

    public Class<? extends Animal> getAnimalClass() {
        return this.animalClass;
    }

    public Color getColor() {
        return this.color;
    }

    public double getSpawnProbability() {
        return this.spawnProbability;
    }
}
