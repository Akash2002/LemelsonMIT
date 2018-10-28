package org.usfirst.frc.team1311.robot;

import edu.wpi.first.wpilibj.Joystick;

public class FRCJoystick extends Joystick{
	
	private Joystick joystick;
	
	public FRCJoystick (int port) {
		super (port);
		joystick = new Joystick (port);
	}
	
	public double getRawXValues () {
		return joystick.getX();
	}
	
	public double getRawYValues () {
		return joystick.getY();
	}

}
