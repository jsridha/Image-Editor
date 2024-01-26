// Author: Jay Sridharan

package controller.text.commands;

import java.util.Objects;
import java.util.Scanner;
import model.IImageDB;
import model.IImageState;
import model.transformations.ITransformation;
import model.transformations.VisualizeIntensityTransform;
import view.IView;

/**
 * Requests image to transform.
 */
public class VisualizeIntensity implements ICommand {

  /**
   * Empty constructor.
   */
  public VisualizeIntensity() {
    //Empty constructor included per JavaStyle auto-grader requirement.
  }

  /**
   * Requests input to transform from user. Passes transformed image to model.
   */
  @Override
  public void run(Scanner scan, IImageDB model, IView view) throws IllegalStateException {
    Objects.requireNonNull(scan);
    IImageDB model1 = Objects.requireNonNull(model);
    Objects.requireNonNull(view);

    if (!scan.hasNext()) {
      throw new IllegalStateException("Second argument must be the ID "
              + "of the image to be modified.");
    }
    String idImage = scan.next();

    if (!scan.hasNext()) {
      throw new IllegalStateException("Third argument must be the ID for the resulting image.");
    }
    String newId = scan.next();

    IImageState sourceImage = model1.getImage(idImage);
    if (sourceImage == null) {
      throw new IllegalStateException("Image with specified ID does not exist.");
    }

    ITransformation visualize = new VisualizeIntensityTransform();
    IImageState visualizedImage = visualize.run(sourceImage);

    model1.loadImage(newId, visualizedImage);
  }
}
