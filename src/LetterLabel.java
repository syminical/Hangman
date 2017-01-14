
//theworldisquiethere

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class LetterLabel extends JLabel {

	private int c = 0;
	private boolean fade = false, fading = false, clicked = false;
	private LetterLabel self;
	private String Letter;

	public LetterLabel(String Container) {

		super(Container, SwingConstants.CENTER);

		self = this;
		Letter = Container.toLowerCase();
		config();

	}

	//controls mouseOver fade, based on (int)c
	public int fade() {

		if (c > 0) {

			setBackground(new Color(c, c, c, 240));
			//repaint();
			c--;

			return 0;

		} else {

			setOpaque(false);
			repaint();
			fading = false;
			fade = false;

			return 1;

		}

	}

	//turns on key gloss glow
	public void on() {

		if (!fading && !clicked) { setOpaque(true); setBackground(new Color(43, 43, 43, 240)); }

	}

	//turns off key gloss glow
	public void off() {

		if (!fading && !clicked) { setOpaque(false); repaint(); }

	}

	//reset key animations
	public void reset() {

		clicked = false;
		fade = false;
		fading = false;
		c = 0;
		setOpaque(false);
		setForeground(Color.LIGHT_GRAY);
		setBorder(new LineBorder(Color.DARK_GRAY, 1, true));

	}

	public String letter() { return Letter; }

	public boolean clicked() { return clicked; }

	//setup key properties and listener
	public void config() {

		setPreferredSize(new Dimension(40, 40));
		setFont(new Font("Seriff", Font.BOLD, 26));
		setForeground(Color.LIGHT_GRAY);
		setBorder(new LineBorder(Color.DARK_GRAY, 1, true));

		this.addMouseListener(new MouseAdapter() {

			//change look, notify game of click
			public void mousePressed(MouseEvent e) {

				if ((Hangman.box() != null && !Hangman.box().gameRunning()) || clicked) return;

				clicked = true;
				setOpaque(true);
				setForeground(new Color(92, 92, 92, 240));
				setBackground(new Color(22, 22, 22, 240));
				setBorder(new LineBorder(new Color(0x303030), 1, true));
				repaint();
				Hangman.box().click(self);

			}

			//start mouseOver glow
			public void mouseEntered(MouseEvent e) {

				if (clicked) return;

				fading = true;

				if (fade) {

					ArrayList<LetterLabel> Temp = Hangman.box().getFadeList();

					if (Temp != null) Temp.remove(self);

				}

				setOpaque(true);
				setBackground(new Color(41, 41, 41, 240));

			}

			//start mouseOver glow fade
			public void mouseExited(MouseEvent e) {

				if (clicked) return;

				fade = true;
				c = 41;
				ArrayList<LetterLabel> Temp = Hangman.box().getFadeList();

				if (Temp != null) Temp.add(self);
				else { setOpaque(false); }

			}

		});

	}

}
