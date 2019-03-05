// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.subsystems;

// import edu.wpi.first.wpilibj.Joystick;
// import edu.wpi.first.wpilibj.PWMVictorSPX;
// import edu.wpi.first.wpilibj.SpeedController;
// import edu.wpi.first.wpilibj.command.Subsystem;
// import frc.robot.RobotMap;
// import frc.robot.commands.MecnumDriveCmd;
// import edu.wpi.first.wpilibj.drive.MecanumDrive;


// /**
//  * Add your docs here.
//  */
// public class MecanumDriver extends Subsystem {
//   // Put methods for controlling this subsystem
//   // here. Call these from Commands.
//   public static PWMVictorSPX frontLeft = new PWMVictorSPX(RobotMap.frontleft);
//   public static PWMVictorSPX backLeft = new PWMVictorSPX(RobotMap.backleft);
//   public static PWMVictorSPX frontRight = new PWMVictorSPX(RobotMap.frontright);
//   public static PWMVictorSPX backRight = new PWMVictorSPX(RobotMap.backright);
//   public static final MecanumDrive MecanumDriveBase = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
  
//   public void mecanumDrive(Joystick joystickZero){
//     MecanumDriveBase.setRightSideInverted(true);
//     MecanumDriveBase.driveCartesian(joystickZero.getX(), -joystickZero.getY(), joystickZero.getZ());
//     }


//   @Override
//   public void initDefaultCommand() {
//     setDefaultCommand(new MecnumDriveCmd());

//     // Set the default command for a subsystem here.
//     // setDefaultCommand(new MySpecialCommand());
//   }

// }
