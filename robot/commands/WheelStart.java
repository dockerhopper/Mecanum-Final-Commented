// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.fourWheelDrive;

public class WheelStart extends CommandBase {
  private final fourWheelDrive wheel;
  private double speed = 0.0;
  /** Creates a new WheelStart. */
  public WheelStart(fourWheelDrive wheel) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.wheel = wheel;
    addRequirements(wheel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    speed=0.0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    for(double i=0; i<=100; i++){
      speed=speed + 0.0003;
      if(speed>=0.5){
        speed=0.5;
      }
      wheel.wheelForward(speed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    wheel.wheelStop();
  }


}
