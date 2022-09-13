import java.awt.*;

public class MyScore extends  Rectangle {

    static int WIDTH;
    static int HEIGHT;
    int player1;
    int player2;

    MyScore(int WIDTH,int HEIGHT){
        MyScore.WIDTH=WIDTH;
        MyScore.HEIGHT=HEIGHT;
    }
    public void draw(Graphics g){
        g.setColor(Color.PINK);
        g.setFont(new Font("comic sans",Font.BOLD,30));
        g.drawLine(WIDTH/2,0,WIDTH/2,HEIGHT);
        g.drawString(String.valueOf(player1),WIDTH/2-50,50);
        g.drawString(String.valueOf(player2),WIDTH/2+50,50);
    }
}
