/** 
 * Tim the timer.
 *
 * "Hi Tim!"
 * "How much Tim - "
 *
 * Tim tim = new Tim();
 * do stuff 
 * tim.elapsedms()
 */
public class Tim {
	double start, end;
    public Tim() {
        this.start = System.currentTimeMillis();
    }
    public double elapsedms() {
        this.end = System.currentTimeMillis();
        return (end-start);
    }
}

