// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.fourWheelDrive;

public class WheelToggle extends CommandBase {
  /** Creates a new WheelToggle. */
  private final fourWheelDrive wheel;

  public WheelToggle(fourWheelDrive wheel) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.wheel = wheel;
    addRequirements(wheel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  @Override
  //All this does is get the current value of the solenoid on the wheel 
  //If that equals the forward value of the solenoid, it will release the wheel,
  // else the wheel remains upwards
  public void execute() {
    if(wheel.get() == Value.kForward) {
      wheel.releaseWheel();
    } else {
      wheel.upWheel();
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
