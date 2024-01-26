// Author: Jay Sridharan

import org.junit.Before;
import org.junit.Test;
import model.IImage;
import model.IImageState;
import model.Image;
import model.transformations.AbsVisualizeTransform;
import model.transformations.BrightenTransform;
import model.transformations.ColorTransform;
import model.transformations.FilterTransform;
import model.transformations.ITransformation;
import model.transformations.VisualizeBlueTransform;
import model.transformations.VisualizeGreenTransform;
import model.transformations.VisualizeIntensityTransform;
import model.transformations.VisualizeLumaTransform;
import model.transformations.VisualizeRedTransform;
import model.transformations.VisualizeValueTransform;
import static org.junit.Assert.assertEquals;

/**
 * Tests the various Transformation function objects.
 */
public class TestTransformations {

  private IImage image;
  private IImageState result;
  private AbsVisualizeTransform visualize;
  private ITransformation transform;

  /**
   * Initializes a test Image as a 3x3 with random values for each pixel's channels.
   * Additionally, initializes the BrightenTransformation object used later in the testing suite.
   */
  @Before
  public void testSetup() {
    this.image = new Image(3, 3);
    // Update image to contain pixels with selected RGB values.
    for (int i = 0; i < this.image.getHeight(); i++) {
      for (int j = 0; j < this.image.getWidth(); j++) {
        // Generate random RGB values for pixel, then load pixel into matrix.
        int red = (int) (Math.random() * 256);
        int green = (int) (Math.random() * 256);
        int blue = (int) (Math.random() * 256);
        this.image.addPixel(i, j, red, green, blue);
      }
    }
  }

  /**
   * Tests VisualizeRedTransformation with valid input.
   */
  @Test
  public void testVisualizeRed() {
    // Run transformation and store result.
    visualize = new VisualizeRedTransform();
    result = visualize.run(image);
    for (int i = 0; i < this.image.getHeight(); i++) {
      for (int j = 0; j < this.image.getWidth(); j++) {
        int param = image.getRedChannel(i, j);

        // Assert all channels for reach pixel equal selected parameter.
        assertEquals(param, result.getRedChannel(i, j));
        assertEquals(param, result.getGreenChannel(i, j));
        assertEquals(param, result.getBlueChannel(i, j));
      }
    }
  }

  /**
   * Tests VisualizeGreenTransformation with valid input.
   */
  @Test
  public void testVisualizeGreen() {
    // Run transformation and store result.
    visualize = new VisualizeGreenTransform();
    result = visualize.run(image);
    for (int i = 0; i < this.image.getHeight(); i++) {
      for (int j = 0; j < this.image.getWidth(); j++) {
        int param = image.getGreenChannel(i, j);

        // Assert all channels for reach pixel equal selected parameter.
        assertEquals(param, result.getRedChannel(i, j));
        assertEquals(param, result.getGreenChannel(i, j));
        assertEquals(param, result.getBlueChannel(i, j));
      }
    }
  }

  /**
   * Tests VisualizeBlueTransformation with valid input.
   */
  @Test
  public void testVisualizeBlue() {
    // Run transformation and store result.
    visualize = new VisualizeBlueTransform();
    result = visualize.run(image);

    // Compare each pixel in result to each pixel in image.
    for (int i = 0; i < this.image.getHeight(); i++) {
      for (int j = 0; j < this.image.getWidth(); j++) {
        int param = image.getBlueChannel(i, j);

        // Assert all channels for reach pixel equal selected parameter.
        assertEquals(param, result.getRedChannel(i, j));
        assertEquals(param, result.getGreenChannel(i, j));
        assertEquals(param, result.getBlueChannel(i, j));
      }
    }
  }

  /**
   * Tests VisualizeLumaTransformation with valid input.
   */
  @Test
  public void testVisualizeLuma() {
    // Run transformation and store result.
    visualize = new VisualizeLumaTransform();
    result = visualize.run(image);

    // Compare each pixel in result to each pixel in image.
    for (int i = 0; i < this.image.getHeight(); i++) {
      for (int j = 0; j < this.image.getWidth(); j++) {
        int red = this.image.getRedChannel(i, j);
        int blue = this.image.getBlueChannel(i, j);
        int green = this.image.getGreenChannel(i, j);
        int param = (int) ((0.2126 * red) + (0.7152 * green) + (0.0722 * blue));

        assertEquals(param, result.getRedChannel(i, j));
        assertEquals(param, result.getGreenChannel(i, j));
        assertEquals(param, result.getBlueChannel(i, j));
      }
    }
  }

