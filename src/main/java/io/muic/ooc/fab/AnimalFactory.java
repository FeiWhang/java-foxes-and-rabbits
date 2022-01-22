package io.muic.ooc.fab;

public class AnimalFactory {


    public static Animal createAnimal(AnimalType animalType,boolean randomAge ,Field field, Location location) {
        if (animalType.equals(AnimalType.RABBIT)) {
            return new Rabbit(randomAge, field, location);
        } else if (animalType.equals(AnimalType.FOX)) {
           return new Fox(randomAge, field, location);
        } else {
            throw new RuntimeException("Unknown animalType");
        }
    }
}
