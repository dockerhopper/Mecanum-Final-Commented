// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmExtension extends SubsystemBase {

  // Declares a new WPI_VictorSPX object for controlling the arm motor
  private final WPI_VictorSPX armOut = new WPI_VictorSPX(Constants.ARM_OUT);

  // Constructor for the class, which is called when a new instance is created
  public ArmExtension() {}

  // Method for extending the arm inward
  public void ArmIn() {
    armOut.set(-Constants.extensionSpeed); // Sets the speed of the motor to a negative value
  }

  // Method for extending the arm outward
  public void armOutC(){
    armOut.set(Constants.extensionSpeed); // Sets the speed of the motor to a positive value
  }

  // Method for extending the arm outward with a custom speed
  public void armOutAuto(double speed) {
    armOut.set(speed); // Sets the speed of the motor to the input value
  }

  // Method for retracting the arm inward with a custom speed
  public void armInAuto(double speed) {
    armOut.set(speed); // Sets the speed of the motor to the input value
  }

  // Method for stopping the motor
  public void turnOff(){
    armOut.stopMotor(); // Stops the motor
  }
}
