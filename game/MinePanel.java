package game;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.Timer;


import java.awt.event.*;

public class MinePanel extends JPanel implements MouseListener
{
	private int width = 500, height = 525; //500 525
	private int unit_size = 16; //25
	int numRows = (height-unit_size) / unit_size;
	int numCols = width / unit_size;
	private JLabel top;
	private JLabel[][] labels;
	private ImageIcon[][] icons;
	private JPanel centerPanel;
	private Random rand = new Random();
	private int numMines;
	private ImageIcon tile = new ImageIcon("images/blank.png");
	private ImageIcon mine = new ImageIcon("images/mine.png");
	private ImageIcon flag = new ImageIcon("images/flag.png");
	private ImageIcon blank = new ImageIcon("images/0.png");
	private JLabel time;
	private long startTime, elapsedTime;
	Timer timer = new Timer(0, new TimeListener());
	private String [] difficulty = {"baby", "easy", "medium", "hard", "impossible"};
	private int difficultyNum = 0;
	private String str;
	
	public MinePanel() 
	{
		this.setPreferredSize(new Dimension(width, height));
		this.setLayout(new BorderLayout());
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(numRows, numCols));
		this.top = new JLabel("game");
		this.add(top, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		str = JOptionPane.showInputDialog("Enter the Difficulty: baby, easy, medium, hard, impossible").toLowerCase();
		String temp = "";
		for (int x = 0; x < str.length(); x++)
		 {
			 if (Character.isLetter(str.charAt(x)))
			 {
				 temp += str.charAt(x);
			 }
		 }
		str = temp;
		System.out.println(str);
		
		setBoard();
		addMines();
		addCount();
		timer.start();
		startTime = System.currentTimeMillis();
		
	}
	private void setBoard()
	{
		labels = new JLabel[numRows][numCols];
		for (int row = 0; row < numRows; row++)
		{
			for (int col = 0; col < numCols; col++)
			{
				labels[row][col] = new JLabel();
				labels[row][col].setIcon(tile);
				labels[row][col].addMouseListener(this);
				centerPanel.add(labels[row][col]);
			}
		}
	}
	private void addMines()
	{
		icons = new ImageIcon[numRows][numCols];
		numMines = 0;
		for (int row = 0; row < numRows; row++)
		{
			for (int col = 0; col < numCols; col++)
			{
				icons[row][col] = new ImageIcon();
//				if(rand.nextInt(100) < 20)
//				{
//					icons[row][col] = mine;
//					numMines++;
//				}
				
			}
		}
		if(str.equals("easy"))
		{
			
			while (difficultyNum < 25)
			{
				
				int r1 = rand.nextInt(numRows);
				int r2 = rand.nextInt(numCols);
				if(icons[r1][r2] != mine)
				{
					icons[r1][r2] = mine;
					numMines++;
					difficultyNum++;
				}
				
			}
			str = "";
		}
		else if (str.equals("medium"))
		{
			while (difficultyNum < 50)
			{
				
				int r1 = rand.nextInt(numRows);
				int r2 = rand.nextInt(numCols);
				if(icons[r1][r2] != mine)
				{
					icons[r1][r2] = mine;
					numMines++;
					difficultyNum++;
				}
				
			}
			str = "";
		}
		else if (str.equals("hard"))
		{
			while (difficultyNum < 100)
			{
				
				int r1 = rand.nextInt(numRows);
				int r2 = rand.nextInt(numCols);
				if(icons[r1][r2] != mine)
				{
					icons[r1][r2] = mine;
					numMines++;
					difficultyNum++;
				}
				
			}
			str = "";
		}
		else if (str.equals("baby"))
		{
			while (difficultyNum < 1)
			{
				
				int r1 = rand.nextInt(numRows);
				int r2 = rand.nextInt(numCols);
				if(icons[r1][r2] != mine)
				{
					icons[r1][r2] = mine;
					numMines++;
					difficultyNum++;
				}
				
			}
			str = "";
		}
		else if (str.equals("impossible"))
		{
			while (difficultyNum < 399)
			{
				
				int r1 = rand.nextInt(numRows);
				int r2 = rand.nextInt(numCols);
				if(icons[r1][r2] != mine)
				{
					icons[r1][r2] = mine;
					numMines++;
					difficultyNum++;
				}
				
			}
			str = "";
		}
		this.top.setText("Mines left: " + numMines);
	}
	private void addCount()
	{
		for (int row = 0; row < numRows; row++)
		{
			for (int col = 0; col < numCols; col++)
			{
				if (icons[row][col].equals(mine))
				{
					continue;
				}
				int counter = 0;
				//check top left
				if (col - 1 >= 0 && row - 1 >= 0)
				{
					if (icons[row-1][col-1].equals(mine))
					{
						counter++;
					}
				}
				//check top
				if (row - 1 >= 0)
				{
					if(icons[row-1][col].equals(mine)) 
					{
						counter++;
					}
				}
				//check top right
				if (col + 1 < numCols && row - 1 >= 0)
				{
					if (icons[row-1][col+1].equals(mine))
					{
						counter++;
					}
				}
				//check left
				if (col - 1 >= 0)
				{
					if (icons[row][col-1].equals(mine))
					{
						counter++;
					}
				}
				//check right
				if (col + 1 < numCols)
				{
					if (icons[row][col+1].equals(mine))
					{
						counter++;
					}
				}
				//check bottom left
				if (col - 1 >= 0 && row + 1 < numRows)
				{
					if (icons[row+1][col-1].equals(mine))
					{
						counter++;
					}
				}
				//check bottom
				if (row + 1 < numRows)
				{
					if (icons[row+1][col].equals(mine))
					{
						counter++;
					}
				}
				//check bottom right
				if (col + 1 < numCols && row + 1 < numRows)
				{
					if (icons[row+1][col+1].equals(mine))
					{
						counter++;
					}
				}
				//add image
				if (counter > 0)
				{
					icons[row][col] = new ImageIcon("images/" + counter + ".png");
				}
				else
				{
					icons[row][col] = blank;
				}
				
				
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) 
	{
		
		Object src = e.getSource();
		if (e.getButton() == MouseEvent.BUTTON1)
		{
			for (int row = 0; row < numRows; row++)
			{
				for (int col = 0; col < numCols; col++)
				{
					if (src == labels[row][col] && labels[row][col].getIcon().equals(tile))
					{
						labels[row][col].setIcon(icons[row][col]);
						labels[row][col].removeMouseListener(this);
						if (icons[row][col].equals(mine))
						{
							gameOver();
						}
						if (icons[row][col].equals(blank))
						{
							showBlanks(row, col);
						}
					}
				}
			}
		}
		else if (e.getButton() == MouseEvent.BUTTON2)
		{
			System.out.println("middle button clicked");
		}
		else if (e.getButton() == MouseEvent.BUTTON3)
		{
			for (int row = 0; row < numRows; row++)
			{
				for (int col = 0; col < numCols; col++)
				{
					if (src == labels[row][col])
					{
						if (labels[row][col].getIcon().equals(flag))
						{
							labels[row][col].setIcon(tile);
							numMines++;
							top.setText("Mines left: " + numMines);
						}
						else if(labels[row][col].getIcon().equals(tile))
						{
							labels[row][col].setIcon(flag);
							numMines--;
							top.setText("Mines left: " + numMines);
						}
					}
				}
			}
		}
		if (win())
		{
			int answer = JOptionPane.showConfirmDialog(null, "You Won!", "Game Over",JOptionPane.OK_CANCEL_OPTION);
			System.exit(0);
			/* if (answer == 0)
			 {
				 str = JOptionPane.showInputDialog("Enter the Difficulty").toLowerCase();
					String temp1 = "";
					for (int x = 0; x < str.length(); x++)
					 {
						 if (Character.isLetter(str.charAt(x)))
						 {
							 temp1 += str.charAt(x);
						 }
					 }
					str = temp1;
					System.out.println(str);
				 	ImageIcon temp = new ImageIcon();
				 	for (int row = 0; row < numRows; row++)
				 	{
				 		for (int col = 0; col < numCols; col++)
				 		{
				 			labels[row][col].setIcon(tile);
				 			labels[row][col].removeMouseListener(this);
				 			labels[row][col].addMouseListener(this);
				 			
				 		}
				 	}
				 	elapsedTime = 0;
				 	
				 	addMines();
					addCount();
					startTime = System.currentTimeMillis();
					timer.start();
			 }
			 else
			 {
				 System.exit(0);
			 }
			 */
		}
	}
	private void showBlanks(int row, int col) 
	{
		if (!(icons[row][col].equals(blank)))
		{
			labels[row][col].setIcon(icons[row][col]);
			labels[row][col].removeMouseListener(this);
			return;
		}
		if(icons[row][col].equals(blank))
		{
			labels[row][col].setIcon(blank);
			labels[row][col].removeMouseListener(this);
			
			if(row - 1 >= 0 && col - 1 >= 0 && labels[row-1][col-1].getMouseListeners().length > 0)
			{
				showBlanks(row-1,col-1);
			}
			if(row - 1 >= 0 && labels[row-1][col].getMouseListeners().length > 0)
			{
				showBlanks(row-1,col);
			}
			if(row - 1 >= 0 && col + 1 < numCols && labels[row-1][col+1].getMouseListeners().length > 0)
			{
				showBlanks(row-1,col+1);
			}
			if (col - 1 >= 0 && labels[row][col-1].getMouseListeners().length > 0)
			{
				showBlanks(row, col-1);
			}
			if (col + 1 < numCols && labels[row][col+1].getMouseListeners().length > 0)
			{
				showBlanks(row, col+1);
			}
			if (row + 1 < numRows && col - 1 >= 0 && labels[row+1][col-1].getMouseListeners().length > 0)
			{
				showBlanks(row+1,col-1);
			}
			if (row + 1 < numRows  && labels[row+1][col].getMouseListeners().length > 0)
			{
				showBlanks(row+1,col);
			}
			if (row + 1 < numRows && col + 1 < numCols && labels[row+1][col+1].getMouseListeners().length > 0)
			{
				showBlanks(row+1,col+1);
			}
			
		}
	}
	private void gameOver() 
	{
		timer.stop();
		System.out.println("L");
		int answer = JOptionPane.showConfirmDialog(null, "You Hit a Mine and Lost :(", "Game Over",JOptionPane.OK_CANCEL_OPTION);
		System.exit(0);
		 /*if (answer == 0)
		 {
			 	ImageIcon temp = new ImageIcon();
			 	for (int row = 0; row < numRows; row++)
			 	{
			 		for (int col = 0; col < numCols; col++)
			 		{
			 			labels[row][col].setIcon(tile);
			 			labels[row][col].removeMouseListener(this);
			 			labels[row][col].addMouseListener(this);

			 		}
			 	}
			 	elapsedTime = 0;
			 	str = JOptionPane.showInputDialog("Enter the Difficulty").toLowerCase();
				String temp1 = "";
				for (int x = 0; x < str.length(); x++)
				 {
					 if (Character.isLetter(str.charAt(x)))
					 {
						 temp1 += str.charAt(x);
					 }
				 }
				str = temp1;
				System.out.println(str);
			 	addMines();
				addCount();
				startTime = System.currentTimeMillis();
				timer.start();
		 }
		 else
		 {
			 System.exit(0);
		 }
	*/
	}
	private boolean win()
	{
		for (int row = 0; row < numRows; row++)
		{
			for (int col = 0; col < numCols; col++)
			{
				if(labels[row][col].getIcon().equals(tile))
				{
					return false;
				}
			}
		}
		return true;
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	 public class TimeListener implements ActionListener
	    {
	   	 @Override
	   	 public void actionPerformed(ActionEvent e)
	   	 {
	   		elapsedTime = System.currentTimeMillis() - startTime;
			long elapsedSeconds = elapsedTime / 1000;
			long secondsDisplay = elapsedSeconds % 60;
			long elapsedMinutes = elapsedSeconds / 60;
			long minutesDisplay = elapsedMinutes % 60;
			long elapsedHours = elapsedMinutes / 60;
			
			
	   		top.setText("Mines left: " + numMines + "                  "
	   				+ "                                "
	   				+ "                             "
	   				+ "                    "
	   				+ "                  Time: " + elapsedHours +":" + minutesDisplay +":" + secondsDisplay);
	   	 }
	    }

}
