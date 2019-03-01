import javax.swing.*;
import java.awt.*;

public class Node implements Shape{
    private double number;
    private State state;
    private int x;
    private int y;
    private int width = 20;
    private int height = 20;
    public Node(double number, int x, int y,State state){
        super();
        this.number = number;
        this.state = state;
        this.x = x;
        this.y = y;

    }

    @Override
    public void paintComponent(Graphics g,Font font) {
        // Draw Tree Here
        Rectangle rectangle = new Rectangle(x,y,width+10,height);
        g.drawRect(x, y, width+10, height);
        drawCenteredString(g,""+number,rectangle,font);
        drawState(x,y,g);
    }

    public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text, x, y);
    }

    public void drawState(int x, int y, Graphics g){
        int newX = x-(width+10);
        int newY = y-(height+10);
        g.drawRect(newX,newY,width,height);
        g.drawLine(newX + (width/2),newY,newX + (width/2),newY+height);
        g.drawLine(newX,newY+(height/2),newX+width,newY+(height/2));

        drawOvals(newX,newY,g);

    }

    public void drawOvals(int x,int y,Graphics g){
        Color color = g.getColor();

        if(state.moves[0][0] != null){
            setColor(g,state.moves[0][0]);
            g.fillOval(x,y,width/2,height/2);
        }
        if(state.moves[0][1] != null){
            setColor(g,state.moves[0][1]);
            g.fillOval(x,y+(height/2),width/2,height/2);
        }
        if(state.moves[0][2] != null){
            setColor(g,state.moves[0][2]);
            g.fillOval(x + (width/2),y+(height/2),width/2,height/2);
        }
        if(state.moves[1][0] != null){
            setColor(g,state.moves[1][0]);
            g.fillOval(x+(width/2),y,width/2,height/2);
        }
        g.setColor(color);
        g.drawString("State",x,y-2);
        g.setColor(color);
    }

    public void setColor(Graphics g, boolean b){
        if(b) {
            g.setColor(Color.GREEN);

        } else {
            g.setColor(Color.BLACK);
        }
    }



}
