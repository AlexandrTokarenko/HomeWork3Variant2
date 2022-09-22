package homework.file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinaryFile<T> {
    public void writeBinaryToFile(List<T> arr, String filename)  {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
                outputStream.writeObject(arr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<T> readBinaryFromFile(String filename) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            return (ArrayList<T>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<T>();
        }

    }
}
