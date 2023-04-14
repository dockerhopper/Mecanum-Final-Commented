// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawGrip;

public class OpenSolenoidCommand extends CommandBase {
  private final ClawGrip clawGrip;
  private final Timer timer;

  public OpenSolenoidCommand(ClawGrip clawGrip) {
      this.clawGrip = clawGrip;
      this.timer = new Timer();
      addRequirements(clawGrip);
  }

  @Override
  public void initialize() {
      clawGrip.open();
      timer.reset();
      timer.start();
  }

  @Override
  public void execute() {
  }

  @Override
  public boolean isFinished() {
    clawGrip.close();
    return timer.hasElapsed(3);
      
  }
}
