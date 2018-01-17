package assignment.main;

import assignment.controller.StudentController;
import assignment.model.ConnectionHelper;
import assignment.model.StudentModel;

import java.text.SimpleDateFormat;
import java.util.Date;
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