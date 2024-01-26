package view.gui;

import java.awt.image.BufferedImage;

import view.IView;

/**
 * Extension of IView interface. Defines methods for a graphical view.
 */
public interface IGUIView extends IView {

  /**
   * Adds the passed ViewListener to the
   * current instance's listener group
   *
   * @param listener - a ViewListener object, typically an IController object for the View.
   */
  void addViewListeners(ViewListener listener);

  /**
   * Renders the current image being
   * worked on to the graphical view.
   *
   * @param image
   */
  void renderImage(BufferedImage image);

  /**
   * Returns focus back to the window.
   */
  void requestFrameFocus();

  /**
   * Sets view window visibility based on boolean passed
   *
   * @param value boolean switch for whether window is visible or not.
   */
  void setVisible(boolean value);

  /**
   * Renders four histograms based on the current being displayed.
   *
   * @param red - Set of data for histogram of red channels.
   * @param green - Set of data for histogram of green channels.
   * @param blue - Set of data for histogram of blue channels.
   * @param intensity - Set of data for histogram of pixel intensity.
   */
  void renderHistograms(int[] red, int[] green, int[] blue, int[] intensity);
}
