
//theworldisquiethere

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.*;

public class Hangman extends JFrame {

	private static Hangman box;
	private String word = "", rawWord = "X X X X X X X X X X X X X X X X";
	private String[] words = { "water", "loquacious", "valiant", "supple", "programing", "pineapple", "radiant", "mystery" };
	private int glow = 1, bc = 0, tries = 0;
	private int[] borderColour = { 0x303030, 0x202020 };
	private boolean gameRunning = false;
	private DragListener drag = new DragListener();
	private JPanel atlas = new JPanel();
	private JPanel top = new JPanel();
	private JPanel mid = new JPanel();
	private JPanel bot = new JPanel();
	private JPanel minP = new JPanel();
	private JPanel closeP = new JPanel();
	private JPanel text = new JPanel();
	private JPanel Labels = new JPanel();
	private JPanel row1 = new JPanel();
	private JPanel row2 = new JPanel();
	private JPanel row3 = new JPanel();
	private StartLabel start = new StartLabel("Start Game");
	private JLabel min = new JLabel("-");
	private JLabel close = new JLabel("X");
	private JLabel title = new JLabel("Welcome to Hangman");
	private JLabel tryHolder = new JLabel(" ", JLabel.CENTER);
	private JLabel wordHolder = new JLabel(rawWord, JLabel.CENTER);
	private LetterLabel a = new LetterLabel("A");
	private LetterLabel b = new LetterLabel("B");
	private LetterLabel c = new LetterLabel("C");
	private LetterLabel d = new LetterLabel("D");
	private LetterLabel e = new LetterLabel("E");
	private LetterLabel f = new LetterLabel("F");
	private LetterLabel g = new LetterLabel("G");
	private LetterLabel h = new LetterLabel("H");
	private LetterLabel i = new LetterLabel("I");
	private LetterLabel j = new LetterLabel("J");
	private LetterLabel k = new LetterLabel("K");
	private LetterLabel l = new LetterLabel("L");
	private LetterLabel m = new LetterLabel("M");
	private LetterLabel n = new LetterLabel("N");
	private LetterLabel o = new LetterLabel("O");
	private LetterLabel p = new LetterLabel("P");
	private LetterLabel q = new LetterLabel("Q");
	private LetterLabel r = new LetterLabel("R");
	private LetterLabel s = new LetterLabel("S");
	private LetterLabel t = new LetterLabel("T");
	private LetterLabel u = new LetterLabel("U");
	private LetterLabel v = new LetterLabel("V");
	private LetterLabel w = new LetterLabel("W");
	private LetterLabel x = new LetterLabel("X");
	private LetterLabel y = new LetterLabel("Y");
	private LetterLabel z = new LetterLabel("Z");
	private ArrayList<LetterLabel> letters = new ArrayList<LetterLabel>();
	private ArrayList<LetterLabel> glow1 = new ArrayList<LetterLabel>();
	private ArrayList<LetterLabel> glow2 = new ArrayList<LetterLabel>();
	private ArrayList<LetterLabel> glow3 = new ArrayList<LetterLabel>();
	private ArrayList<LetterLabel> glow4 = new ArrayList<LetterLabel>();
	private ArrayList<LetterLabel> glow5 = new ArrayList<LetterLabel>();
	private ArrayList<LetterLabel> fadeList = new ArrayList<LetterLabel>();

	public Hangman() {

		super("Hangman");

		box = this;

		prepareComponents();
		burden();
		buildAbox();
		animate();

	}

	//triggered by clicking start/stop button
	private void start() {

		if (gameRunning || word != "") {//sets game to inactive

			for (LetterLabel Temp : letters) Temp.reset();

			start.start();
			word = "";
			tries = 0;
			wordHolder.setText("X X X X X X X X X X X X X X X X");
			tryHolder.setText(" ");
			gameRunning = false;

		} else {//sets game to active

			start.stop();
			chooseWord();
			tryHolder.setText("tries: " + tries);
			gameRunning = true;

		}

	}

	//triggered by clicking start, chooses a random word
	private void chooseWord() {

		String Holder = "";

		word = words[(int)(Math.random() * words.length)]; //System.out.println("[" + word + "]");

		rawWord = "";

		for (int i = 0; i < word.length(); i++) { rawWord += "-"; Holder += "- "; }

		Holder = Holder.substring(0, Holder.length() - 1);

		wordHolder.setText(Holder);

	}

	//returns array, never null, of requested letter location(s) in word
	private int[] findLetters(String Container) {

		int holder = 0;//number of spots
		String holder2 = "";//actual spots

		//search, will always find at least 1 because of click()
		for (int i = 0; i < word.length(); i++) if (word.charAt(i) == Container.charAt(0)) { holder++; holder2 += i; }

		int[] answer = new int[holder];

		//pull spots from string
		for (int i = 0; i < holder; i++) answer[i] = Integer.parseInt("" + holder2.charAt(i));

		return answer;

	}

