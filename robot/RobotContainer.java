//Read FIRST!!!!

/* To start off the configureButtonBindings method sets up the button-to-command mappings. 
 It uses various buttons on the Xbox controller to trigger commands that are executed on the robot.
 For example, when the kB button is pressed, the WheelReverse command is triggered and executed while the button is held down. 
 Similarly, when the kRightBumper button is pressed, the grabClaw command is executed once, and when the kLeftBumper button is pressed,
 the releaseClaw command is executed once. 
 The getTeleopCommand method returns a RunCommand that continuously runs the periodic method on the DriveSubsystem instance, 
 which in turn controls the robot's driving. This command is executed during teleoperated mode.
 The getTeleopCommand2 method returns a RunCommand that continuously runs the periodic method on the Arm instance,
 which controls the robot's arm. This command is executed during teleoperated mode.
 The getTeleopCommmand3 method returns a RunCommand that continuously runs the periodic method on the GyroSubsystem instance, 
 which measures the robot's orientation. The gyro.reset() method is called once at the start of this command to reset the gyro's measurement to zero.
This command is executed during teleoperated mode. Finally, the getAutonomousCommand method returns an AutonomousCommandGroup instance, 
which is a group of commands that the robot executes during autonomous mode. This command is executed automatically by the robot at the start of autonomous mode.
*/

package frc.robot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.ArmIn;
import frc.robot.commands.ArmOut;
import frc.robot.commands.AutonomousCommandGroup;
import frc.robot.commands.ExtendArmCommand;
import frc.robot.commands.WheelReverse;
import frc.robot.commands.WheelStart;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.ArmExtension;
import frc.robot.subsystems.ClawGrip;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.GyroSubsystem;
import frc.robot.subsystems.fourWheelDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // This code initiates an XboxController object as m_driverController using the port specified in OIConstants.kDriverControllerPort.
  XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);
  
  
  // The robot's subsystems.
  //These subsystems are objects, which are then connected to the commands
  //which are used to control the robot.
  private final fourWheelDrive m_Wheel = new fourWheelDrive();
  private final ClawGrip m_clawGrip = new ClawGrip();
  private final DriveSubsystem m_robotDrive = new DriveSubsystem(m_driverController);
  private final ArmExtension m_arm = new ArmExtension();
  private final Arm m_ArmDrive = new Arm(m_driverController);
  private final GyroSubsystem gyro = new GyroSubsystem(); //This code initializes the robot's subsystems as objects, 
                                                          //including the fourWheelDrive, ClawGrip, DriveSubsystem, ArmExtension, Arm, and GyroSubsystem.
  // A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  private final AutonomousCommandGroup autonomousCommand = new AutonomousCommandGroup(m_robotDrive, m_ArmDrive, m_arm, m_clawGrip, m_Wheel);
  // The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings.
    configureButtonBindings();
  }
  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */

  private void configureButtonBindings() {

    //This code defines button bindings for various joystick/controller buttons. 
    //Each button is associated with a specific command or command group that controls the robot's subsystems.
    new JoystickButton(m_driverController, Button.kB.value)
      .whileTrue(new WheelReverse(m_Wheel));
    
    new JoystickButton(m_driverController, Button.kX.value)
      .whileTrue(new WheelStart(m_Wheel)); 
    
    new JoystickButton(m_driverController, Button.kRightBumper.value)
      .onTrue(m_clawGrip.grabClaw());
    
    new JoystickButton(m_driverController, Button.kLeftBumper.value)
      .onTrue(m_clawGrip.releaseClaw());

    new JoystickButton(m_driverController, Button.kA.value)
     .onTrue(m_Wheel.releaseWheels());
    
     new JoystickButton(m_driverController, Button.kY.value)
      .onTrue(m_Wheel.grabWheel());


    //POV Button is all 
    new POVButton(m_driverController, 180)
      .whileTrue(new ArmIn(m_arm));

    new POVButton(m_driverController, 0)
      .whileTrue(new ArmOut(m_arm));

  }

  // The main robot driving command
  public Command getTeleopCommand() {
    return new RunCommand(() -> m_robotDrive.periodic(), m_robotDrive);
  }

  // The dropdown wheel driving command
  public Command getTeleopCommand2() {
    return new RunCommand(() -> m_ArmDrive.periodic(), m_ArmDrive);
  }
  public Command getTeleopCommmand3() {
    gyro.reset();
    return new RunCommand(() -> gyro.periodic(), gyro);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return autonomousCommand;
  }
}
