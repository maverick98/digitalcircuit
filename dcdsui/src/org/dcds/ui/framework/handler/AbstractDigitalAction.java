/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dcds.ui.framework.handler;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author manoranjan
 */
public class AbstractDigitalAction implements javax.swing.Action{

    public Object getValue(String key) {
        return null;
    }

    public void putValue(String key, Object value) {
        return;
    }

    public void setEnabled(boolean b) {
        return;
    }

    public boolean isEnabled() {
        return true;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        
    }

    public void actionPerformed(ActionEvent e) {
        
    }
}
