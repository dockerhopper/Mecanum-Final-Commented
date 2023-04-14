// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.fourWheelDrive;

public class WheelAuto extends CommandBase {
  /** Creates a new WheelAuto. */
  private final fourWheelDrive m_wheel;
  private final double speed;
  private final double duration;
  private double endTime;
  public WheelAuto(fourWheelDrive m_wheel, double speed, double duration) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_wheel = m_wheel;
    this.speed = speed;
    this.duration = duration;
    addRequirements(m_wheel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    endTime = Timer.getFPGATimestamp() + duration;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_wheel.wheelForward(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_wheel.wheelStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Timer.getFPGATimestamp() >= endTime;
  }
}
