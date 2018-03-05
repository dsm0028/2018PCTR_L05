package PCTR05;

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
		try {
		while(!Thread.currentThread().isInterrupted()) {
			
			bola.move();
			tablero.repaint();
			TimeUnit.MILLISECONDS.sleep(30);
			
			}
		} catch (InterruptedException e) {
			System.err.println("Detenido: "+Thread.currentThread().getName());
		}	
	}
	

}
