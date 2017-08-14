/** 
 * Tim the timer.
 *
 * Tim tim = new Tim();
 * do stuff 
 * tim.elapsedms()
 */
public class Tim {
	double cumulativeTotal, start, end;

    public Tim() {
		cumulativeTotal = 0.0;
		start = 0.0; 
		end = 0.0;;
	}
	public void tic() {
        this.start = System.currentTimeMillis();
	}
	public void toc() {
		this.end = System.currentTimeMillis();
		cumulativeTotal += (end-start);
	}
    public double elapsedms() {
        return cumulativeTotal;
    }
}


