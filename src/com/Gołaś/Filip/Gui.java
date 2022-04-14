package com.Gołaś.Filip;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Gui extends JPanel {
    Swiat swiat;
    public Gui(Swiat swiat){
        setLayout(new BorderLayout(3, 3));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        add(tools, BorderLayout.PAGE_START);
        tools.add(new NastepnaTuraPrzycisk(swiat));
    }
}
