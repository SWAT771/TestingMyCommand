// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc771.TestingMyCommand.commands;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;


import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc771.TestingMyCommand.Robot;
//import org.usfirst.frc771.TestingMyCommand.subsystems.Vision;

/**
 *
 */
public class AutonomousCommand extends Command {
//positive is counterclockwise, negative is clockwise
		
	boolean finished;
	int oof = Robot.drive.getOof();
	int scott = 1; //1 for left, 2 for middle, 3 for right
	
	
	
	
	//Vision vision = new Vision();
	//int Schmoo = Robot.drive.getSchmoo();
	
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public AutonomousCommand() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    	requires(Robot.drive);
    	requires(Robot.vision);
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	System.out.println("WE'VE STARTED");
    	//oof = Robot.drive.getOof();
    	oof = 0;
    	System.out.println(oof);
    	Robot.drive.resetGyro();
}
    

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	double angle;
    	double angle_rate;
    	double vision_error;
    	boolean yes_vision;
        System.out.print(oof);
        String gameData;
    	gameData = DriverStation.getInstance().getGameSpecificMessage();
    	
    	if(gameData.length() > 0){
    		
    		
    		
    		
    		if (scott == 1){//1st angles should be negative 
    			
    			
    			
    			
    			if(gameData.charAt(0) == 'L'){ //LL
        			
        			if (oof == 0){
        				
        	        	angle = Robot.drive.findGyroAngle();
        	        	angle_rate = Robot.drive.findGyroRate();
        	        	yes_vision = Robot.vision.doWeHaveVision();
        	    		System.out.println("oof " + oof);
        	    		double target_angle = -90;
        	    		double delta_angle = target_angle + angle;
        	    		
        	    		
        	    		Robot.drive.gyroDriveWithSpeed(0.1, delta_angle, angle_rate);
        	    	
        	    		if (yes_vision == true){
        	    			oof = 1;
        	    			System.out.println("eh? " + Robot.vision.getScaledTargetXpos());
        	    		}	
        	    		
        	    		
        	    		
        	    		
        	    		
        	    	}else if (oof == 1){
        	        	vision_error = Robot.vision.getScaledTargetXpos();
        	        		
        	    		
        	        	if (vision_error > 0.05){
        	        		System.out.println("ah");
        	        		Robot.drive.driveForwardRight();
        				}else if(vision_error < -0.05){
        					System.out.println("oo");
        					Robot.drive.driveForwardLeft();
        				}else if ((vision_error > -0.05) && (vision_error <0.05)){
        					System.out.println(vision_error + " move");
        					Robot.drive.resetGyro();
        	    			oof = 2;
        				}
        				
        	        	
        	        	
        	        	
        	    	}else if(oof == 2){
        	    		yes_vision = Robot.vision.doWeHaveVision();
        	        	angle = Robot.drive.findGyroAngle();
        	        	angle_rate = Robot.drive.findGyroRate();
        	        	
        	    		Robot.drive.gyroDriveWithSpeed(0.3, angle, angle_rate);
        	    		System.out.print("oof" + oof);  
        	    		
        	    		if ((Robot.vision.getDistance() >= 130) || (yes_vision == false )){
        	    			System.out.println("wooooooo");
        	    			Robot.drive.resetGyro();
        	    			oof = 3;
        	    		}
        	    		
        	    		
        	    		
        	    		
        	    		
        	    	}else if(oof == 3){
        	    		yes_vision = Robot.vision.doWeHaveVision();
        	    		//System.out.println("oof" + oof);
        	    		//System.out.println("before");
        	    		//Robot.drive.testDrive(0.2);
        	    		
        	    		if (yes_vision == false){
        	    			angle = Robot.drive.findGyroAngle();
        	            	angle_rate = Robot.drive.findGyroRate();
        	        		System.out.print("oof" + oof);
        	        		System.out.print("ang" + angle);
        	        		double target_angle = 20;
        	        		double delta_angle = target_angle + angle;
        	        		Robot.drive.gyroDriveWithSpeed(-0.2, delta_angle, angle_rate);
        	        		
        	        		if(yes_vision == true){
        	        			Robot.drive.resetGyro();
        	        			oof = 4;
        	        		}
        	        		
        	    		}else{

        	    		Robot.drive.testDrive(0);
        	    		oof = 4;
        	    		}
        	    		
        	    		
        	    		
        	    		
        	    		
        	    	}else if (oof == 4){
        	    		vision_error = Robot.vision.getScaledTargetXpos();
        	        	
        	    		if (vision_error > 0.5){
        	        		System.out.println("ah");
        	        		Robot.drive.driveForwardRight();
        				}else if(vision_error < -0.5){
        					System.out.println("oo");
        					Robot.drive.driveBackwardRight();
        				}else if ((vision_error > -0.5) && (vision_error <0.5)){
        					System.out.println(vision_error + " move");
        					Robot.drive.resetGyro();
        	    			oof = 5;
        				}
        	    	}else if (oof ==5){
        	    		//make the intake 
        	    	}
        			
        			
        			
        		}else{//LR
        			
        			
        			
        			
        			
        			
        			if (oof == 0){
        	        	angle = Robot.drive.findGyroAngle();
        	        	angle_rate = Robot.drive.findGyroRate();
        	        	yes_vision = Robot.vision.doWeHaveVision();
        	    		System.out.println("oof " + oof);
        	    		double target_angle = -50;
        	    		double delta_angle = target_angle + angle;
        	    		
        	    		
        	    		Robot.drive.gyroDriveWithSpeed(0.1, delta_angle, angle_rate);
        	    	
        	    		
        	    		
        	    		if ((delta_angle > -2) && delta_angle < 2){
        	    			Robot.drive.resetGyro();
        	    			oof = 1;
        	    			System.out.println("eh? " + Robot.vision.getScaledTargetXpos());
        	    		}
        	    		
        	    		
        	    		
        	    		
        			}else if (oof == 1){
        				angle = Robot.drive.findGyroAngle();
        	        	angle_rate = Robot.drive.findGyroRate();
        	        	yes_vision = Robot.vision.doWeHaveVision();
        	    		System.out.println("oof " + oof);
        	    		double target_angle = -40;
        	    		double delta_angle = target_angle + angle;
        	    		
        	    		
        	    		Robot.drive.gyroDriveWithSpeed(0.1, delta_angle, angle_rate);
        	    	
        	    		if (yes_vision == true){
        	    			oof = 2;
        	    			System.out.println("eh? " + Robot.vision.getScaledTargetXpos());
        	    		}	
        	    	
        			
        			
        			
        			}else if (oof == 2){
        	        	vision_error = Robot.vision.getScaledTargetXpos();
        	        		
        	    		
        	        	if (vision_error > 0.05){
        	        		System.out.println("ah");
        	        		Robot.drive.driveForwardRight();
        				}else if(vision_error < -0.05){
        					System.out.println("oo");
        					Robot.drive.driveForwardLeft();
        				}else if ((vision_error > -0.05) && (vision_error <0.05)){
        					System.out.println(vision_error + " move");
        					Robot.drive.resetGyro();
        	    			oof = 3;
        				}
        				
        	        	
        	        	
        	        	
        	    	}else if(oof == 3){
        	    		yes_vision = Robot.vision.doWeHaveVision();
        	        	angle = Robot.drive.findGyroAngle();
        	        	angle_rate = Robot.drive.findGyroRate();
        	        	
        	    		Robot.drive.gyroDriveWithSpeed(0.3, angle, angle_rate);
        	    		System.out.print("oof" + oof);  
        	    		
        	    		if ((Robot.vision.getDistance() >= 130) || (yes_vision == false )){
        	    			System.out.println("wooooooo");
        	    			Robot.drive.resetGyro();
        	    			oof = 4;
        	    		}
        	    		
        	    		
        	    		
        	    		
        	    		
        	    	}else if(oof == 4){
        	    		yes_vision = Robot.vision.doWeHaveVision();
        	    		//System.out.println("oof" + oof);
        	    		//System.out.println("before");
        	    		//Robot.drive.testDrive(0.2);
        	    		
        	    		if (yes_vision == false){
        	    			angle = Robot.drive.findGyroAngle();
        	            	angle_rate = Robot.drive.findGyroRate();
        	        		System.out.print("oof" + oof);
        	        		System.out.print("ang" + angle);
        	        		double target_angle = 20;
        	        		double delta_angle = target_angle + angle;
        	        		Robot.drive.gyroDriveWithSpeed(-0.2, delta_angle, angle_rate);
        	        		
        	        		if((delta_angle > -2) && delta_angle < 2){
        	        			Robot.drive.resetGyro();
        	        			oof = 5;
        	        		}
        	        		
        	    		}else{

        	    		Robot.drive.testDrive(0);
        	    		oof = 5;
        	    		}
        	    		
        	    		
        	    		
        	    		
        	    		
        	    	}else if (oof == 5){
        	    		vision_error = Robot.vision.getScaledTargetXpos();
        	        	
        	    		
        	    		if (vision_error > 0.5){
        	        		System.out.println("ah");
        	        		Robot.drive.driveForwardRight();
        				}else if(vision_error < -0.5){
        					System.out.println("oo");
        					Robot.drive.driveBackwardRight();
        				}else if ((vision_error > -0.5) && (vision_error <0.5)){
        					System.out.println(vision_error + " move");
        					Robot.drive.resetGyro();
        	    			oof =  6;
        				}
        	    		
        	    		
        	    	}else if (oof ==6){
        	    		//make the intake 
        	    	}
        			
    		
    		
        	}
    			
    			
    			
    			
    			
    	}else if(scott == 2){ 
    		
    		
    		
    		
    	
    	
    		if(gameData.charAt(0) == 'L'){//ML
    			
    			
    			
    			
    			
    			if (oof == 0){
    	        	angle = Robot.drive.findGyroAngle();
    	        	angle_rate = Robot.drive.findGyroRate();
    	        	yes_vision = Robot.vision.doWeHaveVision();
    	    		System.out.println("oof " + oof);
    	    		double target_angle = 90;
    	    		double delta_angle = target_angle + angle;
    	    		
    	    		
    	    		Robot.drive.gyroDriveWithSpeed(0.1, delta_angle, angle_rate);
    	    	
    	    		if (yes_vision == true){
    	    			oof = 1;
    	    			System.out.println("eh? " + Robot.vision.getScaledTargetXpos());
    	    		}	
    	    		
    	    		
    	    		
    	    		
    	    		
    	    	}else if (oof == 1){
    	        	vision_error = Robot.vision.getScaledTargetXpos();
    	        		
    	    		
    	        	if (vision_error > 0.05){
    	        		System.out.println("ah");
    	        		Robot.drive.driveForwardRight();
    				}else if(vision_error < -0.05){
    					System.out.println("oo");
    					Robot.drive.driveForwardLeft();
    				}else if ((vision_error > -0.05) && (vision_error <0.05)){
    					System.out.println(vision_error + " move");
    					Robot.drive.resetGyro();
    	    			oof = 2;
    				}
    				
    	        	
    	        	
    	        	
    	    	}else if(oof == 2){
    	    		yes_vision = Robot.vision.doWeHaveVision();
    	        	angle = Robot.drive.findGyroAngle();
    	        	angle_rate = Robot.drive.findGyroRate();
    	        	
    	    		Robot.drive.gyroDriveWithSpeed(0.3, angle, angle_rate);
    	    		System.out.print("oof" + oof);  
    	    		
    	    		if ((Robot.vision.getDistance() >= 130) || (yes_vision == false )){
    	    			System.out.println("wooooooo");
    	    			Robot.drive.resetGyro();
    	    			oof = 3;
    	    		}
    	    		
    	    		
    	    		
    	    		
    	    		
    	    	}else if(oof == 3){
    	    		yes_vision = Robot.vision.doWeHaveVision();
    	    		//System.out.println("oof" + oof);
    	    		//System.out.println("before");
    	    		//Robot.drive.testDrive(0.2);
    	    		
    	    		if (yes_vision == false){
    	    			angle = Robot.drive.findGyroAngle();
    	            	angle_rate = Robot.drive.findGyroRate();
    	        		System.out.print("oof" + oof);
    	        		System.out.print("ang" + angle);
    	        		double target_angle = -20;
    	        		double delta_angle = target_angle + angle;
    	        		Robot.drive.gyroDriveWithSpeed(-0.2, delta_angle, angle_rate);
    	        		
    	        		if((delta_angle > -2) && delta_angle < 2){
    	        			Robot.drive.resetGyro();
    	        			oof = 4;
    	        		}
    	        		
    	    		}else{

    	    		Robot.drive.testDrive(0);
    	    		oof = 4;
    	    		}
    	    		
    	    		
    	    		
    	    		
    	    		
    	    	}else if (oof == 4){
    	    		vision_error = Robot.vision.getScaledTargetXpos();
    	        	
    	    		
    	    		if (vision_error > 0.5){
    	        		System.out.println("ah");
    	        		Robot.drive.driveForwardRight();
    				}else if(vision_error < -0.5){
    					System.out.println("oo");
    					Robot.drive.driveBackwardRight();
    				}else if ((vision_error > -0.5) && (vision_error <0.5)){
    					System.out.println(vision_error + " move");
    					Robot.drive.resetGyro();
    	    			oof = 5;
    				}
    	    		
    	    		
    	    	}else if (oof ==5){
    	    		//make the intake 
    	    	}
    			
    			
    			
    			
    			
    			
    			
    		}else{//MR
    			
    			
    			
    			
    			
    			if (oof == 0){
    	        	angle = Robot.drive.findGyroAngle();
    	        	angle_rate = Robot.drive.findGyroRate();
    	        	yes_vision = Robot.vision.doWeHaveVision();
    	    		System.out.println("oof " + oof);
    	    		double target_angle = -90;
    	    		double delta_angle = target_angle + angle;
    	    		
    	    		
    	    		Robot.drive.gyroDriveWithSpeed(0.1, delta_angle, angle_rate);
    	    	
    	    		if (yes_vision == true){
    	    			oof = 1;
    	    			System.out.println("eh? " + Robot.vision.getScaledTargetXpos());
    	    		}	
    	    		
    	    		
    	    		
    	    		
    	    		
    	    	}else if (oof == 1){
    	        	vision_error = Robot.vision.getScaledTargetXpos();
    	        		
    	    		
    	        	if (vision_error > 0.05){
    	        		System.out.println("ah");
    	        		Robot.drive.driveForwardRight();
    				}else if(vision_error < -0.05){
    					System.out.println("oo");
    					Robot.drive.driveForwardLeft();
    				}else if ((vision_error > -0.05) && (vision_error <0.05)){
    					System.out.println(vision_error + " move");
    					Robot.drive.resetGyro();
    	    			oof = 2;
    				}
    				
    	        	
    	        	
    	        	
    	        	
    	        	
    	        	
    	    	}else if(oof == 2){
    	    		yes_vision = Robot.vision.doWeHaveVision();
    	        	angle = Robot.drive.findGyroAngle();
    	        	angle_rate = Robot.drive.findGyroRate();
    	        	
    	    		Robot.drive.gyroDriveWithSpeed(0.3, angle, angle_rate);
    	    		System.out.print("oof" + oof);  
    	    		
    	    		if ((Robot.vision.getDistance() >= 130) || (yes_vision == false )){
    	    			System.out.println("wooooooo");
    	    			Robot.drive.resetGyro();
    	    			oof = 3;
    	    		}
    	    		
    	    		
    	    		
    	    		
    	    		
    	    	}else if(oof == 3){
    	    		yes_vision = Robot.vision.doWeHaveVision();
    	    		//System.out.println("oof" + oof);
    	    		//System.out.println("before");
    	    		//Robot.drive.testDrive(0.2);
    	    		
    	    		if (yes_vision == false){
    	    			angle = Robot.drive.findGyroAngle();
    	            	angle_rate = Robot.drive.findGyroRate();
    	        		System.out.print("oof" + oof);
    	        		System.out.print("ang" + angle);
    	        		double target_angle = 20;
    	        		double delta_angle = target_angle + angle;
    	        		Robot.drive.gyroDriveWithSpeed(-0.2, delta_angle, angle_rate);
    	        		
    	        		if((delta_angle > -2) && delta_angle < 2){
    	        			Robot.drive.resetGyro();
    	        			oof = 4;
    	        		}
    	        		
    	    		}else{

    	    		Robot.drive.testDrive(0);
    	    		oof = 4;
    	    		}
    	    		
    	    		
    	    
    	    		
    	    		
    	    		
    	    		
    	    	}else if (oof == 4){
    	    		vision_error = Robot.vision.getScaledTargetXpos();
    	        	
    	    		
    	    		if (vision_error > 0.5){
    	        		System.out.println("ah");
    	        		Robot.drive.driveForwardRight();
    				}else if(vision_error < -0.5){
    					System.out.println("oo");
    					Robot.drive.driveBackwardRight();
    				}else if ((vision_error > -0.5) && (vision_error <0.5)){
    					System.out.println(vision_error + " move");
    					Robot.drive.resetGyro();
    	    			oof = 5;
    				}
    	    		
    	    		
    	    		
    	    		
    	    	}else if (oof ==5){
    	    		//make the intake 
    	    	}
    			
    		}
    		
    		
    		
    		
    		
    		
    	}else if (scott == 3){//1st angles should be positive 
    		
    		
    		
    		
    		
    		
    		if(gameData.charAt(0) == 'L'){//RL
    			
    			
    			
    			
    			
    			if (oof == 0){
    	        	angle = Robot.drive.findGyroAngle();
    	        	angle_rate = Robot.drive.findGyroRate();
    	        	yes_vision = Robot.vision.doWeHaveVision();
    	    		System.out.println("oof " + oof);
    	    		double target_angle = 50;
    	    		double delta_angle = target_angle + angle;
    	    		
    	    		
    	    		Robot.drive.gyroDriveWithSpeed(0.1, delta_angle, angle_rate);
    	    	
    	    		
    	    		
    	    		if ((delta_angle > -2) && delta_angle < 2){
    	    			Robot.drive.resetGyro();
    	    			oof = 1;
    	    			System.out.println("eh? " + Robot.vision.getScaledTargetXpos());
    	    		}
    	    		
    	    		
    	    		
    	    		
    			}else if (oof == 1){
    				angle = Robot.drive.findGyroAngle();
    	        	angle_rate = Robot.drive.findGyroRate();
    	        	yes_vision = Robot.vision.doWeHaveVision();
    	    		System.out.println("oof " + oof);
    	    		double target_angle = 40;
    	    		double delta_angle = target_angle + angle;
    	    		
    	    		
    	    		Robot.drive.gyroDriveWithSpeed(0.1, delta_angle, angle_rate);
    	    	
    	    		if (yes_vision == true){
    	    			oof = 2;
    	    			System.out.println("eh? " + Robot.vision.getScaledTargetXpos());
    	    		}	
    	    	
    			
    			
    			
    			}else if (oof == 2){
    	        	vision_error = Robot.vision.getScaledTargetXpos();
    	        		
    	    		
    	        	if (vision_error > 0.05){
    	        		System.out.println("ah");
    	        		Robot.drive.driveForwardRight();
    				}else if(vision_error < -0.05){
    					System.out.println("oo");
    					Robot.drive.driveForwardLeft();
    				}else if ((vision_error > -0.05) && (vision_error <0.05)){
    					System.out.println(vision_error + " move");
    					Robot.drive.resetGyro();
    	    			oof = 3;
    				}
    				
    	        	
    	        	
    	        	
    	    	}else if(oof == 3){
    	    		yes_vision = Robot.vision.doWeHaveVision();
    	        	angle = Robot.drive.findGyroAngle();
    	        	angle_rate = Robot.drive.findGyroRate();
    	        	
    	    		Robot.drive.gyroDriveWithSpeed(0.3, angle, angle_rate);
    	    		System.out.print("oof" + oof);  
    	    		
    	    		if ((Robot.vision.getDistance() >= 130) || (yes_vision == false )){
    	    			System.out.println("wooooooo");
    	    			Robot.drive.resetGyro();
    	    			oof = 4;
    	    		}
    	    		
    	    		
    	    		
    	    		
    	    		
    	    	}else if(oof == 4){
    	    		yes_vision = Robot.vision.doWeHaveVision();
    	    		//System.out.println("oof" + oof);
    	    		//System.out.println("before");
    	    		//Robot.drive.testDrive(0.2);
    	    		
    	    		if (yes_vision == false){
    	    			angle = Robot.drive.findGyroAngle();
    	            	angle_rate = Robot.drive.findGyroRate();
    	        		System.out.print("oof" + oof);
    	        		System.out.print("ang" + angle);
    	        		double target_angle = -20;
    	        		double delta_angle = target_angle + angle;
    	        		Robot.drive.gyroDriveWithSpeed(-0.2, delta_angle, angle_rate);
    	        		
    	        		if((delta_angle > -2) && delta_angle < 2){
    	        			Robot.drive.resetGyro();
    	        			oof = 5;
    	        		}
    	        		
    	    		}else{

    	    		Robot.drive.testDrive(0);
    	    		oof = 5;
    	    		}
    	    		
    	    		
    	    		
    	    		
    	    		
    	    	}else if (oof == 5){
    	    		vision_error = Robot.vision.getScaledTargetXpos();
    	        	
    	    		
    	    		if (vision_error > 0.5){
    	        		System.out.println("ah");
    	        		Robot.drive.driveForwardRight();
    				}else if(vision_error < -0.5){
    					System.out.println("oo");
    					Robot.drive.driveBackwardRight();
    				}else if ((vision_error > -0.5) && (vision_error <0.5)){
    					System.out.println(vision_error + " move");
    					Robot.drive.resetGyro();
    	    			oof =  6;
    				}
    	    		
    	    		
    	    	}else if (oof ==6){
    	    		//make the intake 
    	    	}
    			
    			
    			
    			
    			
    			
    			
    			
    		}else{ //RR
    			
    			
    			
    			
    			
    			
    			
    			if (oof == 0){
    	        	angle = Robot.drive.findGyroAngle();
    	        	angle_rate = Robot.drive.findGyroRate();
    	        	yes_vision = Robot.vision.doWeHaveVision();
    	    		System.out.println("oof " + oof);
    	    		double target_angle = 90;
    	    		double delta_angle = target_angle + angle;
    	    		
    	    		
    	    		Robot.drive.gyroDriveWithSpeed(0.1, delta_angle, angle_rate);
    	    	
    	    		if (yes_vision == true){
    	    			oof = 1;
    	    			System.out.println("eh? " + Robot.vision.getScaledTargetXpos());
    	    		}	
    	    		
    	    		
    	    		
    	    		
    	    		
    	    		
    	    		
    	    	}else if (oof == 1){
    	        	vision_error = Robot.vision.getScaledTargetXpos();
    	        		
    	    		
    	        	if (vision_error > 0.05){
    	        		System.out.println("ah");
    	        		Robot.drive.driveForwardRight();
    				}else if(vision_error < -0.05){
    					System.out.println("oo");
    					Robot.drive.driveForwardLeft();
    				}else if ((vision_error > -0.05) && (vision_error <0.05)){
    					System.out.println(vision_error + " move");
    					Robot.drive.resetGyro();
    	    			oof = 2;
    				}
    				
    	        	
    	        	
    	        	
    	        	
    	        	
    	        	
    	    	}else if(oof == 2){
    	    		yes_vision = Robot.vision.doWeHaveVision();
    	        	angle = Robot.drive.findGyroAngle();
    	        	angle_rate = Robot.drive.findGyroRate();
    	        	
    	    		Robot.drive.gyroDriveWithSpeed(0.3, angle, angle_rate);
    	    		System.out.print("oof" + oof);  
    	    		
    	    		if ((Robot.vision.getDistance() >= 130) || (yes_vision == false )){
    	    			System.out.println("wooooooo");
    	    			Robot.drive.resetGyro();
    	    			oof = 3;
    	    		}
    	    		
    	    		
    	    		
    	    		
    	    		
    	    		
    	    		
    	    		
    	    	}else if(oof == 3){
    	    		yes_vision = Robot.vision.doWeHaveVision();
    	    		//System.out.println("oof" + oof);
    	    		//System.out.println("before");
    	    		//Robot.drive.testDrive(0.2);
    	    		
    	    		if (yes_vision == false){
    	    			angle = Robot.drive.findGyroAngle();
    	            	angle_rate = Robot.drive.findGyroRate();
    	        		System.out.print("oof" + oof);
    	        		System.out.print("ang" + angle);
    	        		double target_angle = -20;
    	        		double delta_angle = target_angle + angle;
    	        		Robot.drive.gyroDriveWithSpeed(-0.2, delta_angle, angle_rate);
    	        		
    	        		if((delta_angle > -2) && delta_angle < 2){
    	        			Robot.drive.resetGyro();
    	        			oof = 4;
    	        		}
    	        		
    	    		}else{

    	    		Robot.drive.testDrive(0);
    	    		oof = 4;
    	    		}
    	    		
    	    		
    	    		
    	    		
    	    		
    	    		
    	    		
    	    		
    	    	}else if (oof == 4){
    	    		vision_error = Robot.vision.getScaledTargetXpos();
    	        	
    	    		
    	    		if (vision_error > 0.5){
    	        		System.out.println("ah");
    	        		Robot.drive.driveForwardRight();
    				}else if(vision_error < -0.5){
    					System.out.println("oo");
    					Robot.drive.driveBackwardRight();
    				}else if ((vision_error > -0.5) && (vision_error <0.5)){
    					System.out.println(vision_error + " move");
    					Robot.drive.resetGyro();
    	    			oof = 5;
    				}
    	    			
    	    	}else if (oof ==5){
    	    		//make the intake 
    	    	}
    			
    	    }
    	}
    }
}


    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
		return finished;
    	
//        if(oof == 0|| oof == 1 || oof == 2 || oof == 4){
//        	System.out.println("auto is finished");
    	//        	return finished;
    	//       }else{
    	//        	return isTimedOut();
    	//       }
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}