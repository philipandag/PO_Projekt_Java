package com.Gołaś.Filip.Window.Components;

import javax.swing.*;
import javax.swing.event.ChangeEvent;

public class MySpinner extends JSpinner {
    MySpinner(int min, int max, int def){
        super(new SpinnerNumberModel(def, min, max, 1));
    }
}
