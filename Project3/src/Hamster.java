import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class Hamster extends JPanel{
    private Boolean isLive;
    private ImageIcon LiveImg = new ImageIcon("image/Hamster.png");
    private ImageIcon DeadImg = new ImageIcon("image/Hole.png");
    private ImageIcon HitImg = new ImageIcon("image/DeadHamster.png");
    private ImageIcon ErrorImg = new ImageIcon("image/ErrorHole.png");
    JButton button;
    public Hamster(){
        button = new JButton();
        button.addActionListener(new HitListener());
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorder(null);
        button.setIcon(DeadImg);
        isLive = false;
    }
    public void hamsterShow(){
        button.setIcon(LiveImg);
        isLive = true;
        new LiveThread().start();
    }
    public void hitHamster(){
        button.setIcon(HitImg);
        isLive = false;
        HitHamsterPanel.score++;
    }
    public JButton getButton(){
        return button;
    }
    public boolean getIsLive(){
        return isLive;
    }
    private class HitListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            if(isLive){
                hitHamster();
                HitHamsterPanel.scoreLabel.setText("得分："+HitHamsterPanel.score);
            }
            else {
                HitHamsterPanel.score--;
                button.setIcon(ErrorImg);
                HitHamsterPanel.scoreLabel.setText("得分："+HitHamsterPanel.score+"   打错了，扣分！");
            }

        }
    }
    private class LiveThread extends Thread{
        public void run(){
            Calendar begin = Calendar.getInstance();
            Calendar end;
            long between;
            do{
                end = Calendar.getInstance();
                between = end.getTimeInMillis() - begin.getTimeInMillis();
            }while (between<2000);
            button.setIcon(DeadImg);
            isLive = false;
        }
    }

}
