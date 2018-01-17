package assignment.entity;

import java.util.Scanner;

public class Student {
    private int id;
    private String rollNumber;
    private String name;
    private String phone;
    private String email;
    private long createAt;
    private long updateAt;
    private int status;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", rollNumber='" + rollNumber + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", status=" + status +
                '}';
    }

    public Student() {
    }

    public Student(int id, String rollNumber, String name, String phone, String email, long createAt, long updateAt, int status) {
        this.id = id;
        this.rollNumber = rollNumber;
        this.name = name;
        this.phone = phone;
        this.email = email;
        // truyền vào ko gọi truyền làm giề?
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.status = status;
    }

    public Student(int id, String rollNumber, String name, String phone, String email, int status) {
        this.id = id;
        this.rollNumber = rollNumber;
        this.name = name;
        this.phone = phone;
        this.email = email;
        // truyền vào ko gọi truyền làm giề?
        this.createAt = System.currentTimeMillis();
        this.updateAt = System.currentTimeMillis();
        this.status = status;
        // dc rồi
    }

    //    để e tạo cái contructor nữa nhỉ ?
    Scanner scan = new Scanner(System.in);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    public long getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(long updateAt) {
        this.updateAt = updateAt;
    }
}
