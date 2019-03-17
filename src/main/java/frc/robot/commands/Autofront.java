/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class Autofront extends CommandGroup {
  /**
   * Add your docs here.
   */
  public Autofront() {
      
    addSequential(new DriveForwardCmd(60,.6)); //Don't know for center
    //addSequential(new DriveForwardCmd(2,.5)); //Use this for testing
    //addSequential(new RotateCmd(90));
    //addSequential(new ResetElevatorEncoderCommand());
    //addSequential(new ElevatorWinchCmd(450));
    //addSequential(new YeetCargoCmd());
    //addParallel(new DriveBackwardCmd(8,.5));
    addSequential(new ElevatorTiltCmd(Value.kForward));
    addSequential(new WaitCommand(2));
    addSequential(new ElevatorDownResetCmd());
    addSequential(new ElevatorWinchCmd(150));
    
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
