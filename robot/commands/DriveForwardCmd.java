// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DriveForwardCmd extends CommandBase {
  /** Creates a new DriveForwardCmd. */
  private final DriveSubsystem driveSubsystem;
  private final double speed;
  private final double duration;
  private double endTime;
  public DriveForwardCmd(DriveSubsystem driveSubsystem, double speed, double duration) {
    this.driveSubsystem = driveSubsystem;
    this.speed = speed;
    this.duration = duration;
    addRequirements(driveSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    endTime = Timer.getFPGATimestamp() + duration;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveSubsystem.driveCartesian(speed+0.1, 0, 0+0.01);
}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveSubsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Timer.getFPGATimestamp() >= endTime;
  }
}
