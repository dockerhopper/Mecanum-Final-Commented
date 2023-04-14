// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.Constants.DriveTrainConstants;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.OIConstants;

public class DriveSubsystem extends SubsystemBase {
  // The controller used to drive the robot
  private final XboxController m_controller;

  // Constants for deadzone and deadband
  private static final double DEADZONE = 0.075;
  private static final double DEADBAND = 0.1;

  // The motors on the left side of the drive
  private final WPI_VictorSPX m_frontLeft = new WPI_VictorSPX(DriveTrainConstants.FRONT_LEFT);
  private final WPI_VictorSPX m_backLeft = new WPI_VictorSPX(DriveTrainConstants.BACK_LEFT);

  // The motors on the right side of the drive
  private final WPI_VictorSPX m_frontRight = new WPI_VictorSPX(DriveTrainConstants.FRONT_RIGHT);
  private final WPI_VictorSPX m_backRight = new WPI_VictorSPX(DriveTrainConstants.BACK_RIGHT);

  // The robot's drive, used for the methods such as stopMotor, and the drive cartesian method.
  private final MecanumDrive m_drive = new MecanumDrive(m_frontLeft, m_backLeft, m_frontRight, m_backRight);

  /** 
   * Creates a new DriveSubsystem.
   * @param controller the XboxController to use for driving
   */
  public DriveSubsystem(XboxController controller) {
    m_controller = controller;
  }

  /**
   * Drives the robot using arcade controls.
   * @param ySpeed Y directional speed (sideways)
   * @param xSpeed the commanded forward movement (forwards/backwards)
   * @param zRotation the commanded rotation
   */
  @Override
  public void periodic() {
    //The periodic function runs continously while the robot is on
    //Make sure that you don't run a function in an infinite loop or the robot will
    //run into undefined behaviour.

    // Get joystick input from controller
    double xSpeed = m_controller.getLeftX();
    double ySpeed = m_controller.getLeftY();
    double zRotation = m_controller.getRightX();

    // Apply deadzone and deadband to joystick input
    // Remember that these methods are read by the compiler from top to bottom.
    xSpeed = applyDeadzoneAndDeadband(xSpeed);
    xSpeed = xSpeed + DriveTrainConstants.VeerValue;
    ySpeed = applyDeadzoneAndDeadband(ySpeed);
    zRotation = applyDeadzoneAndDeadband(zRotation);

    // Drive the robot using mecanum drive
    m_drive.driveCartesian(-ySpeed, xSpeed, zRotation);
  }

  /**
   * Applies a deadzone and deadband to joystick input.
   * @param value the joystick input value
   * @return the modified joystick input value
   */

   //This method was used to reduce the drift when driving straight, it was impossible to drive straight correctly,
  //However, since the stick system is on a (1,1)interval axis you can almost make a sticky stick with the code here,
  //This simple method is very effective, I tried to make sure that it was readable, 
  
  //if you want a more compact version here
  /**
   * private double applyDeadzoneAndDeadband(double value) {
   * return (Math.abs(value) < DEADZONE || Math.abs(value) < DEADBAND) ? 0.0 : (value > 0 && value >= 0.90) ? 1.0 : (value < 0 && value <= -0.90) ? -1.0 : value;
   * }
   * Otherwise this method also works in the same way,
   */
  private double applyDeadzoneAndDeadband(double value) {
    if (Math.abs(value) < DEADZONE) {
      // If joystick input is within the deadzone, set it to 0.0
      return 0.0;
    } else if (Math.abs(value) < DEADBAND) {
      // If joystick input is within the deadband, set it to 0.0
      return 0.0;
    } else {
      // If joystick input is outside the deadzone and deadband, apply sticky behavior
      if (value > 0 && value >= 0.90) {
        // If joystick input is at maximum value, set it to 1.0
        return 1.0;
      } else if (value < 0 && value <= -0.90) {
        // If joystick input is at minimum value, set it to -1.0
        return -1.0;
      } else {
        // Otherwise, return the original joystick input value
        return value;
      }
    }
  }
  /**
   * Drives the robot using arcade controls.
   * @param ySpeed Y directional speed (sideways)
   * @param xSpeed the commanded forward movement (forwards/backwards)
   * @param rot the commanded rotation
   */
  /** The driveCartesian method takes in three double parameters: ySpeed, xSpeed, and rot. 
   *  These parameters represent the desired speed for the robot to move in the forward and sideways directions,
   *  as well as the desired rotational speed around its vertical axis. 
   *  The method then calls the driveCartesian method of the m_drive object (which is a MecanumDrive object), 
   *  passing in the negated ySpeed and xSpeed values (to reverse the direction of movement) and the original rot value.
   *  In other words, this method is used to drive the robot in a Cartesian coordinate system
   *  (where the x-axis represents forward and backward movement and the y-axis represents sideways movement) with mecanum wheels.
   *  The driveCartesian method of the MecanumDrive object then handles the translation of these inputs into individual motor control commands 
   *  that allow the robot to move in any direction and rotate around its vertical axis. 
   */
  public void driveCartesian(double ySpeed, double xSpeed, double rot){
    m_drive.driveCartesian(-ySpeed, -xSpeed, rot);
  }

  //STOP ALL MOTORS on the drive train 
  public void stop() {
    m_drive.stopMotor();
  }

  //This function could have been used for something, but didn't get enough time
  public double getDistance() {
    return 0.0;
  }

}