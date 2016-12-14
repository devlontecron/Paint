/*
 * TCSS 305 – Autumn 2015 Assignment 5.1- PowerPaint
 */

/**
 * TCSS 305 – Autumn 2015 Assignment 5.1
 * 
 * a paint program including a GUI with tools like pencils, rectangles, lines,
 * and ellipse.
 * 
 * @author Devin Durham
 * @version 44 Nov 2015
 */

package model;

import gui.PowerPaintGUI;

import java.awt.EventQueue;

/**
 * TCSS 305 – Autumn 2015 Assignment 4
 * 
 * the class that handles starting PowerPaintGUI.
 * 
 * @author Devin Durham
 * @version 37 Nov 2015
 */
//
public final class PowerPaintMain {

    /**
     * Private constructor, to prevent instantiation of this class.
     */
    private PowerPaintMain() {
        throw new IllegalStateException();
    }

    /**
     * The main method, invokes the SnapShop GUI. Command line arguments are
     * ignored.
     * 
     * @param theArgs Command line arguments.
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PowerPaintGUI().start();
            }
        });
    }

}
