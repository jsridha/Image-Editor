//Author: Jay Sridharan

package model;

/**
 * Interface representing a model that stores
 * image states after various functions are performed
 * using a HashMap of String keys and IImageState values.
 */
public interface IImageDB {

  /**
   * Loads a new into the model using the passed String as an id value.
   *
   * @param idImage the String id of the image object
   * @param image - the image being added to the model
   */
  void loadImage(String idImage, IImageState image);

  /**
   * Returns the image associated with the passed String identifier.
   *
   * @param idImage the key value of the image being requested.
   * @return the image associated with the passed key value.
   */
  IImageState getImage(String idImage);

  /**
   * Constructs a list of ids for each image contained within the current model instance.
   * @return a String representation of the above.
   */
  String toString();
}
