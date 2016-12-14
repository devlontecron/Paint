/**
 * TCSS 305 – Autumn 2015 Assignment 5.2
 * 
 * A class that builds the GUI and sets action listeners for the GUI buttons.
 * 
 * @author Devin Durham
 * @version 50 Nov 2015
 */

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 
 * class that extends Jframe to help build the GUI.
 * 
 * @author Devin
 * @version 43
 *
 */
public class PowerPaintGUI extends JFrame implements PropertyChangeListener {
   
    /**
     * the JMenuItem under "File" called "Undo".
     */
    protected static JMenuItem myUndo = new JMenuItem("myUndo all changes");
   
    /**
     * auto generated serial version.
     */
    private static final long serialVersionUID = 6529122849443895629L;
    
    /**
     * sets the default width of the panel to 400px.
     */
    private static final int PANEL_WIDTH = 400;
   
    /**
     * sets the default width of the panel to 200px.
     */
    private static final int PANEL_HEIGHT = 200;

    /**
     * the variable to hold after the JColorChooser.
     */
    protected Color myChosenColor;

    /**
     * the variable to hold the myWidth value of shapes.
     */
    protected int mySliderValue;

    /**
     * the variable used to hold the shaped drawing thickness.
     */
    protected int myWidth;


 
    /**
     * the panel in the center of the GUI to draw on.
     */
    private JPanel myPanel;

    /**
     * The method that holds all the buttons and GUI and builds them.
     */
    

