package mario.frost.ua;
import java.awt.*;
public class Player
{
    private double x;
    private double y;
    private int r;
    private double health;
    public static Graphics2D graphics2D;

    private  double dx;
    private  double dy;

    private int speed;

    private Color color1;
    private Color color2;
    public static EXIT exit;

    public static boolean up;
    public static boolean down;
    public static boolean left;
    public static boolean right;

    public static boolean isFiring;

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }


    public Player(){
        x = GamePanel.WIDTH/2;
        y = GamePanel.HEIGHT/2;

        r = 7;
        health = 10;

        speed = 8;
        dx = 0;
        dy = 0;

        color1 = Color.YELLOW;
        up = false;
        down = false;
        left = false;
        right = false;

        isFiring = false;
    }

    public int getR() {
        return r;
    }

    public void hit(){
        health--;

        if (health < 10 && health > 0)color1 = Color.ORANGE;
        if (health == 0)color1 = Color.RED;
        if (health < 0) ;


    }
    public boolean removePlayer(){
        exit = new EXIT();
        if (health < 0){
            GamePanel.background.update();
            GamePanel.background.draw(graphics2D);
            GamePanel.menue.update();
            GamePanel.menue.draw(graphics2D);

            return true;
        }
        return false;
    }


    public void update()
    {
        if (up && y > r){
            dy = -speed;
        }
        if (down && y < GamePanel.HEIGHT - r){
            dy = speed;
        }
        if (left && x > r){
            dx = -speed;
        }
        if (right && x < GamePanel.WIDTH - r){
            dx = speed;
        }
        if (up && left || up && right || down && left || down && right){
            dy *= Math.sin( Math.toRadians(45));
            dx *= Math.cos( Math.toRadians(45));

        }

        y += dy;
        x += dx;

        dy = 0;
        dx = 0;

        if (isFiring){
            GamePanel.bulletArrayList.add(new Bullet());
        }


    }
    public void draw(Graphics2D graphics2D){
        graphics2D.setColor(color1);

        graphics2D.fillOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.setColor(color1.darker());
        graphics2D.drawOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
        graphics2D.setStroke(new BasicStroke(1));
    }
}
