package com.app;

public class Salary {

    private String month;
    private float totalSalary;

    public Salary(String month, float total_salary){
        this.month=month;
        this.totalSalary=total_salary;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public float getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(float total_salary) {
        this.totalSalary = total_salary;
    }
}
