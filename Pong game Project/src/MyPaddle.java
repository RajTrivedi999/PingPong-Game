import java.awt.*;
import java.awt.event.*;

public class MyPaddle extends Rectangle {

    int id;
    int Yspeed;
    int distance=20;

    MyPaddle(int x,int y,int padwidth,int padheight,int id){
        super(x,y,padwidth,padheight);
        this.id=id;
    }
    public void keypress(KeyEvent e){
        switch(id){
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W){
                    Ydirection(-distance);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_S){
                    Ydirection(distance);
                    move();
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    Ydirection(-distance);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    Ydirection(distance);
                    move();
                }
                break;
        }
    }
    public void keyrelease(KeyEvent e){
        switch (id){
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W){
                    Ydirection(0);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_S){
                    Ydirection(0);
                    move();
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    Ydirection(0);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    Ydirection(0);
                    move();
                }
                break;
        }
    }
    public void Ydirection(int y){
        Yspeed=y;
    }
    public void move(){
        y=y+Yspeed;
    }
    public void draw(Graphics g){
        if(id==1) g.setColor(Color.cyan);
        else g.setColor(Color.GREEN);
        g.fillRect(x,y,width,height);
    }
}