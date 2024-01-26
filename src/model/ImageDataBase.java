//Author: Jay Sridharan

package model;

import java.util.HashMap;
import java.util.Objects;

/**
 * Model for a database of images using a String and IImageState HashMap.
 */
public class ImageDataBase implements IImageDB {

  private final HashMap<String, IImageState> imageMap;

  /**
   * Constructor for a model object. Initializes
   * the imageMap field as an empty hashmap.
   */
  public ImageDataBase() {
    this.imageMap = new HashMap<>();
  }

  /**
   * Loads an image into the hashmap with the specified string id.
   *
   * @param idImage the String id of the image object
   * @param image - the image being added to the model
   */
  @Override
  public void loadImage(String idImage, IImageState image) {
    imageMap.put(Objects.requireNonNull(idImage), Objects.requireNonNull(image));
  }

  @Override
  public IImageState getImage(String idImage) {
    return this.imageMap.get(Objects.requireNonNull(idImage));
  }

  @Override
  public String toString() {
    String contents = "";
    String[] ids = this.imageMap.keySet().toArray(new String[0]);

    for (int i = 0; i < ids.length; i++) {
      contents = contents.concat(ids[i] + "\n");
    }

    return contents;
  }

}