  /**
   * Tests VisualizeIntensityTransformation with valid input.
   */
  @Test
  public void testVisualizeIntensity() {
    // Run transformation and store result.
    visualize = new VisualizeIntensityTransform();
    result = visualize.run(image);

    // Compare each pixel in result to each pixel in image.
    for (int i = 0; i < this.image.getHeight(); i++) {
      for (int j = 0; j < this.image.getWidth(); j++) {
        int red = this.image.getRedChannel(i, j);
        int blue = this.image.getBlueChannel(i, j);
        int green = this.image.getGreenChannel(i, j);
        int param = ((red + blue + green) / 3);

        // Assert all channels for reach pixel equal selected parameter.
        assertEquals(param, result.getRedChannel(i, j));
        assertEquals(param, result.getGreenChannel(i, j));
        assertEquals(param, result.getBlueChannel(i, j));
      }
    }
  }

  /**
   * Tests VisualizeValueTransformation with valid input.
   */
  @Test
  public void testVisualizeValue() {
    // Run transformation and store result.
    visualize = new VisualizeValueTransform();
    result = visualize.run(image);

    // Compare each pixel in result to each pixel in image.
    for (int i = 0; i < this.image.getHeight(); i++) {
      for (int j = 0; j < this.image.getWidth(); j++) {
        int red = this.image.getRedChannel(i, j);
        int blue = this.image.getBlueChannel(i, j);
        int green = this.image.getGreenChannel(i, j);
        int param = Math.max(Math.max(red, blue), green);

        // Assert all channels for reach pixel equal selected parameter.
        assertEquals(param, result.getRedChannel(i, j));
        assertEquals(param, result.getGreenChannel(i, j));
        assertEquals(param, result.getBlueChannel(i, j));
      }
    }
  }

  /**
   * Tests if run() method throws exception when passed a null Image object.
   */
  @Test(expected = NullPointerException.class)
  public void testInvalidImageVisualize() {
    visualize = new VisualizeRedTransform();
    result = visualize.run(null);
  }

