package org.usfirst.frc771.TestingMyCommand.commands;

import org.usfirst.frc771.TestingMyCommand.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorCommand extends Command{
	
	public ElevatorCommand(){
		
		requires(Robot.drive);
		
	}
	
	protected void initialize(){
		
	}
	
	protected void execute(){
		
	}
	
	protected boolean isFinished(){
		return isTimedOut();
	}
	
	protected void end(){
		
	}
	
	protected void interrupted(){
		
	}

}
