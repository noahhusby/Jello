package com.husbylabs.Jello.Drive;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

public class JelloGear {

    DoubleSolenoid doubleShift;
    Solenoid lowShift;
    Solenoid highShift;
    boolean doubleShiftMode;
    boolean reversed;

    public JelloGear(DoubleSolenoid gearShift) {
        this.doubleShift = gearShift;
        this.doubleShiftMode = true;
    }

    public JelloGear(Solenoid lowSolenoid, Solenoid highSolenoid) {
        this.lowShift = lowSolenoid;
        this.highShift = highSolenoid;
        this.doubleShiftMode = false;
    }

    public void setReversed(boolean reversed) {
        this.reversed = reversed;
    }

    public void lowShift() {
        if(doubleShiftMode) {
            if(reversed) {
                doubleShift.set(DoubleSolenoid.Value.kForward);
            } else {
                doubleShift.set(DoubleSolenoid.Value.kReverse);
            }
        } else {
            if(reversed) {
                lowShift.set(false);
                highShift.set(true);
            } else {
                lowShift.set(true);
                lowShift.set(false);
            }
        }
    }

    public void highShift() {
        if(doubleShiftMode) {
            if(reversed) {
                doubleShift.set(DoubleSolenoid.Value.kReverse);
            } else {
                doubleShift.set(DoubleSolenoid.Value.kForward);
            }
        } else {
            if(reversed) {
                lowShift.set(true);
                highShift.set(false);
            } else {
                lowShift.set(false);
                lowShift.set(true);
            }
        }
    }
}
