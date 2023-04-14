// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Gyro extends SubsystemBase {
  /** 
   * Creates a new Gyro. 
   */
  
  // Constructor method, doesn't do anything
  public Gyro() {}

  // Overridden method from SubsystemBase that is called periodically by the robot scheduler
  @Override
  public void periodic() {
    // This method doesn't do anything, it is here just to satisfy the requirements of the SubsystemBase class.
    // If this subsystem needs to do periodic tasks, they can be added here.
    // For example, a subclass could override this method to periodically read data from a gyro sensor
    // and update instance variables that store the current orientation of the robot.
  }
}
