package io.muic.ooc.fab;

import java.lang.reflect.InvocationTargetException;

public class AnimalFactory {

    public static Animal createAnimal(AnimalType animalType,boolean randomAge ,Field field, Location location) {

        try {
            return animalType.getAnimalClass().getDeclaredConstructor(boolean.class, Field.class, Location.class).newInstance(randomAge, field, location);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Unknown animalType");
    }
}
