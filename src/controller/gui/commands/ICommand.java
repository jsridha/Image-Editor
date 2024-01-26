package controller.gui.commands;

import model.IImageDB;

/**
 * Interface for graphics based command patterns to
 * perform various transformations on IImage objects.
 */
public interface ICommand {

  /**
   * Performs a transformation on an image and stores result in model
   *
   * @param model IImageDB model containing IImage objects
   * @param imageID - Image being transformed
   * @param newImage - Title for resulting image
   * @throws IllegalStateException - if unable to locate image/perform transformation.
   */
  void run(IImageDB model, String imageID, String newImage) throws IllegalStateException;
}
