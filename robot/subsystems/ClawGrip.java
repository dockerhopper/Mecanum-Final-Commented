// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.clawConstants;

public class ClawGrip extends SubsystemBase {
  /** Creates a new ClawGrip. */

  // Create a DoubleSolenoid object to control the claw's pneumatic solenoid
  private final DoubleSolenoid m_clawSolenoid =
        new DoubleSolenoid(
            PneumaticsModuleType.CTREPCM, // the type of the pneumatics module
            clawConstants.kClawSolenoidPorts[0], // the port number of the first solenoid
            clawConstants.kClawSolenoidPorts[1]); // the port number of the second solenoid

  // Method to close the claw
  public void close(){
    m_clawSolenoid.set(Value.kForward); // set the solenoid to forward to close the claw
  }

  // Method to open the claw
  public void open() {
    m_clawSolenoid.set(Value.kReverse); // set the solenoid to reverse to open the claw
  }

  // Method to create a command to grab the claw
  public CommandBase grabClaw() {
    // implicitly require `this`
    // create a new command that runs once and sets the solenoid to forward to grab the claw
    return this.runOnce(() -> m_clawSolenoid.set(Value.kForward));
  }

  // Method to create a command to release the claw
  public CommandBase releaseClaw() {
    // implicitly require `this`
    // create a new command that runs once and sets the solenoid to reverse to release the claw
    return this.runOnce(() -> m_clawSolenoid.set(Value.kReverse));
  }  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // Method to get the current state of the claw's solenoid,
  // whether that be set to the value, kReverse, or kForward
  public Value get() {
    return m_clawSolenoid.get();
  }




}
