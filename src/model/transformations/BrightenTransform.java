package model.transformations;

import java.util.Objects;

import model.IImage;
import model.IImageState;
import model.Image;

/**
 * A class that performs a brightening/darkening transformation on an IImage object.
 */
public class BrightenTransform implements ITransformation {

  private final int value;

  /**
   * Constructor for BrightenTransformation object. Assigns value field.
   * @param value - value to update channels.
   */
  public BrightenTransform(int value) {
    this.value = value;
  }

  /**
   * Updates each channel by the passed value. Clamps channels between 0-255.
   */
  @Override
  public IImageState run(IImageState image) {
    IImageState image1 = Objects.requireNonNull(image);

    // Set up new image with same dimensions as original.
    IImage newImage = new Image(image1.getHeight(), image1.getWidth());

    // Iterate through pixel matrix. For each pixel, get
    // current blue value then update array with new pixel.
    for (int i = 0; i < image1.getHeight(); i++) {
      for (int j = 0; j < image1.getWidth(); j++) {
        // Request each channel from pixel and update value accordingly.
        int redVal = clamp(image1.getRedChannel(i,j) + value);
        int greenVal = clamp(image1.getGreenChannel(i,j) + value);
        int blueVal = clamp(image1.getBlueChannel(i,j) + value);

        // Update matrix to reference pixel with new channel values.
        newImage.addPixel(i, j, redVal, greenVal, blueVal);
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
