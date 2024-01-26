// Author: Jay Sridharan

import org.junit.Test;
import model.Pixel;
import model.IPixel;
import static org.junit.Assert.assertEquals;

/**
 * Tests the IPixel interface and its concrete implementations.
 */
public class PixelTest {

  private IPixel pixel;

  /**
   * Tests if constructor correctly assigns values.
   * Additionally, tests the getR(), getG(), & getB() methods.
   */
  @Test
  public void testObjectData() {
    pixel = new Pixel(120, 40, 50);

    assertEquals(120, pixel.getR());
    assertEquals(40, pixel.getG());
    assertEquals(50, pixel.getB());
  }

  /**
   * Tests if exception is thrown for red value greater than 255.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLargeRed() {
    pixel = new Pixel(400, 40, 50);
  }

  /**
   * Tests if exception is thrown for red value greater than 255.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLargeGreen() {
    pixel = new Pixel(120, 2256, 50);
  }

  /**
   * Tests if exception is thrown for red value greater than 255.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLargeBlue() {
    pixel = new Pixel(120, 40, 259);
  }

  /**
   * Tests if exception is thrown for red value less than zero.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSmallRed() {
    pixel = new Pixel(-1, 40, 50);
  }

  /**
   * Tests if exception is thrown for red value less than zero.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSmallGreen() {
    pixel = new Pixel(120, -140, 50);
  }

  /**
   * Tests if exception is thrown for red value less than zero.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSmallBlue() {
    pixel = new Pixel(120, 40, -1000);
  }

}
