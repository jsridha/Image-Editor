// Author: Jay Sridharan

import org.junit.Before;
import org.junit.Test;
import model.IImage;
import model.Image;
import static org.junit.Assert.assertEquals;

/**
 * Tests the IImage and IImageState interfaces, as well as the Image concrete implementation.
 */
public class ImageTest {

  private IImage image;

  @Before
  public void testSetup() {
    this.image = new Image(3, 3);
  }

  /**
   * Tests if constructor correctly assigns values.
   * Additionally, tests the getHeight() & getWidth() methods.
   */
  @Test
  public void testObjectData() {
    assertEquals(3, this.image.getHeight());
    assertEquals(3, this.image.getWidth());
  }

  /**
   * Tests if exception is thrown for width value less than 1.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidWidth() {
    this.image = new Image(3, -1);
  }

  /**
   * Tests if exception is thrown for height value less than 1.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidHeight() {
    this.image = new Image(-3, 1);
  }

  /**
   * Tests if exception is thrown when trying to add a pixel in an invalid column.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddInvalidCol() {
    this.image.addPixel(0,3, 255, 255, 255);
  }

  /**
   * Tests if exception is thrown when trying to add a pixel in an invalid row.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddInvalidRow() {
    this.image.addPixel(3,0, 255, 255, 255);
  }

  /**
   * Tests getRedChannel(), getGreenChannel(), and getBlueChannel() methods.
   */
  @Test
  public void testGetChannel() {

    // Update image to contain pixels with selected RGB values.
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        // Generate random RGB values for pixel, then attempt to load pixel into matrix.
        int red = (int) (Math.random() * 256);
        int green = (int) (Math.random() * 256);
        int blue = (int) (Math.random() * 256);
        this.image.addPixel(i, j, red, green, blue);

        // Perform assertions to test variants of getChannel() for newly created pixel.
        assertEquals(this.image.getRedChannel(i, j), red);
        assertEquals(this.image.getGreenChannel(i, j), green);
        assertEquals(this.image.getBlueChannel(i, j), blue);
      }
    }
  }

}
