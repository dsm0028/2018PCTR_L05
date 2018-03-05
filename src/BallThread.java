
public class BallThread implements Runnable {

	Ball bola;
	
	public BallThread(Ball b) {
		this.bola = b;
	}
	
	@Override
	public void run() {
		for(;;)
			bola.move();
	}

}
