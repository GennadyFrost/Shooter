package mario.frost.ua;

        import java.awt.*;

public class GameBack
{
    private Color color;

    GameBack(){
        color = Color.BLUE;
    }
    public void update(){

    }
    public void draw(Graphics2D graphics2D){
        graphics2D.setColor(color);
        graphics2D.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
    }
}
