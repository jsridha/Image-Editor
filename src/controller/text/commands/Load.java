// Author: Jay Sridharan

package controller.text.commands;

import java.util.Objects;
import java.util.Scanner;
import model.IImageState;
import model.IImageDB;
import model.transformations.IOUtil;
import view.IView;

/**
 * Requests filepath and identifier for image to load.
 */
public class Load implements ICommand {

  /**
   * Constructor for Load command pattern.
   */
  public Load() {
    //Empty constructor included per JavaStyle auto-grader requirement.
  }

  /**
   * Requests input from user. Attempts to read in ppm
   * file at location provided. If successful, loads contents
   * into Image Database with provided identifier.
   */
  @Override
  public void run(Scanner scan, IImageDB model, IView view) throws IllegalStateException {
    Objects.requireNonNull(scan);
    IImageDB model1 = Objects.requireNonNull(model);
    Objects.requireNonNull(view);

    if (!scan.hasNext()) {
      throw new IllegalStateException("Second argument must be the filepath to load.");
    }
    String filepath = scan.next();
    // Extract extension from the filepath in order
    // to determine what type of file is being loaded.
    String[] components = filepath.split("\\.");
    // Extension should always be last thing following a period in filepath.
    String extension = components[components.length - 1];

    if (!scan.hasNext()) {
      throw new IllegalStateException("Third argument must be the ID for the resulting image.");
    }
    String newId = scan.next();
    IImageState sourceImage;

    // Read in image from file, then load image into database.
    if (extension.equals("ppm")) {
      sourceImage = IOUtil.readPPM(filepath);
    } else {
      sourceImage = IOUtil.readNonPPM(filepath);
    }

    model1.loadImage(newId, sourceImage);
  }
}
