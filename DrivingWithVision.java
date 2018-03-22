package org.usfirst.frc771.TestingMyCommand.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc771.TestingMyCommand.Robot;

public class DrivingWithVision extends Command {

	boolean finished;
	public DrivingWithVision (){
        requires(Robot.drive);
        requires(Robot.vision);
	}
	@Override
	protected void initialize(){
		//Robot.drive.resetGyro();
		setTimeout(10);
	}
	@Override
	protected void execute(){
		double Vision_error = Robot.vision.getScaledTargetXpos();
		//SmartDashboard.putNumber("Angle", Vision_error);
		//System.out.println(Vision_error + "awfdweaf");
		double Angle_rate = 1;
	//	Robot.vision.visionDriveWithSpeed(-0.2, Vision_error, Angle_rate);
		//System.out.println("Angle error: " + Vision_error); 
		//System.out.println("Angle_rate: " + Angle_rate);
		//System.out.println(Robot.drive.findGyroAngle());
		//if(Vision_error == 0){
		//	Robot.drive.resetGyro();
		}

	@Override
	protected boolean isFinished(){
		return isTimedOut();
	}	
	@Override
	protected void end(){
		
	}
	@Override
	protected void interrupted(){
		
	}
}
