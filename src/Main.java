/*
 * Developed by Reza Mansouri 2020
 * For Data Structures Final Project in Bachelor
 * Third Term of Software Engineering at Kharazmi University of Tehran
 * std_reza_mansouri@khu.ac.ir
 */

import Collections.*;
import Objects.Compressor;
import Objects.Extractor;

import java.io.*;
import java.util.Scanner;

public class Main {
    private static final String INPUT_DIRECTORY = System.getProperty("user.dir") + "\\files\\input\\";
    private static final String OUTPUT_DIRECTORY = System.getProperty("user.dir") + "\\files\\output\\";

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                printLine();
                System.out.println("1.Compress\n2.Extract\n3.Exit");
                switch (scanner.nextInt()) {
                    case 1:
                        printLine();
                        System.out.println("1.Compress a String\n2.Compress a Text File");
                        switch (scanner.nextInt()) {
                            case 1:
                                System.out.println("Enter Your String: ");
                                scanner.nextLine();
                                String input = scanner.nextLine();
                                printLine();
                                Compressor compressor1 = new Compressor(input);
                                String compressedFileName = input;
                                if (input.length() > 10) compressedFileName = input.substring(0, 10);
                                String resultDirectory1 = OUTPUT_DIRECTORY + compressedFileName + "\\";
                                new File(resultDirectory1).mkdir();
                                PrintWriter writer = new PrintWriter(new FileWriter(resultDirectory1 + "Original.txt"));
                                writer.print(input);
                                writer.close();
                                System.out.println("Compressing...");
                                compressor1.compress(resultDirectory1 + compressedFileName);
                                System.out.println("File Successfully Compressed.\nYou Can Find it at " + resultDirectory1);
                                break;
                            case 2:
                                System.out.println("*Your File Should be in input Folder in The File Directory*\nEnter Your Text File Name: ");
                                scanner.nextLine();
                                String fileName = scanner.nextLine();
                                BufferedReader reader = new BufferedReader(new FileReader(INPUT_DIRECTORY + fileName));
                                fileName = fileName.replaceAll(".txt", "");
                                String resultDirectory2 = OUTPUT_DIRECTORY + fileName + "\\";
                                new File(resultDirectory2).mkdir();
                                Compressor compressor2 = new Compressor(reader);
                                System.out.println("Compressing...");
                                compressor2.compress(resultDirectory2 + fileName);
                                System.out.println("File Successfully Compressed.\nYou Can Find it at: " + resultDirectory2);
                                break;
                        }
                        break;
                    case 2:
                        System.out.println("*Your File Should be in input Folder in The File Directory*\nEnter Your Compressed File Name: ");
                        scanner.nextLine();
                        String fileName = scanner.nextLine();
                        printLine();
                        System.out.println("Extracting...");
                        Extractor extractor = new Extractor(INPUT_DIRECTORY + fileName);
                        String answer = extractor.extract();
                        String resultDirectory = OUTPUT_DIRECTORY + fileName.replaceAll(".losezip", "") + "_Extracted.txt";
                        PrintWriter writer = new PrintWriter(new FileWriter(resultDirectory));
                        writer.print(answer);
                        writer.close();
                        System.out.println("File Successfully Extracted.\nYou Can Find it at: " + OUTPUT_DIRECTORY);
                        printLine();
                        System.out.println("Do You Want to View The Contents Here?\n1.Yes 2.No");
                        switch (scanner.nextInt()) {
                            case 1:
                                System.out.println(answer);
                                break;
                            case 2:
                                break;
                        }
                        break;
                    case 3:
                        return;
                }
            }
        } catch (IOException | ClassNotFoundException | PriorityQueue.QueueOverflowedException | PriorityQueue.EmptyQueueException e) {
            e.printStackTrace();
        }
    }


    private static void printLine() {
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
    }
}
