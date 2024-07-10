package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Student implements Comparable<Student> {
    private String maSinhVien;
    private String name;
    //LocalDate thuộc gói java.time để xử lý các thao tác với ngày tháng
    private LocalDate dateOfBirth;
    private String homeTown;
    private double gpa;

    public Student() {
    }

    public Student(String maSinhVien, String name, String dateOfBirth, String homeTown, double gpa) {
        //DateTimeFormatter để định dạng và phân tích chuỗi ngày tháng
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.maSinhVien = maSinhVien;
        this.name = name;
        this.dateOfBirth = LocalDate.parse(dateOfBirth, formatter);
        this.homeTown = homeTown;
        this.gpa = gpa;
    }

    public String getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dateOfBirth = LocalDate.parse(dateOfBirth, formatter);
        //Chuỗi ngày tháng được chuyển đổi thành LocalDate bằng cách sử dụng DateTimeFormatter và phương thức LocalDate.parse().
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public int compareTo(Student other) {
        return Double.compare(this.gpa, other.gpa);
    }


    @Override
    public String toString() {
        //Định dạng dateOfBirth khi in ra
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return maSinhVien + "," + name + "," + dateOfBirth.format(formatter) + "," + homeTown + "," + gpa;
    }
}
