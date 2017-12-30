package mario.frost.ua;
import java.awt.*;

public class Bullet
{
    private double x;
    private double y;
    private int r;
    private int speed;


    private double bulletDX;
    private double bulletDY;

    private double distX;
    private double distY;
    private double dist;

    private Color color;
    Bullet(){
        x = GamePanel.playerArrayList.get(0).getX();
        y =  GamePanel.playerArrayList.get(0).getY();
        r = 2;
        color = Color.WHITE;
        speed = 20;

//        distX = GamePanel.mouseX - x;
//        distY = GamePanel.mouseY - y;

//        dist = Math.sqrt(distX * distX + distY * distY);

//        bulletDX = distX/dist;
//        bulletDY = distY/dist;
    }

    public double getX()
    {
        return x;
    }

    public int getR()
    {
        return r;
    }

    public double getY()
    {
        return y;
    }

    public void update(){
        y -= speed;
        //dx += speed;
    }
    void draw(Graphics2D graphics2D){
        graphics2D.setColor(color);
        graphics2D.fillOval((int)x,(int)y, r, r);
    }
    public boolean remove(){
        if (y < -200){return true;}
        return false;
    }
}
