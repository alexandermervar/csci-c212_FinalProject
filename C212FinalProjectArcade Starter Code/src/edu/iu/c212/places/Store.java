package edu.iu.c212.places;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import edu.iu.c212.models.Item;
import edu.iu.c212.models.User;
import edu.iu.c212.utils.ConsoleUtils;

public class Store extends Place{

    private enum StoreAction{
        BUY, SELL, LEAVE;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    public Store(String placeName) {
        setPlaceName(placeName);
        setEntryFee(0.0);
        setIsGame(false);
    }

    @Override
    public void onEnter(User user) {
        List<StoreAction> choices = Arrays.asList(StoreAction.class.getEnumConstants());

        while (true) {
            StoreAction choice = ConsoleUtils.printMenuToConsole("Store",choices, true);

            switch (choice) {
                case BUY:
                    buyMenu(user);
                    break;
                case SELL:
                    sellMenu(user);
                    break;
                case LEAVE:
                    getArcade().transitionArcadeState(Places.LOBBY);
                default:
                    break;
            }
        }
    }

    private void sellMenu(User user) {

        List<Item> inventory = user.getInventory();

        List<Boolean> confirm = Arrays.asList(new Boolean[]{true, false});
        
        if (inventory.size() == 0) {
            System.out.println("Sorry, you have no items to sell!");
        }
        else {
            Item buyChoice = ConsoleUtils.printMenuToConsole("Choose and item to Sell", inventory, true);
            double sellPrice = buyChoice.getValue() * .5;

            System.out.println("You will only get $" + sellPrice + " back if you sell this item");

            boolean confirmation = ConsoleUtils.printMenuToConsole("Are you sure you want to buy this item", confirm, true);
            if (confirmation) {
                user.setBalance(user.getBalance() + sellPrice);
                user.getInventory().remove(buyChoice);
            }
        }

    }

    private void buyMenu(User user) {

        List<Item> choices = Arrays.asList(Item.class.getEnumConstants());

        List<Boolean> confirm = Arrays.asList(new Boolean[]{true, false});

        Item buyChoice = ConsoleUtils.printMenuToConsole("Choose and item to buy", choices, true);

        if (user.getInventory().size() >= 3) {
            System.out.println("Sorry, you cannot buy this item because your inventory is full!");
        }
        else if (user.getBalance() < buyChoice.getValue()) {
            System.out.println("Sorry, you cannot buy this item because you have insufficient funds!");
        }
        else {
            boolean confirmation = ConsoleUtils.printMenuToConsole("Are you sure you want to buy this item", confirm, true);
            if (confirmation) {
                user.setBalance(user.getBalance() - buyChoice.getValue());
                user.getInventory().add(buyChoice);
            }
        }
    }

}