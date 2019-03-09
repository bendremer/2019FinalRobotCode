package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.libs.*;
import edu.wpi.first.wpilibj.Encoder;

public class RobotState {
    private static final int MAX_HISTORY_SIZE = 400;
    private static RobotState instance = new RobotState();

    public static RobotState getInstance() {
        return instance;
    }

    private RobotState() {
        ahrs = new AHRS(SerialPort.Port.kMXP);
        ahrs.enableLogging(true); // TODO this may be unnecessary
        encoder = new Encoder(Constants.ENCODER_PORT_1, Constants.ENCODER_PORT_2, false, Encoder.EncodingType.k4X); // TODO cg the encoder type
        encoder.setDistancePerPulse(Constants.ENCODER_DISTANCE_PER_PULSE);
        reset(Robot.time.get(), new Pose(0, 0, 90));
    }

    private AHRS ahrs;
    private Encoder encoder;
    private double lastEncoderValue;
    private InterpolatingTreeMap <InterpolatingDouble, Pose> poseHistory; // the treemap that stores all history poses

    public void reset(double startTime, Pose startPose) {
        poseHistory = new InterpolatingTreeMap <InterpolatingDouble, Pose> (MAX_HISTORY_SIZE);
        poseHistory.put(new InterpolatingDouble(startTime), startPose);
        ahrs.reset();
        ahrs.setAngleAdjustment(startPose.theta);   // angle
        encoder.reset();
        lastEncoderValue = 0;
    }

    /**
     *  get the absolute direction with respect to the game field
     */
    public double getAngle() {
        return 2 * ahrs.getAngleAdjustment() - ahrs.getAngle();
    }

    public Pose getCurrentPose() {
        return poseHistory.lastEntry().getValue();
    }

    public Pose getPastPose(double timestamp) {
        return poseHistory.getInterpolated(new InterpolatingDouble(timestamp));
    }

    /**
     * compensating error
     */
    public void periodic() {
        Pose lastPose = getCurrentPose();
        double distanceFI = encoder.getDistance() - lastEncoderValue;
        addObservation(Robot.time.get(), new Pose(lastPose.x + distanceFI * Math.cos(Math.toRadians(getAngle())), 
            lastPose.y + distanceFI * Math.sin(Math.toRadians(getAngle())), getAngle()));
            // (x + dx, y + dy)
        lastEncoderValue = encoder.getDistance();
        dashboardDisplay();
    }

    /**
     * for observation
     * @param timestamp
     * @param observation
     */
    private void addObservation(double timestamp, Pose observation) {
        poseHistory.put(new InterpolatingDouble(timestamp), observation);
    }
    
    /**
     * display the current pose
     */
    private void dashboardDisplay() {
        Pose pose = getCurrentPose();
        SmartDashboard.putNumber("x", pose.x);
        SmartDashboard.putNumber("y", pose.y);
        SmartDashboard.putNumber("theta", pose.theta);
    }
}
