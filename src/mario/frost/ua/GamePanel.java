package mario.frost.ua;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable
{
    public static int WIDTH = 500;
    public static int HEIGHT = 600;

    private Thread thread;

    public static int mouseX;
    public static int mouseY;

    public static BufferedImage image;
    public static Graphics2D graphics2D;


    public static EXIT exit;




    public static GameBack background;


    public static  ArrayList<Player> playerArrayList;


    public static ArrayList<Bullet> bulletArrayList;
    public static ArrayList<Bullet> bulletArrayList2;
    public static ArrayList<Enemy> enemyArrayList;

    public static Wave wave;
    public static Menue menue;

    private int FPS;
    private double dividerFPS;
    private long timerFPS;
    private  double millisToFPS;
    private int sleepTime;

    public static boolean leftMouse;

    public enum STATES{
        MENUE,
        PLAY,
        EXIT;
    }
    public static STATES state = STATES.MENUE;

    public GamePanel(){
        super();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();

        addKeyListener(new Listener());
        addMouseMotionListener(new Listener());
        addMouseListener(new Listener());

    }

    public void start(){
        thread = new Thread(this);
        thread.start();


    }

    @Override
    public void run()
    {
        FPS = 30;
        millisToFPS = 1000/FPS;
        sleepTime = 0;



        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);
        graphics2D = (Graphics2D)image.getGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        leftMouse = false;
        background = new GameBack();
        playerArrayList = new ArrayList<Player>();
        playerArrayList.add(new Player());
        bulletArrayList = new ArrayList<Bullet>();
        bulletArrayList2 = new ArrayList<Bullet>();
        enemyArrayList = new ArrayList<Enemy>();
        wave = new Wave();
        menue = new Menue();
        exit = new EXIT();



        while (true){
            boolean returnPlay = playerArrayList.get(0).removePlayer();
            timerFPS = System.nanoTime();
            if (state.equals(STATES.MENUE) || returnPlay) {
                background.update();
                background.draw(graphics2D);
                menue.update();
               menue.draw(graphics2D);
                gameDraw();
            }
            if (state.equals(STATES.PLAY)){
                gameUpdate();
                gameRender();
                gameDraw();

            }


            timerFPS = (System.nanoTime() - timerFPS) / 1000000;
            if (millisToFPS > timerFPS){
                sleepTime = (int)(millisToFPS - timerFPS);
            }
            else sleepTime = 1;
            try
            {
                thread.sleep(sleepTime);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            timerFPS = 0;
            sleepTime = 1;

        }
    }
    public void gameUpdate(){


        background.update();
        playerArrayList.get(0).update();





        for (int i = 0; i < enemyArrayList.size(); i++)
        {

            enemyArrayList.get(i).update();
        }

        //Bullets - enemies collide
        for (int i = 0; i < enemyArrayList.size();i++){
            Enemy e = enemyArrayList.get(i);
            double ex = e.getX();
            double ey = e.getY();
            for (int j = 0; j < bulletArrayList.size();j++){
                Bullet b = bulletArrayList.get(j);
                double bx = b.getX();
                double by = b.getY();
                double dx = ex - bx;
                double dy = ey - by;
                double dist =Math.sqrt(dx * dx + dy * dy);
                if ((int)dist <= (e.getR() + b.getR())){
                    e.hit();
                    bulletArrayList.remove(j);j--;
                    boolean remove = e.remove();
                    if (remove){enemyArrayList.remove(i);
                        i--;break;

                    }
                }

            }

        }




        for (int i = 0; i < bulletArrayList.size();i++){
            bulletArrayList.get(i).update();
            boolean remove = bulletArrayList.get(i).remove();
            if (remove){bulletArrayList.remove(i); i--;}
        }
        for (int i = 0; i < enemyArrayList.size();i++){

            Enemy  e = enemyArrayList.get(i);
            double ex = e.getX();
            double ey = e.getY();

            double px = playerArrayList.get(0).getX();
            double py = playerArrayList.get(0).getY();
            double pr = playerArrayList.get(0).getR();
            double dx = ex - px;
            double dy = ey - py;
            double dist = Math.sqrt(dx * dx + dy * dy);
            if ((int)dist <= e.getR() + playerArrayList.get(0).getR()){
                e.hit();
                playerArrayList.get(0).hit();
                boolean remove = e.remove();
                if (remove){enemyArrayList.remove(i);
                    i--;
                }
//
            }
        }
        playerArrayList.get(0).removePlayer();

        wave.update();


    }
    public void gameRender(){
        background.draw(graphics2D);
        playerArrayList.get(0).draw(graphics2D);
        for (int i = 0; i < bulletArrayList.size();i++){
            bulletArrayList.get(i).draw(graphics2D);
        }

        for (int i = 0; i < enemyArrayList.size();i++){
            enemyArrayList.get(i).draw(graphics2D);
        }
        if (wave.showWave()){
            wave.draw(graphics2D);

        }
    }
    public  void gameDraw(){
        Graphics g2 = this.getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
    }




}
