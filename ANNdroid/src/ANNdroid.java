package ANNdroid.src;

import ANNdroid.src.panels.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Point;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.CardLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;

public class ANNdroid extends JFrame{

	public static final Toolkit TK = Toolkit.getDefaultToolkit();

	//public static final int SCREEN_WIDTH = TK.getScreenSize().width;
	//public static final int SCREEN_HEIGHT = TK.getScreenSize().height;
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;	
	public static final int SCREEN_XPOS = TK.getScreenSize().width/4;
	public static final int SCREEN_YPOS = TK.getScreenSize().height/8;

	public static Cursor cursor;

	//public static Simulator simulator;

	public static JPanel glass;

	// Card Layout //
	public final static String LOGINPANEL = "login";
	public final static String ADMINPANEL = "admin";
	public final static String TEACHERPANEL = "teacher";
	public final static String STUDENTPANEL = "student";
	public final static String GAMEPANEL = "game";

	// Cards/Panels //
	public static JPanel cards;	
	public static JPanel bgPanel;
	public static JPanel loginPanel;
	public static JPanel adminPanel;
	public static JPanel teacherPanel;
	public static JPanel studentPanel;
	public static JPanel gamePanel;

	public ANNdroid(){

		super("ANNdroid Invasion");
		//simulator = new Simulator();
		cursor = TK.createCustomCursor(TK.getImage("ANNdroid/resources/img/cursor.png"), new Point(getX(), getY()), "Cursor");

		glass = (JPanel)this.getGlassPane();

		// Frame Properties //
		setCursor(cursor);
		setIconImage(TK.getImage("ANNdroid/resources/img/large_logo.png"));
		glass.setVisible(true);
		//setUndecorated(true);
		//setResizable(false);
		//setExtendedState(MAXIMIZED_BOTH);
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setMinimumSize(new Dimension(800, 600));
		setLocation(SCREEN_WIDTH/3, SCREEN_HEIGHT/8);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		addComponentToPane(this.getContentPane());
		addComponentListener(new ResizeListener());

		// Show Frame //
		pack();
		setVisible(true);
	}

	public void addComponentToPane(Container c){

		// Initialize Cards //
		//gamePanel = new GamePane();
		bgPanel = new BackgroundPanel(SCREEN_HEIGHT, SCREEN_WIDTH);
		loginPanel = new LoginPanel(bgPanel, SCREEN_HEIGHT, SCREEN_WIDTH);
		//adminPanel = new AdminPanel();
		//teacherPanel = new TeacherPanel();
		//studentPanel = new StudentPanel(this);

		// Add Cards/Panel to Cards Container //
		cards = new JPanel(new CardLayout());
		cards.add(loginPanel, LOGINPANEL);
		//cards.add(adminPanel, ADMINPANEL);
		//cards.add(teacherPanel, TEACHERPANEL);
		//cards.add(studentPanel, STUDENTPANEL);
		//cards.add(gamePanel, GAMEPANEL);

		c.add(cards);
	}

	public static void main(String args[]){
		SwingUtilities.invokeLater(()->new ANNdroid());
	}

	// Resizes the Components //
	private class ResizeListener extends ComponentAdapter{
		public void componentResized(ComponentEvent e){
			bgPanel.setBounds(0, 0, getWidth(), getHeight()); // Resizes the bgPanel so that the resize() will execute // 
			//loginPanel.setBounds(0, 0, getWidth(), getHeight());
			//adminPanel.setBounds(0, 0, getWidth(), getHeight());
			//teacherPanel.setBounds(0, 0, getWidth(), getHeight());
			//studentPanel.setBounds(0, 0, getWidth(), getHeight());
			((BackgroundPanel)bgPanel).resize(); // Resizes the bgImage //
			//((LoginPanel)loginPanel).resize();
			//((AdminPanel)adminPanel).resize();
			//((TeacherPanel)teacherPanel).resize();
			//((StudentPanel)studentPanel).resize();
			//((GamePane)gamePanel).resize();
		}
	}
}
