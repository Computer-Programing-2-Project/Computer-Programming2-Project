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
 * System startup file-populating data utility.
 * FIXED: Properly parses primitive numeric inputs and calls the explicit subclass methods.
 * @author huzaifasuhail
 */
public class StartupLoader {
    private static final String STARTUP_FILE = "startup.txt";

    public static void loadFromFile(GymSystem system) {
        File file = new File(STARTUP_FILE);
        if (!file.exists()) {
            System.out.println("Startup config text file missing. Skipping pre-population step.");
            return;
        }

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;

                // Split line values by comma separators
                String[] tokens = line.split(",");
                for (int i = 0; i < tokens.length; i++) {
                    tokens[i] = tokens[i].trim();
                }

                String type = tokens[0].toUpperCase();

                if (type.equals("EMPLOYEE") || type.equals("TRAINER")) {
                    // Parse values safely into correct data types before sending to GymSystem
                    double salary = Double.parseDouble(tokens[1]);
                    String fName = tokens[2];
                    String lName = tokens[3];
                    String addr = tokens[4];
                    String phone = tokens[5];
                    
                    int id = system.getNextStaffId();
                    boolean isTrainer = type.equals("TRAINER");

                    // FIXED: Calls the correct parameter matching method signature in GymSystem
                    system.addEmployee(id, fName, lName, addr, phone, salary, isTrainer);

                } else if (type.equals("STAFF") || type.equals("STUDENT")) {
                    String dob = tokens[1];
                    String gender = tokens[2];
                    String fName = tokens[3];
                    String lName = tokens[4];
                    String addr = tokens[5];
                    String phone = tokens[6];
                    String extra1 = tokens[7]; // Position (Staff) or Course (Student)
                    String extra2 = tokens.length > 8 ? tokens[8] : ""; // Department (Staff) or Team (Student)
                    
                    int memberId = system.getNextMemberId();

                    // FIXED: Diverts records to their explicit matching sub-type methods
                    if (type.equals("STAFF")) {
                        system.addPolyStaff(memberId, dob, gender, extra1, extra2, fName, lName, addr, phone);
                    } else {
                        system.addPolyStudent(memberId, dob, gender, extra1, extra2, fName, lName, addr, phone);
                    }
                }
            }
            System.out.println("Startup configuration successfully processed into registry context.");
        } catch (Exception e) {
            System.err.println("Error reading startup configuration initialization parsing values: " + e.getMessage());
        } finally {
            if (reader != null) {
                try { 
                    reader.close(); 
                } catch (IOException e) {
                    // Quietly handle cleanup exception safely
                }
            }
        }
    }
}