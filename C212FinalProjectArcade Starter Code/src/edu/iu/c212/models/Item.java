package edu.iu.c212.models;

public enum Item {

    TEDDYBEAR ("Teddy Bear", 100.0),
    CANDYBAR ("Candy Bar", 50.0),
    JOLLYRANCHERS ("Jolly Ranchers", 10.0),
    TINYARMYMEN ("Green Army Men", 5.0),
    HEADPHONES ("Headphones", 10000.0),
    DOGCHEWTOY ("Dog Chew Toy", 500.0),
    BOUNCYBALL ("Bouncy Ball", 75.0),
    TOOTSIEROLL ("100 Tootsie Rolls", 1),
    ANEWCAR ("A Brand New 2021 Subaru Outback", 1000000.0),
    GOPROCAMERA ("GoPro Camera", 1000.0),
    GIANTSTUFFEDMONKEY ("Giant Stuffed Monkey", 750.0),
    AVERYSMALLTOYDINOSAUR ("A Very Small Toy Dinosaur (SUPER RARE)", 1000000000000000000000.0),
    STICKYSLAPPYHANDFROMCHILDHOOD ("Sticky Slappy Hand From your Childhood", 100.0),
    SLIME ("Slime (Imported from Nickelodeon)", 1500.0);
    

    String readableName;
    double value;

    Item(String readableName, double value) {
        this.readableName = readableName;
        this.value = value;
    }

    // getters
    public String getName() {
        return readableName;
    }

    @Override
    public String toString() {
        return "Name: " + readableName + " Price: $" + value;
    }
}
