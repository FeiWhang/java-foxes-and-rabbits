package io.muic.ooc.fab;

import java.util.Random;

public abstract class Animal {
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
    private static final Random RANDOM = new Random();

    protected void setAge(int age) {
        this.age = age;
    }

    protected void setAlive(boolean alive) {
        this.alive = alive;
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

    protected void setField(Field field) {
        this.field = field;
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
    protected void incrementAge(int maxAge) {
        age++;
        if (age > maxAge) {
            setDead();
        }
    }

    /**
     * An animal can breed if it has reached the breeding age.
     */
    protected boolean canBreed(int breedingAge) {
        return age >= breedingAge;
    }

    /**
     * Indicate that the fox is no longer alive. It is removed from the field.
     */
    protected void setDead() {
        setAlive(false);
        if (location != null) {
            field.clear(location);
            location = null;
            setField(null);
        }
    }
}
