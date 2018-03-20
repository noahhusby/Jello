package com.husbylabs.Jello.Drive;

import edu.wpi.first.wpilibj.command.Command;

public class Turn extends Command {
    double startAngle = 0;
    JelloDrive jelloDrive;
    boolean isFinished = false;

    public Turn(JelloDrive jelloDrive, double angle) {
        this.jelloDrive = jelloDrive;
        this.startAngle = angle + jelloDrive.getCurrentAngle();
    }
    protected void initialize() {
    }
    protected void execute() {
        jelloDrive.setPIDSpeed(0);
        jelloDrive.setTurnAngle(startAngle);
        jelloDrive.startPID();
        if(jelloDrive.onTarget()) {
            jelloDrive.stopPID();
            jelloDrive.stopMotors();
            isFinished = true;
        }

    }
    protected boolean isFinished() {
        return isFinished;
    }
    protected void end() {}
    protected void interrupted() {}
}
