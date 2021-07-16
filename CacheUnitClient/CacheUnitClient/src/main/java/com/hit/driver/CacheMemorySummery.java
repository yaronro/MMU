package com.hit.driver;


public class CacheMemorySummery {
    private final int capacity;
    private final int available;
    private int numberOfAction;
    private int putAndRemove;

    public CacheMemorySummery(int capacity, int available , int numberOfAction, int putAndRemove ) {
        this.capacity = capacity;
        this.available = available;
        this.numberOfAction = numberOfAction;
        this.putAndRemove = putAndRemove;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailable() {
        return available;
    }

    public int getNumberOfAction() {
        return numberOfAction;
    }

    public int getPutAndRemove() {
        return putAndRemove;
    }

}
