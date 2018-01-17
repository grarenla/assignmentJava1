package assignment.model;

import assignment.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/*Làm nhiệm vụ thao tác với bảng students trong database. Các thao tác gồm có:
 * thêm mới một sinh viên, sửa sinh viên, lấy danh sách sinh viên, xóa thông tin sinh viên*/
public class StudentModel {

    private Connection conn = ConnectionHelper.getConnection();
    // Tạo kết nối tới database;
    ConnectionHelper connectionHelper = new ConnectionHelper();

    public ArrayList<Student> getListStudent() {

//        tạo một array rỗng để chứa dữ liệu trả về
        ArrayList<Student> listStudent = new ArrayList<>();
        try {
//            tạo kết nối vs DB

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                int id = rs.getInt("id");
                String rollNumber = rs.getString("rollNumber");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                long createAt = rs.getLong("createAt");
                long updateAt = rs.getLong("updateAt");
                int status = rs.getInt("status");
                Student student = new Student(id, rollNumber, name, phone, email, createAt, updateAt, status);
                listStudent.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listStudent;
    }

    public void insert(Student student) {
        try {
            String sql = "insert into students (rollNumber, name, phone, email, createAt, updateAt) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, student.getRollNumber());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getPhone());
            preparedStatement.setString(4, student.getEmail());
            preparedStatement.setLong(5, System.currentTimeMillis());
            preparedStatement.setLong(6, System.currentTimeMillis());
            preparedStatement.executeUpdate();
            System.out.println("Add new student success.");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Student getById(int id) {
        Student student = null;
        if (conn == null) {
            return null;
        }
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM students WHERE id = " + id);
            if (rs.next()) {
                int studentId = rs.getInt("id");
                String rollNumber = rs.getString("rollNumber");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                long createAt = rs.getLong("createAt");
                long updateAt = rs.getLong("updateAt");
                int status = rs.getInt("status");
                student = new Student(studentId, rollNumber, name, phone, email, createAt, updateAt, status);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
        return student;
    }

    public void update(Student student) {
        try {
            String sqlUpdate = "UPDATE students SET name=?, phone=?, email=?, updateAt=? WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sqlUpdate);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getPhone());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setLong(4, System.currentTimeMillis());
            preparedStatement.setInt(5, student.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void delete(Student student) {
        try {
            String sqlDelete = "UPDATE students SET status=-1, updateAt=? WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sqlDelete);
            preparedStatement.setLong(1, System.currentTimeMillis());
            preparedStatement.setInt(2, student.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
