/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1311.robot;

import com.ctre.phoenix.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Spark;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class Robot extends IterativeRobot {
	
	private FRCJoystick joystick;
	private JoystickButton[] joystickButtons;
	
	TalonSRX chassisTalonLeft2 = new TalonSRX (20);
	TalonSRX chassisTalonLeft1 = new TalonSRX (21);
	
	VictorSPX linearActuatorRight1 = new VictorSPX (26);
	VictorSPX chassisTalonRight2 = new VictorSPX (25);
	VictorSPX linearActuatorLeft2 = new VictorSPX (24);
	VictorSPX linearActuatorRight2 = new VictorSPX (23);
	VictorSPX linearActuatorLeft1 = new VictorSPX (22);
	VictorSPX chassisTalonRight1 = new VictorSPX (27);
	
	int actuatorDirection = 1;
	boolean hasPressed = false;

	@Override
	public void robotInit() {
		joystick = new FRCJoystick (0);
		joystickButtons = new JoystickButton[12];
		for (int i = 0; i < joystickButtons.length; i++) {
			joystickButtons[i] = new JoystickButton (i, joystick);
		}
	}

	@Override
	public void teleopPeriodic() {
		
		while (joystick.getRawButton(7)) {
			linearActuatorRight1.set(ControlMode.PercentOutput, - 0.47);
			linearActuatorLeft1.set(ControlMode.PercentOutput, - 0.45);
		} 
		while (joystick.getRawButton(6)) {
			linearActuatorRight1.set(ControlMode.PercentOutput, 0.47);
			linearActuatorLeft1.set(ControlMode.PercentOutput, 0.45);
		}
		
		while (joystick.getRawButton(10)) {
			linearActuatorRight2.set(ControlMode.PercentOutput, - 0.47);
			linearActuatorLeft2.set(ControlMode.PercentOutput, - 0.45);
		} 
		
		while (joystick.getRawButton(11)) {
			linearActuatorRight2.set(ControlMode.PercentOutput, 0.47);
			linearActuatorLeft2.set(ControlMode.PercentOutput, 0.45);
		}
		
		if (joystick.getRawButton(2)) {
			if (!hasPressed) {
				actuatorDirection = -actuatorDirection;
				hasPressed = true;
			}
		}
		
		while (joystick.getRawButton(1)) {
			linearActuatorRight2.set(ControlMode.PercentOutput, 0.47 * actuatorDirection);
			linearActuatorLeft2.set(ControlMode.PercentOutput, 0.45 * actuatorDirection);
			linearActuatorRight1.set(ControlMode.PercentOutput, 0.47 * actuatorDirection);
			linearActuatorLeft1.set(ControlMode.PercentOutput, 0.45 * actuatorDirection);
			hasPressed = false;
		}
		
		linearActuatorRight2.set(ControlMode.PercentOutput, 0);
		linearActuatorLeft2.set(ControlMode.PercentOutput, 0);
		linearActuatorRight1.set(ControlMode.PercentOutput, 0);
		linearActuatorLeft1.set(ControlMode.PercentOutput, 0);
		

		if (joystick.getX() < 0.5 && joystick.getX() > -0.5) {
			chassisTalonRight1.set(ControlMode.PercentOutput, joystick.getY());
			chassisTalonRight2.set(ControlMode.PercentOutput, joystick.getY());
			chassisTalonLeft1.set(ControlMode.PercentOutput, joystick.getY());
			chassisTalonLeft2.set(ControlMode.PercentOutput, -joystick.getY());
		} else {
			if (joystick.getX() < - 0.5) {
				chassisTalonRight1.set(ControlMode.PercentOutput, joystick.getX());
				chassisTalonRight2.set(ControlMode.PercentOutput, joystick.getX());
				chassisTalonLeft1.set(ControlMode.PercentOutput,  -joystick.getX());
				chassisTalonLeft2.set(ControlMode.PercentOutput,  joystick.getX());
			} else if (joystick.getX() > 0.5) {
				chassisTalonRight1.set(ControlMode.PercentOutput, joystick.getX());
				chassisTalonRight2.set(ControlMode.PercentOutput, joystick.getX());
				chassisTalonLeft1.set(ControlMode.PercentOutput,  -joystick.getX());
				chassisTalonLeft2.set(ControlMode.PercentOutput,  joystick.getX());
			}
		}
	}
}
