/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package polyfit;

/**
 *
 * @author huzaifasuhail
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // 1. Try loading a previously serialized system state session
        GymSystem gym = FileHandler.loadSystem();

        // 2. If it is a fresh launch, instantiate a clean engine and populate from startup.txt
        if (gym == null) {
            gym = new GymSystem();
            StartupLoader.loadFromFile(gym);
        }

        // 3. Launch the user interface safely on the Swing Event Dispatch Thread
        final GymSystem finalizedSystemReference = gym;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu(finalizedSystemReference).setVisible(true);
            }
        });
    }
    
}
