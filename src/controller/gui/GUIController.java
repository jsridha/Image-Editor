package controller.gui;

import controller.IController;
import view.gui.ViewListener;

/**
 * Extension on the IController interface. Defines
 * renderImage() method for use with graphical views.
 */
public interface GUIController extends IController, ViewListener {

  /**
   * Converts an IImage object into a BufferedImage
   * for rendering using a graphical view.
   *
   * @param imageID - ID of the image to be rendered.
   *
   */
  void renderImage(String imageID);

}
