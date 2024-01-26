// Author: Jay Sridharan

import org.junit.Before;
import org.junit.Test;
import java.io.StringReader;
import controller.IController;
import controller.text.TextController;
import model.IImage;
import model.IImageDB;
import model.Image;
import model.ImageDataBase;
import view.IView;
import view.text.TextView;
import static org.junit.Assert.assertEquals;

/**
 * Tests the IController interface, the TextController concrete implementation, and the
 * command pattern strategies found in the commands package.
 */
public class ImageControllerTest {

  private IImageDB m;
  private Readable r;
  private Appendable log;
  private IView v;
  private IController c;
  private String result;

  /**
   * Initializes test suite.
   */
  @Before
  public void setup() {
    m = new ImageDataBase();
    log = new StringBuilder();
    v = new TextView(m, log);

    IImage image = new Image(3, 3);
    // Update image to contain pixels with selected RGB values.
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        // Generate random RGB values for pixel, then load pixel into matrix.
        int red = (int) (Math.random() * 256);
        int green = (int) (Math.random() * 256);
        int blue = (int) (Math.random() * 256);
        image.addPixel(i, j, red, green, blue);
      }
    }

    m.loadImage("test", image);
  }

  /**
   * Tests the Load command pattern to see if it correctly reads an image
   * from a ppm file and places it in the model when passed valid input.
   */
  @Test
  public void testLoadPPM() {
    r = new StringReader("load res/example.ppm example quit");
    c = new TextController(m, r, v);
    c.run();

    result = "What would you like to do?\n"
           + "The images currently in the model are:\n"
           + "test\n"
           + "example\n\n"
           + "What would you like to do?\n";

    assertEquals(result, log.toString());
  }

  /**
   * Tests the Load command pattern to see if it correctly reads an image
   * from a jpg file and places it in the model when passed valid input.
   */
  @Test
  public void testLoadJPG() {
    r = new StringReader("load res/ex2.jpg ex2 quit");
    c = new TextController(m, r, v);
    c.run();

    result = "What would you like to do?\n"
            + "The images currently in the model are:\n"
            + "test\n"
            + "ex2\n\n"
            + "What would you like to do?\n";

    assertEquals(result, log.toString());
  }

  /**
   * Tests the Load command pattern to see if it prints
   * the correct message when passed an invalid filepath.
   */
  @Test
  public void testLoadInvalid() {
    r = new StringReader("load fakefile example quit");
    c = new TextController(m, r, v);
    c.run();

    result = "What would you like to do?\n"
            + "File fakefile not found!\n"
            + "The images currently in the model are:\n"
            + "test\n\n"
            + "What would you like to do?\n";

    assertEquals(result, log.toString());
  }

  /**
   * Tests the brighten pattern to see if it correctly
   * reads in valid input and appends new image to model.
   */
  @Test
  public void testBright() {
    r = new StringReader("brighten 15 test test-bright quit");
    c = new TextController(m, r, v);
    c.run();

    result = "What would you like to do?\n"
            + "The images currently in the model are:\n"
            + "test\n"
            + "test-bright\n\n"
            + "What would you like to do?\n";

    assertEquals(result, log.toString());
  }

  /**
   * Tests the brighten pattern to see if it correctly
   * recognizes invalid input and prints and appropriate error message.
   */
  @Test
  public void testBrightInvalid() {
    r = new StringReader("brighten quit");
    c = new TextController(m, r, v);
    c.run();

    result = "What would you like to do?\n"
            + "Second argument must be the value to brighten/darken the image by.\n"
            + "The images currently in the model are:\n"
            + "test\n\n"
            + "What would you like to do?\n";

    assertEquals(result, log.toString());
  }

  /**
   * Tests the visualize red pattern to see if it correctly
   * reads in valid input and appends new image to model.
   */
  @Test
  public void testVisualizeRed() {
    r = new StringReader("red-component test test-v");
    c = new TextController(m, r, v);
    c.run();

    result = "What would you like to do?\n"
            + "The images currently in the model are:\n"
            + "test\n"
            + "test-v\n\n"
            + "What would you like to do?\n";

    assertEquals(result, log.toString());
  }

  /**
   * Tests the visualize green pattern to see if it correctly
   * reads in valid input and appends new image to model.
   */
  @Test
  public void testVisualizeGreen() {
    r = new StringReader("green-component test test-v");
    c = new TextController(m, r, v);
    c.run();

    result = "What would you like to do?\n"
            + "The images currently in the model are:\n"
            + "test\n"
            + "test-v\n\n"
            + "What would you like to do?\n";

    assertEquals(result, log.toString());
  }

  /**
   * Tests the visualize blue pattern to see if it correctly
   * reads in valid input and appends new image to model.
   */
  @Test
  public void testVisualizeBlue() {
    r = new StringReader("blue-component test test-v");
    c = new TextController(m, r, v);
    c.run();

    result = "What would you like to do?\n"
            + "The images currently in the model are:\n"
            + "test\n"
            + "test-v\n\n"
            + "What would you like to do?\n";

    assertEquals(result, log.toString());
  }

  /**
   * Tests the visualize luma pattern to see if it correctly
   * reads in valid input and appends new image to model.
   */
  @Test
  public void testVisualizeLuma() {
    r = new StringReader("luma-component test test-v");
    c = new TextController(m, r, v);
    c.run();

    result = "What would you like to do?\n"
            + "The images currently in the model are:\n"
            + "test\n"
            + "test-v\n\n"
            + "What would you like to do?\n";

    assertEquals(result, log.toString());
  }

  /**
   * Tests the visualize value pattern to see if it correctly
   * reads in valid input and appends new image to model.
   */
  @Test
  public void testVisualizeValue() {
    r = new StringReader("value-component test test-v");
    c = new TextController(m, r, v);
    c.run();

    result = "What would you like to do?\n"
            + "The images currently in the model are:\n"
            + "test\n"
            + "test-v\n\n"
            + "What would you like to do?\n";

    assertEquals(result, log.toString());
  }

  /**
   * Tests the visualize intensity pattern to see if it correctly
   * reads in valid input and appends new image to model.
   */
  @Test
  public void testVisualizeIntensity() {
    r = new StringReader("intensity-component test test-v");
    c = new TextController(m, r, v);
    c.run();

    result = "What would you like to do?\n"
            + "The images currently in the model are:\n"
            + "test\n"
            + "test-v\n\n"
            + "What would you like to do?\n";

    assertEquals(result, log.toString());
  }

  /**
   * Tests if controller correctly parses blur command, and if filter
   * pattern correctly reads in valid input and appends new image to model.
   */
  @Test
  public void testBlur() {
    r = new StringReader("blur test test-blur");
    c = new TextController(m, r, v);
    c.run();

    result = "What would you like to do?\n"
            + "The images currently in the model are:\n"
            + "test-blur\n"
            + "test\n\n"
            + "What would you like to do?\n";

    assertEquals(result, log.toString());
  }

  /**
   * Tests if controller correctly parses sharpen command, and if filter
   * pattern correctly reads in valid input and appends new image to model.
   */
  @Test
  public void testSharpen() {
    r = new StringReader("sharpen test test-sharp");
    c = new TextController(m, r, v);
    c.run();

    result = "What would you like to do?\n"
            + "The images currently in the model are:\n"
            + "test\n"
            + "test-sharp\n\n"
            + "What would you like to do?\n";

    assertEquals(result, log.toString());
  }

  /**
   * Tests if controller correctly parses sepia command, and if filter
   * pattern correctly reads in valid input and appends new image to model.
   */
  @Test
  public void testSepia() {
    r = new StringReader("sepia test test-sepia");
    c = new TextController(m, r, v);
    c.run();

    result = "What would you like to do?\n"
            + "The images currently in the model are:\n"
            + "test\n"
            + "test-sepia\n\n"
            + "What would you like to do?\n";

    assertEquals(result, log.toString());
  }

  /**
   * Tests if controller correctly parses greyscale command, and if filter
   * pattern correctly reads in valid input and appends new image to model.
   */
  @Test
  public void testGreyscale() {
    r = new StringReader("greyscale test test-greyscale");
    c = new TextController(m, r, v);
    c.run();

    result = "What would you like to do?\n"
            + "The images currently in the model are:\n"
            + "test\n"
            + "test-greyscale\n\n"
            + "What would you like to do?\n";

    assertEquals(result, log.toString());
  }

}
