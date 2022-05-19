package com.Gołaś.Filip.Listeners;

import java.awt.event.KeyEvent;
import java.io.Serializable;

public interface HumanInputListener extends Serializable {
    public boolean execute(KeyEvent keyEvent);
}
