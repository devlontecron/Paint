/**
 * TCSS 305 – Autumn 2015 Assignment 5.2
 * 
 * the class that handles the events and listeners for the drawing panel.
 * 
 * @author Devin Durham
 * @version 50 Nov 2015
 */

package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.AbstractShapes;
import model.Ellipse;
import model.Line;
import model.Pencil;
import model.Rectangle;
import model.Shapes;

/**
 * All events that take place within the drawing panel or in this case, the
 * JPanel.
 * 
 * @version 43
 * @author Devin
 */
public class DrawingPanel extends JPanel {

    /**
     * the variable array used to store and hold painted shapes.
     */
    protected static ArrayList<AbstractShapes> shapesDrawn = new ArrayList<AbstractShapes>();

    /**
     * auto generated serial version.
     */
    private static final long serialVersionUID = -9209553409610465080L;

    /**
     * variable for the coordinates when the mouse releases.
     */
    protected double myEndX;
    /**
     * variable for the coordinates when the mouse releases.
     */
    protected double myEndY;
    /**
     * variable for the coordinates when the mouse clicks.
     */
    protected double myStartX;
    /**
     * variable for the coordinates when the mouse clicks.
     */
    protected double myStartY;

    /**
     * the variable to hold the shape width. initially set at 1.
     */
    protected int myWidth = 1;

    /**
     * the variable to hold the shapes color from myChosenColor.
     */
    protected Color myColor;

    /**
     * the variable used to know Line tool is in use.
     */
    private boolean myUseLine;

    /**
     * the variable used to know Ellipse tool is in use.
     */
    private boolean myUseEllipse;

    /**
     * the variable used to know rectangle tool is in use.
     */
    private boolean myUseRectangle;

    /**
     * the variable used to know pencil tool is in use.
     */
    private boolean myUsePencil;

    /**
     * sets the shapes color.
     * 
     * @param theColor passing in type color for myColor.
     */
    public void setColor(final Color theColor) {
        firePropertyChange("color", null, theColor);

        myColor = theColor;
    }

    /**
     * sets the shapes width.
     * 
     * @param theWidth passing in type int for myWidth.
     */
    public void setWidth(final int theWidth) {
        myWidth = theWidth;
    }

    /**
     * identifies which tool is in use and disables the others.
     * 
     * @param theToolName for switch statement.
     */
    public void pathPanel(final String theToolName) {

        switch (theToolName) {
            case "Line":
                myUseLine = true;
                myUseEllipse = false;
                myUseRectangle = false;
                myUsePencil = false;
                break;

            case "Ellipse":
                myUseLine = false;
                myUseEllipse = true;
                myUseRectangle = false;
                myUsePencil = false;
                break;

            case "Rectangle":
                myUseLine = false;
                myUseEllipse = false;
                myUseRectangle = true;
                myUsePencil = false;
                break;

            case "Pencil":
                myUseLine = false;
                myUseEllipse = false;
                myUseRectangle = false;
                myUsePencil = true;
                break;
            default:
                break;

        }
        addMouseMotionListener(new MotionListener());
        addMouseListener(new MyMouseListener());
    }

    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);

        for (int i = 0; i < shapesDrawn.size(); i++) {
            shapesDrawn.get(i).draw(theGraphics);
        }

    }

    /**
     * Listens for mouse drag, to draw on panel.
     */
    public class MyMouseListener extends MouseAdapter {
        /**
         * Handles a mouse event.
         * 
         * @param theEvent The event.
         */

        @Override
        public void mousePressed(final MouseEvent theLineEvent) {

            myStartX = theLineEvent.getX();
            myStartY = theLineEvent.getY();
            AbstractShapes shape = null;

            if (myUseLine) {
                shape = new Line(myColor, myWidth, myStartX, myStartY, myStartX, myStartY);

            }
            if (myUseEllipse) {
                shape = new Ellipse(myColor, myWidth, myStartX, myStartY, myStartX, myStartY);

            }
            if (myUsePencil) {
                shape = new Pencil(myColor, myWidth, myStartX, myStartY, myStartX, myStartY);

            }
            if (myUseRectangle) {
                shape = new Rectangle(myColor, myWidth, myStartX, myStartY, myStartX,
                                      myStartY);

            }

            if (shape == null) {
                return;
            }
            shape.setStartX(myStartX);
            shape.setStartY(myStartY);
            shape.setEndX(myStartX);
            shape.setEndY(myStartY);
            shape.setColor(myColor);
            shape.setWidth(myWidth);
            shapesDrawn.add(shape);

            PowerPaintGUI.myUndo.setEnabled(true);
            repaint();

        }

        /**
         * mouse listener to get the release coordinates for drawing the line.
         * 
         * @param theLineEvent This is the type parameter
         */
        @Override
        public void mouseReleased(final MouseEvent theLineEvent) {

            myEndX = theLineEvent.getX();
            myEndY = theLineEvent.getY();
            final Shapes shape = shapesDrawn.get(shapesDrawn.size() - 1);
            shape.setEndX(myEndX);

            shape.setEndY(myEndY);
        }

    }

    /**
     * a mouse motion listener to update the panel as the mouse is moved.
     * 
     * @author Devin
     *
     */
    public class MotionListener implements MouseMotionListener {

        @Override
        public void mouseDragged(final MouseEvent theLineEvent) {

            if (myUsePencil) {
                myEndX = theLineEvent.getX();
                myEndY = theLineEvent.getY();
                final Pencil pencil = new Pencil(myColor, myWidth, theLineEvent.getX(),
                                                 theLineEvent.getY(), theLineEvent.getX(),
                                                 theLineEvent.getY());
                shapesDrawn.add(pencil);
            }

            myEndX = theLineEvent.getX();
            myEndY = theLineEvent.getY();
            final Shapes shape = shapesDrawn.get(shapesDrawn.size() - 1);
            shape.setEndX(myEndX);
            shape.setEndY(myEndY);

            repaint();
        }

        @Override
        public void mouseMoved(final MouseEvent theArgs) {

        }

    }
}
