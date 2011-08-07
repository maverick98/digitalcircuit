/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dcds.ui.framework.handler;

import java.awt.Cursor;
import javax.swing.Action;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.swing.JComponent;
import org.dcds.ui.DigitalApp;
import org.dcds.ui.DigitalView;
import org.dcds.ui.framework.annotation.DigitalAction;

/**
 *
 * @author manoranjan
 */
public abstract class DigitalActionHandler {

    
    private Map<String, Action> commandMap = new HashMap<String, Action>();
    private List<Method> commandMethods = new ArrayList<Method>();
    public static final String HTML_CENTER_BEGINNING_TAG = "<html><center>";
    public static final String HTML_CENTER_ENDING_TAG = "</center></html>";
    

    public DigitalActionHandler() {

        initializeActionMethods();
    }

    

    private void initializeActionMethods() {

        Method[] methods = this.getClass().getDeclaredMethods();
        for (Method aMethod : methods) {

            Annotation commandAnnotation = aMethod.getAnnotation(DigitalAction.class);
            if (commandAnnotation != null) {
                commandMethods.add(aMethod);
            }
        }
    }

    protected DigitalView getApp() {
        return (DigitalView) DigitalApp.getApplication().getMainView();
    }

    public DigitalActionHandler getChildClass() {
        return this;
    }

    /**
     * Gets an action without a payload
     *
     * @param command
     * @return
     */
    public Action getAction(String command) {
        return getAction(command, null);
    }

    public Action getAction(String command, Object dumbPayLoad) {
        Action actionForcmd = commandMap.get(command + dumbPayLoad);
        if (actionForcmd == null) {
            initAction(command, dumbPayLoad);
            actionForcmd = commandMap.get(command + dumbPayLoad);
        }
        return actionForcmd;
    }

    private void initAction(final String command, final Object dumbPayLoad) {
        
        for (Iterator<Method> it = commandMethods.iterator(); it.hasNext();) {

            final Method xpActionMethod = it.next();
            DigitalAction actionAnot = xpActionMethod.getAnnotation(DigitalAction.class);
            String thisMethodCmd = actionAnot.id();
            if (thisMethodCmd.trim().equals(command.trim())) {

                commandMap.put(command + dumbPayLoad, new AbstractDigitalAction() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            super.actionPerformed(e);
                            
                            if (getApp() != null && getApp().getFrame() != null) {
                                getApp().getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                            }
                            


                            if (e.getSource() instanceof JComponent) {
                                JComponent actionComponent = (JComponent) e.getSource();
                                boolean dataValid = true;//VerificationBinder.validateActionOn(actionComponent);
                                if (!dataValid) {
                                    
                                    Toolkit.getDefaultToolkit().beep();
                                    return;
                                }
                            }
                            
                            long startTime = System.currentTimeMillis();
                            Class[] params = xpActionMethod.getParameterTypes();
                            if (dumbPayLoad != null && params != null && params.length == 1) {
                                xpActionMethod.invoke(getChildClass(), new Object[]{dumbPayLoad});
                            } else if (dumbPayLoad == null) {
                                xpActionMethod.invoke(getChildClass(), new Object[]{});
                            } else {
                                
                            }
                            long endTime = System.currentTimeMillis();
                            
                        } catch (Throwable ex) {
                            
                            
                        } finally {
                            if (getApp() != null && getApp().getFrame() != null) {
                                getApp().getFrame().setCursor(Cursor.getDefaultCursor());
                            }
                        }
                    }
                });
            }
        }
    }

    
}
