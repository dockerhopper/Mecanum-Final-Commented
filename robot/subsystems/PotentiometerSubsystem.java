// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

//import java.io.Serial;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PotentiometerSubsystem extends SubsystemBase {
    private final DriveSubsystem driveSubsystem;
    private final SerialPort arduinoSerial;
    private double potValue;
  
    public PotentiometerSubsystem(DriveSubsystem driveSubsystem) {
      this.driveSubsystem = driveSubsystem;
      arduinoSerial = new SerialPort(9600, SerialPort.Port.kUSB1, 8);
    }
  
    
  public void readPotentiometer() {
    String potValueString = arduinoSerial.readString();
    System.out.println(potValueString);
    try {
      //potValue = Double.parseDouble(potValueString);
    } catch (NumberFormatException e) {
      System.out.println("Invalid potentiometer value: " + potValueString);
    }
  }
    @Override
    public void periodic() {
      readPotentiometer();
      double xSpeed = 0.0;
      double ySpeed = 0.0;
      double rotation = mapPotValueToRotation(potValue);
      double forwardSpeed = mapPotValueToForwardSpeed(potValue);
      double strafeSpeed = mapPotValueToStrafeSpeed(potValue);
      xSpeed += forwardSpeed;
      ySpeed += strafeSpeed;
      driveSubsystem.driveCartesian(xSpeed, ySpeed, rotation);
    }
  
    private double mapPotValueToRotation(double potValue) {
      double minPotValue = 0;
      double maxPotValue = 1023;
      double minRotation = -1.0;
      double maxRotation = 1.0;
      double mappedRotation = ((potValue - minPotValue) / (maxPotValue - minPotValue)) * (maxRotation - minRotation) + minRotation;
      return mappedRotation;
    }
  
    private double mapPotValueToForwardSpeed(double potValue) {
      double minPotValue = 0;
      double maxPotValue = 1023;
      double minSpeed = -1.0;
      double maxSpeed = 1.0;
      double mappedSpeed = ((potValue - minPotValue) / (maxPotValue - minPotValue)) * (maxSpeed - minSpeed) + minSpeed;
      return mappedSpeed;
    }
  
    private double mapPotValueToStrafeSpeed(double potValue) {
      double minPotValue = 0;
      double maxPotValue = 1023;
      double minSpeed = -1.0;
      double maxSpeed = 1.0;
      double mappedSpeed = ((potValue - minPotValue) / (maxPotValue - minPotValue)) * (maxSpeed - minSpeed) + minSpeed;
      return mappedSpeed;
    }
  }
/*
public class PotentiometerSubsystem extends SubsystemBase {
    private final SerialPort serialPort;
    
    public PotentiometerSubsystem(int usbPortNumber) {
        serialPort = new SerialPort(9600, SerialPort.Port.kUSB, usbPortNumber);
    }

    public double getPotentiometerValue() {
        String potValueString = serialPort.readString();
        double potValue = Double.parseDouble(potValueString);
        return potValue;
    }
}
*/