package com.Gołaś.Filip.Window;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class ConsoleWindow extends JFrame {
    JPanel panel;
    JTextArea textArea;
    public ConsoleWindow(){
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.BLACK);
        textArea = new JTextArea(16, 64);
        textArea.setEditable(false);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.LIGHT_GRAY);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                textArea.append(String.valueOf((char) b));
            }
        }));

        panel.add(scrollPane, BorderLayout.CENTER);
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setMaximumSize(getSize());
        setMinimumSize(getSize());
    }

    public void reset(){
        textArea.setText("");
    }

    public String getText(){
        return textArea.getText();
    }
    public void setText(String text){
        textArea.setText(text);
    }
}
