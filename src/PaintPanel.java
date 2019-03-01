import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PaintPanel extends JPanel {

    public List<Shape> shapeList;

    PaintPanel(){
        super();
        shapeList = new ArrayList<Shape>();
    }

    public void addShape(Shape shape){
        shapeList.add(shape);
    }

    @Override
    public void paintComponent(Graphics g) {
        for(Shape panel : shapeList){
            panel.paintComponent(g,this.getFont());
        }
    }

}
