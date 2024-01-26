package controller.gui.commands;

import java.util.Objects;

import model.IImageDB;
import model.IImageState;
import model.transformations.ColorTransform;
import model.transformations.ITransformation;

/**
 * Graphical command pattern for requesting user
 * input to perform a linear color transformation
 * on an image currently in the model.
 */
public class LinearColor implements ICommand {

  private double[][] colormatrix;

  /**
   * Constructs a LinearColor object. Based on which type of color
   * transformation is being applied, sets colormatrix field.
   *
   * @param type - String containing the type of filter being applied.
   */
  public LinearColor(String type) {
    if (type.equalsIgnoreCase("greyscale")) {
      this.colormatrix = new double[][] {{(0.2126), (0.7152), (0.0722)},
              {(0.2126), (0.7152), (0.0722)},
              {(0.2126), (0.7152), (0.0722)}};
    }

    else if (type.equalsIgnoreCase("sepia")) {
      this.colormatrix = new double[][] {{(0.393), (0.769), (0.189)},
              {(0.349), (0.686), (0.168)},
              {(0.272), (0.7152), (0.131)}};
    }
  }

  @Override
  public void run(IImageDB model, String imageID, String newImage) throws IllegalStateException {
    Objects.requireNonNull(model);

    IImageState sourceImage = model.getImage(imageID);
    if (sourceImage == null) {
      throw new IllegalStateException("Image with specified ID does not exist.");
    }

    ITransformation color = new ColorTransform(this.colormatrix);
    IImageState transformedImage = color.run(sourceImage);

    model.loadImage(newImage, transformedImage);
  }
}
