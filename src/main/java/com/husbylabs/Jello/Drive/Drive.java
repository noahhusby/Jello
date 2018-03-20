package com.husbylabs.Jello.Drive;

import edu.wpi.first.wpilibj.command.Command;

public class Drive extends Command {
    boolean isFinished = false;
    JelloDrive jelloDrive;

    double distance = 0;
    double speed = 0;
    double startAngle;
    double startDistance;

    public Drive(JelloDrive jelloDrive, double distance, double speed) {
        this.distance = distance;
        this.jelloDrive = jelloDrive;
        this.speed = speed;
    }

    protected void initialize() {
        startAngle = jelloDrive.getCurrentAngle();
        startDistance = jelloDrive.getDistance();
    }
    protected void execute() {

        jelloDrive.setPIDSpeed(speed);

        if(jelloDrive.getDistance() < distance + startDistance) {
            jelloDrive.setTurnAngle(startAngle);
            jelloDrive.startPID();
        } else {
            jelloDrive.stopPID();
            jelloDrive.stopMotors();
            isFinished = true;
        }
    }
    protected boolean isFinished() {
        return isFinished;
    }
    protected void end() {
        jelloDrive.stopPID();
    }
    protected void interrupted() {}
}
