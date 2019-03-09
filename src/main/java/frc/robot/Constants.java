package frc.robot;
public class Constants {
    public static final double DEFAULT_RAMP = 0.2; // cg
    public static final double MIN_TURN_SPEED = 0.09; // cg
    public static final double MAX_ALLOWED_ANGLE_ERROR = 3; // cg

    // OI
    public static final int kDriveJoystickPort = 0; // cg

    // camera
    public static final double CAMERA_YAW_OFFSET = 0; // the degree it turns right from the robot front  
    public static final double CAMERA_PITCH_OFFSET = 0; // 0deg is level, higher is positive
    public static final double CAMERA_ROW_OFFSET = 0; // TODO need a rotation matrix for a camera to row themselves
    public static final double CAMERA_HEIGHT = 800; // unit: mm

    // encoder
    public static final int ENCODER_PORT_1 = 4;  // check for the encoder parts
    public static final int ENCODER_PORT_2 = 5;
    public static final double ENCODER_DISTANCE_PER_PULSE = 1.925;
    
    //drive motors
    public static final int DRIVE_PEAK_CURRENT_DURANTION = 100; // unit: ms
    public static final int DRIVE_PEAK_CURRENT_LIMIT = 50; // unit: amps
    public static final int DRIVE_CONTINUOUS_CURRENT_LIMIT = 20; // unit: amps
    
    //drive physical
    public static final double DRIVE_WIDTH = 620;
    public static double TRACK_SCRUB_FACTOR = 0.924; // TODO check this
}
