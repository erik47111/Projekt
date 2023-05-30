package Kinggames.game.gameui.state;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.StringJoiner;
public class State  {
    public static final int red = 1;
    public static final int blue = 2;
    public static final int Floor = 0;

    public static final int Destroyed_floor = 3;


    public  int[][] current_state = {
            {0, 0, 0, 0, 0, 0, 0, 0, },
            {0, 0, 0, 0, 0, 0, 0, 0, },
            {0, 0, 0, 0, 0, 0, 0, 2, },
            {1, 0, 0, 0, 0, 0, 0, 0, },
            {0, 0, 0, 0, 0, 0, 0, 0, },
            {0, 0, 0, 0, 0, 0, 0, 0, },

    };

    public int[][] getCurrent_state() {
        return current_state;
    }

    public Position position;
    public boolean canMove(Position position,Direction direction) {
        return switch (direction) {
            case UP -> canMoveUp(position);
            case RIGHT -> canMoveRight(position);
            case DOWN -> canMoveDown(position);
            case LEFT -> canMoveLeft(position);
        };



}

    private boolean canMoveLeft(Position position) {
       if(position.row()>0 && position.row()-1==0){
           System.out.println("true");
           return true;

       }else {
           System.out.println("false");
           return false;
       }
    }

    private boolean canMoveDown(Position position) {
        if(position.col()<5 && position.col()+1==0){
            System.out.println("true");
            return true;

        }else {
            System.out.println("false");
            return false;
        }
    }

    private boolean canMoveRight(Position position) {
        if(position.row()<7 && position.row()+1==0){
            System.out.println("true");
            return true;

        }else {
            System.out.println("false");
            return false;
        }
    }

    private boolean canMoveUp(Position position) {
        if(position.col()>0 && position.col()-1==0){
            System.out.println("true");
            return true;

        }else {
            System.out.println("false");
            return false;
        }







}}

