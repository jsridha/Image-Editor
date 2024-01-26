//Author: Jay Sridharan

package model;

/**
 * Concrete implementation of the PixelState interfaces. Represents
 * a pixel using 8-bit RGB channels. Additionally, derives the
 * value, intensity, and luma of the pixel based on component values.
 */
public class Pixel implements IPixel {

  private int red;
  private int green;
  private int blue;

  /**
   * Constructor for a pixel objects.
   *
   * @param red - Integer value for red component. Must be within range 0-255.
   * @param green - Integer value for red component. Must be within range 0-255.
   * @param blue - Integer value for red component. Must be within range 0-255.
   */
  public Pixel(int red, int green, int blue) throws IllegalArgumentException {
    // Checks value for red component. If valid, sets field.
    if (checkValue(red)) {
      this.red = red;
    }
    // Checks value for green component. If valid, sets field.
    if (checkValue(green)) {
      this.green = green;
    }
    // Checks value for blue component. If valid, sets field.
    if (checkValue(blue)) {
      this.blue = blue;
    }
  }

  /**
   * Helper method to check if a passed value is within valid parameters.
   * @param value the integer being passed to a function
   * @return true if value is between 0 - 255. Otherwise, throws exception.
   */
  private boolean checkValue(int value) throws IllegalArgumentException {
    if (value < 0 || value > 255) {
      throw new IllegalArgumentException("Invalid component value passed.");
    }
    else {
      return true;
    }
  }

  @Override
  public int getR() {
    return this.red;
  }

  @Override
  public int getG() {
    return this.green;
  }

  @Override
  public int getB() {
    return this.blue;
  }
}
