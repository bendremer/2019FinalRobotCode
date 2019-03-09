// package frc.robot.control;

// import edu.wpi.first.wpilibj.Joystick;
// import frc.robot.Constants;

// public class StickControl extends IControl {
//     private static StickControl mInstance = null;

//     public static StickControl getInstance() {
//         if (mInstance == null)
//             mInstance = new StickControl();
//         return mInstance;
//     }

//     private Joystick mJoystick;

//     public StickControl() {
//         mJoystick = new Joystick(Constants.kDriveJoystickPort);
//     }

//     @Override
//     public double getSpeed() {
//         return (1 - mJoystick.getRawAxis(3)) / 2;
//     }

//     @Override
//     public double getForwardThrottle() {
//         return mJoystick.getRawAxis(1) * -1;
//     }

//     @Override
//     public double getRotationThrottle() {
//         return mJoystick.getRawAxis(0);
//     }

//     @Override
//     public boolean isChangeHatchGrabber() {
//         return mJoystick.getRawButtonPressed(6);
//     }

//     @Override
//     public boolean isChangeDrawSlide() {
//         return mJoystick.getRawButtonPressed(4);
//     }

//     @Override
//     public boolean isShootHatch() {
//         //return mJoystick.getRawButton(2);
//         return false;
//     }

//     @Override
//     public boolean isStartAuto() {
//         return mJoystick.getRawButton(2);
//     }

//     @Override
//     public boolean isEstop() {
//         return mJoystick.getRawButton(1);
//     }

//     @Override
//     public boolean isJustGoForward() {
//         return mJoystick.getRawButton(12);
//     }
// }