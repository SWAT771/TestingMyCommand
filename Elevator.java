package org.usfirst.frc771.TestingMyCommand.subsystems;

import org.usfirst.frc771.TestingMyCommand.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc771.TestingMyCommand.commands.*;
import org.usfirst.frc771.TestingMyCommand.OI;
import org.usfirst.frc771.TestingMyCommand.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;



public class Elevator extends Subsystem {
	private final DigitalInput LimitSwitch1 = RobotMap.LimitSwitch1;
	private final WPI_TalonSRX elevatorMotor = RobotMap.elevatorWPI_TalonSRX7; 
	
	
//	int tomato = 0;
	
	DigitalInput limitSwitch = new DigitalInput(1);
	
	public boolean getLimit(){
		return limitSwitch.get();
	}

	public void elevatorCommand() {
		Joystick elevatorJoystick = Robot.oi.joystickOperator;
		double y = (double) elevatorJoystick.getRawAxis(1);
		elevatorMotor.set(y);
	}
	
	public double deadband(int value, int limit){
		if (value>limit){
			return limit;
		}else if (value<-limit){
			return -limit;
		}else {
			return value;
		}
	}
	
	    	int motorValue = 0; { 
	    		
	    	}
	    	
// if ( SensorValue [ limitSwitch ] == 1 )  {
//	if ( motorValue > 0 );  {
//		motorValue = 0; }
//	   
	public void initDefaultCommand(){
		
	}
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand()); 
		
		
		
//    }
//	
//  public void setTomato(int value){
//		tomato = value;
//	}
//	
//	public int getTomato(){
//		return tomato;
//	}
//	
}
