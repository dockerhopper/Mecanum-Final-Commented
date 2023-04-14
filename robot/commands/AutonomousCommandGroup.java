// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.ArmExtension;
import frc.robot.subsystems.ClawGrip;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.fourWheelDrive;
import pabeles.concurrency.IntOperatorTask.Max;
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonomousCommandGroup extends SequentialCommandGroup {
  private final DriveSubsystem driveSubsystem;
  private final Arm arm;
  private final ArmExtension armOut;
  private final ClawGrip claw;
  private final fourWheelDrive fourWheel;


  /** Creates a new AutonomousCommandGroup. */
  public AutonomousCommandGroup(DriveSubsystem driveSubsystem, Arm arm, ArmExtension armOut, ClawGrip claw, fourWheelDrive fourWheel) {
    this.driveSubsystem = driveSubsystem;
    this.arm = arm;
    this.armOut = armOut;
    this.claw = claw;
    this.fourWheel = fourWheel;

    //double BackwardDuration = 3;
    //double Acceleration = 0.1;
    
    addCommands(
      new ClawAutoClose(claw),
      new ArmOutAuto(armOut, .75, 1.9),
      new ArmDownAuto(arm,-0.6, 0.2),
      new WaitCommand(1),
      new ClawSolenoidAuto(claw),
      new DriveForwardCmd(driveSubsystem, 1, 5)
      /*new DriveForwardCmd(driveSubsystem, -0.3, Acceleration),
      new DriveForwardCmd(driveSubsystem, -0.4, Acceleration),
      new DriveForwardCmd(driveSubsystem, -0.5, Acceleration),
      new DriveForwardCmd(driveSubsystem, -0.6, BackwardDuration-0.6),
      new DriveForwardCmd(driveSubsystem, -0.5, Acceleration),
      new DriveForwardCmd(driveSubsystem, -0.4, Acceleration),
      new DriveForwardCmd(driveSubsystem, -0.3, Acceleration)*/
    );
  }
}