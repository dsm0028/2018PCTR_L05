import java.util.concurrent.*;

public class BallThread implements Runnable {

	Ball bola;
	Board tablero;
	
	public BallThread(Ball b, Board t) {
		this.bola = b;
		this.tablero = t;
	}
	
	@Override
	public void run() {
		for(;;) {
			try {
			bola.move();
			tablero.repaint();
			TimeUnit.MILLISECONDS.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}

}
