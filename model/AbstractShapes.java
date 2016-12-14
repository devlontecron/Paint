/**
 * TCSS 305 – Autumn 2015 Assignment 5.2
 * 
 * the abstract class that handles shared methods for shapes.
 * 
 * @author Devin Durham
 * @version 50 Nov 2015
 */

package model;

import java.awt.Color;
import java.awt.Graphics;

/**
 * the abstract class for all the shape types.
 * 
 * @author Devin
 * @version 50
 */
public abstract class AbstractShapes implements Shapes {

    /**
     * the variable to hold the starting X of the shape.
     */
    protected double myStartX;

    /**
     * the variable to hold the starting Y of the shape.
     */
    protected double myStartY;

    /**
     * the variable to hold the ending X of the shape.
     */
    protected double myEndX;

    /**
     * the variable to hold the ending X of the shape.
     */
    protected double myEndY;

    /**
     * the variable to hold the color of the shape.
     */
    protected Color myColor;

    /**
     * the variable to hold the the width of the shape.
     */
    protected int myWidth;

    /**
     * the constructor that all Shapes have.
     * 
     * @param theColor 
     * @param theWidth 
     * @param theStartX 
     * @param theStartY 
     * @param theEndX 
     * @param theEndY 
     */
    public AbstractShapes(final Color theColor, final int theWidth, final double theStartX,
                          final double theStartY, final double theEndX, final double theEndY) {
        myStartX = theStartX;
        myStartY = theStartY;
        myEndX = theEndX;
        myEndY = theEndY;
        myWidth = theWidth;

        myColor = theColor;

    }

    @Override
    public double getStartX() {
        return myStartX;
    }

    @Override
    public double getStartY() {
        return myStartY;
    }

    @Override
    public double getEndX() {
        return myEndX;
    }

    @Override
    public double getEndY() {
        return myEndY;
    }

    @Override
    public int getWidth() {
        return myWidth;
    }

    @Override
    public void setStartX(final double theStartX) {
        myStartX = theStartX;

    }

    @Override
    public void setStartY(final double theStartY) {
        myStartY = theStartY;

    }

    @Override
    public void setEndX(final double theEndX) {
        myEndX = theEndX;

    }

    @Override
    public void setEndY(final double theEndY) {
        myEndY = theEndY;

    }

    @Override
    public Color getColor() {
        return myColor;
    }

    /**
     * the method that set myColor.
     * 
     * @param theColor 
     */
    public void setColor(final Color theColor) {
        myColor = theColor;
    }

    /**
     * the method that sets myWidth.
     * 
     * @param theWidth 
     */
    public void setWidth(final int theWidth) {
        myWidth = theWidth;
    }

    /**
     * the method that carries the draw method of graphic for each shape.
     * 
     * @param theGraphics 
     */
    public void draw(final Graphics theGraphics) {
    }
}
