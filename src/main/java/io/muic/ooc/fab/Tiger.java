package io.muic.ooc.fab;

import java.util.List;

public class Tiger extends Animal {
    // The age at which a tiger can start to breed.
    private static final int BREEDING_AGE = 20;
    // The age to which a tiger can live.
    private static final int MAX_AGE = 125;
    // The likelihood of a tiger breeding.
    private static final double BREEDING_PROBABILITY = 0.02;

    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 1;

    // Individual characteristics (instance fields).
    // The tiger's food level, which is increased by eating rabbits.
    private int foodLevel;

    /**
     * Create a tiger. A tiger can be created as a new born (age zero and not
     * hungry) or with a random age and food level.
     *
     * @param randomAge If true, the tiger will have random age and hunger level.
     * @param field     The field currently occupied.
     * @param location  The location within the field.
     */
    public Tiger(boolean randomAge, Field field, Location location) {
        super(randomAge, field, location);
        foodLevel = RANDOM.nextInt(AnimalType.RABBIT.getFoodValue() + AnimalType.FOX.getFoodValue());
    }

    @Override
    protected int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected int getBreedingAge() {
        return BREEDING_AGE;
    }

    @Override
    protected double getBreedingProbability() {
        return BREEDING_PROBABILITY;
    }

    @Override
    protected int getMaxLitterSize() {
        return MAX_LITTER_SIZE;
    }

    @Override
    protected void action(List<Animal> newTigers) {
        incrementAge();
        incrementHunger();
        if (isAlive()) {
            giveBirth(newTigers, AnimalType.TIGER);
            // Move towards a source of food if found.
            Location newLocation = findFood();
            if (newLocation == null) {
                // No food found - try to move to a free location.
                newLocation = getField().freeAdjacentLocation(getLocation());
            }
            // See if it was possible to move.
            if (newLocation != null) {
                setLocation(newLocation);
            } else {
                // Overcrowding.
                setDead();
            }
        }
    }

    /**
     * Make this tiger more hungry. This could result in the tiger's death.
     */
    private void incrementHunger() {
        foodLevel--;
        if (foodLevel <= 0) {
            setDead();
        }
    }

    /**
     * Look for rabbits or foxes adjacent to the current location.
     *
     * @return Where food was found, or null if it wasn't.
     */
    private Location findFood() {
        for (Location where : getAdjacent()) {
            Animal animal = getField().getObjectAt(where);
            if (animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;
                if (rabbit.isAlive()) {
                    rabbit.setDead();
                    foodLevel = AnimalType.RABBIT.getFoodValue();
                    return where;
                }
            } else if (animal instanceof Fox) {
                Fox fox = (Fox) animal;
                if (fox.isAlive()) {
                    fox.setDead();
                    foodLevel = AnimalType.FOX.getFoodValue();
                    return where;
                }
            }
        }
        return null;
    }
}
