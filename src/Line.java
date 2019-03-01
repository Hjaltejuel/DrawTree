import java.awt.*;

public class Line implements Shape {

    int x1,x2,y1,y2;
    boolean T,drawLine;

    public Line(int x1, int y1, int x2, int y2, boolean T,boolean drawLine){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.T = T;
        this.drawLine = drawLine;
    }
    @Override
    public void paintComponent(Graphics g, Font font) {
        g.drawLine(x1,y1,x2,y2);
        if(drawLine) {
            if (T) {
                g.drawString("T", (x1 + x2) / 2 + 5, (y1 + y2) / 2 - 2);
            } else {
                g.drawString("H", (x1 + x2) / 2 - 15, (y1 + y2) / 2 - 2);
            }
        }
    }
}
