/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thelastpromise;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

/**
 *
 * @author justi_000
 */
public class BuildMenuFrame extends JFrame implements WindowFocusListener,  WindowListener {
    boolean build;
    public BuildMenuFrame()
    {
        super();
        addWindowFocusListener(this);
        addWindowListener(this);
        build = true;
    }
    
    public BuildMenuFrame(boolean build)
    {
        super();
        addWindowFocusListener(this);
        addWindowListener(this);
        this.build = build;
    }

    public void windowGainedFocus(WindowEvent e) {}

    public void windowLostFocus(WindowEvent e) {
        if(build&&!BuildMenu.getLastBuilder().selected)
        { BuildMenu.getLastBuilder().select();}
        this.dispose();
    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e){
    }

    public void windowClosed(WindowEvent e) {
        if(build&&!BuildMenu.getLastBuilder().selected)
        { BuildMenu.getLastBuilder().select();}
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }
    
}