	//adds letter to all spots in word where present, also colourizes the label with html/css
	private void addLetter(String Container) {

		int[] spots = findLetters(Container);

		//replace char @ spots
		for (int i = 0; i < spots.length; i++) rawWord = rawWord.substring(0, spots[i]) + Container + rawWord.substring(spots[i] + 1, rawWord.length());

		//format the string
		String Holder = "<html>";

		for (int i = 0; i < rawWord.length(); i++) if (rawWord.charAt(i) != '-' && rawWord.charAt(i) != ' ') Holder += "<span color=white>" + rawWord.charAt(i) + "</span> "; else Holder += "- ";

		Holder = Holder.substring(0, Holder.length() - 1) + "</html>";

		//no more letters to guess
		if (!rawWord.contains("-")) gameRunning = false;

		wordHolder.setText(Holder);

	}

	//triggered by clicking on a letter
	public void click(LetterLabel Container) {

		if (!word.equals("") && word.contains(Container.letter())) { addLetter(Container.letter()); }

		tries++; tryHolder.setText("tries: " + tries);

	}

	//puts components together, sets their properties
	private void prepareComponents() {

		top.setPreferredSize(new Dimension(600, 20));
		top.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		top.setOpaque(false);
			minP.setBackground(Color.YELLOW);
			minP.setOpaque(false);
				min.setForeground(Color.YELLOW);
				min.setOpaque(false);
			minP.add(min);
			closeP.setBackground(Color.RED);
			closeP.setOpaque(false);
				close.setForeground(Color.RED);
				close.setOpaque(false);
			closeP.add(close);
		top.add(minP);
		top.add(closeP);

		mid.setPreferredSize(new Dimension(600, 300));
		mid.setOpaque(false);
			text.setPreferredSize(new Dimension(600, 110));
			text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
			text.setOpaque(false);
				title.setForeground(new Color(190, 190, 190));
				title.setFont(new Font("Seriff", Font.BOLD, 30));
				title.setAlignmentX(Component.CENTER_ALIGNMENT);
				tryHolder.setForeground(new Color(102, 102, 102));
				tryHolder.setFont(new Font("Seriff", Font.PLAIN, 15));
				tryHolder.setAlignmentX(Component.CENTER_ALIGNMENT);
				wordHolder.setForeground(new Color(128, 128, 128));
				wordHolder.setFont(new Font("Seriff", Font.BOLD, 35));
				wordHolder.setAlignmentX(Component.CENTER_ALIGNMENT);
			text.add(title);
			text.add(tryHolder);
			text.add(Box.createVerticalStrut(7));
			text.add(wordHolder);
			Labels.setPreferredSize(new Dimension(600, 190));
			Labels.setLayout(new BoxLayout(Labels, BoxLayout.Y_AXIS));
			Labels.setOpaque(false);
				row1.setPreferredSize(new Dimension(600, 25));
				row1.setLayout(new FlowLayout(FlowLayout.LEFT));
				row1.setOpaque(false);
				row1.add(Box.createHorizontalStrut(65));
				row1.add(a);
				row1.add(b);
				row1.add(c);
				row1.add(d);
				row1.add(e);
				row1.add(f);
				row1.add(g);
				row1.add(h);
				row1.add(i);
				row1.add(j);
				row2.setPreferredSize(new Dimension(600, 25));
				row2.setLayout(new FlowLayout(FlowLayout.LEFT));
				row2.setOpaque(false);
				row2.add(Box.createHorizontalStrut(65));
				row2.add(k);
				row2.add(l);
				row2.add(m);
				row2.add(n);
				row2.add(o);
				row2.add(p);
				row2.add(q);
				row2.add(r);
				row2.add(s);
				row2.add(t);
				row3.setPreferredSize(new Dimension(600, 25));
				row3.setLayout(new FlowLayout(FlowLayout.LEFT));
				row3.setOpaque(false);
				row3.add(Box.createHorizontalStrut(155));
				row3.add(u);
				row3.add(v);
				row3.add(w);
				row3.add(x);
				row3.add(y);
				row3.add(z);
			Labels.add(Box.createVerticalStrut(25));
			Labels.add(row1);
			Labels.add(row2);
			Labels.add(row3);
		mid.add(text);
		mid.add(Labels);

		bot.setPreferredSize(new Dimension(600, 40));
		bot.setLayout(new GridBagLayout());
		bot.setOpaque(false);
			start.setForeground(Color.WHITE);
			start.setOpaque(false);
			start.setFont(new Font("Seriff", Font.BOLD, 26));
		bot.add(start);

		glow1.add(a);
		glow1.add(b);
		glow1.add(k);
		glow2.add(c);
		glow2.add(d);
		glow2.add(l);
		glow2.add(m);
		glow3.add(e);
		glow3.add(f);
		glow3.add(n);
		glow3.add(o);
		glow3.add(u);
		glow3.add(v);
		glow4.add(g);
		glow4.add(h);
		glow4.add(p);
		glow4.add(q);
		glow4.add(w);
		glow4.add(x);
		glow5.add(y);
		glow5.add(i);
		glow5.add(j);
		glow5.add(r);
		glow5.add(s);
		glow5.add(t);
		glow5.add(z);

		letters.add(a);
		letters.add(b);
		letters.add(c);
		letters.add(d);
		letters.add(e);
		letters.add(f);
		letters.add(g);
		letters.add(h);
		letters.add(i);
		letters.add(j);
		letters.add(k);
		letters.add(l);
		letters.add(m);
		letters.add(n);
		letters.add(o);
		letters.add(p);
		letters.add(q);
		letters.add(r);
		letters.add(s);
		letters.add(t);
		letters.add(u);
		letters.add(v);
		letters.add(w);
		letters.add(x);
		letters.add(y);
		letters.add(z);

		componentListeners();

	}

