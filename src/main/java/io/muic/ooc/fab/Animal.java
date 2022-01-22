package io.muic.ooc.fab;

import java.util.List;
import java.util.Random;

public abstract class Animal {
    protected abstract int getMaxAge();
    protected abstract int getBreedingAge();
    protected abstract double getBreedingProbability();
    protected abstract int getMaxLitterSize();


    // Individual characteristics (instance fields).
    // The animal age
    private int age = 0;
    // Whether the animal is alive or not.
    private boolean alive = true;
    // The animal's position.
    private Location location;
    // The field occupied.
    private Field field;
    // Random generator
    protected static final Random RANDOM = new Random();

    /**
     * Create a animal. A animal can be created as a new born (age zero)
     * or with a random age.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Animal(boolean randomAge ,Field field, Location location) {
        this.field = field;
        setLocation(location);
        if (randomAge) {
            age = (RANDOM.nextInt(getMaxAge()));
        }
    }

    /**
     * Return the animal's location.
     *
     * @return The animal's location.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Place the animal at the new location in the given field.
     *
     * @param newLocation The animal's new location.
     */
    protected void setLocation(Location newLocation) {
        if (location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }

    protected Field getField() {
        return field;
    }

    /**
     * Check whether the fox is alive or not.
     *
     * @return True if the fox is still alive.
     */
    protected boolean isAlive() {
        return alive;
    }

    /**
     * Increase the age. This could result in the animal's death.
     */
    protected void incrementAge() {
        age++;
        if (age > getMaxAge()) {
            setDead();
        }
    }

    /**
     * An animal can breed if it has reached the breeding age.
     */
    protected boolean canBreed(int breedingAge) {
        return age >= breedingAge;
    }

    protected abstract void action(List<Animal> newAnimals);

    protected List<Location> getAdjacent() {
        return field.adjacentLocations(location);
    }

    /**
     * Generate a number representing the number of births, if it can breed.
     *
     * @return The number of births (may be zero).
     */
    protected int breed() {
        int births = 0;
        if (canBreed(getBreedingAge()) && RANDOM.nextDouble() <= getBreedingProbability()) {
            births = RANDOM.nextInt(getMaxLitterSize()) + 1;
        }
        return births;
    }

    /**
     * Check whether or not this animal is to give birth at this step. New births
     * will be made into free adjacent locations.
     *
     * @param newAnimals A list to return newly born animals.
     */
    protected void giveBirth(List<Animal> newAnimals, AnimalType animalType) {
        // New foxes are born into adjacent locations.
        // Get a list of adjacent free locations.
        List<Location> free = field.getFreeAdjacentLocations(location);
        int births = breed();
        for (int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Animal young = AnimalFactory.createAnimal(animalType,false, getField(), loc);
            newAnimals.add(young);
        }
    }

    /**
     * Indicate that the animal is no longer alive. It is removed from the field.
     */
    protected void setDead() {
        alive = false;
        if (location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }
}
