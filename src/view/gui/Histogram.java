package view.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Creates a Histogram component that can be included in a JFrame Layout.
 */
public class Histogram extends JPanel {

  private String channel;
  private int[] channelValues;

  @Override
  public void paintComponent(Graphics g) {

    if (channelValues == null) {
      return;
    }

    super.paintComponent(g);

    int width = getWidth();
    int height = getHeight();
    int interval = 10;
    int individualWidth = 1;

    int maxCount = 0;
    for (int i = 0; i < channelValues.length; i++) {
      if (maxCount < channelValues[i])
        maxCount = channelValues[i];
    }

    int x = 0;

    g.drawString(channel, width / 2, 10);
    g.drawLine(10, height - 45, width - 10, height - 45);
    for (int i = 0; i < channelValues.length; i++) {

      int barHeight = (int)(((double)channelValues[i] / (double)maxCount) * (height - 55));
      g.drawRect(x, height - 45 - barHeight, individualWidth, barHeight);

      x += interval;
    }
  }

  /**
   * Renders histogram.
   *
   * @param channel - channel histogram refers to
   * @param channelValues - channel values for each pixel
   */
  public void renderHistogram(String channel, int[] channelValues) {
    this.channel = channel;
    this.channelValues = channelValues;
    repaint();
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(150, 150);
  }

}
