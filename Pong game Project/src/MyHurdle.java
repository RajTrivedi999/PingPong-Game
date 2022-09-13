import java.awt.*;

public class MyHurdle extends Rectangle {
    MyHurdle(int x,int y,int width,int height){
        super(x,y,width,height);
    }
    public void draw(Graphics g){
        g.setColor(Color.magenta);
        g.fillRect(x,y,width,height);
    }
}
