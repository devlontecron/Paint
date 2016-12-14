
/**
 * TCSS 305 – Autumn 2015 Assignment 5.2
 * 
 * the class that handles the shared variables and interfaces for shapes.
 * 
 * @author Devin Durham
 * @version 50 Nov 2015
 */

package model;

import java.awt.Color;


/**
 * an interface class to hold the setters and getters for the shapes.
 * @author Devin
 * @version 50 
 *
 */
public interface Shapes {

    /**
     * the setter method for startX.
     * @return StartX
     */
    double getStartX();
        
    /**
     * the getter method for startY.
     * @return StartY
     */
    double getStartY();

    /**
     * the getter method for EndX.
     * @return EndX
     */
    double getEndX();

    /**
     * the getter method for EndY.
     * @return EndY
     */
    double getEndY();

    /**
     * the getter method for color.
     * @return Color
     */
    Color getColor();

    /**
     * the getter method for line width.
     * @return width
     */
    int getWidth();

    /**
     * the setter method for startX.
     * @param theStartX 
     */
    void setStartX(double theStartX);

    /**
     * the setter method for startY.
     * @param theStartY 
     */
    void setStartY(double theStartY);

    /**
     * the setter method for EndX.
     * @param theEndX 
     */
    void setEndX(double theEndX);

    /**
     * the setter method for EndY.
     * @param theEndY 
     */
    void setEndY(double theEndY);

    /**
     * the setter method for color.
     * @param theColor 
     */
    void setColor(Color theColor);

    /**
     * the setter method for width of the line.
     * @param theWidth   
     */
    void setWidth(int theWidth);

}
