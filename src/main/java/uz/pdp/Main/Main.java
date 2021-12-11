package uz.pdp.Main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.Scanner;

public class Main {
    static Scanner scannerStr = new Scanner(System.in);
    static Scanner scannerInt = new Scanner(System.in);

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (OutputStream outputStream = new FileOutputStream("src/main/resources/test.docx")) {
            XWPFDocument document = new XWPFDocument();
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setBold(true);
            run.setItalic(true);
            run.setFontFamily("Algerian");
            run.setFontSize(14);

            XWPFTable table = document.createTable();
            table.setTableAlignment(TableRowAlign.CENTER);
            XWPFTableRow row = table.getRow(0);
            XWPFTableCell postId = row.getCell(0);
            XWPFTableCell id = row.addNewTableCell();
            XWPFTableCell name = row.addNewTableCell();
            XWPFTableCell email = row.addNewTableCell();
            XWPFTableCell body = row.addNewTableCell();
            postId.setText("USER ID");
            id.setText("ID");
            name.setText("NAME");
            email.setText("EMAIL");
            body.setText("BODY");

            try (Reader reader = new FileReader("src/main/resources/comments.json")) {
                Comments[] comments = gson.fromJson(reader, Comments[].class);
                for (Comments comments1 : comments) {
                    XWPFTableRow row1 = table.createRow();
                    row1.getCell(0).setText(String.valueOf(comments1.getPostId()));
                    row1.getCell(1).setText(String.valueOf(comments1.getId()));
                    row1.getCell(2).setText(String.valueOf(comments1.getName()));
                    row1.getCell(3).setText(String.valueOf(comments1.getEmail()));
                    row1.getCell(4).setText(String.valueOf(comments1.getBody()));
                }
            }

            document.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

