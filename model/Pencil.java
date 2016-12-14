/**
 * TCSS 305 – Autumn 2015 Assignment 5.2
 * 
 * the class that handles and draws the pencil shape.
 * 
 * @author Devin Durham
 * @version 50 Nov 2015
 */

package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;

/**
 * Builds the Rectangle shape.
 * 
 * @author Devin
 * @version 50
 */
public class Pencil extends AbstractShapes {

    /**
     * the constructor for the shape.
     * 
     * @param theColor 
     * @param theWidth 
     * @param theStartX 
     * @param theStartY 
     * @param theEndX 
     * @param theEndY 
     */
    public Pencil(final Color theColor, final int theWidth, final double theStartX,
                  final double theStartY, final double theEndX, final double theEndY) {
        super(theColor, theWidth, theStartX, theStartY, theEndX, theEndY);

    }

    @Override
    public void draw(final Graphics theGraphics) {
        super.draw(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

        // for better graphics display
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);

        final Path2D.Double pencil = new Path2D.Double();

        pencil.moveTo(myStartX, myStartY);
        pencil.lineTo(myEndX, myEndY);

        // pencil.lineTo(myEndX/2, myEndY/2);

        g2d.draw(pencil);

        g2d.setPaint(myColor);
        g2d.setStroke(new BasicStroke(myWidth));
        g2d.draw(pencil);

    }

}
