//Author: Jay Sridharan

package model;

/**
 * Interface containing methods for reading data from a Pixel object.
 */
public interface IPixel {

  /**
   * Getter method for red field.
   *
   * @return the current value of the red component, scaled between 0-255.
   */
  int getR();

  /**
   * Getter method for green field.
   *
   * @return the current value of the green component, scaled between 0-255.
   */
  int getG();

  /**
   * Getter method for blue field.
   *
   * @return the current value of the blue component, scaled between 0-255.
   */
  int getB();
}
