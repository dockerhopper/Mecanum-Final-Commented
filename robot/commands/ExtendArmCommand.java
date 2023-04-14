// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmExtension;

public class ExtendArmCommand extends CommandBase {

  private final double MAX_EXTEND_TIME_SECONDS = 5.0; // Change this to the maximum allowed time
  private final double EXTEND_SPEED = 0.5; // Change this to the desired speed
  private final ArmExtension armMotor;
  private final Timer timer;
  private double totalExtendTime=0.0;
  public double deltaTime=0.0;

  public ExtendArmCommand(ArmExtension armMotor) {
    this.armMotor = armMotor;
    this.timer = new Timer();
    addRequirements(armMotor);
  }

  @Override
  public void initialize() {
    if(totalExtendTime>0.0) {

    }
    timer.start();
    armMotor.armOutAuto(EXTEND_SPEED);
  }

  @Override
  public void execute() {
    double deltaTime = timer.get();
    totalExtendTime += deltaTime;
    timer.reset();
    armMotor.armOutAuto(EXTEND_SPEED);
  }

  @Override
  public boolean isFinished() {
    return totalExtendTime >= MAX_EXTEND_TIME_SECONDS;
  }

  @Override
  public void end(boolean interrupted) {
    armMotor.turnOff();
    deltaTime = totalExtendTime;
  }
}
