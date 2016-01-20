package com.example.gonzalo.ex_1;

public class Salary {
    String month;
    double total_salary;
    public Salary(String month, int total_salary){
        this.month=month;
        this.total_salary=total_salary;
    }
    public String getMonth(){
        return month;
    }
    public double getTotal_salary(){
        return total_salary;
    }
}
