package com.husbylabs.Jello.Drive;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class JelloDrive {
    private DifferentialDrive differentialDrive;
    private MecanumDrive mecanumDrive;

    private JelloGear gear;
    private JelloPID jelloPID;
    private double driveMultiplier = 1;

    private boolean reversed;
    private boolean mecanum;


    /**
     * The constructor for a new JelloDrive using DiffrentialDrive
     * @param drive DiffrentialDrive object with configured motor controllers
     */
    public JelloDrive(DifferentialDrive drive) {
        this.mecanum = false;

    }

    /**
     * The constructor for a new JelloDrive using MecanumDrive
     * @param drive MecanumDrive object with configured motor controllers
     */
    public JelloDrive(MecanumDrive drive) {
        this.mecanum = true;
    }

    /**
     * Adds JelloGear, which allows a gear shift to be controlled by JelloDrive
     * @param jelloGear JelloGear constructor with configured solenoid
     */
    public void addGear(JelloGear jelloGear) {
        this.gear = jelloGear;
    }

    /**
     * Adds JelloPID to JelloDrive, used for autonomous control of robot
     * @param jelloPID
     */
    public void addPID(JelloPID jelloPID) {
        this.jelloPID = jelloPID;
        if(mecanum) {
            this.jelloPID.setDrivetrain(mecanumDrive);
        } else {
            this.jelloPID.setDrivetrain(differentialDrive);
        }
    }

    /**
     * Looped method from a gyroscope to keep JelloPID updated
     * @param angle Angle from gyroscope
     */
    public void returnAngleInput(double angle) {
        jelloPID.returnAngleInput(angle);
    }

    public void returnDistanceInput(double distance) {
        jelloPID.updateDistance(distance);
    }

    public double getDistance() {
        return jelloPID.getDistance();
    }

    public void setTurnAngle(double angle) {
        jelloPID.setAngle(angle);
    }

    public void startPID() {
        jelloPID.startPID();
    }

    public void stopPID() {
        jelloPID.stopPID();
    }

    public double getCurrentAngle() {
        return jelloPID.getCurrentAngle();
    }

    public void stopMotors() {
        jelloPID.stopMotors();
    }

    public boolean onTarget() {
        return jelloPID.isOnTarget();
    }
    /**
     * Multiplier to control driveTrain speed
     * @param driveMultiplier Speed Multiplier
     */
    public void setDriveMultiplier(double driveMultiplier) {
        this.driveMultiplier = driveMultiplier;
    }

    public void setPIDSpeed(double speed) {
        jelloPID.setForwardSpeed(speed);
    }

    /**
     * Sets the forwardBackwardAxis in reverse
     * @param reversed True for on, false for off
     */
    public void setReversed(boolean reversed) {
        this.reversed = reversed;
    }

    /**
     * Drives the selected DriveTrain
     * @param forwardBackwardsAxis See DifferentialDrive.arcadeDrive.
     * @param turningAxis See DifferentialDrive.arcadeDrive.
     */
    public void drive(double forwardBackwardsAxis, double turningAxis) {
        if(mecanum) {
            if(reversed) {

            } else {

            }
        } else {
            if(reversed) {
                differentialDrive.arcadeDrive(-1 * driveMultiplier * forwardBackwardsAxis, driveMultiplier * turningAxis);
            }
            else {
                differentialDrive.arcadeDrive( driveMultiplier * forwardBackwardsAxis, driveMultiplier * turningAxis);
            }
        }
    }



}
