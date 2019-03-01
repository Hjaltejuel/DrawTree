import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameState {

    public JFrame jFrame;
    public PaintPanel paintPanel;
    private int nodeWidth = 25;
    private int nodeHeight = 20;


    public  State result(State state, Action action,States states){
        if(state.moves[action.firstArrayD][action.secondArrayD] == null) {
            State state1 = state.copy();
            if(states==States.MAX) {
                state1.moves[action.firstArrayD][action.secondArrayD] = true;
            } else {
                state1.moves[action.firstArrayD][action.secondArrayD] = false;
            }
            return state1;
        }
        return null;
    }


    public  List<Action> actions(State state){
        List<Action> actions = new ArrayList<>();
        if(state.ishead){
            for(int i = 0; i <3 ; i++){
                if(state.moves[0][i] == null){
                    actions.add(new Action(0,i));
                }
            }
        } else {
            if(state.moves[1][0] == null){
                actions.add(new Action(1,0));
            }
        }
        return actions;
    }


    public double playRandomFirst(State state,int depth){
        State stateR = state.copy();
        stateR.ishead = true;
        double val = playMax(stateR,depth+1,(jFrame.getWidth()+75)/2,jFrame.getWidth(),75);
        paintPanel.addShape(new OverState(depth,States.RANDOM,jFrame.getHeight()));
        paintPanel.addShape(new Node(val,(jFrame.getWidth()+75)/2,height(depth),state));
        paintPanel.addShape(new Line((int) (((jFrame.getWidth()+75)/2)+(nodeWidth/2)),(int)(height(depth)+nodeHeight),(int)(((jFrame.getWidth()+75)/2) + (nodeWidth/2)), height(depth+1),false,true));
        jFrame.repaint();
        return val;

    }

    public  double playRandom(State state, int depth,int x, int xMax, int xLow){
        if(state.isFinished()){
            if(state.nextMove == States.MIN){
                paintPanel.addShape(new Node(1,x,height(depth),state));
                return 1;
            } else {
                paintPanel.addShape(new Node(-1,x,height(depth),state));
                return -1;
            }
        }

        State stateRCopy;
        State stateLCopy;
        double rval;
        double lval;
        int placementR = xLow + ((x-xLow)/2);
        int placementL = x+((xMax-x)/2);

        paintPanel.addShape(new Line(x+(nodeWidth/2),height(depth)+nodeHeight,placementL+(nodeWidth/2),height(depth+1),true,true));
        paintPanel.addShape(new Line(x+(nodeWidth/2),height(depth)+nodeHeight,placementR+(nodeWidth/2),height(depth+1),false,true));
        switch (state.nextMove){
            case MAX:
                stateRCopy = state.copy();
                stateRCopy.ishead = true;
                rval = playMax(stateRCopy,depth+1,placementR,x,xLow);
                stateLCopy = state.copy();
                stateLCopy.ishead = false;
                lval = playMax(stateLCopy,depth+1,placementL,xMax,x);
                double value = (lval + rval)/2;
                paintPanel.addShape(new OverState(depth,States.RANDOM,jFrame.getHeight()));
                paintPanel.addShape(new Node(value,x,height(depth),state));

                if(value < -2){
                    System.out.println("Eh");
                }
                return value;


            case MIN:
                stateRCopy = state.copy();
                stateRCopy.ishead = true;
                rval = playMin(stateRCopy,depth+1,placementR,x,xLow);
                stateLCopy = state.copy();
                stateLCopy.ishead = false;
                lval = playMin(stateLCopy,depth+1,placementL,xMax,x);
                double val = (rval+lval) /2;

                paintPanel.addShape(new OverState(depth,States.RANDOM,jFrame.getHeight()));
                paintPanel.addShape(new Node(val,x,height(depth),state));
                if(val < -2){
                    System.out.println("Eh");
                }
                return val;

        }

        return -500;




    }

    public int height(int depth){
        return ((jFrame.getHeight()/9)*depth)-30;
    }
    public  double playMin(State state, int depth,int x, int xMax, int xLow){
        paintPanel.addShape(new OverState(depth,States.MIN,jFrame.getHeight()));
        List<Action> actions = actions(state);
        if(actions.size() == 0 ){

            paintPanel.addShape(new Node(0,x,height(depth),state));
            return 0;
        }

        double v = Integer.MAX_VALUE;
        int xlowSub = xLow;
        double[] connections = new double[actions.size()];

        for(int i = 0 ; i< actions.size(); i++){
            State result = result(state,actions.get(i),States.MIN);
            if(result == null){

            }
            result.nextMove= States.MAX;
            int part = (xMax-xLow)/actions.size();
            int xMidSub = xlowSub+ (part/2);
            int xMaxSub = xlowSub+ part;
            connections[i] = xMidSub + nodeWidth/2;
            double val = playRandom(result,depth+1,xMidSub,xMaxSub,xlowSub);
            xlowSub +=part;
            v = Math.min(val,v);
        }
        for(int j = 0; j< connections.length; j++){
            paintPanel.addShape(new Line(x+(nodeWidth/2),height(depth)+nodeHeight,(int)connections[j],height(depth+1),!state.ishead,false));
        }

        paintPanel.addShape(new Node(v,x,height(depth),state));

        return v;

    }

    public double playMax(State state, int depth,int x, int xMax, int xLow){
        paintPanel.addShape(new OverState(depth,States.MAX,jFrame.getHeight()));
        List<Action> actions = actions(state);
       if(actions.size() == 0 ){

           paintPanel.addShape(new Node(0,x,height(depth),state));
           return 0;
       }

        double v = Integer.MIN_VALUE;
        int xlowSub = xLow;
        double[] connections = new double[actions.size()];

        for(int i = 0 ; i< actions.size(); i++){
            State result = result(state,actions.get(i),States.MAX);
            result.nextMove= States.MIN;
            int part = (xMax-xLow)/actions.size();
            int xMidSub = xlowSub+ (part/2);
            int xMaxSub = xlowSub+ part;
            connections[i] = xMidSub + nodeWidth/2;
            double val = playRandom(result,depth+1,xMidSub,xMaxSub,xlowSub);
            xlowSub +=part;
            v = Math.max(val,v);
        }
        for(int j = 0; j< connections.length; j++){
            paintPanel.addShape(new Line(x+(nodeWidth/2),height(depth)+nodeHeight,(int)connections[j],height(depth+1),!state.ishead,false));
        }

        paintPanel.addShape(new Node(v,x,height(depth),state));

        return v;
    }

    public  double play(){
        State state = new State();
        state.nextMove = States.MAX;
        return playRandomFirst(state,1);
    }

    public GameState (){

        jFrame = new JFrame("Tree");
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setUndecorated(true);
        jFrame.setVisible(true);
        paintPanel =new PaintPanel();
        jFrame.add(paintPanel);
        jFrame.repaint();


        System.out.println(play());
        Scanner s = new Scanner(System.in);
        s.next();
    }


}

