/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */



public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;
  //

  //Drive Motors
  public static int left1 = 2;
  public static int left2 = 3;
  public static int right1 = 0;
  public static int right2 = 1;

  //Intake Motors
  public static int armLeft = 4;
  public static int armRight = 5;

  //Climb Motors
  public static int climbFront = 6;

  //Winch/Elevator Motor
  public static int elevator = 7; //Moved to 7 when Front Climb motor was added

  //Drive Encoders
  public static int rightEncoderPort1 = 0;
  public static int rightEncoderPort2 = 1;
  public static int leftEncoderPort1 = 2;
  public static int leftEncoderPort2 = 3;

  //Elevator Winch Encoder
  public static int elevatorEncoderPort1 = 4;
  public static int elevatorEncoderPort2 = 5;

  //Intake Limit Switch
    public static int limit = 7;

    //Solenoid Valves for Pneumatics
  public static int eleSole1 = 1;
  public static int eleSole2 = 0;
  public static int hatchSole3 = 3;
 

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
