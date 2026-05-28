/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package polyfit;

/**
 *
 * @author huzaifasuhail
 */
import java.util.ArrayList;

/**
 * Class representing a Personal Trainer with a collection of assigned gym members.
 */
public class PersonalTrainer extends Employee {
    private static final long serialVersionUID = 1L;
    
    private ArrayList<Member> members;

    public PersonalTrainer(int staffId, double salary, String firstName, String lastName, String address, String phone) {
        super(staffId, salary, firstName, lastName, address, phone);
        this.members = new ArrayList<Member>();
    }

    public ArrayList<Member> getMembers() { return members; }
    
    public void addMember(Member m) {
        if (!members.contains(m)) {
            members.add(m);
        }
    }

    public void removeMember(Member m) {
        members.remove(m);
    }

    public int getMemberCount() {
        return members.size();
    }
}
