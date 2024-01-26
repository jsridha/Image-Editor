// Author: Jay Sridharan

package controller.text.commands;

import java.util.Scanner;
import model.IImageDB;
import view.IView;

/**
 * Interface for text-based command patterns to
 * perform various transformations on IImage objects.
 */
public interface ICommand {

  /**
   * Initiates command pattern to request
   * input and perform relevant transformation.
   *
   * @param scan Scanner that is reading in user input.
   * @param model IImageDB model containing IImage objects
   * @param view IView that is rendering view of program.
   */
  void run(Scanner scan, IImageDB model, IView view) throws IllegalStateException;
}
