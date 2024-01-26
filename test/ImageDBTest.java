// Author: Jay Sridharan

import org.junit.Before;
import org.junit.Test;
import model.IImageDB;
import model.IImageState;
import model.Image;
import model.ImageDataBase;
import static org.junit.Assert.assertEquals;

/**
 * Tests the IImageDB interface and its implementations.
 */
public class ImageDBTest {

  private IImageDB model;
  private IImageState image;
  private String id;

  /**
   * Initializes model, view, and appendable for testing.
   */
  @Before
  public void setup() {
    model = new ImageDataBase();
  }

  /**
   * Tests if the loadImage() method correctly adds image to hashMap at provided key.
   */
  @Test
  public void testLoad() {
    // assert that model is empty.
    assertEquals("", model.toString());

    // Try loading image.
    id = "test";
    image = new Image(3,3);
    model.loadImage(id, image);

    // assert that there is now an image in the model with the specified identifier.
    assertEquals("test\n", model.toString());
  }

  /**
   * Tests if the loadImages method throws
   * a NullPointerException when passed a null string.
   */
  @Test(expected = NullPointerException.class)
  public void testLoadNullID() {
    id = null;
    image = new Image(3,3);

    // Try loading image. SHOULD THROW EXCEPTION HERE.
    model.loadImage(id, image);

  }

  /**
   * Tests if the loadImages method throws
   * a NullPointerException when passed a null Image.
   */
  @Test(expected = NullPointerException.class)
  public void testLoadNullImage() {
    id = "test";
    image = null;

    // Try loading image. SHOULD THROW EXCEPTION HERE.
    model.loadImage(id, image);

  }

  /**
   * Tests if the getter method for images in the model throws
   * a NullPointerException when passed a null string.
   */
  @Test(expected = NullPointerException.class)
  public void testgetNullID() {
    id = "test";
    image = new Image(3,3);

    // Load in an image.
    model.loadImage(id, image);
    // Attempt to get image using null value. Should throw exception.
    model.getImage(null);
  }
}