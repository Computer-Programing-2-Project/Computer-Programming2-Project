/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package polyfit;

/**
 *
 * @author huzaifasuhail
 */
import java.io.*;

/**
 * Utility class processing Object Serialization workflows for system data state.
 */
public class FileHandler {
    private static final String FILE_NAME = "gymData.ser";

    public static void saveSystem(GymSystem system) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            oos.writeObject(system);
            oos.close();
            System.out.println("System state saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving system data serialization state: " + e.getMessage());
        }
    }

    public static GymSystem loadSystem() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No saved session file located. Ready for Initialization step.");
            return null;
        }
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME));
            GymSystem system = (GymSystem) ois.readObject();
            ois.close();
            System.out.println("System state recovered successfully.");
            return system;
        } catch (Exception e) {
            System.err.println("Serialization failure processing load action: " + e.getMessage());
            return null;
        }
    }
}