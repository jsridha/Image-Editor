package view.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.*;

/**
 * Concrete implementation of IView. Graphically presents
 * objects contained in IImageDB model, and provides interface
 * to provide input parsed by an IController object.
 */
public class GUIView extends JFrame implements KeyListener, ActionListener, IGUIView {

  private final JButton visualize;
  private final JButton brighten;
  private final JButton loadFromFile;
  private final JButton save;
  private final JButton sepia;
  private final JButton greyscale;
  private final JButton blur;
  private final JButton sharpen;
  private final JButton loadFromModel;
  private final Canvas canvas;
  private final List<ViewListener> listenerList;

  private final Histogram redHistogram;

  private final Histogram greenHistogram;

  private final Histogram blueHistogram;

  private final Histogram intensityHistogram;

  /**
   * Constructor for GUIView. Constructs window using Java Swing.
   */
  public GUIView() {
    setSize(1000, 1000);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    // Initialize buttons for various functions as a Grid Panel on right side of window.
    JPanel buttonPanel = new JPanel (new GridLayout(5, 2, 10, 10));
    this.visualize = new JButton("Visualize Component");
    this.brighten = new JButton("Brighten/Darken");
    this.loadFromFile = new JButton("Load image from file");
    this.save = new JButton("Save to file");
    this.sepia = new JButton("Sepia");
    this.greyscale = new JButton("Greyscale");
    this.blur = new JButton("Blur");
    this.sharpen = new JButton("Sharpen");
    this.loadFromModel = new JButton("Render different image");
    buttonPanel.add(this.loadFromFile);
    buttonPanel.add(this.save);
    buttonPanel.add(this.visualize);
    buttonPanel.add(this.brighten);
    buttonPanel.add(this.sepia);
    buttonPanel.add(this.greyscale);
    buttonPanel.add(this.blur);
    buttonPanel.add(this.sharpen);
    buttonPanel.add(loadFromModel);
    add(buttonPanel, BorderLayout.EAST);

    this.canvas = new Canvas();
    add(this.canvas, BorderLayout.CENTER);

    this.setFocusable(true);

    this.visualize.addActionListener(this);
    this.brighten.addActionListener(this);
    this.loadFromFile.addActionListener(this);
    this.save.addActionListener(this);
    this.sepia.addActionListener(this);
    this.greyscale.addActionListener(this);
    this.blur.addActionListener(this);
    this.sharpen.addActionListener(this);
    this.loadFromModel.addActionListener(this);
    this.addKeyListener(this);

    listenerList = new ArrayList<>();

    JPanel histogramPanel = new JPanel(new GridLayout(4, 1, 10 , 10));
    this.redHistogram = new Histogram();
    this.greenHistogram = new Histogram();
    this.blueHistogram = new Histogram();
    this.intensityHistogram = new Histogram();

    histogramPanel.add(redHistogram);
    histogramPanel.add(greenHistogram);
    histogramPanel.add(blueHistogram);
    histogramPanel.add(intensityHistogram);
    add(histogramPanel, BorderLayout.WEST);
  }

  @Override
  public void addViewListeners(ViewListener listener) {
    Objects.requireNonNull(listener);
    this.listenerList.add(listener);
  }

  @Override
  public void renderImageList() {
    throw new IllegalStateException("You still need to fill render() out");
  }

  @Override
  public void renderHistograms(int[] red, int[] green, int[] blue, int[] intensity) {
    this.redHistogram.renderHistogram("red", red);
    this.greenHistogram.renderHistogram("green", green);
    this.blueHistogram.renderHistogram("blue", blue);
    this.intensityHistogram.renderHistogram("intensity", intensity);
  }

  @Override
  public void renderMessage(String message) {
    JOptionPane.showMessageDialog(null, message);
  }

  @Override
  public void renderImage(BufferedImage image) {
    Objects.requireNonNull(image);
    this.canvas.renderImage(image);
  }

  @Override
  public void requestFrameFocus() {
    this.requestFocus();
  }

  /**
   * Notifies each ViewListener associated with the current
   * instance of a Load event. Requests filepath of item
   * to read in and what to call the resulting image.
   */
  private void emitLoadFileEvent() {
    FileDialog fd = new FileDialog(this, "Choose a file to load:", FileDialog.LOAD);
    fd.setVisible(true);
    String filepath = fd.getDirectory() + fd.getFile();
    System.out.println(filepath);
    String imageID = JOptionPane.showInputDialog("What would you like "
            + "to call this image?");
    for (ViewListener listener : listenerList) {
      listener.handleLoadEvent(filepath, imageID);
    }
  }

