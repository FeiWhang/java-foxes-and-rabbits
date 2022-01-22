package io.muic.ooc.fab;

public abstract class Animal {
    // The animal age
    private int age;

    /**
     * Increase the age. This could result in the animal's death.
     */
    protected void incrementAge(int maxAge) {
        age++;
        if (age > maxAge) {
            setDead();
        }
    }

    protected abstract void setDead();

}
