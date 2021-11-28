package edu.iu.c212.utils;

import edu.iu.c212.models.User;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileUtils {
    private static File file = new File("your-file-location-here");

    // line format:
    // user_name|balance|item1,item2,item3
    // user name not allowed to contain pipe

    /**
     * Write user data to the file you provided above.
     *
     * @param users The total list of all users
     */
    public static void writeUserDataToFile(List<User> users) throws IOException {
    }

    /**
     * Read user data from the file you provided above. Return a list of Users
     */
    public static List<User> getUserDataFromFile() throws IOException {
    }
}
