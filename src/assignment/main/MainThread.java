package assignment.main;

import assignment.controller.StudentController;
import assignment.model.ConnectionHelper;
import assignment.model.StudentModel;

import java.util.Scanner;

public class MainThread {


    public static void main(String[] args) {
        StudentController controller = new StudentController();
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Scanner scan = new Scanner(System.in);
        String repeat;
        int choice;
        do {
            System.out.println("==========*Student Manager*==========");
            System.out.println("1. Student list.");
            System.out.println("2. Search for students by id.");
            System.out.println("3. Add new student.");
            System.out.println("4. Edit a student.");
            System.out.println("5. Delete a student.");
            System.out.println("6. Exit");
            System.out.println("Please enter your choice: ");
            choice = scan.nextInt();
            scan.nextLine();
            switch (choice) {
                case 1:
                    controller.printStudentList();
                    break;
                case 2:
                    controller.findStudent();
                    break;
                case 3:
                    controller.addStudent();
                    break;
                case 4:
                    controller.editStudent();
                    break;
                case 5:
                    controller.deleteStudent();
                    break;
                case 6:
                    connectionHelper.closeConnect();
                    System.exit(0);
                    break;
                default:
                    System.err.println("Please choice from 1 to 5.");
            }
            System.out.println("you want to continue? Y/ N");
            repeat = scan.nextLine();
            repeat = repeat.toLowerCase();
            if (repeat.equals("n")) connectionHelper.closeConnect();
        } while (repeat.equals("y"));
    }
}
/*
main
    quan ly sinh vien
    -----------------
        Danh sach sinh vien
            lấy dữ liệu từ DB
                tạo connection
                tạo PreparedStatement
                    tạo StringBuilbder để chứa mã SQL
                        đẩy StringBuilbder vào PreparedStatement
                đẩy PreparedStatement lên DB
                tạo resultSet chứa dữ liệu trả về từ DB
                sử dụng vòng lặp để lưu giá trị của các trường trong bảng rồi
                    tạo 1 đối tượng student với những giá trị lấy được từ bảng
                    add đối tượng đó vào arrayList
            in danh sách sinh viên
                dùng vòng lặp duyệt qua từng list trong array


        Xem chi tiet sinh vien theo rollNumber
        them sinh vien
        --------------
            tạo connection
            tạo PreparedStatement
                tạo StringBuilbder để chứa mã SQL
                    đẩy StringBuilbder vào PreparedStatement
            đẩy PreparedStatement lên DB
        sua sinh vien
        Xoa sinh vien
        Thoat
    quan ly giao vien
*/