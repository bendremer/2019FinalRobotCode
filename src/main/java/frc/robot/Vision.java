package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.libs.Pose;
import frc.robot.libs.Vector;

public class Vision {
    private static final double TARGET_HEIGHT = 800;

    NetworkTableInstance ntinst;
    NetworkTable outputTable;
    NetworkTableEntry leftXEntry, leftYEntry, rightXEntry, rightYEntry, RPITimeEntry, timestampEntry, 
        outputCntEntry, leftSizeEntry, rightSizeEntry;
    public boolean RPIConnection = false;
    public boolean TargetFound = false;
    public double timestamp; // unit: sec (from roboRio)
    public Vector tgtL, tgtR; // two tapes
    public double angleToTarget, tgtHDis; // the distance between the targets

    private double updatedRPITime, deltaTime; // unit: sec, deltaTime = piCurrentTime - timestamp
    private double[] leftX, leftY, rightX, rightY, sizeL, sizeR;
    private double outputCnt;
    private int reconnectCnt;

    public Vision() {
        ntinst = NetworkTableInstance.getDefault();
        outputTable = ntinst.getTable("PIOutput"); 
        leftXEntry = outputTable.getEntry("leftX");
        leftYEntry = outputTable.getEntry("leftY");
        rightXEntry = outputTable.getEntry("rightX");
        rightYEntry = outputTable.getEntry("rightY");
        leftSizeEntry = outputTable.getEntry("sizeL");
        rightSizeEntry = outputTable.getEntry("sizeR");
        RPITimeEntry = outputTable.getEntry("PITime");
        timestampEntry = outputTable.getEntry("timestamp");
        outputCntEntry = outputTable.getEntry("outputCnt");
    }

    public void run() {
        reconnectRPI();
        if (!RPIConnection){
            TargetFound = false;
            dashboardDisplay();
            return;
        }
        double curOutputCnt = outputCntEntry.getDouble(-1);

        // the sleep time interval
        if (Math.abs(curOutputCnt - outputCnt) > 0.99)
            getTarget();
        outputCnt = curOutputCnt;
        dashboardDisplay();
    }

    private void dashboardDisplay() {
        SmartDashboard.putBoolean("RPiConnection", RPIConnection);
        SmartDashboard.putBoolean("TargetFoundStatus", TargetFound);
        SmartDashboard.putNumber("Timestamp", timestamp);
        SmartDashboard.putNumber("deltaTime", deltaTime);
        SmartDashboard.putNumber("Tgt_Horizontal_Distance", tgtHDis);
    }

    /**
     * network table getting values 
     */
    private void getTarget() {
        timestamp = timestampEntry.getDouble(-1000) / 1000 - deltaTime;
        leftX = leftXEntry.getDoubleArray(new double[0]);
        leftY = leftYEntry.getDoubleArray(new double[0]);
        rightX = rightXEntry.getDoubleArray(new double[0]);
        rightY = rightYEntry.getDoubleArray(new double[0]);
        sizeL = leftSizeEntry.getDoubleArray(new double[0]);
        sizeR = rightSizeEntry.getDoubleArray(new double[0]);
        
        TargetFound = (leftX.length == 1); // if left length is not 1, we continue searching TODO fix
        if (TargetFound = false)
            return;
        
        tgtL = getTargetPose(leftX[0], leftY[0]);
        tgtR = getTargetPose(rightX[0], rightY[0]);
        angleToTarget = (leftX[0] + rightX[0]) / 2;
        tgtHDis = rightX[0] - leftX[0];
       
        double l_to_r = sizeL[0]/sizeR[0];
       
        /**
         * 2 ways to rid implausible tape images
         */
        if(l_to_r > 1.1){
            angleToTarget += 1;
            System.out.println("Left tape is 1.1x right size. Implausible. ");
            if(l_to_r > 1.2){
                angleToTarget += 1.5;
                System.out.println("Left tape is 1.2x right size. Implausible. ");
                if(l_to_r > 1.3){
                angleToTarget += 1.5;
                System.out.println("Left tape is 1.3x right size. Implausible. ");
                }
            }
        }   
        else if(l_to_r < 0.91) {
            angleToTarget -= 1;
            System.out.println("Right tape is 1.1x left size. Implausible.");
            if (l_to_r < 0.8) {
                angleToTarget -= 1;
                System.out.println("Right tape is 1.2x left size. Implausible.");
                if (l_to_r < 0.7) {
                    angleToTarget -= 1.5;
                    System.out.println("Right tape is 1.3x left size. Implausible.");
                }
            }
        }
        // System.out.printf("angle %.1f\n", targetDif);
    }

    /**
     * RPI need to update the nwtable in 40ms to be considered reconnected;
     * RPI need to differ from the Roborio time for 150 ms to be considerd disconnected
     * @return boolean of connection
     */
    private boolean reconnectRPI() {
        double curRPiTime = RPITimeEntry.getDouble(-1000) / 1000;
        if (curRPiTime < 0) {
            RPIConnection = false;
            return false;
        }
        if (RPIConnection = false) { 
            reconnectCnt ++;
            if (reconnectCnt == 1)
                updatedRPITime = curRPiTime;
            else if (updatedRPITime != curRPiTime) {
                RPIConnection = true;
                outputCnt = outputCntEntry.getDouble(0);
                deltaTime = updatedRPITime - Robot.time.get();
                System.out.printf("RPI has been reconnected; the last blank interval is %f. \n", deltaTime);
            } else if (reconnectCnt == 3)
                reconnectCnt = 0;
            if (RPIConnection = false)
                return false;
        }

        updatedRPITime = curRPiTime;

        if (Math.abs(deltaTime - (updatedRPITime - Robot.time.get())) > 0.15) {
            System.out.printf("RPI has been disconnected inferred from its time. \n");
            RPIConnection = false;
            reconnectCnt = 0;
            return false;
        }
        return true;
    }
    
    /**  TODO needs to be configured
     *  yaw for angle, pitch for distance
     */
    private Vector getTargetPose(double yawAngle, double pitchDis) {
        pitchDis += Constants.CAMERA_PITCH_OFFSET;
        yawAngle += Robot.robotState.getAngle() + Constants.CAMERA_YAW_OFFSET; // compute the actual angle 
        double distance = (TARGET_HEIGHT - Constants.CAMERA_HEIGHT) / Math.tan(Math.toRadians(pitchDis));
        Pose currentPose = Robot.robotState.getCurrentPose();
        return new Vector(currentPose.x + distance * Math.cos(Math.toRadians(yawAngle)),
                currentPose.y + distance * Math.sin(Math.toRadians(yawAngle))); // x + dx, y + dy 
    }
}
