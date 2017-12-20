package JavaServerP;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

class ChatRoomDisplay extends JFrame implements ActionListener, KeyListener, ListSelectionListener, ChangeListener {
	private ClientThread cr_thread;
	private String idTo;
	private boolean isSelected;
	public boolean isAdmin;

	private JLabel roomer;
	public JList roomerInfo;
	private JButton coerceOut, dutchPay, quitRoom;
	private Font font;
	private JViewport view;
	private JScrollPane jsp3;
	public JTextArea messages;
	public JTextField message;

	public ChatRoomDisplay(ClientThread thread) {
		super("YOGI YOGI ");

		cr_thread = thread;
		isSelected = false;
		isAdmin = false;
		font = new Font("SanSerif", Font.PLAIN, 12);

		Container c = getContentPane();
		c.setLayout(null);

		JPanel p = new JPanel();
		p.setLayout(null);
		p.setBounds(425, 10, 140, 175);
		p.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), "참여자"));

		roomerInfo = new JList();
		roomerInfo.setFont(font);
		JScrollPane jsp2 = new JScrollPane(roomerInfo);
		roomerInfo.addListSelectionListener(this);
		jsp2.setBounds(15, 25, 110, 135);
		p.add(jsp2);

		c.add(p);

		p = new JPanel();
		p.setLayout(null);
		p.setBounds(10, 10, 410, 340);
		p.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), "채팅창"));

		view = new JViewport();
		messages = new JTextArea();
		messages.setFont(font);
		messages.setEditable(false);
		view.add(messages);
		view.addChangeListener(this);
		jsp3 = new JScrollPane(view);
		jsp3.setBounds(15, 25, 380, 270);
		p.add(jsp3);

		message = new JTextField();
		message.setFont(font);
		message.addKeyListener(this);
		message.setBounds(15, 305, 380, 20);
		message.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		p.add(message);

		c.add(p);

		coerceOut = new JButton("강 퇴 ! ! ");
		coerceOut.setFont(font);
		coerceOut.addActionListener(this);
		coerceOut.setBounds(445, 195, 100, 30);
		coerceOut.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		c.add(coerceOut);

		dutchPay = new JButton("더치페이!~!");
		dutchPay.setFont(font);
		dutchPay.addActionListener(this);
		dutchPay.setBounds(445, 235, 100, 30);
		dutchPay.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		c.add(dutchPay);

		quitRoom = new JButton("나 갈 래 요");
		quitRoom.setFont(font);
		quitRoom.addActionListener(this);
		quitRoom.setBounds(445, 275, 100, 30);
		quitRoom.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		c.add(quitRoom);

		Dimension dim = getToolkit().getScreenSize();
		setSize(580, 400);
		setLocation(dim.width / 2 - getWidth() / 2, dim.height / 2 - getHeight() / 2);
		show();

		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				message.requestFocusInWindow();
			}
		});

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				cr_thread.requestQuitRoom();
			}
		});
	}

	public void resetComponents() {
		messages.setText("");
		message.setText("");
		message.requestFocusInWindow();
	}

	public void keyPressed(KeyEvent ke) {
		if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
			String words = message.getText();
			String data;
			String idTo;
			if (words.startsWith("/w")) {
				StringTokenizer st = new StringTokenizer(words, " ");
				String command = st.nextToken();
				idTo = st.nextToken();
				data = st.nextToken();
				message.setText("");
			} else {
				cr_thread.requestSendWord(words);
				message.requestFocusInWindow();
			}
		}
	}

	public void valueChanged(ListSelectionEvent e) {
		isSelected = true;
		idTo = String.valueOf(((JList) e.getSource()).getSelectedValue());
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == coerceOut) {
			if (!isAdmin) {
				JOptionPane.showMessageDialog(this, "You are Not Trump", "강제퇴장", JOptionPane.ERROR_MESSAGE);
			} else if (!isSelected) {
				JOptionPane.showMessageDialog(this, "강제 퇴장 시킬 닉네임은?", "강제퇴장", JOptionPane.ERROR_MESSAGE);
			} else {
				cr_thread.requestCoerceOut(idTo);
				isSelected = false;
			}
		} else if (ae.getSource() == quitRoom) {
			cr_thread.requestQuitRoom();
		}

		else if (ae.getSource() == dutchPay) {
			dutchDisplay d = new dutchDisplay();
		}
	}

	public void stateChanged(ChangeEvent e) {
		jsp3.getVerticalScrollBar().setValue((jsp3.getVerticalScrollBar().getValue() + 20));
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}
}
