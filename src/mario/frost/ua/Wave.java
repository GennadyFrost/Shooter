package mario.frost.ua;

import java.awt.*;

public class Wave {

    private String waveText;
    private int waveNumber;
    private int waveMultiplier;

    private long waveTimer;
    private long waveDelay;
    private long waveTimerDiff;


    Wave(){
        waveNumber = 1;

        waveMultiplier = 5;

        waveTimer = 0;
        waveDelay = 5000;
        waveTimerDiff = 0;

        waveText = " В О Л Н А - ";

    }

    public void update(){

        if(GamePanel.enemyArrayList.size() == 0 && waveTimer == 0){
            waveTimer = System.nanoTime();

        }
        if (waveTimer > 0){
            waveTimerDiff += (System.nanoTime() - waveTimer)/1000000;
            waveTimer = System.nanoTime();
        }
        if (waveTimerDiff > waveDelay){
            createEnemies();
            waveTimer = 0;
            waveTimerDiff = 0;
        }
    }
    public void createEnemies(){
        int enemyCount = waveNumber * waveMultiplier;
        if (waveNumber < 40){
            while(enemyCount > 0){
                int type = 1;
                int rank = 1;
                GamePanel.enemyArrayList.add(new Enemy(type, rank));
                GamePanel.enemyArrayList.add(new Enemy(type, rank));
                enemyCount -= type * rank;
            }
        }
        waveNumber++;
    }
    public boolean showWave(){
        if (waveTimer != 0){
            return true;
        }
        else return false;
    }


    public void draw(Graphics2D graphics2D){
        double divider = waveDelay/180;
        double alpha = waveTimerDiff/divider;
        alpha = 255 * Math.sin(Math.toRadians(alpha));
        if (alpha < 0){
            alpha = 0;
        }
        if (alpha > 255){
            alpha = 255;
        }
        graphics2D.setFont(new Font("consolas", Font.PLAIN, 20));
        graphics2D.setColor(new Color(255, 255, 255, (int) alpha));
        String s = " - " + waveNumber + " - ая" + waveText;
        long length = (int) graphics2D.getFontMetrics().getStringBounds(s, graphics2D).getWidth();
        graphics2D.drawString(s, (GamePanel.WIDTH/2) - (int)(length / 2), GamePanel.HEIGHT/2);

    }
}
