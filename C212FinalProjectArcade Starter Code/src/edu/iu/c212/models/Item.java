package edu.iu.c212.models;

public enum Item {
    // TODO Create final items
    // Test items
    APPLE ("apple", 12.5);

    String readableName;
    double value;

    Item(String readableName, double value) {
        this.readableName = readableName;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Name: " + readableName + " Price: " + value;
    }
}
