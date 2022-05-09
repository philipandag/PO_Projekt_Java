package com.Gołaś.Filip.Window.Components;

import javax.swing.*;
import java.io.File;

public class FileSaver extends JFileChooser {

    private JFrame parent;

    public FileSaver(JFrame parent){
        this.parent = parent;
    }
    @Override
    public void approveSelection() {
        File f = getSelectedFile();
        if(getDialogType() == SAVE_DIALOG){
            if(f.exists())
            {
                int result = JOptionPane.showConfirmDialog(parent, "The file exists, overwrite?", "Existing file", JOptionPane.YES_NO_CANCEL_OPTION);

                switch(result){
                    case JOptionPane.YES_OPTION:
                        super.approveSelection();
                        return;
                    case JOptionPane.NO_OPTION:
                        return;
                    case JOptionPane.CLOSED_OPTION:
                        return;
                    case JOptionPane.CANCEL_OPTION:
                        cancelSelection();
                        return;
                }
            }
            else
                super.approveSelection();
        }
    }
}
