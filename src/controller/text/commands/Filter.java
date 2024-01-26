package controller.text.commands;

import java.util.Objects;
import java.util.Scanner;

import model.IImageDB;
import model.IImageState;
import model.transformations.FilterTransform;
import model.transformations.ITransformation;
import view.IView;

/**
 * Requests image to transform and name to store result under.
 */
public class Filter implements ICommand {
  private double[][] kernel;

  /**
   * Constructs a Filter object. Based on which type
   * of filter is being applied, sets kernel field.
   *
   * @param type - String containing the type of filter being applied.
   */
  public Filter(String type) {
    if (type.equalsIgnoreCase("blur")) {
      this.kernel = new double[][] {{(0.0625), (0.125), (0.0625)},
                                    {(0.125), (0.25), (0.125)},
                                    {(0.0625), (0.125), (0.0625)}};
    }

    if (type.equalsIgnoreCase("sharpen")) {
      this.kernel = new double[][] {{(-0.125), (-0.125), (-0.125), (-0.125), (-0.125)},
                                    {(-0.125), (0.25), (0.25), (0.25), (-0.125)},
                                    {(-0.125), (0.25), (1), (0.25), (-0.125)},
                                    {(-0.125), (0.25), (0.25), (0.25), (-0.125)},
                                    {(-0.125), (-0.125), (-0.125), (-0.125), (-0.125)}};
    }
  }

  @Override
  public void run(Scanner scan, IImageDB model, IView view) throws IllegalStateException {
    Objects.requireNonNull(scan);
    IImageDB model1 = Objects.requireNonNull(model);
    Objects.requireNonNull(view);

    if (!scan.hasNext()) {
      throw new IllegalStateException("Second argument must be the "
              + "ID of the image to be modified.");
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

    ITransformation filter = new FilterTransform(this.kernel);
    IImageState visualizedImage = filter.run(sourceImage);

    model1.loadImage(newId, visualizedImage);
  }
}
