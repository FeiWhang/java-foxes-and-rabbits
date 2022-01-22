package io.muic.ooc.fab;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class AnimalFactory {

    private static final Map<AnimalType, Class<? extends Animal>> ANIMAL_TYPE_CLASS_MAP = new HashMap<>();

    static {
        ANIMAL_TYPE_CLASS_MAP.put(AnimalType.RABBIT, Rabbit.class);
        ANIMAL_TYPE_CLASS_MAP.put(AnimalType.FOX, Fox.class);
    }

    public static Animal createAnimal(AnimalType animalType,boolean randomAge ,Field field, Location location) {

        try {
            return ANIMAL_TYPE_CLASS_MAP.get(animalType).getDeclaredConstructor(boolean.class, Field.class, Location.class).newInstance(randomAge, field, location);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Unknown animalType");
    }
}
