package edu.iu.c212.places;

import java.util.HashMap;
import java.util.List;

import edu.iu.c212.models.Item;
import edu.iu.c212.models.User;

public class Inventory extends Place {

    public Inventory(String placeName) {
        setPlaceName(placeName);
        setEntryFee(0);
        setIsGame(false);
    }

    @Override
    public void onEnter(User user) {
        List<Item> inventory = user.getInventory();
        HashMap<Item, Integer> items = new HashMap<>();
        int inventorySize = inventory.size();
        double netWorth = user.getBalance();

        for (Item item : inventory) {
            if(!items.containsKey(item))
                items.put(item, 1);
            else
                items.put(item, items.get(item) + 1);
        }

        System.out.println("Hello, " + user.getUsername() + "! Your inventory looks like this:");
        for (Item item : items.keySet()) {
            int amount = items.get(item);
            double itemValue = item.getValue();

            System.out.println(item.getName() + ": " + amount + " (value: " + itemValue + ")");
            netWorth += itemValue * amount;
        }
        System.out.println("Current balance is: $" + user.getBalance());
        System.out.println("Total Net worth: $" + netWorth);

        if (inventorySize == 3) {
            System.out.println("REMEMBER! You can only have 3 items at a time. Sell one by going to the Store");
        }
        
        getArcade().transitionArcadeState(Places.LOBBY);
    }
    
}
