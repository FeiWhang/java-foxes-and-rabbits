package io.muic.ooc.fab;

import java.awt.Color;

public enum AnimalType {
    RABBIT(Rabbit.class, Color.ORANGE, 0.08, 9),
    FOX(Fox.class, Color.BLUE, 0.04, 18),
    TIGER(Tiger.class, Color.RED, 0.01, 36),
    HUNTER(Hunter.class, Color.BLACK, 0.0005, Integer.MAX_VALUE);

    private final Class<? extends Animal> animalClass;
    private final Color color;
    private final double spawnProbability;
    private final int foodValue;

    AnimalType(Class<? extends Animal> animalClass, Color color, double spawnProbability, int foodValue) {
        this.animalClass = animalClass;
        this.color = color;
        this.spawnProbability = spawnProbability;
        this.foodValue = foodValue;
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

    public int getFoodValue() {
        return this.foodValue;
    }
}
