package frc.robot.libs;

public class MotionPose extends Pose{
	double v,a,omiga,alpha,time;
	
	public MotionPose(double _x, double _y, double _theta) {
		super(_x, _y, _theta);
		v = a = omiga = alpha = time = 0;
	}
	
}
