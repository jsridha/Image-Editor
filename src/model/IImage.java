package model;

/**
 * Interface representing methods required to update
 * an image, which consists of a 2-D array of Pixels.
 */
public interface IImage extends IImageState {

  /**
   * Adds a pixel to an 2-D image at the given coordinate.
   *
   * @param i - x coordinate of pixel
   * @param j - y coordinate of pixel
   * @param red - red component of pixel
   * @param green - green component of pixel
   * @param blue - blue component of pixel
   */
  void addPixel(int i, int j, int red, int green, int blue);
}
