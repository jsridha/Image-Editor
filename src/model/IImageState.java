// Author: Jay Sridharan

package model;

/**
 * Interface representing methods required to present
 * an image, which consists of a 2-D array of Pixels.
 */
public interface IImageState {

  /**
   * Gets the height of the image.
   *
   * @return the height of the image.
   */
  int getHeight();

  /**
   * Gets the width of the image.
   *
   * @return the width of the image.
   */
  int getWidth();

  /**
   * Gets the red channel of the pixel at the location in the image.
   *
   * @return the red channel of the chosen pixel.
   */
  int getRedChannel(int i, int j);

  /**
   * Gets the blue channel of the pixel at the location in the image.
   *
   * @return the blue channel of the chosen pixel.
   */
  int getBlueChannel(int i, int j);

  /**
   * Gets the green channel of the pixel at the location in the image.
   *
   * @return the green channel of the chosen pixel.
   */
  int getGreenChannel(int i, int j);
}
