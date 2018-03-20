package com.husbylabs.Jello.Drive;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class JelloPID extends PIDSubsystem {

    private DifferentialDrive differentialDrive;
    private MecanumDrive mecanumDrive;

    private double angle = 0;
    private double distance = 0;
    private double forwardSpeed = 0;
    private boolean isMecanumDrive;


    public JelloPID(double kP, double kI, double kD) {
        super(kP, kI, kD);
        this.getPIDController().setContinuous(true);
    }

    public void returnAngleInput(double input) {
        this.angle = input;
    }

    public void setInputRange(int min, int max) {
        this.setInputRange(min,max);
    }

    public void setMaxSpeed(double speed) {
        this.setOutputRange(-speed,speed);
    }

    public void setAbsouteTolerance(double tolerance) {
        this.setAbsoluteTolerance(tolerance);
    }


    public void setDrivetrain(DifferentialDrive differentialDrive) {
        this.differentialDrive = differentialDrive;
        this.isMecanumDrive = false;
    }

    public void setDrivetrain(MecanumDrive mecanumDrive) {
        this.mecanumDrive = mecanumDrive;
        this.isMecanumDrive = true;
    }

    public void startPID() {
        this.startPID();
    }

    public void stopPID() {
        this.stopPID();
    }

    public void setAngle(double turnTo) {
        this.setSetpoint(turnTo);
    }

    public boolean isOnTarget() {
        return onTarget();
    }

    public void stopMotors() {
        if(isMecanumDrive) {
            mecanumDrive.stopMotor();
        } else {
            differentialDrive.stopMotor();
        }
    }

    public double getCurrentAngle() {
        return angle;
    }

    public void updateDistance(double distance) {
        this.distance = distance;
    }

    public double getDistance(){
        return this.distance;
    }

    @Override
    protected double returnPIDInput() {
        return angle;
    }

    public void setForwardSpeed(double speed) {
        this.forwardSpeed = speed;
    }


    @Override
    protected void usePIDOutput(double output) {
        if(isMecanumDrive) {
            mecanumDrive.driveCartesian(0,forwardSpeed,output);
        } else {
            differentialDrive.arcadeDrive(forwardSpeed, output);
        }
    }

    @Override
    protected void initDefaultCommand() {

    }
}
