package model.transformations;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.IImageState;
import model.Image;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * This class contains utility methods to
 * handle IO for reading and writing PPM files.
 */
public class IOUtil {

  /**
   * Empty constructor.
   */
  public IOUtil() {
    //Empty constructor included per JavaStyle auto-grader requirement.
  }

  /**
   * Read an image file in the PPM
   * format and constructs an Image object.
   *
   * @param filename the path of the file.
   */
  public static IImageState readPPM(String filename) throws IllegalArgumentException {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(filename));
    }
    catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + filename + " not found!");
    }

    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());
    String token;
    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }

    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();
    Image newImage = new Image(width, height);
    for (int i = 0;i < width; i++) {
      for (int j = 0;j < height; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        newImage.addPixel(i, j, r, g, b);
      }
    }
    return newImage;
  }

  /**
   * Reads an image file in JPG, BMP, or PNG
   * file format and constructs an Image object.
   *
   * @param filename the path of the file. 
   */
  public static IImageState readNonPPM(String filename) throws IllegalArgumentException {
    // Try to read in image at specified file location to new BufferedImage object.
    BufferedImage image;
    try {
      File newFile = new File(filename);
      image = ImageIO.read(newFile);
    } catch (IOException e) {
      throw new IllegalArgumentException("File " + filename + " not found!");
    }

    // Read in image height and width, create new Image object,
    // then begin iterating through BufferedImage to each pixel.
    int width = image.getWidth();
    int height = image.getHeight();
    Image newImage = new Image(height, width);

    // For each pixel, request individual channel values
    // then append to new Image object at specified location.
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Color pixelColor = new Color(image.getRGB(j, i));
        int r = pixelColor.getRed();
        int g = pixelColor.getGreen();
        int b = pixelColor.getBlue();
        newImage.addPixel(i, j, r, g, b);
      }
    }
    return newImage;
  }

  /**
   * Writes the contents of an IImageState object to the provided filepath as a non-PPM file.
   *
   * @param filename - Name of the file to write to.
   * @param image - IImageState object to read contents from.
   * @param fileType - extension of file being written to.
   */
  public static void writeNonPPM(String filename, IImageState image, String fileType) {
    // Request height and width from image, then initialize new BufferedImage.
    int width = image.getWidth();
    int height = image.getHeight();
    BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    // Iterate through matrix and append pixels into newImage at corresponding locations.
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int red = image.getRedChannel(i, j);
        int green = image.getGreenChannel(i, j);
        int blue = image.getBlueChannel(i, j);

        Color newColor = new Color(red, green, blue);

        newImage.setRGB(j, i, newColor.getRGB());
      }
    }

    // Try to write to specified location. If unable, throw exception.
    try {

      File output = new File(filename);
      // Called write() utility method to write BufferedImage to filepath.
      ImageIO.write(newImage, fileType, output);
    }

    // If unable to write image to location, Throw exception with appropriate message.
    catch (IOException e) {
      throw new IllegalStateException("Unable to write to file: " + filename);
    }
  }

  /**
   * Writes the contents of an IImageState object to the provided filepath as a PPM file.
   *
   * @param filename - Name of the file to write to.
   * @param image - IImageState object to read contents from.
   */
  public static void writeToPPM(String filename, IImageState image) {
    String result = "P3\n";
    int height = image.getHeight();
    int width = image.getWidth();
    result = result.concat(String.format("%d %d\n255\n", height, width));

    // Iterate through matrix and append pixel channels into string.
    for (int i = 0; i < height; i++) {
      String line = "";
      for (int j = 0; j < width; j++) {
        int red = image.getRedChannel(i, j);
        int green = image.getGreenChannel(i, j);
        int blue = image.getBlueChannel(i, j);

        line = line.concat(String.format("%d %d %d ", red, green, blue));
      }
      line = line.substring(0, line.length() - 1);
      // Append line into string.
      result = result.concat(line);
      // If not on the last row of array, append
      // line break before continuing iteration.
      if (i != height - 1) {
        result = result.concat("\n");
      }
    }

    try {
      FileOutputStream foo = new FileOutputStream(filename);
      foo.write(result.getBytes());
      foo.close();
    } catch (Exception e) {
      throw new IllegalStateException("Unable to write to: " + filename + "!\n");
    }
  }
}

