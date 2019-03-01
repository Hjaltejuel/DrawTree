import javax.swing.*;
import java.awt.*;

public class OverState implements Shape{

    private int depth;
    private States states;
    private int totalWidth;
    private static int deppestDepth = Integer.MIN_VALUE;
    public OverState(int depth, States states, int totalWidth){
        this.depth = depth;
        this.states =states;
        this.totalWidth = totalWidth;
        if(depth > deppestDepth){
            deppestDepth = depth;
        }
    }

    @Override
    public void paintComponent(Graphics g,Font font) {
        // Draw Tree Here
        g.drawString(states.getName(),15,(int)((totalWidth/(deppestDepth+1))*(depth))-30);



    }
}
