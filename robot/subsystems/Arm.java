// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;



public class Arm extends SubsystemBase {
  /** Creates a new Arm. */

  // Motor controllers for the arm
  private final WPI_VictorSPX m_Motor1 = new WPI_VictorSPX(Constants.ArmVerticalConstants.kElevator1);
  private final WPI_VictorSPX m_Motor2 = new WPI_VictorSPX(Constants.ArmVerticalConstants.kElevator2);
  
  // Xbox controller object for input
  private final XboxController m_controller;

  /*This code defines a class constructor for an "Arm" class, which takes an instance of the "XboxController" class as a parameter.
    The constructor initializes a member variable "m_controller" with the value of the "controller" parameter. 
    This allows the "Arm" class to access and use the buttons and axes of the Xbox controller passed to it through the "controller" parameter,
     in order to control the arm's movement or perform other related tasks. */ 
  public Arm(XboxController controller) {
    m_controller = controller;
  }
  
  @Override
  public void periodic() {
    // Get the current trigger axis values,
    //Just returns the
    double lSpeed = m_controller.getLeftTriggerAxis();
    double rSpeed = m_controller.getRightTriggerAxis();

    // Have m_Motor2 follow m_Motor1, so they are the same speed
    m_Motor2.follow(m_Motor1);

    // Set motor speed based on trigger axis values
    /**This is a ternary operator expression that checks if lSpeed is greater than rSpeed, 
     * then sets the motor speed to -lSpeed * Constants.ArmVerticalConstants.armUpSpeed if true. 
     * If false, it checks if rSpeed is greater than or equal to lSpeed, 
     * then sets the motor speed to rSpeed * Constants.ArmVerticalConstants.armDownSpeed if true. 
     * If both conditions are false, it sets the motor speed to 0. */
    m_Motor1.set(lSpeed > rSpeed ? -lSpeed * Constants.ArmVerticalConstants.armUpSpeed : rSpeed >= lSpeed ? rSpeed * Constants.ArmVerticalConstants.armDownSpeed : 0);
    
    //Readable code
    /** 
      if (lSpeed > rSpeed) { 
        m_Motor1.set(-lSpeed * Constants.ArmVerticalConstants.armUpSpeed);
    } else if (rSpeed >= lSpeed) {
        m_Motor1.set(rSpeed * Constants.ArmVerticalConstants.armDownSpeed);
    } else {
        m_Motor1.set(0);
    }
    */
  }

  // Method to move arm down at a specified speed
  public void armDown(double speed) {
    m_Motor1.set(speed);
  }

  // Method to stop the arm motor
  public void stop() {
    m_Motor1.stopMotor();
  }
}