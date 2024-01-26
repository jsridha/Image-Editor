//Author: Jay Sridharan

package model;

/**
 * Concrete Implementation of the IImageState and IImage interfaces.
 * Represents an image using a 2-D array of pixel objects,
 */
public class Image implements IImage {
  private final IPixel[][] data;
  private final int height;
  private final int width;

  /**
   * Constructor for an image. Initializes the data
   * matrix and assigns the height and width fields.
   *
   * @param height - height of the image
   * @param width - width of the image.
   */
  public Image(int height, int width) throws IllegalArgumentException {
    // Ensure dimensions are positive. Throw exception if not.
    if (height <= 0 || width  <= 0) {
      throw new IllegalArgumentException("Invalid dimensions for image.");
    }

    this.height = height;
    this.width = width;
    this.data = new IPixel[this.height][this.width];
  }

  @Override
  public void addPixel(int i, int j, int red, int green, int blue)
          throws IllegalArgumentException {
    // If location is in array, attempt to add new pixel with given parameters.
    if (i < this.height) {
      if (j < this.width) {
        this.data[i][j] = new Pixel(red, green, blue);
        // Exit method after adding new pixel.
        return;
      }
    }

    // If location not in array, throw exception.
    throw new IllegalArgumentException("Invalid location for pixel");
  }

  @Override
  public int getRedChannel(int i, int j) {
    return this.data[i][j].getR();
  }

  @Override
  public int getGreenChannel(int i, int j) {
    return this.data[i][j].getG();
  }

  @Override
  public int getBlueChannel(int i, int j) {
    return this.data[i][j].getB();
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getWidth() {
    return this.width;
  }
}
