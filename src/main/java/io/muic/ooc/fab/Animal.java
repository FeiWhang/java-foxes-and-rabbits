package io.muic.ooc.fab;

import java.util.Random;

public abstract class Animal {
    // The animal age
    private int age = 0;
    private boolean alive = true;
    // Random generator
    private static final Random RANDOM = new Random();


    protected void setAge(int age) {
        this.age = age;
    }

    protected void setAlive(boolean alive) {
        this.alive = alive;
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


    protected abstract void setDead();

}
