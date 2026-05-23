/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

/**
 *
 * @author huzaifasuhail
 */
import java.util.ArrayList;

public class GymSystem extends Reportabel  {

    static GymSystem loadData(String gymdatatxt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private ArrayList<Employee> employees = new ArrayList<>();
    private ArrayList<Member> members = new ArrayList<>();
    private int nextMemberId  = 1;
    private int nextStaffId   = 1;

    public Employee addEmployee(String fn, String ln, String ad,
                                String ph, double sal, boolean trainer) {
        Employee e = new Employee(nextStaffId ++, fn, ln, ad, ph, (int) sal, trainer);
        employees.add(e);
        return e;
    }

    public Member addMember(int memberId, String dateOfBirth, String gender, PersonalTrainer trainer, int id, String fn, String ln, String ad, String ph)  {
        Member m = new Member(memberId, dateOfBirth, , gender, PersonalTrainer trainer, int id, String fn, String ln, String ad, String ph));
        members.add(m);
        return m;
    }

    

    public Employee findEmployee(int id) {
        for (Employee e : employees) if (e.getId() == id) return e;
        return null;
    }

    public Member findMember(int id) {
        for (Member m : members) if (m.getId() == id) return m;
        return null;
    }

    public void assignMemberToTrainer(int memberId, int trainerId) {
        Member m = findMember(memberId);
        Employee t = findEmployee(trainerId);
        if (m != null && t != null && t.isTrainer()) {
            for (Employee e : employees) if (e.isTrainer()) e.removeMember((Member) (java.lang.reflect.Member) m);
            t.addMember((Member) (java.lang.reflect.Member) m);
        }
    }

    
    public String serialize() {
        StringBuilder sb = new StringBuilder();
        for (Employee e : employees) sb.append("EMP:").append(e.serialize()).append("\n");
        for (Member m : members) sb.append("MEM:").append(m.serialize()).append("\n");
        return sb.toString();
    }

    
    public void deserialize(String data) {
        String[] lines = data.split("\n");
        for (String line : lines) {
            if (line.startsWith("EMP:")) {
                Employee e = new Employee(0,"","","","",0,false);
                e.deserialize(line.substring(4));
                employees.add(e);
            } else if (line.startsWith("MEM:")) {
                Member m = new Member(0,"","","","","","","","","");
                m.deserialize(line.substring(4));
                members.add(m);
            }
        }
    }

