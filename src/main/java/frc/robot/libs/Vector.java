package frc.robot.libs;

public class Vector {
    public double x,y;
    public Vector(double _x, double _y){
        x=_x;
        y=_y;
    }

    public double distance(Vector other){
        return Math.sqrt((x-other.x)*(x-other.x)+(y-other.y)*(y-other.y));
    }
}
