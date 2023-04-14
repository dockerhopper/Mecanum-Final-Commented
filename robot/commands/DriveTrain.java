// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DriveTrain extends CommandBase {
  /** Creates a new DriveTrain. */
  private final DriveSubsystem driveSubsystem;
  private final DoubleSupplier y_Speed;
  private final DoubleSupplier x_Speed;
  private final DoubleSupplier Rot;
  public DriveTrain(DriveSubsystem driveSubsystem, DoubleSupplier ySpeed, DoubleSupplier xSpeed, DoubleSupplier rot) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.driveSubsystem = driveSubsystem;
    y_Speed = ySpeed;
    x_Speed = xSpeed;
    Rot = rot;
    addRequirements(driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveSubsystem.driveCartesian(y_Speed.getAsDouble(), x_Speed.getAsDouble(), Rot.getAsDouble());
    //double ySpeed, double xSpeed, double rot
  }

  public boolean isFinished() {
    return false; // Runs until interrupted
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
    driveSubsystem.driveCartesian(0, 0, 0);
  }

}
