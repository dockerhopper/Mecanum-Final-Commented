// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

//just importing librarys
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.clawConstants;


public class fourWheelDrive extends SubsystemBase {
  /** 
   * Creates a new Wheels
   * This subsystem was designed for the plank balancing section for the robot
   * The solenoids would shoot out and bring the robot upwards and then shoot down to 
   * remain in a neutral position.
   * The motor was in control of the rubber wheels used to bring the robot up
   */
  
  // Constructor method, doesn't do anything
  public fourWheelDrive() {}

  // Define motor and solenoid objects for the subsystem
  private final WPI_VictorSPX motorWheel = new WPI_VictorSPX(Constants.MOTOR_WHEEL);
  private final DoubleSolenoid m_clawSolenoidOne =
      new DoubleSolenoid(
          PneumaticsModuleType.CTREPCM,
          clawConstants.kClawSolenoidPortsOne[0],
          clawConstants.kClawSolenoidPortsOne[1]);

  // Command methods for controlling the solenoid
  public void releaseWheel() {
    // Set solenoid value to reverse to release the wheel
    m_clawSolenoidOne.set(Value.kReverse);
  }

  public void upWheel() {
    // Set solenoid value to forward to lift the wheel
    m_clawSolenoidOne.set(Value.kForward);
  }

  // Methods for controlling the wheel motor
  public void wheelForward(double speed){
    // Set motor speed to move the wheel forward
    motorWheel.set(speed);
  }

  public void wheelReverse(double speed){
    // Set motor speed to move the wheel backward
    motorWheel.set(speed);
  }

  public void wheelStop(){
    // Stop the wheel motor
    motorWheel.stopMotor();
  }

  // Method for getting the current solenoid value
  public Value get() {
    return m_clawSolenoidOne.get();
  }

  // Command methods for controlling the solenoid using WPILib Commands
  public CommandBase grabWheel() {
    // Return a Command that sets the solenoid value to reverse to grab the wheel
    return this.runOnce(() -> m_clawSolenoidOne.set(Value.kReverse));
  }

  public CommandBase releaseWheels() {
    // Return a Command that sets the solenoid value to forward to release the wheel
    return this.runOnce(() -> m_clawSolenoidOne.set(Value.kForward));
  }

  // Overridden method from SubsystemBase that is called periodically by the robot scheduler
  @Override
  public void periodic() {
    // This method doesn't do anything, it is here just to satisfy the requirements of the SubsystemBase class.
    // If this subsystem needs to do periodic tasks, they can be added here.
    //such as doing a task over and over again really fast, the fastest it can go is 5ms increments
  }
}
