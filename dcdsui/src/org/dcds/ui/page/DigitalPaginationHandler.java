/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.dcds.ui.page;

import org.dcds.ui.framework.annotation.DigitalAction;
import org.dcds.ui.framework.handler.DigitalActionHandler;

/**
 *
 * @author manoranjan
 */
public class DigitalPaginationHandler extends DigitalActionHandler{

    @DigitalAction(id="back")
    public void back(){
        System.out.println("Back clicked...");
        
    }
    @DigitalAction(id="forward")
    public void forward(){
        System.out.println("forward clicked...");
    }

}
