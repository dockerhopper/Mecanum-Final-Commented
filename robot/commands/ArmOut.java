// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmExtension;

public class ArmOut extends CommandBase {
  /** Creates a new ArmOut. */
  private final ArmExtension arm;
  public ArmOut(ArmExtension arm) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.arm = arm;
    addRequirements(arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    arm.armOutC();
  }
   
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    arm.turnOff();
  }
}
