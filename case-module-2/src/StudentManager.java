package src;

import entity.Student;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class StudentManager {
    private StudentDao studentDao;
    private List<Student> students;
    private Scanner scanner = new Scanner(System.in);

    public StudentManager() {
        studentDao = new StudentDao();
        students = studentDao.read();
    }

    public void addStudent() {
        System.out.println("-----Nhập thông tin sinh viên-----");
        System.out.print("Nhập mã sinh viên (SV-XXX): ");
        String maSinhVien = scanner.nextLine();
        if (!Utils.isValidMaSinhVien(maSinhVien)) {
            System.out.println("Mã sinh viên không hợp lệ.");
            return;
        }
        for (Student student : students) {
            if (student.getMaSinhVien().equals(maSinhVien)) {
                System.out.println("Mã sinh viên đã tồn tại.");
                return;
            }
        }

        System.out.print("Nhập tên: ");
        String name = scanner.nextLine();

        System.out.print("Nhập ngày sinh (dd/MM/yyyy): ");
        String dateOfBirth = scanner.nextLine();
        if (!Utils.isValidDate(dateOfBirth)) {
            System.out.println("Ngày sinh không hợp lệ.");
            return;
        }

        System.out.print("Nhập quê quán: ");
        String hometown = scanner.nextLine();

        System.out.print("Nhập điểm trung bình (0-10): ");
        double gpa = scanner.nextDouble();
        if (!Utils.isValidGpa(gpa)) {
            System.out.println("Điểm trung bình không hợp lệ.");
            return;
        }
        students.add(new Student(maSinhVien, name, dateOfBirth, hometown, gpa));
        System.out.println("Thêm sinh viên thành công.");
        studentDao.write(students);
    }

    public void updateStudent() {
        System.out.println("-----Nhập thông tin sinh viên-----");
        System.out.print("Nhập mã sinh viên (SV-XXX): ");
        String maSinhVien = scanner.nextLine();
        for (Student student : students) {
            if (student.getMaSinhVien().equals(maSinhVien)) {
                System.out.print("Nhập tên mới: ");
                String name = scanner.nextLine();

                System.out.print("Nhập ngày sinh mới (dd/MM/yyyy): ");
                String dateOfBirth = scanner.nextLine();
                if (!Utils.isValidDate(dateOfBirth)) {
                    System.out.println("Ngày sinh không hợp lệ.");
                    return;
                }

                System.out.print("Nhập quê quán mới: ");
                String hometown = scanner.nextLine();

                System.out.print("Nhập điểm trung bình (0-10) mới: ");
                double gpa = scanner.nextDouble();
                if (!Utils.isValidGpa(gpa)) {
                    System.out.println("Điểm trung bình không hợp lệ");
                    return;
                }
                scanner.nextLine();
                student.setName(name);
                student.setDateOfBirth(dateOfBirth);
                student.setHomeTown(hometown);
                student.setGpa(gpa);
                studentDao.write(students);
                System.out.println("Cập nhật sinh viên thành công.");
                return;
            }
        }
        System.out.println("Không tìm thấy sinh viên.");
    }

    public void deleteStudent() {
        System.out.print("Nhập mã sinh viên cần xóa: ");
        String maSinhVien = scanner.nextLine();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (student.getMaSinhVien().equals(maSinhVien)) {
                System.out.println("Bạn có chắc chắn muốn xóa sinh viên này? (y/n): ");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("y")) {
                    students.remove(i);
                    System.out.println("Xóa sinh viên thành công.");
                    studentDao.write(students);
                    return;
                } else {
                    System.out.println("Hủy xóa sinh viên.");
                }
                return;
            }
        }
        System.out.println("Không tìm thấy sinh viên.");
    }

    public void displayStudent() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void sortStudentByGpa() {
        students.sort(Comparator.comparingDouble(Student::getGpa));
        System.out.println("----- Sinh viên theo điểm trung bình (tăng dần) -----");
        for (Student student : students) {
            System.out.println(student);
        }
    }


    public void sortStudentByMaSinhVien() {
        students.sort(Comparator.comparing(Student::getMaSinhVien));
        System.out.println("----- Sinh viên theo mã sinh viên (tăng dần) -----");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void searchStudent() {
        System.out.print("Nhập từ khóa tìm kiếm (mã sinh viên hoặc tên): ");
        String keyword = scanner.nextLine().toLowerCase();
        for (Student student : students) {
            if (student.getMaSinhVien().toLowerCase().contains(keyword) || student.getName().toLowerCase().contains(keyword)) {
                System.out.println(student);
            }
        }
    }

    public void displayStudentsBornInMonth() {
        System.out.println("Nhập tháng sinh (1-12): ");
        int month = scanner.nextInt();
        scanner.nextLine();

        if (month < 1 || month > 12) {
            System.out.println("Tháng không hợp lệ.");
            return;
        }
        System.out.println("----- Sinh viên sinh trong tháng " + month + " -----");
        for (Student student : students) {
            if (student.getDateOfBirth().getMonthValue() == month) {
                System.out.println(student);
            }
        }
    }
}