    public void start() {

        final String pencil = "Pencil";
        final String pencilImg = "./images/pencil_bw.gif";

        final String rectangle = "Rectangle";
        final String rectangleImg = "./images/rectangle_bw.gif";

        final String line = "Line";
        final String lineImg = "./images/line_bw.gif";

        final String ellipse = "Ellipse";
        final String ellipseImg = "./images/ellipse_bw.gif";

        ButtonGroup togBtnGrp;

        ButtonGroup radBtnGrp;

        JToggleButton togBtnPen;
        JToggleButton togBtnLine;
        JToggleButton togBtnRec;
        JToggleButton togBtnElip;

        JMenu menuO;
        JCheckBoxMenuItem gridBtn;
        JMenu thickness;
        JMenuItem color;

        JMenu menuF;
        JMenuItem exit;

        final int min = 0;
        final int max = 20;
        final int init = 1;

        JMenu menuT;
        JRadioButtonMenuItem radBtnPen;
        JRadioButtonMenuItem radBtnLine;
        JRadioButtonMenuItem radBtnRec;
        JRadioButtonMenuItem radBtnElip;

        JMenu menuH;
        JMenuItem about;

        final int largeTickMarks = 5;

        setTitle("PowerPaint");
        final ImageIcon img = new ImageIcon("./images/w.gif");
        setIconImage(img.getImage());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myPanel = new DrawingPanel();

        myPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

        myPanel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        myPanel.setBackground(Color.WHITE);
        add(myPanel, BorderLayout.CENTER);

        final JMenuBar menuBar = new JMenuBar();

        menuF = new JMenu("File");

        new JMenuItem("Open...");
        new JMenuItem("Close Image");
        exit = new JMenuItem("Exit");

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theLineEvent) {
                System.exit(0);
            }
        });

        myUndo = new JMenuItem("Undo all changes");
        myUndo.setEnabled(false);
        myUndo.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theLineEvent) {
                DrawingPanel.shapesDrawn.clear();
                repaint();
                myUndo.setEnabled(false);
            }
        });

        menuF.add(myUndo);
        menuF.addSeparator();
        menuF.add(exit);

        menuF.setMnemonic(KeyEvent.VK_F);
        exit.setMnemonic(KeyEvent.VK_X);
        myUndo.setMnemonic(KeyEvent.VK_U);

        menuBar.add(menuF);

        menuO = new JMenu("Options");

        gridBtn = new JCheckBoxMenuItem("Grid");
        gridBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theGridEvent) {
                if (gridBtn.getState()) {

                    myPanel.setBackground(null);
                } else {
                    myPanel.setBackground(Color.WHITE);
                }
            }
        });

        thickness = new JMenu("Thickness");
        final ImageIcon colorBox = new ImageIcon(rectangleImg);
        color = new JMenuItem("Color...", colorBox);
        color.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theColorEvent) {
                myChosenColor = JColorChooser.showDialog(null, "Color Chooser", myChosenColor);
                ((DrawingPanel) myPanel).setColor(myChosenColor);
            }
        });

        final JSlider sliderThickness = new JSlider(JSlider.HORIZONTAL, min, max, init);

        sliderThickness.setMajorTickSpacing(largeTickMarks);
        sliderThickness.setMinorTickSpacing(init);
        sliderThickness.setPaintTicks(true);
        sliderThickness.setPaintLabels(true);

        mySliderValue = sliderThickness.getValue();
        sliderThickness.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent theEvent) {
                mySliderValue = ((JSlider) theEvent.getSource()).getValue();

                if (mySliderValue == 0) {
                    JOptionPane.showMessageDialog(null, "Can't draw shape with 0 Thickness");
                }
                else {
                    ((DrawingPanel) myPanel).setWidth(mySliderValue);
                }
            }
        });

        thickness.add(sliderThickness);

        menuO.add(gridBtn);
        menuO.addSeparator();
        menuO.add(thickness);
        menuO.addSeparator();
        menuO.add(color);

        menuO.setMnemonic(KeyEvent.VK_O);
        gridBtn.setMnemonic(KeyEvent.VK_G);
        color.setMnemonic(KeyEvent.VK_C);

        menuBar.add(menuO);

        menuT = new JMenu("Tools");
        radBtnGrp = new ButtonGroup();
        radBtnPen = new JRadioButtonMenuItem(pencil, new ImageIcon(pencilImg));

        radBtnLine = new JRadioButtonMenuItem(line, new ImageIcon(lineImg));
        radBtnRec = new JRadioButtonMenuItem(rectangle, new ImageIcon(rectangleImg));
        radBtnElip = new JRadioButtonMenuItem(ellipse, new ImageIcon(ellipseImg));
        radBtnGrp.add(radBtnPen);
        radBtnGrp.add(radBtnLine);
        radBtnGrp.add(radBtnRec);
        radBtnGrp.add(radBtnElip);

        menuT.add(radBtnPen);
        menuT.add(radBtnLine);
        menuT.add(radBtnRec);
        menuT.add(radBtnElip);

        menuT.setMnemonic(KeyEvent.VK_T);
        radBtnPen.setMnemonic(KeyEvent.VK_P);
        radBtnLine.setMnemonic(KeyEvent.VK_L);
        radBtnRec.setMnemonic(KeyEvent.VK_R);
        radBtnElip.setMnemonic(KeyEvent.VK_E);

        radBtnPen.setSelected(true);

        menuBar.add(menuT);
        ((DrawingPanel) myPanel).pathPanel(pencil);
        menuH = new JMenu("Help");
        about = new JMenuItem("About...");
        about.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theLineEvent) {
                JOptionPane.showMessageDialog(null, "Power Paint \nCreated by: Devin Durham \n"
                                               + "Instructor: Fowler \nClass: 305 Fall 2015");
            }
        });

        menuH.add(about);

        menuH.setMnemonic(KeyEvent.VK_H);
        about.setMnemonic(KeyEvent.VK_A);

        menuBar.add(menuH);

        setJMenuBar(menuBar);

        final JToolBar bar = new JToolBar();
        togBtnGrp = new ButtonGroup();
        togBtnPen = new JToggleButton(pencil, new ImageIcon(pencilImg));

        togBtnPen.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theLineEvent) {
                ((DrawingPanel) myPanel).pathPanel(pencil);
                radBtnPen.setSelected(true);
            }
        });

        togBtnLine = new JToggleButton(line, new ImageIcon(lineImg));
        togBtnLine.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theLineEvent) {
                ((DrawingPanel) myPanel).pathPanel(line);
                radBtnLine.setSelected(true);
            }
        });

        togBtnElip = new JToggleButton(ellipse, new ImageIcon(ellipseImg));
        togBtnElip.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theLineEvent) {
                ((DrawingPanel) myPanel).pathPanel(ellipse);
                radBtnElip.setSelected(true);
            }
        });

        togBtnRec = new JToggleButton(rectangle, new ImageIcon(rectangleImg));
        togBtnRec.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theLineEvent) {
                ((DrawingPanel) myPanel).pathPanel(rectangle);
                radBtnRec.setSelected(true);
            }
        });

        togBtnGrp.add(togBtnPen);
        togBtnGrp.add(togBtnLine);
        togBtnGrp.add(togBtnRec);
        togBtnGrp.add(togBtnElip);

        bar.add(togBtnPen);
        bar.add(togBtnLine);
        bar.add(togBtnElip);
        bar.add(togBtnRec);

        togBtnPen.setMnemonic(KeyEvent.VK_P);
        togBtnLine.setMnemonic(KeyEvent.VK_L);
        togBtnRec.setMnemonic(KeyEvent.VK_R);
        togBtnElip.setMnemonic(KeyEvent.VK_E);

        togBtnPen.setSelected(true);

        add(bar, BorderLayout.SOUTH);

        radBtnPen.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theButEvent) {
                ((DrawingPanel) myPanel).pathPanel(pencil);
                togBtnPen.setSelected(true);
            }
        });

        radBtnLine.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theButEvent) {
                ((DrawingPanel) myPanel).pathPanel(line);
                togBtnLine.setSelected(true);
            }
        });

        radBtnElip.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theButEvent) {
                ((DrawingPanel) myPanel).pathPanel(ellipse);
                togBtnElip.setSelected(true);
            }
        });

        radBtnRec.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theButEvent) {
                ((DrawingPanel) myPanel).pathPanel(rectangle);
                togBtnRec.setSelected(true);
            }
        });

        // This is setting the pencil tool in use when the program starts.
        ((DrawingPanel) myPanel).pathPanel(pencil);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    @Override
    public void propertyChange(final PropertyChangeEvent theUndoEvent) {
        if ("color".equals(theUndoEvent.getPropertyName())) {
            System.out.println("Text action reached");

            // OK, let's enable the quit button based on the state of the
            // panel's text
            myUndo.setEnabled(false);
        }
    }

}
