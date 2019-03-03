package genericheap;

// Class Angle is used to store an angle in degrees and minutes, as in "29
// degrees and 35 minutes."

public class Angle implements Comparable<Angle> {
    private int degrees;
    private int minutes;

    // pre: minutes <= 59 and minutes >= 0 and degrees >= 0
    //    (throws IllegalArgumentException if not true)
    public Angle(int degrees, int minutes) {
        if (minutes < 0 || minutes > 59 || degrees < 0)
            throw new IllegalArgumentException("degrees: " + degrees
                    + " minutes: " + minutes);
        this.degrees = degrees;
        this.minutes = minutes;
    }

    // post: returns a String representation of this angle as in "29 d 35 m"
    @Override
    public String toString() {
        return degrees + "d " + minutes + "m";
    }

    // post: returns the integer degrees for this angle
    public int getDegrees() {
        return degrees;
    }

    // post: returns the minutes portion of this angle (not counting degrees)
    public int getMinutes() {
        return minutes;
    }

    // post: returns a new angle that represents the sum of this angle and the
    //     given angle
    public Angle add(Angle other) {
        int d = this.degrees + other.degrees;
        int m = this.minutes + other.minutes;
        if (m >= 60) m -= 60;
        return new Angle(d, m);
    }

    @Override
    public int compareTo(Angle other) {
        if (this.degrees != other.degrees)
            return this.degrees - other.degrees;
        return this.minutes - other.minutes;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Angle) {
            Angle other = (Angle) obj;
            return this.degrees == other.degrees && this.minutes == other.minutes;
        }
        return false;
    }
}