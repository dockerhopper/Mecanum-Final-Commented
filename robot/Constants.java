// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 * 
 * It is also good practice to put static variables that are used for functions such that you can 
 * show what your variables represent, instead of just a number.
 */
public final class Constants {
  public static final class DriveConstants {

    public static final boolean kGyroReversed = false;
    
    public static final double kTurnI = 0;
    public static final double kTurnD = 0;
    public static final double kTurnP = 1;

    public static final double kTurnToleranceDeg = 5;
    public static final double kTurnRateToleranceDegPerS = 1;
}

  public static final class OIConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static final class clawConstants{
    public static final int kClawSolenoidModule = 0;
    public static final int[] kClawSolenoidPorts = new int[] {4, 5};
    public static final int[] kClawSolenoidPortsOne = new int[] {6, 7};
  }

  public static final class DriveTrainConstants {
    public static final int FRONT_LEFT = 1;
    public static final int BACK_LEFT = 2;
    public static final int BACK_RIGHT = 3;
    public static final int FRONT_RIGHT = 4;
    // The amount to offset the steering to make the robot stay straight.
    public static final double VeerValue = 0.05;
  }

  public static final class ArmVerticalConstants {
    public static final int kElevator1 = 6;
    public static final int kElevator2 = 7;
    // Expressed as a percentage of 1
    public static final double armUpSpeed = 0.5;
    public static final double armDownSpeed = 0.5;
  }

  public static final int SERVO_ROT = 0;
  public static final int SERVO_UP = 1;
  public static final int ARM_OUT = 5;
  
  public static final int POT = 0;
  public static final int MOTOR_WHEEL = 8;
  public static final double extensionSpeed = 0.75;

}
