package model.transformations;

import java.util.Objects;
import model.IImage;
import model.IImageState;
import model.Image;

/**
 * Applies a filter to an image using a 2-D "kernel".
 */
public class FilterTransform implements ITransformation {

  private final double[][] kernel;

  /**
   * Constructor for FilterTransform object. Assigns kernel field based on passed value.
   *
   * @param kernel - kernel to use when applying filter.
   */
  public FilterTransform(double[][] kernel) {
    this.kernel = Objects.requireNonNull(kernel);
  }

  /**
   * Filters image and updates channels according
   * to kernel logic. Clamps channels between 0-255.
   *
   * @param image - image to modify.
   * @return a new version of the image with updated
   *         channels. Does not mutate original.
   */
  @Override
  public IImageState run(IImageState image) {
    IImageState image1 = Objects.requireNonNull(image);

    // Set up new image with same dimensions as original.
    IImage newImage = new Image(image1.getHeight(), image1.getWidth());

    // Iterate through pixel matrix. For each pixel,
    // apply filter then update array with new pixel.
    for (int i = 0; i < image1.getHeight(); i++) {
      for (int j = 0; j < image1.getWidth(); j++) {

        // Initialize parameters for new pixel.
        double redVal = 0;
        double greenVal = 0;
        double blueVal = 0;
        // Calculate distance to top left from
        // center of kernel, laterally and vertically
        int vOffset = this.kernel.length / 2;
        int lOffset = this.kernel[0].length / 2;

        // Iterate through kernel and apply filter to current pixel.
        for (int x = 0; x < this.kernel.length; x++) {
          for (int y = 0; y < this.kernel[0].length; y++) {
            try {
              redVal += this.kernel[x][y]
                      * image1.getRedChannel(i - vOffset + x, j - lOffset + y);
              greenVal += this.kernel[x][y]
                      * image1.getGreenChannel(i - vOffset + x, j - lOffset + y);
              blueVal += this.kernel[x][y]
                      * image1.getBlueChannel(i - vOffset + x, j - lOffset + y);
            }
            // Error handling for when requesting value from a pixel that is not present in image.
            catch (Exception e) {
              continue;
            }
          }
        }

        // Update matrix to reference pixel with new channel values.
        newImage.addPixel(i, j, clamp((int) redVal), clamp((int) greenVal), clamp((int) blueVal));
      }
    }

    // Returns newly modified image.
    return newImage;
  }

  /**
   * Helper method to clamp value between 0-255.
   *
   * @param value the value to be clamped.
   * @return either 0, 255, or the value if clamping is not necessary.
   */
  private int clamp(int value) {
    if (value < 0) {
      return 0;
    }

    if (value > 255) {
      return 255;
    }

    return value;
  }
}
