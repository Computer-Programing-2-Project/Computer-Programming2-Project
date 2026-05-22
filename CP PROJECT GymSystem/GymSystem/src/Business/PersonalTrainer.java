/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

import java.util.ArrayList;

/**
 *
 * @author ABC
 */
public class PersonalTrainer extends Employee  {
        private  ArrayList<Member> members;
    
    private  int sttafID;
    private  String first_name;
    private  String last_name;
    private  String address;
    private  int phone;
    private  int salary;

    public PersonalTrainer(ArrayList<Member> members, int sttafID, String first_name, String last_name, String address, int phone, int slary, int id, String fn, String ln, String ad, String ph, double sal, boolean trainer) {
        super(id, fn, ln, ad, ph, sal, trainer);
        this.members = members;
        this.sttafID = sttafID;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.phone = phone;
        this.salary = slary;
    }

    

    public ArrayList<Member> getMembers() {
        return members;
    }

    public int getSttafID() {
        return sttafID;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getAddress() {
        return address;
    }

    public int getPhone() {
        return phone;
    }

    public int getSlary() {
        return salary;
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }

    public void setSttafID(int sttafID) {
        this.sttafID = sttafID;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

        @Override
    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setSlary(int slary) {
        this.salary = slary;
    }
    
    
    @Override
    public void addMember(Member m){
        if (m == null){
           throw new NullPointerException("Member cannot be null");
        }
        members.add(m); 
        }
    
    @Override
    public void removeMember(Member m){
        if (m == null){
           throw new NullPointerException("Member cannot be null");
        }
        members.remove(m); 
        }
    
//Get the number of members assigned to this trainer
public int getMemberCount(){
    return members.size();
   
}    

public void getTrainer(){
    
}
 
//Display trainer information as a string
    public String toStringPersonalTrainer(){
    return "PersonalTrainer[ID=" + getSttafID() +
               ", Name=" + getFirst_name() + " " + getLast_name() +
               ", Members=" + getMemberCount() + "]";
}
    
    
}

