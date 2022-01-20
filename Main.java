//Written by Shahram Taheri
//You can find several codes and their explanation on my YouTube channel.
//https://www.youtube.com/channel/UC2CVqCLGinsK-Ignzm5t18A

import javax.swing.*;
import java.awt.*;


class Main {
  public static void main(String[] args) {
    JFrame win = new JFrame("X-O Game");
        win.setSize(1024,768);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.add(new XOGame());
        win.setVisible(true);
  }
}