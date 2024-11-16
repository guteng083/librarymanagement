package utils;

import java.util.Scanner;

public class ScannerUtils {
    static Scanner scanner = new Scanner(System.in);

    public static String inputText(String prompt) {
        System.out.print(prompt+" : ");
        return scanner.nextLine();
    }
}
