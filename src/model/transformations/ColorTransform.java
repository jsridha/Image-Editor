package model.transformations;

import java.util.Objects;

import model.IImage;
import model.IImageState;
import model.Image;

/**
 * Class that performs a linear color transformation shift for an Image object.
 */
public class ColorTransform implements ITransformation {

  private final double[][] cm;

  /**
   * Constructor for ColorTransform object. Assigns the cm field.
   *
   * @param colormatrix matrix containing the logic for the transformation requested by the user.
   */
  public ColorTransform(double[][] colormatrix) {
    this.cm = Objects.requireNonNull(colormatrix);
  }

  @Override
  public IImageState run(IImageState image) {
    IImageState image1 = Objects.requireNonNull(image);

    // Set up new image with same dimensions as original.
    IImage newImage = new Image(image1.getHeight(), image1.getWidth());

    // Iterate through pixel matrix. For each pixel,
    // apply transformation then update array with new pixel.
    for (int i = 0; i < image1.getHeight(); i++) {
      for (int j = 0; j < image1.getWidth(); j++) {
        int r = image1.getRedChannel(i,j);
        int g = image1.getGreenChannel(i,j);
        int b = image1.getBlueChannel(i,j);

        // Calculate new pixel values based on color transformation being applied.
        double redVal = (cm[0][0] * r) + (cm[0][1] * g) + (cm[0][2] * b);
        double greenVal = (cm[1][0] * r) + (cm[1][1] * g) + (cm[1][2] * b);
        double blueVal = (cm[2][0] * r) + (cm[2][1] * g) + (cm[2][2] * b);

        // Update image matrix to reference pixel with new channel values.
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
