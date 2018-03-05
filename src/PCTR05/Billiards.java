import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class Billiards extends JFrame {

	public static int Width = 800;
	public static int Height = 600;

	private JButton b_start, b_stop;

	private Board board;

	private final int N_BALL = 8;
	private Ball[] balls;
	Thread[] hilos;

	public Billiards() {

		board = new Board();
		board.setForeground(new Color(0, 128, 0));
		board.setBackground(new Color(0, 128, 0));

		hilos = new Thread[N_BALL];
		balls = new Ball[N_BALL];
		
		initBalls();

		b_start = new JButton("Empezar");
		b_start.addActionListener(new StartListener());
		b_stop = new JButton("Parar");
		b_stop.addActionListener(new StopListener());

		JPanel p_Botton = new JPanel();
		p_Botton.setLayout(new FlowLayout());
		p_Botton.add(b_start);
		p_Botton.add(b_stop);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(board, BorderLayout.CENTER);
		getContentPane().add(p_Botton, BorderLayout.PAGE_END);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Width, Height);
		setLocationRelativeTo(null);
		setTitle("Práctica programación concurrente objetos móviles independientes");
		setResizable(false);
		setVisible(true);
	}

	private void initBalls() {
		for(int i = 0;i<balls.length;i++) {
			balls[i] = new Ball();
		}
		board.setBalls(balls);
	}

	private class StartListener implements ActionListener {
				
		@Override
		public void actionPerformed(ActionEvent arg0) {
			for(int i = 0;i<balls.length;i++) {
				hilos[i] = new Thread(new BallThread(balls[i],board));
			}
			
			for(Thread t : hilos) {
				t.start();
			}
		}
	}

	private class StopListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			for(Thread t : hilos) {
				t.interrupt();
			}

			for(int i = 0;i<balls.length;i++) {
				hilos[i] = null;
			}
			
		}
	}

	public static void main(String[] args) {
		new Billiards();
	}
}