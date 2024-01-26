package controller.gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

import controller.gui.commands.ICommand;
import controller.gui.commands.Brighten;
import controller.gui.commands.LinearColor;
import controller.gui.commands.Load;
import controller.gui.commands.Save;
import controller.gui.commands.Visualize;
import controller.gui.commands.Filter;
import model.IImageDB;
import model.IImageState;
import view.gui.IGUIView;

/**
 * IController implementation for a graphical IView implementation.
 * Implements the IGUIController interface, which extends
 * IController and ViewListener.
 */
public class GraphicsController implements GUIController {

  private final IImageDB model;
  private final IGUIView view;
  private ICommand command;

  private String currentID;

  /**
   * Constructor for GUI controller. Throws exception if passed null arguments.
   *
   * @param model - an IImageDB object.
   * @param view - a GUIView object.
   */
  public GraphicsController(IImageDB model, IGUIView view) {
    this.model = Objects.requireNonNull(model);
    this.view = Objects.requireNonNull(view);
    this.view.addViewListeners(this);
  }

  @Override
  public void run() {
    // Set view to visible.
    this.view.setVisible(true);
  }

  @Override
  public void handleLoadEvent(String filepath, String imageID) {
    command = new Load();
    command.run(this.model, filepath, imageID);
    renderImage(imageID);
  }

  @Override
  public void handleSaveEvent(String filepath) {
    command = new Save();
    command.run(this.model, filepath, currentID);
    renderImage(currentID);
  }

  @Override
  public void handleBrightenEvent(int value, String newImage) {
    command = new Brighten(value);
    command.run(this.model, currentID, newImage);
    renderImage(newImage);
  }

  @Override
  public void handleFilterEvent(String type, String newImage) {
    command = new Filter(type);
    command.run(this.model, currentID, newImage);
    renderImage(newImage);
  }

  @Override
  public void handleLinearColorEvent(String type, String newImage) {
    command = new LinearColor(type);
    command.run(this.model, currentID, newImage);
    renderImage(newImage);
  }

  @Override
  public void handleVisualizeEvent(String param, String newImage) {
    command = new Visualize(param);
    command.run(this.model, currentID, newImage);
    renderImage(newImage);
  }

  @Override
  public void handleRenderEvent(String imageID) {
    renderImage(imageID);
  }

  @Override
  public void renderImage(String imageID) {

    IImageState sourceImage = model.getImage(imageID);
    if (sourceImage == null) {
      throw new IllegalStateException("Image with specified ID does not exist.");
    }

    // Update currentID field with the
    // String ID of the rendered IImage.
    this.currentID = imageID;

    // Request height and width from image, then initialize new BufferedImage.
    int width = sourceImage.getWidth();
    int height = sourceImage.getHeight();
    BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    int[] redVals = new int[width * height];
    int[] blueVals = new int[width * height];
    int[] greenVals = new int[width * height];
    int[] intensityVals = new int[width * height];

    int k = 0;
    // Iterate through matrix and append pixels into newImage at corresponding locations.
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int red = sourceImage.getRedChannel(i, j);
        int green = sourceImage.getGreenChannel(i, j);
        int blue = sourceImage.getBlueChannel(i, j);

        redVals[k] = red;
        greenVals[k] = green;
        blueVals[k] = blue;
        intensityVals[k] = (red + green + blue)/3;

        Color newColor = new Color(red, green, blue);
        newImage.setRGB(j, i, newColor.getRGB());
        k++;
      }
    }

    // Send image to render to view.
    this.view.renderImage(newImage);
    this.view.renderHistograms(redVals, greenVals, blueVals, intensityVals);
    this.view.requestFrameFocus();
  }
}