  /**
   * Tests Brighten with valid input for brightening transformation.
   */
  @Test
  public void testBrighten() {
    transform = new BrightenTransform(15);
    image = new Image(2,2);
    image.addPixel(0, 0, 120, 235, 63);
    image.addPixel(0, 1, 230, 120, 14);
    image.addPixel(1, 0, 1, 76, 4);
    image.addPixel(1, 1, 172, 1, 86);
    result = transform.run(image);

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int redVal = this.image.getRedChannel(i, j) + 15;
        int greenVal = this.image.getGreenChannel(i, j) + 15;
        int blueVal = this.image.getBlueChannel(i, j) + 15;
        assertEquals(redVal, this.result.getRedChannel(i, j));
        assertEquals(greenVal, this.result.getGreenChannel(i, j));
        assertEquals(blueVal, this.result.getBlueChannel(i, j));
      }
    }
  }

  /**
   * Tests Brighten with valid input for brightening transformation.
   */
  @Test
  public void testTooBright() {
    transform = new BrightenTransform(15);
    image = new Image(2,2);
    image.addPixel(0, 0, 245, 235, 63);
    image.addPixel(0, 1, 245, 120, 14);
    image.addPixel(1, 0, 245, 76, 4);
    image.addPixel(1, 1, 245, 1, 86);
    transform = new BrightenTransform(15);
    result = transform.run(image);

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int redVal = 255;
        int greenVal = this.image.getGreenChannel(i, j) + 15;
        int blueVal = this.image.getBlueChannel(i, j) + 15;
        assertEquals(redVal, this.result.getRedChannel(i, j));
        assertEquals(greenVal, this.result.getGreenChannel(i, j));
        assertEquals(blueVal, this.result.getBlueChannel(i, j));
      }
    }
  }

  /**
   * Tests Brighten with valid input for brightening transformation.
   */
  @Test
  public void testDarken() {
    transform = new BrightenTransform(-15);
    image = new Image(2,2);
    image.addPixel(0, 0, 120, 235, 63);
    image.addPixel(0, 1, 230, 120, 18);
    image.addPixel(1, 0, 16, 76, 42);
    image.addPixel(1, 1, 172, 17, 86);
    result = transform.run(image);

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int redVal = this.image.getRedChannel(i, j) - 15;
        int greenVal = this.image.getGreenChannel(i, j) - 15;
        int blueVal = this.image.getBlueChannel(i, j) - 15;
        assertEquals(redVal, this.result.getRedChannel(i, j));
        assertEquals(greenVal, this.result.getGreenChannel(i, j));
        assertEquals(blueVal, this.result.getBlueChannel(i, j));
      }
    }
  }

  /**
   * Tests Brighten with valid input for brightening transformation.
   */
  @Test
  public void testTooDark() {
    transform = new BrightenTransform(-15);
    image = new Image(2,2);
    image.addPixel(0, 0, 14, 235, 63);
    image.addPixel(0, 1, 14, 120, 18);
    image.addPixel(1, 0, 14, 76, 42);
    image.addPixel(1, 1, 14, 17, 86);
    result = transform.run(image);

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int redVal = 0;
        int greenVal = this.image.getGreenChannel(i, j) - 15;
        int blueVal = this.image.getBlueChannel(i, j) - 15;
        assertEquals(redVal, this.result.getRedChannel(i, j));
        assertEquals(greenVal, this.result.getGreenChannel(i, j));
        assertEquals(blueVal, this.result.getBlueChannel(i, j));
      }
    }
  }

  /**
   * Tests if run method throws a NullPointerException when passed a null image.
   */
  @Test(expected = NullPointerException.class)
  public void testBrightenInvalidImage() {
    transform = new BrightenTransform(15);
    result = transform.run(null);
  }

  /**
   * Tests if filter method correctly performs a filter transformation
   * on selected image using a provided transformation matrix.
   */
  @Test
  public void testFilterTransform() {
    double [][] kernel = new double[][] {{(0.0625), (0.125), (0.0625)},
                                         {(0.125), (0.25), (0.125)},
                                         {(0.0625), (0.125), (0.0625)}};
    transform = new FilterTransform(kernel);
    image.addPixel(0, 0, 50, 210, 154);
    image.addPixel(0, 1, 50, 210, 154);
    image.addPixel(0, 2, 50, 210, 154);
    image.addPixel(1, 0, 28, 227, 16);
    image.addPixel(1, 1, 28, 227, 16);
    image.addPixel(1, 2, 28, 227, 16);
    image.addPixel(2, 0, 35, 16, 154);
    image.addPixel(2, 1, 35, 16, 154);
    image.addPixel(2, 2, 35, 16, 154);
    result = transform.run(image);

    IImage expected = new Image(this.image.getHeight(), this.image.getWidth());
    expected.addPixel(0, 0, 24, 121, 60);
    expected.addPixel(0, 1, 32, 161, 81);
    expected.addPixel(0, 2, 24, 121, 60);
    expected.addPixel(1, 0, 26, 127, 63);
    expected.addPixel(1, 1, 35, 170, 85);
    expected.addPixel(1, 2, 26, 127, 63);
    expected.addPixel(2, 0, 18, 48, 60);
    expected.addPixel(2, 1, 24, 64, 81);
    expected.addPixel(2, 2, 18, 48, 60);

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        assertEquals(expected.getRedChannel(i, j), this.result.getRedChannel(i, j));
        assertEquals(expected.getGreenChannel(i, j), this.result.getGreenChannel(i, j));
        assertEquals(expected.getBlueChannel(i, j), this.result.getBlueChannel(i, j));
      }
    }
  }

  /**
   * Tests if run method throws a NullPointerException when passed a null image.
   */
  @Test(expected = NullPointerException.class)
  public void testFilterInvalidImage() {
    transform = new FilterTransform(new double [][] {{}});
    result = transform.run(null);
  }

  /**
   * Tests if run method throws a NullPointerException
   * when passed a null transformation matrix.
   */
  @Test(expected = NullPointerException.class)
  public void testFilterInvalidMatrix() {
    transform = new FilterTransform(null);
  }

  /**
   * Tests if ColorTransform method correctly calculates and assigns
   * new pixel channel values based on transformation matrix selected.
   */
  @Test
  public void testColorTransform() {
    double [][] cm = new double[][] {{0.25, 0.50, 0.25},
                                     {0.1, 0.2, 0.1},
                                     {0.25, 0.50, 0.25}};
    transform = new ColorTransform(cm);
    result = transform.run(image);

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int r = image.getRedChannel(i,j);
        int g = image.getGreenChannel(i,j);
        int b = image.getBlueChannel(i,j);
        double redVal = (cm[0][0] * r) + (cm[0][1] * g) + (cm[0][2] * b);
        double greenVal = (cm[1][0] * r) + (cm[1][1] * g) + (cm[1][2] * b);
        double blueVal = (cm[2][0] * r) + (cm[2][1] * g) + (cm[2][2] * b);

        assertEquals((int) redVal, this.result.getRedChannel(i, j));
        assertEquals((int) greenVal, this.result.getGreenChannel(i, j));
        assertEquals((int) blueVal, this.result.getBlueChannel(i, j));
      }
    }

  }

  /**
   * Tests if run method throws a NullPointerException when passed a null image.
   */
  @Test(expected = NullPointerException.class)
  public void testColorTransformInvalidImage() {
    transform = new ColorTransform(new double [][] {{}});
    result = transform.run(null);
  }

  /**
   * Tests if run method throws a NullPointerException
   * when passed a null transformation matrix.
   */
  @Test(expected = NullPointerException.class)
  public void testColorInvalidMatrix() {
    transform = new ColorTransform(null);
  }

}
