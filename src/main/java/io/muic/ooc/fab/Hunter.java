package io.muic.ooc.fab;

import java.util.List;

public class Hunter extends Animal {
    // The age at which a fox can start to breed.
    private static final int BREEDING_AGE = 18;
    // The age to which a fox can live.
    private static final int MAX_AGE = 100;
    // The likelihood of a fox breeding.
    private static final double BREEDING_PROBABILITY = 0.1;

    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 2;

    /**
     * Create a animal. A animal can be created as a new born (age zero)
     * or with a random age.
     *
     * @param randomAge If true, the hunter will have random age and hunger level.
     * @param field     The field currently occupied.
     * @param location  The location within the field.
     */
    public Hunter(boolean randomAge, Field field, Location location) {
        super(randomAge, field, location);
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
    protected void action(List<Animal> newHunters) {
        // hunter is immortal
        giveBirth(newHunters, AnimalType.HUNTER);
        // Move towards a source of food if found.
        Location newLocation = findPrey();
        if (newLocation == null) {
            // No food found - try to move to a free location.
            newLocation = getField().freeAdjacentLocation(getLocation());
        }
        // See if it was possible to move.
        if (newLocation != null) {
            setLocation(newLocation);
        }
    }

    /**
     * Hunt for rabbits, foxes or tigers adjacent to the current location.
     *
     * @return Where food was found, or null if it wasn't.
     */
    private Location findPrey() {
        for (Location where : getAdjacent()) {
            Animal animal = getField().getObjectAt(where);
            if (animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;
                if (rabbit.isAlive()) {
                    rabbit.setDead();
                    return where;
                }
            } else if (animal instanceof Fox) {
                Fox fox = (Fox) animal;
                if (fox.isAlive()) {
                    fox.setDead();
                    return where;
                }
            } else if (animal instanceof Tiger) {
                Tiger tiger = (Tiger) animal;
                if (tiger.isAlive()) {
                    tiger.setDead();
                    return where;
                }
            }
        }
        return null;
    }
}
