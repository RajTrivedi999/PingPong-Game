import java.awt.*;
import java.lang.management.GarbageCollectorMXBean;
import java.util.Random;

public class Myball extends Rectangle {

    Random random;
    int Xspeed;
    int Yspeed;
    int startingspeed=3;

    Myball(int x,int y,int width,int height){
        super(x,y,width,height);
        random=new Random();
        int randomx= random.nextInt(2);
        if(randomx==0) randomx--;
        Xdirection(randomx*startingspeed);
        int randomy= random.nextInt(2);
        if(randomy==0) randomy--;
        Ydirection(randomy*startingspeed);

    }
    public void Xdirection(int x){
        Xspeed=x;
    }
    public void Ydirection(int y){
        Yspeed=y;
    }
    public void move(){
        x+=Xspeed;
        y+=Yspeed;
    }
    public void draw(Graphics g){
        g.setColor(Color.gray);
        g.fillOval(x,y,width,height);
    }
}
