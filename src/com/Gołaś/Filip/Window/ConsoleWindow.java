package com.Gołaś.Filip.Window;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.awt.Color;
import java.awt.Font;

public class ConsoleWindow extends JFrame {
    public ConsoleWindow(){
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        JTextArea textArea = new JTextArea(16, 64);
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

        panel.add(scrollPane);
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setMaximumSize(getSize());
        setMinimumSize(getSize());
    }
}