    void updateMember(int mid, String ad, String ph) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void updateEmployee(int eid, String ad, String ph) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void removeMemberFromTrainer(int mid, int eid) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

   
    Iterable<Member> getMembersOfTrainer(int eid) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void saveData(String gymdatatxt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
  //  ID Generators  
     public int generateMemberId() {
        return nextMemberId++;
    }

   
    public int generateStaffId() {
        return nextStaffId++;
    }
    
    // ── Add Employee
    
    public void addEmployee(Employee e) {
        if (e == null) {
            throw new NullPointerException("Employee cannot be null");
        }
        employees.add(e);
    }

    //  Add Member
    
    public void addMember(Member m) {
        if (m == null) {
            throw new NullPointerException("Member cannot be null");
        }
        members.add(m);
    }

    //  Delete Employee

    public boolean deleteEmployee(int staffId) {
        Employee e = findEmployeeById(staffId);
        if (e == null) return false;

        if (e instanceof PersonalTrainer) {
            PersonalTrainer pt = (PersonalTrainer) e;
            if (pt.getMemberCount() > 0) {
                return false; // cannot delete — has members
            }
        }
        employees.remove(e);
        return true;
    }

    //  Delete Member
    public void deleteMember(int memberId) {
        Member m2 = findMemberById(memberId);
        if (m2 == null) return;

        // remove from trainer list if assigned
        if (m2.getTrainer()!= null) {
            m2.getTrainer().removeMember(m2);
            m2.setTrainer(null);
        }
        members.remove(m2);
    }

    //  Find by ID
    public Employee findEmployeeById(int staffId) {
        for (Employee e : employees) {
            if (e.getId()== staffId) {
                return e;
            }
        }
        return null;
    }

    
    public Member findMemberById(int memberId) {
        for (Member m : members) {
            if (m.getId()== memberId) {
                return m;
            }
        }
        return null;
    }

    //  Update
    public void updateEmployee(Employee e) {
        // fields already updated via setters
        // method exists for GUI panels to call
    }

    
    public void updateMember(Member m) {
        // fields already updated via setters
        // method exists for GUI panels to call
    }

    //  Assign Trainer 
    public void assignTrainer(int memberId, int trainerId) {
        Member m   = findMemberById(memberId);
        Employee e = findEmployeeById(trainerId);

        if (m == null || !(e instanceof PersonalTrainer)) return;

        PersonalTrainer pt = (PersonalTrainer) e;

        // remove from old trainer first if assigned
        if (m.toString()!= null) {
            m.toString().removeMember(m);
        }

        pt.addMember(m);
        m.setTrainer(pt);
    }

    
    public void removeTrainer(int memberId) {
        Member m = findMemberById(memberId);
        if (m == null || m.getTrainer() == null) return;

        m.getTrainer().removeMember(m);
        m.setTrainer(null);
    }

    //  Getters for lists
    public ArrayList<Member> getAllMembers() {
        return members;
    }

    
    public ArrayList<Employee> getAllEmployees() {
        return employees;
    }

    
    public ArrayList<PersonalTrainer> getTrainers() {
        ArrayList<PersonalTrainer> trainers = new ArrayList<>();
        for (Employee e : employees) {
            if (e instanceof PersonalTrainer) {
                trainers.add((PersonalTrainer) e);
            }
        }
        return trainers;
    }

   
    public ArrayList<Member> getTrainerMembers(int trainerId) {
        Employee e = findEmployeeById(trainerId);
        if (e instanceof PersonalTrainer) {
            return ((PersonalTrainer) e).getMembers();
        }
        return new ArrayList<>();
    }

    // ── Reportable interface methods
    public boolean canDeleteTrainer(int trainerId) {
        Employee e = findEmployeeById(trainerId);
        if (e instanceof PersonalTrainer) {
            return ((PersonalTrainer) e).getMemberCount() == 0;
        }
        return true; // regular employee — can always delete
    }

    
    public void generateMarketingReport() {
        try {
            BufferedWriter writer = new BufferedWriter(
                new FileWriter("marketingReport.txt"));

            // ── Staff section ──
            writer.write("============================");
            writer.newLine();
            writer.write("  POLYTECHNIC STAFF MEMBERS");
            writer.newLine();
            writer.write("============================");
            writer.newLine();

            int staffCount = 0;
            for (Member m : members) {
                if (m instanceof PolyStaff) {
                    PolyStaff ps = (PolyStaff) m;
                    writer.write("Name:       " + ps.getFirstName()
                        + " " + ps.getLastName());
                    writer.newLine();
                    writer.write("Address:    " + ps.getAddress());
                    writer.newLine();
                    writer.write("Phone:      " + ps.getPhone());
                    writer.newLine();
                    writer.write("Position:   " + ps.getPosition());
                    writer.newLine();
                    writer.write("Department: " + ps.getDepartment());
                    writer.newLine();
                    writer.write("----------------------------");
                    writer.newLine();
                    staffCount++;
                }
            }
            writer.write("Total Staff Members: " + staffCount);
            writer.newLine();
            writer.newLine();

            // ── Students section ──
            writer.write("============================");
            writer.newLine();
            writer.write("  POLYTECHNIC STUDENTS");
            writer.newLine();
            writer.write("============================");
            writer.newLine();

            int studentCount = 0;
            for (Member m : members) {
                if (m instanceof PolyStudent) {
                    PolyStudent ps = (PolyStudent) m;
                    writer.write("Name:    " + ps.getFirstName()
                        + " " + ps.getLastName());
                    writer.newLine();
                    writer.write("Address: " + ps.getAddress());
                    writer.newLine();
                    writer.write("Phone:   " + ps.getPhone());
                    writer.newLine();
                    writer.write("Course:  " + ps.getCourse());
                    writer.newLine();
                    writer.write("Team:    " + (ps.getTeam().isEmpty()
                        ? "None" : ps.getTeam()));
                    writer.newLine();
                    writer.write("----------------------------");
                    writer.newLine();
                    studentCount++;
                }
            }
            writer.write("Total Students: " + studentCount);
            writer.newLine();

            writer.close();
            System.out.println("Report generated successfully.");

        } catch (IOException ex) {
            System.out.println("Error generating report: "
                + ex.getMessage());
        }
}
