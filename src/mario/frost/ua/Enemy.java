package mario.frost.ua;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Enemy
{
    private  double x;
    private  double y;
    private int r;
    private int healthFinal;

    private double speed;
    private double dx;
    private double dy;
    private  double health;
    public static Random random = new Random();
    public static Graphics2D graphics2D;
    Color colorHit;




    private  Color color;

    private int type;
    private int rank;

    public Enemy(int type, int rank){
        colorHit = Color.CYAN;
        this.type = type;
        this.rank = rank;

        switch(type){
            case(1): color = Color.CYAN;
                switch (rank){
                case(1):
                    x = Math.random() * GamePanel.WIDTH;
                    y = 0;
                    r = 10;
                    speed = 10;
                    healthFinal = 10;
                    health = 3;
                    double angle = Math.toRadians(Math.random() * 360);
                    dx = Math.sin(angle)* speed;
                    dy = Math.cos(angle)* speed;


            }
        }
    }
    public void RandomDist(){

            int go = random.nextInt(50);
            if (go == 5){
            double angle = Math.toRadians(Math.random() * 360);
            dx = (Math.sin(angle)/2) * speed;
            dy = (Math.cos(angle)/2) * speed;
        }
}


    public double getY()
    {
        return y;
    }

    public int getR()
    {
        return r;
    }

    public double getX()
    {

        return x;
    }

    public boolean remove(){
        if (health <= 0){return true;}
        return false;
    }

    public boolean hitColor(){
        return false;

    }
    public void hit(){
        health--;
        color = Color.RED;
        color = colorHit;







    }

    public void update(){
        x += dx;
        y += dy;
            RandomDist();
        if (x < 0 && dx < 0){
            dx = -dx;
        }
        if (x > GamePanel.WIDTH && dx > 0){
            dx = -dx;
        }
        if ( y < 0 && dy < 0){
            dy = -dy;
        }
        if (y > GamePanel.HEIGHT && dy > 0){
            dy = -dy;
        }


//        for (int i = 0; i < GamePanel.enemyArrayList.size();i++){
//            Enemy e1 = GamePanel.enemyArrayList.get(i);
//            double ex = e1.getX();
//            double ey = e1.getY();
//            double er = e1.getR();
//            for (int j = 0; j < GamePanel.enemyArrayList.size() && i != j;j++){
//                Enemy e2 = GamePanel.enemyArrayList.get(i);
//                double e2x = e2.getX();
//                double e2y = e2.getY();
//                double e2r = e2.getR();
//                double dx = ex - e2x;
//                double dy = ey - e2y;
//                double dist =Math.sqrt(dx * dx + dy * dy);
//                if ((int) dist <= (er + e2r)){
//
//
//                }
//            }
//        }




    }
    public void draw(Graphics2D graphics2D){
        graphics2D.setColor(color);
        graphics2D.fillOval((int) x - r, (int) y - r, r * 2, r * 2);
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.setColor(color.darker());
        graphics2D.drawOval((int) x - r, (int) y - r, r * 2, r * 2);
        graphics2D.setStroke(new BasicStroke(9));

    }
}
