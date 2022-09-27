package game;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MineFrame extends JFrame {

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MineFrame frame = new MineFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MineFrame() {
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Minesweeper");
		ImageIcon icon = new ImageIcon("src/images/custom_java_icon.png");
		this.setIconImage(icon.getImage());
		this.setLocation(300, 20);
		this.setResizable(false);
		setContentPane(new MinePanel());
		this.pack();
	}
}
