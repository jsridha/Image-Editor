package view.gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Canvas extends JPanel {

  private BufferedImage image;
  private int x;
  private int y;

  public Canvas() {
    setBackground(Color.LIGHT_GRAY);
    x = 0;
    y = 0;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(this.image, this.x, this.y, null);
  }

  public void renderImage(BufferedImage image) {
    this.image = image;
    this.x = (this.getWidth() - this.image.getWidth(null)) / 2;
    this.y = (this.getHeight() - this.image.getHeight(null)) / 2;
    repaint();
  }

}
