package src;

import entity.Student;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class StudentManager {
    private final StudentDao studentDao;
    private final List<Student> students;
    private final Scanner scanner = new Scanner(System.in);

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
            this.addStudent();
        }

        if (existsStudentCode(maSinhVien)) {
            System.out.println("Mã sinh viên đã tồn tại.");
            this.addStudent();
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
        if (studentDao.write(students)) {
            System.out.println("Thêm sinh viên thành công.");
        } else {
            System.out.println("Thêm sinh viên không thành công.");
        }

    }

    private boolean existsStudentCode(String maSinhVien) {
        for (Student student : students) {
            if (student.getMaSinhVien().equals(maSinhVien)) {
                return true;
            }
        }
        return false;
    }

    private Student findStudentByCode(String maSinhVien) {
        for (Student student : students) {
            if (student.getMaSinhVien().equals(maSinhVien)) {
                return student;
            }
        }
        return null;
    }

    public void updateStudent() {
        System.out.println("-----Nhập thông tin sinh viên-----");
        System.out.print("Nhập mã sinh viên (SV-XXX): ");
        String maSinhVien = scanner.nextLine();
        Student student = findStudentByCode(maSinhVien);
        if (student == null) {
            System.out.println("Không tìm thấy sinh viên.");
            this.updateStudent();
        }

        System.out.println("Sửa theo (1 - Tên, 2 - Ngày sinh, 3 - Quê quán, 4 - Gpa)");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            System.out.print("Nhập tên mới: ");
            String name = scanner.nextLine();
            student.setName(name);
            if (studentDao.write(students)) {
                System.out.println("Cập nhật sinh viên thành công.");
            } else {
                System.out.println("Cập nhật sinh viên không thành công.");
            }
        }

        if (choice == 2) {
            System.out.print("Nhập ngày sinh mới (dd/MM/yyyy): ");
            String dateOfBirth = scanner.nextLine();
            if (!Utils.isValidDate(dateOfBirth)) {
                System.out.println("Ngày sinh không hợp lệ.");
                return;
            }
            student.setDateOfBirth(dateOfBirth);
            if (studentDao.write(students)) {
                System.out.println("Cập nhật sinh viên thành công.");
            } else {
                System.out.println("Cập nhật sinh viên không thành công.");
            }
        }

        if (choice == 3) {
            System.out.print("Nhập quê quán mới: ");
            String hometown = scanner.nextLine();
            student.setHomeTown(hometown);
            if (studentDao.write(students)) {
                System.out.println("Cập nhật sinh viên thành công.");
            } else {
                System.out.println("Cập nhật sinh viên không thành công.");
            }
        }

        if (choice == 4) {
            System.out.print("Nhập điểm trung bình (0-10) mới: ");
            double gpa = scanner.nextDouble();
            if (!Utils.isValidGpa(gpa)) {
                System.out.println("Điểm trung bình không hợp lệ");
                return;
            }
            student.setGpa(gpa);
            if (studentDao.write(students)) {
                System.out.println("Cập nhật sinh viên thành công.");
            } else {
                System.out.println("Cập nhật sinh viên không thành công.");
            }
        }
    }


    public void deleteStudent() {
        System.out.print("Nhập mã sinh viên cần xóa: ");
        String maSinhVien = scanner.nextLine();
        Student student = findStudentByCode(maSinhVien);
        if (student == null) {
            System.out.println("Không tìm thấy sinh viên.");
            this.deleteStudent();
        }
        System.out.println("Bạn có chắc chắn muốn xóa sinh viên này? (y/n): ");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("y")) {
            if (students.remove(student)) {
                System.out.println("Xóa sinh viên thành công.");
                studentDao.write(students);
            } else {
                System.out.println("Xóa sinh viên không thành công.");
            }
        } else {
            System.out.println("Hủy xóa sinh viên.");
        }
    }

    public void displayStudent() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void sortStudentByGpa() {
        System.out.println("----- Sắp xếp sinh viên theo điểm trung bình -----");
        System.out.println("1 - Tăng dần, 2 - Giảm dần");
        int choice = scanner.nextInt();
        if (choice == 1) {
            students.sort(Comparator.comparingDouble(Student::getGpa));
            for (Student student : students) {
                System.out.println(student);
            }
        }

        if (choice == 2) {
            students.sort(Comparator.comparingDouble(Student::getGpa).reversed());
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }


    public void sortStudentByMaSinhVien() {
        System.out.println("----- Sinh viên theo mã sinh viên (tăng dần) -----");
        System.out.println("1 - Tăng dần, 2 - Giảm dần");
        int choice = scanner.nextInt();
        if (choice == 1) {
            students.sort(Comparator.comparing(Student::getMaSinhVien));
            for (Student student : students) {
                System.out.println(student);
            }
        }
        if (choice == 2) {
            students.sort(Comparator.comparing(Student::getMaSinhVien).reversed());
            for (Student student : students) {
                System.out.println(student);
            }
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
                break;
            } else {
                System.out.println("Không có sinh viên nào sinh vào này");
                this.displayStudentsBornInMonth();
            }
        }
    }
}