	//makes start/stop and top right buttons click-able
	private void componentListeners() {

		minP.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) { box.setState(Frame.ICONIFIED); }

			public void mouseEntered(MouseEvent e) { minP.setOpaque(true); minP.repaint(); }

			public void mouseExited(MouseEvent e) { minP.setOpaque(false); minP.repaint(); }

		});

		closeP.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) { box.dispatchEvent(new WindowEvent(box, WindowEvent.WINDOW_CLOSING)); }

			public void mouseEntered(MouseEvent e) { closeP.setOpaque(true); closeP.repaint(); }

			public void mouseExited(MouseEvent e) { closeP.setOpaque(false); closeP.repaint();  }

		});

		start.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) { start(); };

		});

	}

	//makes atlas carry everything
	private void burden() {

		atlas.setPreferredSize(new Dimension(600, 390));
		atlas.setLayout(new BoxLayout(atlas, BoxLayout.Y_AXIS));
		atlas.setBackground(new Color(0, 0, 0, 240));
		atlas.add(top);
		atlas.add(mid);
		atlas.add(bot);

	}

	//JFrame setup
	private void buildAbox() {

		box.addMouseListener(drag);
		box.addMouseMotionListener(drag);

		box.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		box.setResizable(false);
		box.setUndecorated(true);
		box.setBackground(new Color(0, 0, 0, 0));
		box.add(new AlphaContainer(atlas));
		box.pack();
		box.setLocationRelativeTo(null);
		box.setVisible(true);

	}

	//main loop after setup, handles all animation
	private void animate() {

		double bg = 0, sl = 0, f = 0, gl = 0, cb = 0;
		int c = 0, max = 20;
		boolean inc = true, bgw = false, gw = false;

		while (true) {

			//background breath, cooler window
			if (System.currentTimeMillis() - bg >= 66) {

				if (bgw) {

					if (c > 0) c--;
					else { bgw = false; c = 0; inc = true; }

				} else {

					if (c < max && inc) c++; //:)
					else c--;

					atlas.setBackground(new Color(c, c, c, 240));

					if (c == max) inc = false;
					else if (c == 0) { bgw = true; c = (2 * max); }

				}

				bg = System.currentTimeMillis();

			}

			//start flash, draws attention (needs click to continue)
			if (!gameRunning && System.currentTimeMillis() - sl >= 333) { start.animate(); sl = System.currentTimeMillis(); }

			//key trail, more interactive
			if (System.currentTimeMillis() - f >= 20) {

				for (int i = 0; i < fadeList.size(); i++)

					if (fadeList.get(i).fade() == 1) fadeList.remove(i);

				f = System.currentTimeMillis();

			}

			//key gloss, draws attention to keys
			if (System.currentTimeMillis() - gl >= ((gw) ? 12000 : 60)) { if (gw) gw = false; if(glow()) gw = true;  gl = System.currentTimeMillis(); }

			//flashy borders, points out clickable keys
			if (gameRunning && System.currentTimeMillis() - cb >= 1000) { for(LetterLabel Temp : letters) if (!Temp.clicked()) Temp.setBorder(new LineBorder(new Color(borderColour[bc]), 1, true)); if (bc < 1) bc++; else bc = 0; cb = System.currentTimeMillis();}

			//cpu saver
			try { Thread.sleep(10); } catch(Exception e) { System.out.println("*");}

		}

	}

	//turns on gloss effect for group
	private void glowOn(ArrayList<LetterLabel> Container) {

		for (LetterLabel Temp : Container) { Temp.on(); Temp.repaint(); }

	}

	//turns off gloss effect for group
	private void glowOff(ArrayList<LetterLabel> Container) {

		for (LetterLabel Temp : Container) { Temp.off(); Temp.repaint(); }

	}

	//controls stages of gloss animation, based on (int)glow
	private boolean glow() {

		switch(glow) {

			case 1: glowOn(glow1); break;

			case 2: glowOff(glow1); glowOn(glow2); break;

			case 3: glowOff(glow2); glowOn(glow3); break;

			case 4: glowOff(glow3); glowOn(glow4); break;

			case 5: glowOff(glow4); glowOn(glow5); break;

			case 6: glowOff(glow5); break;

		}

		if (glow < 6) glow++;
		else { glow = 1; return true; }

		return false;

	}

	public ArrayList<LetterLabel> getFadeList() { return fadeList; }

	public boolean gameRunning() { return gameRunning; }

	public static Hangman box() { return box; }

	public static void main(String Container[]) {

		new Hangman();

	}

}
