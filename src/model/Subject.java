package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
@Table(name="subject")
public class Subject {
    @Id
    @Column(name="subject_id")
    private String id;
    @Column(name="subject_name")
    private String name;
    private int credits;
    public Subject() {}
    public Subject(String id,String name,int credits)  {
        this.id=id;
        this.name=name;
        this.credits=credits;
    }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }
}