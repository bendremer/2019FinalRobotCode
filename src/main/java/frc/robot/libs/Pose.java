package frc.robot.libs;

public class Pose extends Vector implements Interpolable<Pose> {
    public double theta;
    
    /**
     * @param _theta in radians
     */
	public Pose(double _x,double _y,double _theta) {
		super(_x, _y);
		theta = _theta;
	}
	
	//TODO can change to constant curvature interpolation
	@Override
	public Pose interpolate(Pose other, double scale) {
		double dx = other.x - x;
		double dy = other.y - y;
		double dtheta = other.theta - theta;
		return new Pose(x + dx * scale, y + dy * scale, theta + dtheta * scale);
    }
    
    public double cosTheta(){
        return Math.cos(theta);
    }
    public double sinTheta() {
        return Math.sin(theta);
    }
}
