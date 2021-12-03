package edu.iu.c212.utils;

import edu.iu.c212.models.Item;
import edu.iu.c212.models.User;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtils {
    private static File file = new File("C212FinalProjectArcade Starter Code/src/edu/iu/c212/utils/Users.txt");

    // line format:
    // user_name|balance|item1,item2,item3
    // user name not allowed to contain pipe

    /**
     * Write user data to the file you provided above.
     *
     * @param users The total list of all users
     */
    public static void writeUserDataToFile(List<User> users) throws IOException {
        // loop through the list of the users and write each user to the file
        PrintWriter out = new PrintWriter(file);
        for (User user : users) {
            String userData = user.getUsername() + "|" + user.getBalance() + "|" + user.getInventory();
            out.println(userData);
        }
        out.close();
    }

    /**
     * Read user data from the file you provided above. Return a list of Users
     */
    public static List<User> getUserDataFromFile() throws IOException {
        File in = new File("C212FinalProjectArcade Starter Code/src/edu/iu/c212/utils/Users.txt");
        List<User> users = new ArrayList<>();
        
        // Read the file line by line and create a user for each line and add it to the list
        Scanner scan = new Scanner(in);
        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] userData = line.split("\\|");

            // Create a list from the inventory string
            String[] userInventoryString = userData[2].split(",");
            List<Item> userInventory = new ArrayList<Item>();

            for (String item : userInventoryString) {
                Item selectedItem = Item.valueOf(item);
                userInventory.add(selectedItem);
            }

            User user = new User(userData[0], Integer.parseInt(userData[1]), userInventory);
            users.add(user);
        }
        return users;
    }
}
