/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package polyfit;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Core Controller Class managing Gym operations and processing data logic rules.
 * Aligned with project requirements Req-15 and Req-16.
 * * @author huzaifasuhail
 */
public class GymSystem implements Reportable, Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<Employee> employees;
    private ArrayList<Member> members;
    private int nextMemberId;
    private int nextStaffId;

    public GymSystem() {
        this.employees = new ArrayList<Employee>();
        this.members = new ArrayList<Member>();
        this.nextMemberId = 1;
        this.nextStaffId = 1;
    }

    // Accessors for Collections
    public ArrayList<Employee> getEmployees() { 
        return employees; 
    }
    
    public ArrayList<Member> getMembers() { 
        return members; 
    }

    // ID Sequence Tracking Management
    public int getNextMemberId() { 
        return nextMemberId; 
    }
    
    public int getNextStaffId() { 
        return nextStaffId; 
    }

    // --- Employee Core Logic Operations ---
    
    /**
     * Fixed Method to safely instantiate and add employees/trainers.
     * Rearranged parameter mapping sequence to match specific constructor designs.
     */
    public Employee addEmployee(int id, String fn, String ln, String ad, String ph, double salary, boolean isTrainer) {
        // Req-15: Ensure parameters do not accept empty values or invalid logic states
        if (fn == null || ln == null || ad == null || ph == null) {
            throw new NullPointerException("Employee text parameter arguments cannot be null.");
        }
        if (fn.trim().isEmpty() || ln.trim().isEmpty()) {
            throw new IllegalArgumentException("Employee name fields cannot be blank.");
        }
        if (salary < 0) {
            throw new IllegalArgumentException("Base salary values cannot be negative.");
        }

        Employee emp;
        if (isTrainer) {
            // FIXED: Salary is passed as the second argument to match PersonalTrainer constructor layout
            emp = new PersonalTrainer(id, salary, fn, ln, ad, ph);
        } else {
            // FIXED: Salary is passed as the second argument to match Employee constructor layout
            emp = new Employee(id, salary, fn, ln, ad, ph);
        }
        
        employees.add(emp);
        nextStaffId++; // Safely advance tracking sequences
        return emp;
    }

    public Employee findEmployeeById(int id) {
        for (Employee e : employees) {
            if (e.getStaffId() == id) return e;
        }
        return null;
    }

    public void deleteEmployee(int id) {
        Employee emp = findEmployeeById(id);
        if (emp != null) {
            if (emp instanceof PersonalTrainer && !canDeleteTrainer(id)) {
                throw new IllegalStateException("Cannot delete a Personal Trainer who still has assigned members.");
            }
            employees.remove(emp);
        }
    }

    // --- Member Core Logic Operations ---
    
    /**
     * Directly links dynamic sub-typed PolyStudent records to the master registry.
     */
    public Member addPolyStudent(int memberId, String dob, String gender, String course, String team, String fn, String ln, String ad, String ph) {
        if (fn == null || ln == null || dob == null || gender == null) {
            throw new NullPointerException("Student record attributes cannot point to null references.");
        }
        
        Member m = new PolyStudent(memberId, dob, gender, course, team, fn, ln, ad, ph);
        members.add(m);
        nextMemberId++;
        return m;
    }

    /**
     * Directly links dynamic sub-typed PolyStaff records to the master registry.
     */
    public Member addPolyStaff(int memberId, String dob, String gender, String position, String dept, String fn, String ln, String ad, String ph) {
        if (fn == null || ln == null || dob == null || gender == null) {
            throw new NullPointerException("Staff record attributes cannot point to null references.");
        }
        
        Member m = new PolyStaff(memberId, dob, gender, position, dept, fn, ln, ad, ph);
        members.add(m);
        nextMemberId++;
        return m;
    }

    public Member findMemberById(int id) {
        for (Member m : members) {
            if (m.getMemberId() == id) return m;
        }
        return null;
    }

    public void deleteMember(int id) {
        Member m = findMemberById(id);
        if (m != null) {
            // Cleanly sever assigned personal training relationships prior to object deletion
            if (m.getTrainer() != null) {
                m.getTrainer().removeMember(m);
            }
            members.remove(m);
        }
    }

    // --- Personal Trainer Assignment Relationships Logic ---
    public void assignTrainer(int memberId, int trainerId) {
        Member m = findMemberById(memberId);
        Employee e = findEmployeeById(trainerId);
        
        if (m == null) {
            throw new IllegalArgumentException("Assignment failed: Member ID does not exist.");
        }
        if (e == null || !(e instanceof PersonalTrainer)) {
            throw new IllegalArgumentException("Assignment failed: Trainer ID does not match an active Personal Trainer profile.");
        }

        PersonalTrainer pt = (PersonalTrainer) e;
        
        // Remove from current trainer if already assigned somewhere else
        if (m.getTrainer() != null) {
            m.getTrainer().removeMember(m);
        }
        
        m.setTrainer(pt);
        pt.addMember(m);
    }

    public void removeTrainer(int memberId) {
        Member m = findMemberById(memberId);
        if (m != null && m.getTrainer() != null) {
            m.getTrainer().removeMember(m);
            m.setTrainer(null);
        }
    }

    // Helper utility method to get only employees who are Personal Trainers
    public ArrayList<PersonalTrainer> getTrainers() {
        ArrayList<PersonalTrainer> trainersList = new ArrayList<PersonalTrainer>();
        for (Employee e : employees) {
            if (e instanceof PersonalTrainer) {
                trainersList.add((PersonalTrainer) e);
            }
        }
        return trainersList;
    }

    // --- Interface Method Implementations (Reportable) ---
    @Override
    public boolean canDeleteTrainer(int trainerId) {
        Employee e = findEmployeeById(trainerId);
        if (e instanceof PersonalTrainer) {
            return ((PersonalTrainer) e).getMemberCount() == 0;
        }
        return true; 
    }

    @Override
    public void generateMarketingReport() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter("marketingReport.txt"));
            writer.println("=================================================");
            writer.println("   POLYFIT GYM MANAGEMENT SYSTEM - MARKETING REPORT");
            writer.println("=================================================");
            writer.println();
            
            writer.println("--- POLYTECHNIC STAFF MEMBERS ---");
            int staffCount = 0;
            for (Member m : members) {
                if (m instanceof PolyStaff) {
                    PolyStaff ps = (PolyStaff) m;
                    writer.printf("ID: %-5d Name: %-20s Dept: %-15s Position: %s%n", 
                        ps.getMemberId(), (ps.getFirstName() + " " + ps.getLastName()), 
                        ps.getDepartment(), ps.getPosition());
                    staffCount++;
                }
            }
            writer.println("Total Staff Members: " + staffCount);
            writer.println();

            writer.println("--- POLYTECHNIC STUDENT MEMBERS ---");
            int studentCount = 0;
            for (Member m : members) {
                if (m instanceof PolyStudent) {
                    PolyStudent ps = (PolyStudent) m;
                    writer.printf("ID: %-5d Name: %-20s Course: %-15s Team: %s%n", 
                        ps.getMemberId(), (ps.getFirstName() + " " + ps.getLastName()), 
                        ps.getCourse(), ps.getTeam().isEmpty() ? "None" : ps.getTeam());
                    studentCount++;
                }
            }
            writer.println("Total Student Members: " + studentCount);
            writer.println();
            writer.println("=================================================");
            writer.println("End of Report.");
        } catch (IOException ex) {
            System.err.println("Error generating text based report structure: " + ex.getMessage());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}