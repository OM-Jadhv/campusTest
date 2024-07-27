package org.owlmon.model;

public class Test {
    private int id;
    private String name;
    private int durationInSeconds;

    public Test(int id, String name, int durationInSeconds) {
        this.id = id;
        this.name = name;
        this.durationInSeconds = durationInSeconds;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getDurationInSeconds() { return durationInSeconds; }
}