  /**
   * Notifies each ViewListener associated with the current
   * instance of a RenderNew event. Requests id of image to render.
   */
  private void emitRenderNew() {
    String imageID = JOptionPane.showInputDialog("What image would you like to render?");
    for (ViewListener listener : listenerList) {
      listener.handleRenderEvent(imageID);
    }
  }

  /**
   * Notifies each ViewListener associated with the current
   * instance of a Visualize event. Requests name of image
   * to save and filepath of location to save to.
   */
  private void emitSaveEvent() {
    String filepath = JOptionPane.showInputDialog("Enter the filepath for where you "
            + "would like to save the image.");

    for (ViewListener listener : listenerList) {
      listener.handleSaveEvent(filepath);
    }
  }

  /**
   * Notifies each ViewListener associated with the current
   * instance of a Visualize event. Requests parameter to
   * visualize, image to update, and what to call result.
   */
  private void emitVisualizeEvent() {
    String param = JOptionPane.showInputDialog("Which parameter should be visualized?");
    if (param.isEmpty()) {
      throw new IllegalStateException("Must provide the parameter to visualize.");
    }
    String newID = getID();
    for (ViewListener listener : listenerList) {
      listener.handleVisualizeEvent(param, newID);
    }
  }

  /**
   * Notifies each ViewListener associated with the current
   * instance of a Brighten event. Requests amount to brighten
   * image by, image to update, and what to call result.
   */
  private void emitBrightenEvent() {
    int value;
    try {
      value = Integer.parseInt(JOptionPane.showInputDialog("How much should the image " +
              "be brightened/darkened by?"));
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Must provide an integer value to brighten/darken by.");
    }
    String newID = getID();
    for (ViewListener listener : listenerList) {
      listener.handleBrightenEvent(value, newID);
    }
  }

  /**
   * Notifies each ViewListener associated with the current instance
   * of a Filter event. Requests image to update and what to call result.
   *
   * @param type type of linear color being affected. Currently
   *             supports blur and sharpen transformations.
   */
  private void emitFilterEvent(String type) {
    String newID = getID();
    for (ViewListener listener : listenerList) {
      listener.handleFilterEvent(type, newID);
    }
  }

  /**
   * Notifies each ViewListener associated with the current instance of a
   * LinearColor event. Requests image to update and what to call result.
   *
   * @param type type of linear color being affected. Currently
   *             supports sepia and greyscale transformations.
   */
  private void emitLinearColorEvent(String type) {
    String newID = getID();
    for (ViewListener listener : listenerList) {
      listener.handleLinearColorEvent(type, newID);
    }
  }

  /**
   * Helper method defining behavior for requesting
   * names of images from user upon button press.
   */
  private String getID() {
    String newImage = JOptionPane.showInputDialog("What should the resulting image be titled?");
    if (newImage.isEmpty()) {
      throw new IllegalStateException("Cannot leave name of resulting image blank.");
    }

    return newImage;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // Emit a view event to all listeners based on action command received.
    try {
      switch (e.getActionCommand() ) {
        case "Load image from file":
          emitLoadFileEvent();
          break;
        case "Render different image":
          emitRenderNew();
          break;
        case "Save to file":
          emitSaveEvent();
          break;
        case "Visualize Component":
          emitVisualizeEvent();
          break;
        case "Brighten/Darken":
          emitBrightenEvent();
          break;
        case "Sepia":
          emitLinearColorEvent("Sepia");
          break;
        case "Greyscale":
          emitLinearColorEvent("Greyscale");
          break;
        case "Blur":
          emitFilterEvent("Blur");
          break;
        case "Sharpen":
          emitFilterEvent("Sharpen");
          break;
        default:
          throw new IllegalStateException("Unknown action command.");
      }
    }

    // If exception is thrown, inform user via pop-up window.
    catch (Exception ex) {
      renderMessage(ex.getMessage());
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {
    try {
      // Define key commands for load and save events.
      if (e.getKeyChar() == 'l') {
        emitLoadFileEvent();
      }

      if (e.getKeyChar() == 's') {
        emitSaveEvent();
      }
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(null, ex.getMessage());
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    // No action should be performed on key press, so method is empty.
  }

  @Override
  public void keyReleased(KeyEvent e) {
    // No action should be performed on key release, so method is empty.
  }
}
