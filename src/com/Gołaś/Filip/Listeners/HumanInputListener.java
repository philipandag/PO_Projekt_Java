package com.Gołaś.Filip.Listeners;

import com.Gołaś.Filip.Game.Direction;

import java.awt.event.KeyEvent;
import java.io.Serializable;

public interface HumanInputListener extends Serializable {
    public void execute(KeyEvent keyEvent);
}
