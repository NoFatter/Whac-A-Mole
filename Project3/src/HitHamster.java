import javax.swing.*;
import java.awt.*;

public class HitHamster {
    public static void main(String[] args){
        JFrame frame = new JFrame("欢乐打地鼠");
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image hammerImg = new ImageIcon("image/Hammer.png").getImage();
        Cursor cursor = tk.createCustomCursor(hammerImg,new Point(10,10),"norm");
        frame.setCursor(cursor);
        frame.getContentPane().add(new HitHamsterPanel());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
