package homework.hw4.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Employee extends Person{
    @Column(name = "salary")
    private double salary;
    @Column(name = "working_hours")
    private int workingHours;

    public Employee() {
    }

    public Employee(String first_name, String last_name, String phone, String email, double salary, int workingHours) {
        super(first_name, last_name, phone, email);
        this.salary = salary;
        this.workingHours = workingHours;
    }

    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public int getWorkingHours() {
        return workingHours;
    }
    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }

    @Override
    public String toString() {
        return super.toString() + "Employee [salary=" + salary + ", workingHours=" + workingHours + "]";
    }
}
