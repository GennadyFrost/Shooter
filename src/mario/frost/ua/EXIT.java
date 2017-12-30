package mario.frost.ua;

import java.awt.*;

public class EXIT {
    private int buttonWigth;
    private int buttonHeight;
    private Color color1;
    private String s;
    private int transp = 0;


    EXIT(){
        buttonHeight = 120;
        buttonWigth = 60;
        s = "Вы проиграли, нажмите для перезапуска";

        color1 = Color.red;
    }

    public void update(){
        if (GamePanel.mouseX > GamePanel.WIDTH/2 - buttonWigth/2 &&
                GamePanel.mouseX < GamePanel.WIDTH/2 + buttonWigth/2 &&
                GamePanel.mouseY > GamePanel.HEIGHT/2 - buttonHeight/2 &&
                GamePanel.mouseY < GamePanel.HEIGHT/2 + buttonHeight/2){
            transp = 60;
            if (GamePanel.leftMouse){
                GamePanel.state = GamePanel.STATES.PLAY;
            }
        }
        else {
            transp = 160;
        }
    }
    public void draw(Graphics2D graphics2D){
        graphics2D.setColor(color1);
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.drawRect(GamePanel.WIDTH / 2 - buttonWigth / 2,
                GamePanel.HEIGHT / 2 - buttonHeight / 2, buttonWigth, buttonHeight);
        graphics2D.setColor(new Color(255, 255, 255, transp));
        graphics2D.fillRect(GamePanel.WIDTH / 2 - buttonWigth / 2,
                GamePanel.HEIGHT / 2 - buttonHeight / 2, buttonWigth, buttonHeight);
        graphics2D.setStroke(new BasicStroke(1));
        graphics2D.setFont(new Font("Consolas", Font.BOLD, 40));
        long length = (int)graphics2D.getFontMetrics().getStringBounds(s, graphics2D).getWidth();
        graphics2D.drawString(s, (int)(GamePanel.WIDTH/2 - length/2), (int)(GamePanel.HEIGHT/2 + buttonHeight/4));
    }
}

