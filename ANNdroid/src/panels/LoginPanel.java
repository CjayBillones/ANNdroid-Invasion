package ANNdroid.src.panels;

//import ANNdroid.src.custom_swing.*;
//import ANNdroid.src.objects.*;
//import ANNdroid.src.events.*;
import ANNdroid.src.util.*;
import ANNdroid.src.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class LoginPanel extends JPanel{

	SoundPlayer bgmx;

	public LoginPanel(JPanel bgPanel, int height, int width){

		bgmx = new SoundPlayer("ANNdroid/resources/sounds/pso2_1.mp3", true);
		bgmx.play();

		setLayout(null);
		setPreferredSize(new Dimension(width, height));
		setBounds(0, 0, width, height);

		add(bgPanel);
		
	}

}