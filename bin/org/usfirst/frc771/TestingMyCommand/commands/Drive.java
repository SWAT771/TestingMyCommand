// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc771.TestingMyCommand.subsystems;

import org.usfirst.frc771.TestingMyCommand.Robot;
import org.usfirst.frc771.TestingMyCommand.RobotMap;
import org.usfirst.frc771.TestingMyCommand.commands.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Joystick;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;//this line imports the gyro but it will be different depending on what type of 
import edu.wpi.first.wpilibj.DoubleSolenoid;
//gyro is being used
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
//import com.ctre.WPI_TalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

import edu.wpi.first.wpilibj.Ultrasonic;

/**
 *
 */

public class Drive extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final WPI_TalonSRX WPI_TalonSRX1 = RobotMap.driveWPI_TalonSRX1;
    private final WPI_TalonSRX WPI_TalonSRX2 = RobotMap.driveWPI_TalonSRX2;
    private final WPI_TalonSRX WPI_TalonSRX3 = RobotMap.driveWPI_TalonSRX3;
    private final SpeedControllerGroup speedControllerGroup1 = RobotMap.driveSpeedControllerGroup1;
    private final WPI_TalonSRX WPI_TalonSRX4 = RobotMap.driveWPI_TalonSRX4;
    private final WPI_TalonSRX WPI_TalonSRX5 = RobotMap.driveWPI_TalonSRX5;
    private final WPI_TalonSRX WPI_TalonSRX6 = RobotMap.driveWPI_TalonSRX6;
    private final SpeedControllerGroup speedControllerGroup2 = RobotMap.driveSpeedControllerGroup2;
    private final DifferentialDrive differentialDrive1 = RobotMap.driveDifferentialDrive1;
//    private final DoubleSolenoid gearShiftSolenoidLeft = RobotMap.gearShiftSolenoidLeft;
//    private final DoubleSolenoid gearShiftSolenoidRight = RobotMap.gearShiftSolenoidRight;
//

    private final Ultrasonic ultra = RobotMap.elevatorUltra1;
    
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    private final ADXRS450_Gyro angy = new ADXRS450_Gyro();
    	int Marmalade = 0;
    	int Schmoo = 0;
    	int Minou = 0;
    	int Snow = 0;
    
    public void DifferentialDrive (double leftValue, double rightValue){
    	
    	Joystick joy = Robot.oi.joystickDriver;
    	
    	double x = (double) joy.getRawAxis(1);
    	double y = (double) joy.getRawAxis(5);
    	
    	double rate_limit = 1;
    	
//    	differentialDrive1.tankDrive(-x/rate_limit, -y/rate_limit);
    	
//    	FOR THE TEST ROBOT
    	
    	differentialDrive1.tankDrive(-y/rate_limit, -x/rate_limit);
    	
    }
    
    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
    	setDefaultCommand(new DriveCommand());
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    	
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.



//public int getSchmoo(){
//	return Schmoo;
//}
//
//public void setSchmoo(int value){
//	Schmoo = value;
//}
//
//public int getMarmalade(){
//	return Marmalade;
//}
//
//public void setMarmalade(int value){
//	Marmalade = value;
//}
//
//public int getMinou(){
//	return Minou;
//}
//
//public void setMinou(int value){
//	Minou = value;
//}
//
//public int getSnow(){
//	return Snow;
//}
//
//public void setSnow(int value){
//	Snow = value;
//}

public double Limiter(double input, double limit){//this function validates the range of the gyro angle
	if(input > limit){
		return limit;
	}else if(input < -limit){
		return -limit;
	}else{
		return input;
	}
} 
public double findGyroAngle(){
double angle = angy.getAngle();//this function finds the robot's current angle relative to its initial position 
return angle; 
}       

public void resetGyro(){
	angy.reset(); //resetting the gyro establishes the robot's initial position which is equal to zero; always reset the gyro before using it
}        

//public void gyroDrive(double Angle_error) {   
//	double turnRate = Robot.drive.Limiter(Angle_error*0.65, 1); //0.3 for temperamental robot     
//	differentialDrive1.tankDrive(-0.2, turnRate); //0.2
//}

//this function performs the calculations using proportional and derivative gains that determine the robot's new turn rate 
//and commands the robot to execute it
public void gyroDriveWithSpeed(double Speed, double Angle_error, double Angle_rate) {       
	double Kp = 1; //Kp is the proportional gain that adjusts the rate at which the robot approaches its desired position
	double Kd = 1; //Kd is the derivative gain that adjusts the rate at which the robot approaches its desired position
	double turnRate = Robot.drive.Limiter((Angle_error*Kp)+(Angle_rate*Kd), 1); //Input the Kp and Kd that will determine the turn rate
//	differentialDrive1.tankDrive(Speed-turnRate, Speed+turnRate);
	differentialDrive1.arcadeDrive(-Speed, turnRate); //update the motor control drive according to the new turn rate
	}        
	
public void driveWithTurnRate(double speed, double turnRate){        
		differentialDrive1.tankDrive(speed, turnRate);
		}
	
//this function finds the rate at which the robot's position is changing
public double findGyroRate(){    
	double Angle_rate = angy.getRate();
	return Angle_rate;
}




 
 public void killGyro(){
	 speedControllerGroup1.set(0);
	 speedControllerGroup2.set(0);
 }
 
 public void testDrive(){
	 speedControllerGroup1.set(0.2);
	 speedControllerGroup2.set(-0.2);
 }
 
 public void getUltra(){
	 ultra.setAutomaticMode(true);
	 SmartDashboard.putNumber("Ultrasonic Distance", ultra.getRangeInches());
	 System.out.println("Ultrasonic Distance: " + ultra.getRangeInches());
 }
 
 public void turnOnUltra(){
//	 ultra.setAutomaticMode(true);
 }
 
 
 
// public void gearUpShift(){
//	 gearShiftSolenoidLeft.set(DoubleSolenoid.Value.kForward);
//	 gearShiftSolenoidRight.set(DoubleSolenoid.Value.kForward);
// }	 
// public void gearDownShift(){
//	 gearShiftSolenoidLeft.set(DoubleSolenoid.Value.kReverse);
//	 gearShiftSolenoidRight.set(DoubleSolenoid.Value.kReverse);
	 
 
 }
 
 