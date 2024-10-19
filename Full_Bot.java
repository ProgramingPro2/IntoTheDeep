package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Full_Bot")
public class Full_Bot extends LinearOpMode {

  private DcMotor FR;
  private DcMotor FL;
  private DcMotor ArmUp; 
  private DcMotor ArmRotate; //Does not work
  private DcMotor Lift;
  private CRServo Intake;
  private CRServo ArmLength;

  private boolean Use_Arm = true;
  private boolean Use_ArmRotate = true;
  private boolean Use_Intake = true;
  private boolean Use_Lift = false;

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
        Intake = hardwareMap.get(CRServo.class, "Intake");
      }
      ArmLength = hardwareMap.get(CRServo.class, "ArmLength");
    } 

    if (Use_Lift) {
      Lift = hardwareMap.get(DcMotor.class, "Lift");
    }

    // Reverse one of the drive motors.
    // dont go to diddy party
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
            ArmUp.setPower(-gamepad2.left_stick_y/2);
            telemetry.addData("Arm Position", "up");
          } 
          else if (gamepad2.left_stick_y < 0) {
            ArmUp.setPower(-gamepad2.left_stick_y/2);
            telemetry.addData("Arm Position", "down");
          }
          else {
            ArmUp.setPower(0);
          }
        }
        if (Use_ArmRotate) {
          
          if (gamepad2.right_stick_y > 0) {
            ArmRotate.setPower(gamepad2.right_stick_y/1.5);
            telemetry.addData("ArmRotate Position", "up");
          } 
          else if (gamepad2.right_stick_y < 0) {
            ArmRotate.setPower(gamepad2.right_stick_y/1.5);
            telemetry.addData("ArmRotate Position", "down");
          }
          else {
            ArmRotate.setPower(0);
          }

          if (gamepad2.a == true) {
            ArmLength.setPower(-1);
            telemetry.addData("ArmLength Power", "down");
          } 
          else if (gamepad2.y == true) {
            ArmLength.setPower(1);
            telemetry.addData("ArmLength Power", "up");
          }
          else {
            ArmLength.setPower(0);
          }
        }

        if (Use_Intake) {
          if (gamepad2.dpad_up) {
            Intake.setPower(1);
            telemetry.addData("Intake Speed", "up");
          } 
          else if (gamepad2.dpad_down) {
            Intake.setPower(-1);
            telemetry.addData("Intake Speed", "down");
          }
          else {
            Intake.setPower(0);
          }
        }
        
        if (Use_Lift) {
            if (gamepad2.left_trigger > 0) {
                Lift.setPower(1);
                telemetry.addData("TopLift Position", "up");
            } 
            else if (gamepad2.left_bumper) {
                Lift.setPower(-1);
                telemetry.addData("Top Lift Position", "down");
            }
            else {
                Lift.setPower(0);
            }
        }
            
        
        telemetry.update();
      }
    }

  }
}
