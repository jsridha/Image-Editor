package controller.gui.commands;

import model.IImageDB;

/**
 * Interface for input/output command patterns.
 * Redefines method signature for run() method.
 */
public interface IOCommand extends ICommand {

  /**
   * Executes file input or output using specified file name and path.
   *
   * @param model    IImageDB model containing IImage objects
   * @param filepath - filepath for IO operation.
   * @param imageID  - Title of image.
   * @throws IllegalStateException - if unable to locate image/perform IO request.
   */
  @Override
  void run(IImageDB model, String filepath, String imageID) throws IllegalStateException;

}