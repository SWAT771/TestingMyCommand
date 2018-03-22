package org.usfirst.frc771.TestingMyCommand.subsystems;

import java.util.Set;

import org.usfirst.frc771.TestingMyCommand.Robot;
import org.usfirst.frc771.TestingMyCommand.RobotMap;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Vision extends Subsystem {
    private final DifferentialDrive differentialDrive1 = RobotMap.driveDifferentialDrive1;
	public	static	final	double	xRes	=	320;
	public	static	final	double	yRes	=	240;
	NetworkTableInstance x;
	NetworkTable  table;
	NetworkTable GRIP;
	
	public	Vision()	{
		
		x = NetworkTableInstance.getDefault();
		System.out.println(x + " blah");
		//String subtables = x.getSubTables();
		GRIP = x.getTable("GRIP");
		System.out.println(x);
		System.out.println(GRIP + " blah");
		table = GRIP.getSubTable("contourReport");
		System.out.println(table+ "blah2");
		
		System.out.println(GRIP.getSubTables());	
		System.out.println(table.getSubTables());
		//Set<String> keys = theSauceTable.getKeys();
		//System.out.println(keys);
	}
	public boolean doWeHaveVision(){
		double[]	defaultValue	=	new	double[0];
		while(true){
			double[]	targetX	=	table.getEntry("centerX").getDoubleArray(defaultValue);
			if (targetX.length == 0){
				//System.out.println(targetX.length);
				return false;
			}else{
				//System.out.println(targetX.length);
				return true;
			}
		}
	}
	
	
	public double	getRawTargetXpos()	{
		double	xPos;
		double[]	defaultValue	=	new	double[0];
		//	while(true){
		double[]	targetX	=	table.getEntry("centerX").getDoubleArray(defaultValue);
		double[]	areas	=	table.getEntry("area").getDoubleArray(defaultValue);
		//System.out.println(targetX.length + " blahblah3");
		//System.out.println(targetX[0] + " blahblah3");
		//Timer.delay(0.2);
			if (targetX.length != areas.length){
				System.out.println("NetworkTable	udpated	in	the	middle	of	getRawTargetXpos;	returning	first	valid	entry");
			}
			if (targetX.length == 0){
				xPos = xRes/2;
			}else{
				xPos = targetX[0];
				return xPos;
			}
			int largestIdx = 0;
			if	(targetX.length	>	1)		{
				double	largest	=	0;
				for	(int	c	=	0;	c	<	areas.length;	c++)	{
					if	(areas[c]	>	largest){
						largest	=	areas[c];
						largestIdx	=	c;
						}
					}
				}
			if (targetX.length == 0){
				xPos = xRes/2;
				return xPos;
			}else{
				xPos = targetX[largestIdx];
				return xPos;
			}
	}
	
	public	double	getScaledTargetXpos()	{
		double raw = getRawTargetXpos();
		double	scaled	=	(raw-xRes/2)/(xRes/2);
		//System.out.println("HEY CHECK IT OUT " + scaled);
		 return	-scaled;
	} 
	
	
/*	public double getRawTargetYpos(){
		double yPos;
		double [] defaultValue   =  new double[0];
		double[]	targetY = table.getEntry("centerY").getDoubleArray(defaultValue);
		double[]	areas = table.getEntry("area").getDoubleArray(defaultValue);
		//Timer.delay(0.2);
		if (targetY.length == 0){
			yPos = 0;
		}else{
			yPos = targetY[0];
			return yPos;
		} int largestIdx = 0;
		if (targetY.length >1 ){
			double largest = 0;
			for (int c = 0; c < areas.length; c++){
				if (areas[c] > largest){
					largest = areas[c];
					largestIdx = c;
				}
			}
		}
		if (targetY.length == 0){
			yPos = 0;
			return yPos;
		}else{
			yPos = targetY[largestIdx];
			return yPos;
		}
}
*/	
	public double getDistance(){
		double heightpos;
		double [] defaultValue   =  new double[0];
		double[]	height = table.getEntry("height").getDoubleArray(defaultValue);
		double[]	areas = table.getEntry("area").getDoubleArray(defaultValue);
		if (height.length == 0){
			heightpos = 0;
		}else{
			heightpos = height[0];
			return heightpos;
		} 
		int largestIdx = 0;
		if (height.length > 1 ){
			double largest = 0;
			for (int c = 0; c < areas.length; c++){
				if (areas[c] > largest){
					largest = areas[c];
					largestIdx = c;
				}
			}
		}
		if (height.length == 0){
			heightpos = 0;
			return heightpos;
		}else{
			heightpos = height[largestIdx];
//			System.out.println(height[largestIdx]);
			return heightpos;
			}
		//return 0.07*getRawTargetYpos() + 117.71;
	} 
	
	
	public double Limiter(double input, double limit){
		if(input > limit){
			return limit;
		}else if(input < -limit){
			return -limit;
		}else{
			return input;
		}
	} 
	
//	public void visionDriveWithSpeed() {       
//		double Vision_error = Robot.vision.getScaledTargetXpos();
//		double Kp = 0.6; //Proportional gain 0.65
//		double KraftDinner = 0.05;
//		double turnRate = Robot.vision.Limiter((Vision_error*Kp)+(Angle_rate*KraftDinner), 0.1);
//		differentialDrive1.tankDrive(Speed-turnRate, Speed+turnRate);
//		differentialDrive1.arcadeDrive(-Speed, turnRate, true);
//		differentialDrive1.curvatureDrive(Speed, turnRate, false);
		

		
//		if (Vision_error > 0.25){
//			Robot.drive.driveForwardRight();
//			Timer.delay(1);
//		}else if(Vision_error < 0.25){
//			Robot.drive.driveForwardLeft();
			//Timer.delay(1);
//		}else{
	//		Robot.drive.testDrive();
		//}

			//Timer.delay(1);
	
//		SmartDashboard.putNumber("Vision error", Vision_error);
//		SmartDashboard.putNumber("Angle Rate", Angle_rate);
//		SmartDashboard.putNumber("Turn Rate", turnRate);
//		}   
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}

