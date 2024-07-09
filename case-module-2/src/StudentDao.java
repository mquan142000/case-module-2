package src;

import entity.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentDao {
    private static final String FILE_NAME = "/Users/minhquan/Documents/case-module-2/src/student.txt";

    public List<Student> read() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] parts = line.split(",");
//                System.out.println(Arrays.toString(parts));
                String maSinhVien = parts[0];
                String name = parts[1];
                String dateOfBirth = parts[2];
                String homeTown = parts[3];
                double gpa = Double.parseDouble(parts[4]);
                Student student = new Student(maSinhVien, name, dateOfBirth, homeTown, gpa);
//                System.out.println(student);
                students.add(student);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    public void write(List<Student> students) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student student : students) {
                bw.write(student.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
