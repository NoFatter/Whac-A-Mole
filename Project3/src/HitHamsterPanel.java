import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Random;

//主面板
public class HitHamsterPanel extends JPanel {
    private final int WIDTH = 600;
    private final int HEIGHT = 620;
    private Dimension window = Toolkit.getDefaultToolkit().getScreenSize();
    private JLabel titleLabel;
    public static JLabel scoreLabel;
    private JButton startGame;
    private JButton saveGame;
    private JButton loadGame;
    private ImageIcon startGameImg = new ImageIcon("image/StartGame.png");
    private ImageIcon startGameImgFocus = new ImageIcon("image/StartGameFocus.png");
    private Hamster[] hamsters = new Hamster[6];
    private SouthPanel southPanel;
    private CenterPanel centerPanel;
    private NorthPanel northPanel;
    private GamePanel gamePanel;
    private int Level = 10;
    public static int score = 0;
    private boolean isStart = false;
    public HitHamsterPanel(){
        for(int i=0;i<6;i++){
            hamsters[i] = new Hamster();
        }
        this.setLayout(new BorderLayout());
        southPanel = new SouthPanel();
        centerPanel = new CenterPanel();
        northPanel = new NorthPanel();
        gamePanel = new GamePanel();
        gamePanel.setVisible(false);
        add(southPanel,BorderLayout.SOUTH);
        add(centerPanel,BorderLayout.CENTER);
        add(gamePanel,BorderLayout.CENTER);
        add(northPanel,BorderLayout.NORTH);
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
    }
    protected void paintComponent(Graphics g){
        ImageIcon backgroundImg = new ImageIcon("image/background.jpg");
        Image background = backgroundImg.getImage();
        super.paintComponent(g);
        g.drawImage(background,0,0,getWidth(),getHeight(),this);
    }
    //北面板
    private class NorthPanel extends JPanel{
        public NorthPanel(){
            setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
            setOpaque(false);
            scoreLabel = new JLabel("得分：0");
            scoreLabel.setFont(new Font("黑体",Font.BOLD,40));
            scoreLabel.setForeground(Color.orange);
            scoreLabel.setVisible(false);
            add(scoreLabel);
            /*saveGame = new JButton("存档");
            loadGame = new JButton("读档");
            add(saveGame);
            add(loadGame);*/
        }
    }
    //开始游戏监听
    private class FocusStartGameListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {

        }
        @Override
        public void mousePressed(MouseEvent e) {
            southPanel.setVisible(false);
            titleLabel.setVisible(false);
            isStart = true;
            gamePanel.setVisible(true);
            scoreLabel.setVisible(true);
            new TimeThread().start();
        }
        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            startGame.setIcon(startGameImgFocus);
        }
        @Override
        public void mouseExited(MouseEvent e) {
            startGame.setIcon(startGameImg);
        }
    }
    //南面板
    private class SouthPanel extends JPanel{
        public SouthPanel(){
            setOpaque(false);
            startGame = new JButton();
            startGame.setOpaque(false);
            startGame.setContentAreaFilled(false);
            startGame.setFocusPainted(false);
            startGame.setIcon(startGameImg);
            startGame.setBorder(null);
            startGame.addMouseListener(new FocusStartGameListener());
            add(startGame);
        }
    }
    //中心面板
    private class CenterPanel extends JPanel{
        public CenterPanel(){
            setOpaque(false);
            ImageIcon titleLabelImg = new ImageIcon("image/GameName.png");
            titleLabel = new JLabel(titleLabelImg);
            add(titleLabel);
        }
    }
    //游戏面板
    private class GamePanel extends JPanel{
        public GamePanel(){
            int i;
            JLabel label = new JLabel("               ");
            label.setPreferredSize(new Dimension(600,300));
            setLayout(new FlowLayout(FlowLayout.LEFT,70,20));
            setOpaque(false);
            add(label);
            for(i=0;i<6;i++){
                add(hamsters[i].getButton());
            }
        }
    }
    //游戏开始
    private class TimeThread extends Thread{
        public void run(){
            Calendar begin = Calendar.getInstance();
            Calendar end;
            long between;
            do{
                if(Level==10&&score>5){
                    Level--;
                    scoreLabel.setText("得分："+HitHamsterPanel.score+"   难度提升！");
                }
                if(Level==9&&score>10){
                    Level--;
                    scoreLabel.setText("得分："+HitHamsterPanel.score+"   难度提升！");
                }
                if(Level==8&&score>15){
                    Level--;
                    scoreLabel.setText("得分："+HitHamsterPanel.score+"   难度提升！");
                }
                if(Level==7&&score>20){
                    Level--;
                    scoreLabel.setText("得分："+HitHamsterPanel.score+"   难度提升！");
                }
                if(Level==6&&score>25){
                    Level--;
                    scoreLabel.setText("得分："+HitHamsterPanel.score+"   难度提升！");
                }
                if(Level==5&&score>30){
                    Level--;
                    scoreLabel.setText("得分："+HitHamsterPanel.score+"   难度提升！");
                }
                if(Level==4&&score>35){
                    Level--;
                    scoreLabel.setText("得分："+HitHamsterPanel.score+"   难度提升！");
                }
                if(Level==3&&score>40){
                    Level--;
                    scoreLabel.setText("得分："+HitHamsterPanel.score+"   难度提升！");
                }
                if(Level==2&&score>45){
                    Level--;
                    scoreLabel.setText("得分："+HitHamsterPanel.score+"   难度提升！");
                }
                end = Calendar.getInstance();
                between = end.getTimeInMillis() - begin.getTimeInMillis();
                int show = new Random().nextInt(6);
                if(hamsters[show].getIsLive()){
                    continue;
                }
                else{
                    hamsters[show].hamsterShow();
                }
                try{
                    Thread.sleep(100*Level);
                }catch (InterruptedException e){

                }
            }while (between<60000);
            ImageIcon celebrate = new ImageIcon("image/Celebrate.png");
            JOptionPane.showMessageDialog(null, "你得到了"+score+"分","恭喜",JOptionPane.WARNING_MESSAGE,celebrate);
            score = 0;
            Level = 10;
            scoreLabel.setText("得分：0");
            isStart = false;
            southPanel.setVisible(true);
            titleLabel.setVisible(true);
            gamePanel.setVisible(false);
            scoreLabel.setVisible(false);
        }
    }
}
