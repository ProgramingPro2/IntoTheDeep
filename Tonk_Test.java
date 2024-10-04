package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Tank Test")
public class Tonk_Test extends LinearOpMode {

  private DcMotor FR;
  private DcMotor FL;
  private boolean Use_Arm = false;
  private boolean Use_Liner_Track = false;

  /**
   * This function is executed when this OpMode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    FR = hardwareMap.get(DcMotor.class, "FR");
    FL = hardwareMap.get(DcMotor.class, "FL");

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
        
        telemetry.update();
      }
    }

  }
}
