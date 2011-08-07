/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.dcds.ui.framework.handler;

/**
 *
 * @author manoranjan
 */

import java.util.HashMap;
import java.util.Map;

public class HandlerProvider {

    private final static Map<Class, DigitalActionHandler> factoryMap = new HashMap<Class, DigitalActionHandler>();

    public synchronized static <T> T  get(Class<T> claz) {

        DigitalActionHandler handler = factoryMap.get(claz);
        if (handler != null) {
            return (T) handler;
        }

        try {
            handler = (DigitalActionHandler) claz.newInstance();
            factoryMap.put(claz, handler);

            return (T)handler;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }


    }

    public static void reset(Class claz) {
        factoryMap.put(claz, null);
    }
}
