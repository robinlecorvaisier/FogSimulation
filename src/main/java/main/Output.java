package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Output {
    private static Scanner x;

    public static void edit(int expiredData, int dataProcessed, String file) {

        try {
            String filePath = "./src/main/java/main/" + file + ".csv";
            FileWriter fileWriter = new FileWriter(filePath, true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(expiredData + ";" + dataProcessed);

            pw.flush();
            pw.close();
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }
}
