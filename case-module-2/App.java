import src.StudentManager;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        StudentManager studentManager = new StudentManager();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("-----QUẢN LÝ SINH VIÊN-----");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Sửa sinh viên");
            System.out.println("3. Xóa sinh viên");
            System.out.println("4. Hiển thị sinh viên");
            System.out.println("5. Sắp xếp sinh viên với điểm trung bình");
            System.out.println("6. Sắp xếp sinh viên theo mã sinh viên");
            System.out.println("7. Tìm kiếm sinh viên (mã sinh viên hoặc tên)");
            System.out.println("8. Hiển thị sinh viên sinh trong tháng");
            System.out.println("0. Thoát");
            System.out.print("Chọn một sự lựa chọn: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    studentManager.addStudent();
                    break;
                case 2:
                    studentManager.updateStudent();
                    break;
                case 3:
                    studentManager.deleteStudent();
                    break;
                case 4:
                    studentManager.displayStudent();
                    break;
                case 5:
                    studentManager.sortStudentByGpa();
                    break;
                case 6:
                    studentManager.sortStudentByMaSinhVien();
                    break;
                case 7:
                    studentManager.searchStudent();
                    break;
                case 8:
                    studentManager.displayStudentsBornInMonth();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Chọn tùy chọn hợp lệ");
            }
        }
    }
}
