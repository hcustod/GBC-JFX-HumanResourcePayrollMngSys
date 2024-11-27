package org.live.humanresourcemangandpayrollsys.helpers;

import java.io.*;
import java.util.List;

public class FileHelper
{
    public static <T> void saveToFile(String filename, List<T> data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(data);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> List<T> loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<T>) ois.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
