// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmExtension;

public class ArmOutAuto extends CommandBase {
  /** Creates a new ArmOutAuto. */
  private final ArmExtension arm;
  private final double speed;
  private final double duration;
  private double endTime;
  public ArmOutAuto(ArmExtension arm, double speed, double duration) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.speed = speed;
    this.duration = duration;
    this.arm = arm;
    addRequirements(arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    endTime = Timer.getFPGATimestamp() + duration;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    arm.armOutAuto(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    arm.turnOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Timer.getFPGATimestamp() >= endTime;
  }
}
