package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Full_Bot")
public class Full_Bot extends LinearOpMode {

  private DcMotor FR;
  private DcMotor FL;
  private DcMotor ArmUp;
  private DcMotor ArmRotate;
  private Servo Intake;
  private Servo ArmLength;

  private boolean Use_Arm = false;
  private boolean Use_ArmRotate = false;
  private boolean Use_Intake = false;
  private boolean Use_ArmUp = false;

  /**
   * This function is executed when this OpMode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    FR = hardwareMap.get(DcMotor.class, "FR");
    FL = hardwareMap.get(DcMotor.class, "FL");

    if (Use_Arm) {
      ArmUp = hardwareMap.get(DcMotor.class, "Arm");
      if (Use_ArmRotate) {
        ArmRotate = hardwareMap.get(DcMotor.class, "ArmRotate");
      }
      if (Use_Intake) {
        Intake = hardwareMap.get(Servo.class, "Intake");
      }
      ArmLength = hardwareMap.get(Servo.class, "ArmLength");
    } 

    // Reverse one of the drive motors.
    // You will have to determine which motor to reverse for your robot.
    // In this example, the right motor was reversed so that positive
    // applied power makes it move the robot in the forward direction.
    FR.setDirection(DcMotor.Direction.REVERSE);
    waitForStart();
    if (opModeIsActive()) {
      // Put run blocks here.
      while (opModeIsActive()) {
        // Put loop blocks here.
        // The Y axis of a joystick ranges from -1 in its topmost position to +1 in its bottommost position.
        // We negate this value so that the topmost position corresponds to maximum forward power.
        FR.setPower(-gamepad1.left_stick_y);
        FL.setPower(-gamepad1.right_stick_y);
        telemetry.addData("Left Pow", (FL.getPower() * 2));
        telemetry.addData("Right Pow", (FR.getPower() * 2));

        if (Use_Arm) {
          if (gamepad2.left_stick_y > 0) {
            ArmUp.setPower(gamepad2.left_stick_y);
            telemetry.addData("Arm Position", "up");
          } 
          else if (gamepad2.left_stick_y < 0) {
            ArmUp.setPower(gamepad2.left_stick_y);
            telemetry.addData("Arm Position", "down");
          }
        }
        
        if (Use_ArmRotate) {
          
          if (gamepad2.right_stick_x > 0) {
            ArmRotate.setPower(gamepad2.right_stick_x);
            telemetry.addData("ArmRotate Position", "up");
          } 
          else if (gamepad2.right_stick_x < 0) {
            ArmRotate.setPower(gamepad2.right_stick_x);
            telemetry.addData("ArmRotate Position", "down");
          }
        }

        if (Use_Intake) {
          if (gamepad2.dpad_up) {
            Intake.setPosition(1);
            telemetry.addData("Intake Position", "up");
          } 
          else if (gamepad2.dpad_down) {
            Intake.setPosition(-1);
            telemetry.addData("Intake Position", "down");
          }
        }

        
        /* 
        if (Use_Lift) {
            if (gamepad2.left_trigger > 0) {
                Lift_Top.setPower(gamepad2.left_trigger);
                telemetry.addData("TopLift Position", "up");
            } 
            else if (gamepad2.left_bumper) {
                Lift_Bottom.setPower(-1);
                telemetry.addData("Top Lift Position", "down");
            }

            if (gamepad2.right_trigger > 0) {
                Lift_Top.setPower(gamepad2.right_trigger);
                telemetry.addData("TopLift Position", "up");
            } 
            else if (gamepad2.right_bumper) {
                Lift_Bottom.setPower(-1);
                telemetry.addData("Top Lift Position", "down");
            }
        }
            */
        
        telemetry.update();
      }
    }

  }
}
