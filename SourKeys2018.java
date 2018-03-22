package org.usfirst.frc771.TestingMyCommand.commands;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc771.TestingMyCommand.Robot;

public class SourKeys2018 extends Command{
	
	boolean finished;
	
	public SourKeys2018(){
		
		requires(Robot.drive);
		
	}
	
	protected void initialize(){
		
		Robot.drive.resetGyro();
		setTimeout(10);
		
	}
	
	protected void execute(){
		double Angle_error = Robot.drive.findGyroAngle();
		SmartDashboard.putNumber("Angle", Angle_error);
		double Angle_rate = Robot.drive.findGyroRate();
		Robot.drive.gyroDriveWithSpeed(-0.2, Angle_error, Angle_rate);
		System.out.println("Angle error: " + Angle_error);
//		System.out.println("Angle_rate: " + Angle_rate);
	}
	
	protected boolean isFinished(){
		return isTimedOut();
	}
	
	protected void end(){
		
	}
	
	protected void interrupted(){
		
	}

}
