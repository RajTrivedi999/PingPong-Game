import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

public class MyPanel extends JPanel implements Runnable {

    static final int WIDTH=1000;
    static final int HEIGHT=(int)(WIDTH*(0.5555));
    static final Dimension SCREEN=new Dimension(WIDTH,HEIGHT);
    static final int BALLDIA=20;
    static final int PADWIDTH=25;
    static final int PADHEIGHT=100;
    Thread gamethread;
    Image image;
    Graphics graphics;
    Random random;
    MyPaddle paddle1;
    MyPaddle paddle2;
    Myball ball;
    MyScore score;
    MyHurdle hurdle1;
    MyHurdle hurdle2;

    MyPanel(){
        Paddle();
        Ball();
        Hurdle();
        score=new MyScore(WIDTH,HEIGHT);
        setFocusable(true);
        addKeyListener(new Al());
        setPreferredSize(SCREEN);

        gamethread=new Thread(this);
        gamethread.start();
    }
    public void Ball(){
        random=new Random();
        ball=new Myball((WIDTH/2)-(BALLDIA/2),random.nextInt(HEIGHT-BALLDIA),BALLDIA,BALLDIA);
    }
    public void Paddle(){
        paddle1=new MyPaddle(0,(HEIGHT/2)-(PADHEIGHT/2),PADWIDTH,PADHEIGHT,1);
        paddle2=new MyPaddle(WIDTH-PADWIDTH,(HEIGHT/2)-(PADHEIGHT/2),PADWIDTH,PADHEIGHT,2);
    }
    public void Hurdle(){
        hurdle1=new MyHurdle(WIDTH/2-PADWIDTH/2,(HEIGHT/2-PADHEIGHT/2)/2,25,50);
        hurdle2=new MyHurdle(WIDTH/2-PADWIDTH/2,(HEIGHT/2-PADHEIGHT/2)+(HEIGHT/2-PADHEIGHT/2)/2,25,50);
    }
    public void paint(Graphics g){
        image=createImage(getWidth(),getHeight());
        graphics= image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }
    public void draw(Graphics g){
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
        hurdle1.draw(g);
        hurdle2.draw(g);
    }
    public void move(){
        paddle1.move();
        paddle2.move();
        ball.move();
    }
    public void collision(){
        if(ball.y<=0) ball.Ydirection(-ball.Yspeed);
        if(ball.y>=HEIGHT-BALLDIA) ball.Ydirection(-ball.Yspeed);

        if(ball.intersects(paddle1)){
            ball.Xspeed=Math.abs(ball.Xspeed);
            ball.Xspeed++;
            //if(ball.Yspeed>0) ball.Yspeed++;
            //else ball.Yspeed--;
            ball.Xdirection(ball.Xspeed);
            ball.Ydirection(ball.Yspeed);
        }
        if(ball.intersects(paddle2)){
            ball.Xspeed=Math.abs(ball.Xspeed);
            ball.Xspeed++;
            //if(ball.Yspeed>0) ball.Yspeed++;
            //else ball.Yspeed--;
            ball.Xdirection(-ball.Xspeed);
            ball.Ydirection(ball.Yspeed);
        }

        if(paddle1.y<=0) paddle1.y=0;
        if(paddle1.y>=(HEIGHT-PADHEIGHT)) paddle1.y=HEIGHT-PADHEIGHT;
        if(paddle2.y<=0) paddle2.y=0;
        if(paddle2.y>=(HEIGHT-PADHEIGHT)) paddle2.y=HEIGHT-PADHEIGHT;

        if(ball.x<=0){
            score.player2++;
            Paddle();
            Ball();
            System.out.println("Player 2: "+score.player2);
        }
        if(ball.x>=WIDTH-BALLDIA){
            score.player1++;
            Paddle();
            Ball();
            System.out.println("Player 1: "+score.player1);
        }
        if(ball.intersects(hurdle1)){
            //ball.Xspeed=Math.abs(ball.Xspeed);
            ball.Xdirection(ball.Xspeed);
            ball.Xdirection(-ball.Xspeed);
        }
        if(ball.intersects(hurdle2)){
            ball.Xdirection(ball.Xspeed);
            ball.Xdirection(-ball.Xspeed);
        }
    }
    public void run(){
        long lasttime=System.nanoTime();
        double timer=60.0;
        double ns=1000000000/timer;
        double delta=0;
        while (true){
            long now=System.nanoTime();
            delta+=(now-lasttime)/ns;
            lasttime=now;
            if(delta>=1){
                move();
                collision();
                repaint();
                delta--;
            }
        }
    }
    public class Al extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            paddle1.keypress(e);
            paddle2.keypress(e);
        }
        public void keyReleased(KeyEvent e){
            paddle1.keyrelease(e);
            paddle2.keyrelease(e);
        }
    }
}
