// Author: Jay Sridharan

package controller.text.commands;

import java.util.Objects;
import java.util.Scanner;
import model.IImageState;
import model.IImageDB;
import model.transformations.BrightenTransform;
import model.transformations.ITransformation;
import view.IView;

/**
 * Strategy pattern to apply a brighten or darken transformation to an Image.
 */
public class Brighten implements ICommand {

  /**
   * Empty Constructor.
   */
  public Brighten() {
    //Empty constructor included per JavaStyle auto-grader requirement.
  }

  /**
   * Brightens/Darkens an image by adding the value passed
   * to the constructor to each channel contained in a pixel.
   * Clamps values between 0-255.
   */
  @Override
  public void run(Scanner scan, IImageDB model, IView view) throws IllegalStateException {
    Objects.requireNonNull(scan);
    IImageDB model1 = Objects.requireNonNull(model);
    Objects.requireNonNull(view);

    if (!scan.hasNextInt()) {
      throw new IllegalStateException("Second argument must be the value"
                                    + " to brighten/darken the image by.");
    }
    int value = scan.nextInt();

    if (!scan.hasNext()) {
      throw new IllegalStateException("Third argument must be the ID of"
             + " the image to be modified.");
    }
    String idImage = scan.next();

    if (!scan.hasNext()) {
      throw new IllegalStateException("Fourth argument must be the ID for the resulting image.");
    }
    String newId = scan.next();

    IImageState sourceImage = model1.getImage(idImage);
    if (sourceImage == null) {
      throw new IllegalStateException("Image with specified ID does not exist.");
    }

    ITransformation brighten = new BrightenTransform(value);
    IImageState brightenedImage = brighten.run(sourceImage);

    model1.loadImage(newId, brightenedImage);
  }
}
