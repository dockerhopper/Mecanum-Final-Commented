// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class GyroSubsystem extends SubsystemBase {
  /** 
   * Creates a new GyroSubsystem. 
   * This is the beginning of the class definition. 
   * It starts with the package name and any imports needed for the class. 
   * Then, the class is defined as "GyroSubsystem" and it extends "SubsystemBase",
   * which is a built-in class in the WPILib library used in FRC robots.
   */
  
  /**
   * Here, a private instance variable is declared and initialized as an ADXRS450_Gyro object,
   * which is a class in the WPILib library used to access the gyro sensor. The SmartDashboard is also 
   * accessed to get a NetworkTableEntry 
   * object that will be used later to display the gyro readings
   * Declare an instance variable of type ADXRS450_Gyro
   */
  private final ADXRS450_Gyro gyro = new ADXRS450_Gyro();
  
  // Get a NetworkTableEntry object from the SmartDashboard for displaying the gyro readings
  NetworkTableEntry gyroEntry = SmartDashboard.getEntry("Gyro Reading: ");

  // Constructor method, doesn't do anything
  // This is a constructor method for the GyroSubsystem class, but it doesn't do anything since there are no parameters and no code within the method body.
  // but it doesn't do anything since there are no parameters and no code within the method body.
  public GyroSubsystem() {}

  // Reset the gyro sensor to zero called at the start of the startup so that the current heading of the robot is zero
  public void reset() {
    gyro.reset();
  }

  // Calibrate the gyro sensor for accurate readings
  public void calibrateAngle() {
    gyro.calibrate();
  }

  // Get the current angle of the robot as measured by the gyro sensor
  public double getAngle() {
    return gyro.getAngle() % 360; 
    /**This is the modulo operator, denoted by the % symbol, returns the remainder of dividing one number by another. 
     * For example, 7 % 3 would return 1, because 7 divided by 3 leaves a remainder of 1. Similarly, 
     * 10 % 4 would return 2, because 10 divided by 4 leaves a remainder of 2.
     *
     * Here's another way to think about it: if you have a certain number of items, 
     * and you want to divide them evenly into groups of a certain size, 
     * the modulo operator tells you how many items are left over that don't fit into a complete group. 
     * For example, if you have 7 apples and you want to divide them into groups of 3, 
     * you can make two complete groups of 3 apples, leaving 1 apple left over. So 7 % 3 would be 1.
     */
  }

  // Overridden method from SubsystemBase that is called periodically by the robot scheduler
  @Override
  public void periodic() {
    // Get the current angle from the gyro sensor
    double gyroAngle = gyro.getAngle();
    
    // Calculate the angle modulo 360 to ensure it is always between 0 and 359 degrees
    double mod = gyroAngle % 360;
    
    // Set the gyroEntry NetworkTableEntry object to the new angle value for display on the SmartDashboard
    gyroEntry.setDouble(mod);
  }
}
