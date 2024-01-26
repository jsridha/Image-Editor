package model.transformations;

import java.util.Objects;

import model.IImage;
import model.IImageState;
import model.Image;

/**
 * Class that performs a greyscale shift for an Image object.
 */
public abstract class AbsVisualizeTransform implements ITransformation {

  /**
   * Visualizes an image using the selected parameter for each pixel.
   */
  @Override
  public final IImageState run(IImageState image) {
    IImageState image1 = Objects.requireNonNull(image);

    // Set up new image
    IImage newImage = new Image(image1.getHeight(), image1.getWidth());

    // Iterate through pixel matrix. For each pixel, get
    // relevant parameter then update array with new pixel.
    for (int i = 0; i < image1.getHeight(); i++) {
      for (int j = 0; j < image1.getWidth(); j++) {
        // Use the helper method to request relevant parameter for greyscale transformation.
        int parameter = getParam(image1, i, j);
        newImage.addPixel(i, j, parameter, parameter, parameter);
      }
    }

    return newImage;
  }

  /**
   * Helper method. Gets the relevant parameter from a pixel
   * in an image to perform the greyscale transformation.
   *
   * @param image the image being transformed
   * @param i - row containing pixel.
   * @param j - column containing pixel.
   * @return the requested parameter.
   */
  protected abstract int getParam(IImageState image, int i, int j);
}
