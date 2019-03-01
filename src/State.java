import java.util.Arrays;
import java.util.Random;

public class State {
    public Boolean[][] moves;
    public States states;
    public States nextMove;
    public boolean ishead;

    public State(){
        moves = new Boolean[][]{{null,null,null},{null}};
        states = States.RANDOM;
        nextMove = States.MAX;
        ishead = false;
    }

    public State(Boolean[][] moves, States states, States nextMove, boolean ishead){
        this.moves  = new Boolean[][]{{null,null,null},{null}};
        copyArray(moves);
        this.states = states;
        this.nextMove = nextMove;
        this.ishead = ishead;
    }

    public boolean isFinished(){
        return moves[0][0] != null && moves[0][0] && moves[0][1] != null && moves[0][1] || moves[0][0] != null && moves[0][1] != null &&  !moves[0][0] && !moves[0][1] ||
                moves[0][1] != null && moves[0][2] != null && moves[0][1] && moves[0][2] || moves[0][1] != null && moves[0][2] != null &&  !moves[0][1] && !moves[0][2] ||
                moves[0][0] != null && moves[1][0] != null && moves[0][0] && moves[1][0] ||moves[0][0] != null && moves[1][0] != null &&   !moves[0][0] && !moves[1][0] ||
                moves[0][1] != null && moves[1][0] != null && moves[0][1] && moves[1][0] ||moves[0][1] != null && moves[1][0] != null &&  !moves[0][1] && !moves[1][0] ||
                moves[0][2] != null && moves[1][0] != null && moves[0][2] && moves[1][0] || moves[0][2] != null && moves[1][0] != null &&  !moves[0][2] && !moves[1][0];
    }



    public void copyArray(Boolean[][] moves){
        for(int i = 0 ; i<moves.length;i++){
            for(int j = 0; j<moves[i].length;j++){
                this.moves[i][j] = moves[i][j];
            }
        }
    }

    public State copy(){
        return new State(moves,states,nextMove,ishead);
    }


}
