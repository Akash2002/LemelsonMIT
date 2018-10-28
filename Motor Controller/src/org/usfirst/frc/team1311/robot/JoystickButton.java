package org.usfirst.frc.team1311.robot;

import edu.wpi.first.wpilibj.Joystick;

public class JoystickButton {
	private int port = 0;
	private Joystick joystick;
	
	public JoystickButton (int port, FRCJoystick joystick2) {
		this.port = port;
		this.joystick = joystick2;
	}
	
	public boolean isPressed () {
		return joystick.getRawButton(port);
	}
	
}
