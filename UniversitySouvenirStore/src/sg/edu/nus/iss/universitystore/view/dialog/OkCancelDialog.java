package sg.edu.nus.iss.universitystore.view.dialog;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public abstract class OkCancelDialog extends Dialog {

    public OkCancelDialog (JFrame parent, String title) {
        super (parent, title);
        add ("Center", createFormPanel());
        add ("South",  createButtonPanel());
    }

    public OkCancelDialog (JFrame parent) {
        this (parent, "");
    }

    private Panel createButtonPanel () {
        Panel p = new Panel ();

        Button b;
        ActionListener l;

        b = new Button ("OK");
        l = new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                boolean success = performOkAction ();
                if (success) {
                    destroyDialog ();
                }
            }
        };
        b.addActionListener (l);
        p.add (b);

        b = new Button ("Cancel");
        l = new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                destroyDialog ();
            }
        };
        b.addActionListener (l);
        p.add (b);

        return p;
    }

    private void destroyDialog () {
        setVisible (false);
        dispose();
    }

    protected abstract Panel createFormPanel () ;

    protected abstract boolean performOkAction () ;

}