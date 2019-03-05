/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.Autofront;
import frc.robot.commands.DriveForwardCmd;
import frc.robot.commands.ElevatorTiltCmd;
import frc.robot.commands.ElevatorWinchCmd;
import frc.robot.commands.FrontDownCmd;
import frc.robot.commands.FrontUpCmd;
// import frc.robot.commands.PushoutCmd;
import frc.robot.commands.PushoutCmd;
// import frc.robot.commands.DriveForwardCmd;
import frc.robot.commands.RollinCmd;
import frc.robot.commands.RolloutCmd;
// import frc.robot.commands.RotateCmd;
import frc.robot.commands.RotateCmd;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);
  public Joystick joystickZero = new Joystick(0);
  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.
  Button trigger = new JoystickButton(joystickZero, 1);
  Button thumb = new JoystickButton(joystickZero, 2);
  public Button three = new JoystickButton(joystickZero, 3);
  public Button four = new JoystickButton(joystickZero, 4);
  public Button five = new JoystickButton(joystickZero, 5);
  public Button six = new JoystickButton(joystickZero, 6);
  public Button seven = new JoystickButton(joystickZero, 7);
  Button eight = new JoystickButton(joystickZero, 8);
  public Button nine = new JoystickButton(joystickZero, 9);
  Button ten = new JoystickButton(joystickZero, 10);
  public Button eleven = new JoystickButton(joystickZero, 11);
  Button twelve = new JoystickButton(joystickZero, 12);
  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  public OI() {
    // Elevator Tilt
    //eight.whenPressed(new ElevatorTiltCmd(Value.kForward)); //TILT COMMANDS, WORKING
    //ten.whenPressed(new ElevatorTiltCmd(Value.kReverse)); //TILT COMMANDS, WORKING
    //eleven.whenPressed(new ElevatorWinchCmd(10688));
    //twelve.whenPressed(new ElevatorWinchCmd(-7168));
    //nine.whenPressed(new ElevatorWinchCmd(7104));
    //ten.whenPressed(new ElevatorWinchCmd(3584));
    //seven.whenPressed(new ElevatorWinchCmd(4950));
    
    //Intake
    thumb.whileHeld(new RollinCmd());
    trigger.whileHeld(new RolloutCmd());
    
    //Front Climber
    seven.whileHeld(new FrontUpCmd());
    eight.whileHeld(new FrontDownCmd());

    //Winch Height
    eleven.whenPressed(new ElevatorWinchCmd(0));
    //three.whenPressed(new Autofront());
    twelve.whenPressed(new ElevatorWinchCmd(1000)); //CHANGE TO CALCULATED VALUE HEIGHTS 
    nine.whenPressed(new ElevatorWinchCmd(2500));
    ten.whenPressed(new ElevatorWinchCmd(2750));

    /*  COMMANDS
        Buttons - 13
      FrontUpCmd() - Climbing, lowers climbing legs
      FrontDownCmd() - Climbing, raises climbing legs
      ElevatorWinchCmd(0) - Raises elevator to said calculated height
        ElevatorWinchCmd(0) -1 Lowest
        ElevatorWinchCmd(0) -2
        ElevatorWinchCmd(0) -3
        ElevatorWinchCmd(0) -4
        ElevatorWinchCmd(0) -5
        ElevatorWinchCmd(0) -6
        ElevatorWinchCmd(0) -7
        ElevatorWinchCmd(0) -8
        ElevatorWinchCmd(0) -9     
      ElevatorTiltCmd(Value.kForward) - Tilts elevator forward
      ElevatorTiltCmd(Value.kReverse) - Tilts elevator backward
      eleven.whenPressed(new RotateCmd(25) - Rotates robot said degrees
      PushoutCmd() - Ejects the hatch panel
    */
    
    // Rotate and Drive Commands
    //eleven.whenPressed(new RotateCmd(25)); 
    //twelve.whenPressed(new RotateCmd(-25)); 
    
    // Hatch Release Command
    six.whenPressed(new PushoutCmd());

    // PLEASE KEEP THIS COMMENTED FOR NOW

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());
  }
  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}
