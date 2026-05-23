/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;
// TEST
/**
 *
 * @author huzaifasuhail
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GymSystem sys = new GymSystem();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Gym System Menu ===");
            System.out.println("1. Add Employee");
            System.out.println("2. Add Member");
            System.out.println("3. Update Member Details");
            System.out.println("4. Update Employee Details");
            System.out.println("5. Assign Member to Trainer");
            System.out.println("6. Remove Member from Trainer");
            System.out.println("7. Delete Employee");
            System.out.println("8. Delete Member");
            System.out.println("9. Show Members of Trainer");
            System.out.println("10. Generate Marketing Report");
            System.out.println("11. Save System Data");
            System.out.println("12. Load System Data");
            System.out.println("13. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("First name: ");
                    String fn = sc.nextLine();
                    System.out.print("Last name: ");
                    String ln = sc.nextLine();
                    System.out.print("Address: ");
                    String ad = sc.nextLine();
                    System.out.print("Phone: ");
                    String ph = sc.nextLine();
                    System.out.print("Salary: ");
                    double sal = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Is trainer (true/false): ");
                    boolean trainer = sc.nextBoolean();
                    sc.nextLine();
                    sys.addEmployee(fn, ln, ad, ph, sal, trainer);
                    System.out.println("Employee added.");
                    break;

                case 2:
                    System.out.print("First name: ");
                    fn = sc.nextLine();
                    System.out.print("Last name: ");
                    ln = sc.nextLine();
                    System.out.print("Address: ");
                    ad = sc.nextLine();
                    System.out.print("Date of Birth: ");
                    String dob = sc.nextLine();
                    System.out.print("Phone: ");
                    ph = sc.nextLine();
                    System.out.print("Gender: ");
                    String gender = sc.nextLine();
                    System.out.print("Type (staff/student): ");
                    String type = sc.nextLine();
                    System.out.print("Extra1 (position/course): ");
                    String extra1 = sc.nextLine();
                    System.out.print("Extra2 (department/team): ");
                    String extra2 = sc.nextLine();
                    sys.addMember(fn, ln, ad, dateOfBirth, ph, gender);
                    System.out.println("Member added.");
                    break;

                case 3:
                    System.out.print("Member ID: ");
                    int mid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New address: ");
                    ad = sc.nextLine();
                    System.out.print("New phone: ");
                    ph = sc.nextLine();
                    sys.updateMember(mid, ad, ph);
                    System.out.println("Member updated.");
                    break;

                case 4:
                    System.out.print("Employee ID: ");
                    int eid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New address: ");
                    ad = sc.nextLine();
                    System.out.print("New phone: ");
                    ph = sc.nextLine();
                    sys.updateEmployee(eid, ad, ph);
                    System.out.println("Employee updated.");
                    break;

                case 5:
                    System.out.print("Member ID: ");
                    mid = sc.nextInt();
                    System.out.print("Trainer ID: ");
                    eid = sc.nextInt();
                    sc.nextLine();
                    sys.assignMemberToTrainer(mid, eid);
                    System.out.println("Member assigned.");
                    break;

                case 6:
                    System.out.print("Member ID: ");
                    mid = sc.nextInt();
                    System.out.print("Trainer ID: ");
                    eid = sc.nextInt();
                    sc.nextLine();
                    sys.removeMemberFromTrainer(mid, eid);
                    System.out.println("Member removed from trainer.");
                    break;

                case 7:
                    System.out.print("Employee ID: ");
                    eid = sc.nextInt();
                    sc.nextLine();
                    sys.deleteEmployee(eid);
                    System.out.println("Employee deleted.");
                    break;

                case 8:
                    System.out.print("Member ID: ");
                    mid = sc.nextInt();
                    sc.nextLine();
                    sys.deleteMember(mid);
                    System.out.println("Member deleted.");
                    break;

                case 9:
                    System.out.print("Trainer ID: ");
                    eid = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Members of trainer:");
                    for (Member m : sys.getMembersOfTrainer(eid)) {
                        System.out.println(m.serialize());
                    }
                    break;

                case 10:
                    System.out.println("Marketing Report:");
                    System.out.println(sys.serialize()); // simple text output
                    break;

                case 11:
                    sys.saveData("gymdata.txt");
                    System.out.println("System data saved.");
                    break;

                case 12:
                    GymSystem loaded = GymSystem.loadData("gymdata.txt");
                    if (loaded != null) {
                        sys = loaded;
                        System.out.println("System data loaded.");
                    } else {
                        System.out.println("No data found.");
                    }
                    break;

                case 13:
                    running = false;
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

         public static void assignMemberToTrainer(PersonalTrainer trainer){
        System.out.println("Enter the member ID you would like to add: ");
        int id = scan.nextInt();
        Member memb = getMemberByID(id);
        trainer.assignMember(memb);
    }
    
    public static void removeMemberFromTrainer(Trainer trainer){
        System.out.println("Enter the member ID you would like to remove: ");
        int id = scan.nextInt();
        ArrayList<Member> membList = trainer.getAssignedMembers();
        Member person = membList.get(id);
        if(person != null){
            trainer.removeMember(person);
            System.out.println("Member removed");
        }else{
            System.out.println("Member is not in the List");
        }
        sc.close();
    }
}
