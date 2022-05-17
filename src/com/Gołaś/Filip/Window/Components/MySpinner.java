package com.Gołaś.Filip.Window.Components;

import javax.swing.*;
import javax.swing.event.ChangeEvent;

public class MySpinner extends JSpinner {
    private JSpinner spinner;
    MySpinner(int min, int max, int def){
        //SpinnerNumberModel model = new SpinnerNumberModel(def, min, max, 1);
        super(new SpinnerNumberModel(def, min, max, 1));
    }
}
