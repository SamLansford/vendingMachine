package com.vendingmachine;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class VendingGuiMain {

    private JPanel panelMain;
    private JButton buyButton;
    private JButton clearButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextPane tp;


    public VendingGuiMain() {
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                textField2.setText("");
            }
        });

        String first = "C:\\Users\\wajiz.pk\\IdeaProjects\\Vending Machine Gui\\src\\main\\java\\input.json"; // this is the same as:  /Users/temp/IdeaProjects/ReadFromJson/BobFile.json

        try {

            String contents = new String((Files.readAllBytes(Paths.get(first))));
            JSONObject o = new JSONObject(contents);

            JSONObject config = o.getJSONObject("config");
            JSONArray items = o.getJSONArray("items");

            String columns =  config.get("columns").toString();
            String rows =  config.get("rows").toString();


            System.out.println(columns);
            System.out.println(rows);



            tp.setText("   name | price | amount \n\n");



            for (int i = 0; i < items.length(); i++)
            {
                JSONObject item = (JSONObject) items.get(i);
                String name = item.get("name").toString();
                String amount = item.get("amount").toString();
                String price = item.get("price").toString();

                StyledDocument document = (StyledDocument) tp.getDocument();

                try {
                    document.insertString(document.getLength(), String.format("%d| %s ",i + 1, name), null);
                    document.insertString(document.getLength(), String.format("| %s | " , price), null);
                    document.insertString(document.getLength(), String.format("%s |\n", amount), null);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }

            }

            System.out.println(items);

        }catch(IOException e){
            e.printStackTrace();
        }




    }

    public static void main(String[] args) {

        System.out.println("test");

        JFrame frame = new JFrame("VendingGuiMain");
        frame.setContentPane(new VendingGuiMain().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize( 700, 500);
        frame.setResizable(false);

    }


}
