package assignment.test;

import java.util.Scanner;

public class class1701 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap so luong phan tu trong mang: ");
        int soluong = scanner.nextInt();
        scanner.nextLine();
        int a[] = new int[soluong];
        for (int i = 0; i < a.length; i++) {
            System.out.println("Nhap gia tri cho phan tu thu " + (i + 1) + " :");
            a[i] = scanner.nextInt();
        }
        for (int i = 0; i < a.length; i++) {
            System.out.println("Gia tri cua phan tu thu " + (i + 1) + " la: " + a[i]);
        }
    }
}
