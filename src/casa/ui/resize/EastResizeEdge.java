package casa.ui.resize;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * <p>Copyright: Copyright 2003-2014, Knowledge Science Group, University of Calgary.
 * Permission to use, copy, modify, distribute and sell this software and its
 * documentation for any purpose is hereby granted without fee, provided that
 * the above copyright notice appear in all copies and that both that copyright
 * notice and this permission notice appear in supporting documentation.  The
 * Knowledge Science Group makes no representations about the suitability of
 * this software for any purpose.  It is provided "as is" without express or
 * implied warranty.</p>
 *
 * @author <a href="http://cpsc.ucalgary.ca/~kremer/">Rob Kremer</a>
 * @version 0.9
 * @author <a href="mailto:ayala@cpsc.ucalgary.ca">Gabriel Becerra</a>
 */
public class EastResizeEdge extends JPanel
    implements MouseListener, MouseMotionListener {
  //
  // Variables
  //
  private int     WIDTH     = 0;   // The constant thikness
  private int     MIN_WIDTH = 50;  // The component's minumum resizable width
  private boolean dragging;

  //
  // JAVA UI Objects
  //
  private JComponent resizeComponent;  // The component to resize

  /**
   *
   * @param component that is resized
   */
  public EastResizeEdge (JComponent component) {
    resizeComponent = component;
    addMouseListener (this);
    addMouseMotionListener (this);
    setOpaque (true);
    setBackground (Color.black);
  }

  /**
   * Ensures that the edges have a constant thickness
   * @return the desired dimensions
   */
  public Dimension getPreferredSize () {
    return new Dimension (WIDTH, resizeComponent.getHeight ());
  }

  //
  // MouseEvents Section
  //
  public void mouseClicked (MouseEvent e) {}
  public void mouseMoved (MouseEvent e) {}
  public void mouseReleased (MouseEvent e) {}
  public void mouseDragged (MouseEvent e) {}
  public void mouseEntered (MouseEvent e) {
    setCursor (Cursor.getPredefinedCursor (Cursor.E_RESIZE_CURSOR));
  }
  public void mouseExited (MouseEvent e) {
    setCursor (Cursor.getPredefinedCursor (Cursor.DEFAULT_CURSOR));
  }
  public void mousePressed (MouseEvent e) {
    //toFront();
  }
}