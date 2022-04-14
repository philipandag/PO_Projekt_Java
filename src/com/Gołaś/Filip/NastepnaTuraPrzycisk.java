package com.Gołaś.Filip;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class NastepnaTuraPrzycisk extends JButton {
    Swiat swiat;
    public NastepnaTuraPrzycisk(Swiat swiat) {
        super("Nastepna Tura");
        addActionListener((ActionEvent e) -> {
                swiat.nastepnaTura();
        });
    }
}
