package com.Gołaś.Filip;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Gui extends JPanel {
    public Gui(){
        setLayout(new BorderLayout(3, 3));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        add(tools, BorderLayout.PAGE_START);
        tools.add(new JButton("Zapisz"));
        tools.add(new JButton("Wczytaj"));
        tools.addSeparator();
        tools.add(new JButton("Nastepna Tura"));
    }
}
