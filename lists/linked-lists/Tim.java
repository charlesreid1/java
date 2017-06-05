/** 
 * Tim the timer.
 *
 * "Hi Tim!"
 * "How much Tim - "
 *
 * Tim tim = new Tim();
 * do stuff 
 * tim.elapsed()
 */
class Tim {
	double start, end;
    public Tim() {
        this.start = System.currentTimeMillis();
    }
    public double elapsed() {
        this.end = System.currentTimeMillis();
        return (end-start)/1000.0;
    }
}

