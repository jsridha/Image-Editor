package controller.gui.commands;

import java.util.Objects;

import model.IImageDB;
import model.IImageState;
import model.transformations.FilterTransform;
import model.transformations.ITransformation;

/**
 * Graphical command pattern for requesting user
 * input to perform a filter transformation
 * on an image currently in the model.
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
  public void run(IImageDB model, String imageID, String newImage) throws IllegalStateException {
    Objects.requireNonNull(model);

    IImageState sourceImage = model.getImage(imageID);
    if (sourceImage == null) {
      throw new IllegalStateException("Image with specified ID does not exist.");
    }

    ITransformation filter = new FilterTransform(kernel);
    IImageState filteredImage = filter.run(sourceImage);

    model.loadImage(newImage, filteredImage);
  }
}
