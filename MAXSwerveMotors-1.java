package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap.MotorMap;

/** An intake subsystem that utilizes sensor input. */
public class MAXSwerveMotors extends SubsystemBase {
  private static MAXSwerveMotors instance;
  private static boolean running;

  public static MAXSwerveMotors getInstance() {
    if (instance == null) instance = new MAXSwerveMotors();
    return instance;
  }

  private CANSparkMax motorOne;
  private CANSparkMax motorTwo;

  public MAXSwerveMotors() {
    motorOne = new CANSparkMax(MotorMap.MOTOR_ID_1, MotorType.kBrushless);
    motorTwo = new CANSparkMax(MotorMap.MOTOR_ID_2, MotorType.kBrushless);
    motorTwo.follow(motorOne, true);
    motorOne.set(0);
    running = false;
  }

  public void runMotors() {
    double setSpeed = 0.1; // Change this number up to 1 or -1
    double speed = motorOne.get() == 0 ? setSpeed : 0; 
    motorOne.set(speed);
  }

  /**
   * A Command that runs the intake at some power unless a note has been collected and is in the
   * robot.
   *
   * @param objectIn Whether a note is currently in the robot. This will be based on a sensor's
   *     input in the future, however it is currently a BooleanSupplier for a type placeholder.
   * @return A Command that dictates the correct motor behavior based on whether the note is in the
   *     robot
   */
  public Command run() {
    return new InstantCommand(this::runMotors);
  }
}
