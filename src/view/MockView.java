package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import view.gui.IGUIView;
import view.gui.ViewListener;

public class MockView implements IGUIView {

  public BufferedImage imageToTest;
  private StringBuilder log;

  public MockView(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void addViewListeners(ViewListener listener) {
    // Empty mock method, functionality not being tested.
  }

  @Override
  public void renderImage(BufferedImage image) {
    this.imageToTest = Objects.requireNonNull(image);
    StringBuilder imageString = new StringBuilder();

    for (int i = 0; i < this.imageToTest.getWidth(); i++) {
      for (int j = 0; j < this.imageToTest.getHeight(); j++) {
        Color color = new Color(this.imageToTest.getRGB(i, j));

        imageString.append(color.getRed() + " " + color.getGreen() + " " + color.getBlue() + " ");
      }
    }

    log.append(imageString);
  }

  @Override
  public void requestFrameFocus() {
    // Empty mock method, functionality not being tested.
  }

  @Override
  public void setVisible(boolean value) {
    // Empty mock method, functionality not being tested.
  }

  @Override
  public void renderHistograms(int[] red, int[] green, int[] blue, int[] intensity) {
    // Empty mock method, functionality not being tested.
  }

  @Override
  public void renderImageList() throws IOException {
    // Empty mock method, functionality not being tested.
  }

  @Override
  public void renderMessage(String message) throws IOException {
    // Empty mock method, functionality not being tested.
  }
}