package assignment.controller;

import assignment.entity.Student;
import assignment.model.ConnectionHelper;
import assignment.model.StudentModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentController {
    ConnectionHelper connectionHelper = new ConnectionHelper();
    private Connection conn = ConnectionHelper.getConnection();
    StudentModel model = new StudentModel();

    public void printStudentList() {
        ArrayList<Student> list = model.getListStudent();
        System.out.println("\tID\t\tRollNumber\t\t\t\tName\t\t\tPhone\t\t\t\t\tEmail\t\t\t\tStatus");
        for (int i = 0; i < list.size(); i++) {
//            Student student = list.get(i);
            System.out.printf("%6d %13s %21s %16s %25s %14s\n", list.get(i).getId(), list.get(i).getRollNumber(), list.get(i).getName(), list.get(i).getPhone(), list.get(i).getEmail(), list.get(i).getStatus());
        }
    }
    public void addStudent() {
        Student student = new Student();
        Scanner scan = new Scanner(System.in);
//        valid name

        while (true) {
            System.out.println("Please enter your name(>=7 character): ");
            String name = scan.nextLine();
            Pattern pattern = Pattern.compile("[a-zA-Z]{7,20}");
            Matcher matcher = pattern.matcher(name);
            boolean isName = matcher.matches();
            if (name.length() >= 7 && isName) {
                student.setName(name);
                break;
            }
            System.err.println("Name is shorter than 7 characters.");
        }
//        valid rollNumber

        while (true) {
            System.out.println("Please enter your rollnumber(>=6 character): ");
            String rollNumber = scan.nextLine();
            if (rollNumber != null && rollNumber.length() >= 6) {
                student.setRollNumber(rollNumber);
                break;
            }
            System.err.println("RollNumber is shorter than 6 characters.");
        }
//        valid phone

        while (true) {
            System.out.println("Please enter new phone(10-11 character): ");
            String phone = scan.nextLine();
            String reg = "[0-9]{10,11}";
            Pattern pattern = Pattern.compile(reg);
            Matcher matcher = pattern.matcher(phone);
            boolean isPhone = matcher.matches();
            if (isPhone) {
                student.setPhone(phone);
                break;
            }
            System.err.println("Phone numbers must be between 10 and 11 characters.");
        }

//        valid email
        while (true) {

            System.out.println("Please enter your email(a12@xyz.abc): ");
            String email = scan.nextLine();
            String reg = "^[a-zA-Z]+[0-9]*@[a-z]{3,10}\\.[a-z]{2,3}$";
            Pattern pattern = Pattern.compile(reg);
            Matcher matcher = pattern.matcher(email);
            boolean isEmail = matcher.matches();
            System.out.println(isEmail);
            if (isEmail) {
                student.setEmail(email);
                break;
            }
            System.err.println("The email you entered is not in the correct format.");
        }
        model.insert(student);
    }

    public void editStudent() {
        Scanner scan = new Scanner(System.in);
        System.out.println("********************Edit Student********************");
        System.out.println("Please enter student id: ");
        int id = scan.nextInt();
        scan.nextLine();
        Student existStudent = model.getById(id);

        if (existStudent.getId() == 0) {
            System.out.println("Student is not exists or has been deleted.");
        } else {
            System.out.println("----------Student information----------");
            System.out.println("Id: " + existStudent.getId());
            System.out.println("RollNumber: " + existStudent.getRollNumber());
            System.out.println("Name: " + existStudent.getName());
            System.out.println("Phone: " + existStudent.getPhone());
            System.out.println("Email: " + existStudent.getEmail());
            System.out.println("Status: " + existStudent.getStatus());
            System.out.println("----------------------------------------");

            String newName;
            while (true) {
                System.out.println("Please enter new name(>=7 character): ");
                newName = scan.nextLine();
                Pattern pattern = Pattern.compile("[a-zA-Z]{7,20}");
                Matcher matcher = pattern.matcher(newName);
                boolean isName = matcher.matches();
                if (newName.length() >= 7 && isName) {
                    existStudent.setName(newName);
                    break;
                }
                System.err.println("Name is shorter than 7 characters.");
            }

            String newPhone;
            while (true) {
                System.out.println("Please enter new phone(10-11 number): ");
                newPhone = scan.nextLine();
                String reg = "[0-9]{10,11}";
                Pattern pattern = Pattern.compile(reg);
                Matcher matcher = pattern.matcher(newPhone);
                boolean isPhone = matcher.matches();
                if (isPhone) {
                    existStudent.setPhone(newPhone);
                    break;
                }
                System.err.println("Phone numbers must be between 10 and 11 characters.");
            }

            String newEmail;
            while (true) {
                System.out.println("Please enter new email(a12@xyz.abc): ");
                newEmail = scan.nextLine();
                Pattern pattern = Pattern.compile("^[a-zA-Z]+[0-9]*@[a-z]{3,10}\\.[a-z]{2,3}$");
                Matcher matcher = pattern.matcher(newEmail);
                boolean isEmail = matcher.matches();
                if (isEmail) {
                    existStudent.setEmail(newEmail);
                    break;
                }
                System.err.println("The email you entered is not in the correct format.");
            }
            existStudent.setName(newName);
            existStudent.setPhone(newPhone);
            existStudent.setEmail(newEmail);
            model.update(existStudent);
            System.out.println("Update success.");
        }
    }

    public void deleteStudent() {
        Scanner scan = new Scanner(System.in);
        System.out.println("********************Delete Student********************");
        System.out.println("Please enter student id: ");
        int id = scan.nextInt();
        scan.nextLine();
        Student existStudent = model.getById(id);
        if (existStudent.getId() == 0) {
            System.out.println("Student is not exists or has been deleted.");
        } else {
            System.out.println("----------Student information----------");
            System.out.println("Id: " + existStudent.getId());
            System.out.println("RollNumber: " + existStudent.getRollNumber());
            System.out.println("Name: " + existStudent.getName());
            System.out.println("Phone: " + existStudent.getPhone());
            System.out.println("Email: " + existStudent.getEmail());
            System.out.println("Status: " + existStudent.getStatus());
            System.out.println("----------------------------------------");

            System.out.println("Do you want to delete student? Y/ N");
            String choice = scan.nextLine();
            choice = choice.toLowerCase();
            if (choice.equals("y")) {
//                System.out.println(existStudent.getStatus());
                model.delete(existStudent);
                System.out.println("Update success.");
            }

        }
    }

    public void findStudent() {
        Scanner scan = new Scanner(System.in);
        System.out.println("********************Search Student********************");
        System.out.println("Please enter student id: ");
        int id = scan.nextInt();
        scan.nextLine();
        Student existStudent = model.getById(id);
        if (existStudent.getId() == 0) {
            System.out.println("Student is not exists or has been deleted.");
        } else {
            System.out.println("----------Student information----------");
            System.out.println("Id: " + existStudent.getId());
            System.out.println("RollNumber: " + existStudent.getRollNumber());
            System.out.println("Name: " + existStudent.getName());
            System.out.println("Phone: " + existStudent.getPhone());
            System.out.println("Email: " + existStudent.getEmail());
            System.out.println("Status: " + existStudent.getStatus());
            System.out.println("----------------------------------------");
        }
    }
}
