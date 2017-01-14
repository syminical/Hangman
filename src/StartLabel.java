
//theworldisquiethere

import javax.swing.*;
import java.awt.*;

public class StartLabel extends JLabel {

	private int c = 255, c2 = 200;
	private boolean dec = true;

	public StartLabel(String Container) {

		super(Container);

		setForeground(new Color(c, c, c));

	}

	//controls animation stage based on (int)c and (boolean)dec
	public void animate() {

		if (dec) setForeground(new Color(c, c, c));
		else setForeground(new Color(c2, c2, c2));

		dec = !dec;

	}

	//setup for startButton mode
	public void start() {

		setForeground(Color.WHITE);
		setText("Start Game");

	}

	//setup for stopButton mode
	public void stop() {

		setForeground(new Color(92, 92, 92));
		setText("Stop Game");

	}

}
