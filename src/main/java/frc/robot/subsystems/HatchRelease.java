/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Robot;
import frc.robot.RobotMap;

// Port 0,1 is hatch, kReverse means open
// Port 2,3 is slide, kReverse means slide forward

public class HatchRelease extends Subsystem {  
    public DoubleSolenoid hatchPusher = new DoubleSolenoid(RobotMap.eleSole1, RobotMap.eleSole2);
    public Solenoid oneHatchPusher = new Solenoid(RobotMap.hatchSole3);
    
    public void pushAway(){
        oneHatchPusher.set(true);
    }

    public boolean hatchout(){
    if(Robot.hatchRelease.oneHatchPusher.get() == true)
        return true;
    return false;
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    public void pushBack() {
        oneHatchPusher.set(false);
    }



}