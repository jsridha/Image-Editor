// Author: Jay Sridharan

package controller.text.commands;

import java.util.Objects;
import java.util.Scanner;
import model.IImageState;
import model.IImageDB;
import model.transformations.IOUtil;
import view.IView;

/**
 * Requests input from user to save an image in the database.
 */
public class Save implements ICommand {

  /**
   * Constructor for Save command pattern.
   */
  public Save() {
    //Empty constructor included per JavaStyle auto-grader requirement.
  }

  /**
   * Requests input from user. Attempts to save the image
   * specified by user to the location specified.
   */
  @Override
  public void run(Scanner scan, IImageDB model, IView view) throws IllegalStateException {
    Objects.requireNonNull(scan);
    IImageDB model1 = Objects.requireNonNull(model);
    Objects.requireNonNull(view);

    if (!scan.hasNext()) {
      throw new IllegalStateException("Second argument must the location to save the image to.");
    }
    String filepath = scan.next();
    // Extract extension from the filepath in order
    // to determine what type of file is being loaded.
    String[] components = filepath.split("\\.");
    String extension = components[components.length - 1];

    if (!scan.hasNext()) {
      throw new IllegalStateException("Third argument must be the ID for the Image to save.");
    }
    String idImage = scan.next();

    IImageState sourceImage = model1.getImage(idImage);
    if (sourceImage == null) {
      throw new IllegalStateException("Image with specified ID does not exist.");
    }

    // Read in image from file, then load image into database.
    if (extension.equals("ppm")) {
      IOUtil.writeToPPM(filepath, sourceImage);
    } else {
      IOUtil.writeNonPPM(filepath, sourceImage, extension);
    }
  }
}
