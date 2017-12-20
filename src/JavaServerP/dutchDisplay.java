package JavaServerP;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JOptionPane;

import java.awt.*;
import java.awt.event.*;

class dutchDisplay extends JFrame {
	int w = 100;
	int h = 40;

	JTextField total, person;
	JButton b = new JButton("���");
	JButton r = new JButton("�ʱ�ȭ");

	public dutchDisplay() {

		setBounds(100, 100, 300, 300);
		setLayout(null);
		setTitle("��ġ����");

		JPanel t = new JPanel();
		t.setBounds(10, 10, w, h);
		t.setBorder(new BevelBorder(BevelBorder.RAISED));
		t.setLayout(new BorderLayout());
		t.add(new JLabel("�Ѿ�", JLabel.CENTER));

		add(t);
		JPanel p = new JPanel();
		p.setBounds(10, 70, w, h);
		p.setBorder(new BevelBorder(BevelBorder.RAISED));
		p.setLayout(new BorderLayout());
		p.add(new JLabel("�ο�", JLabel.CENTER));
		add(p);

		JPanel t_t = new JPanel();
		t_t.setBounds(130, 10, w, h);
		t_t.setBorder(new BevelBorder(BevelBorder.RAISED));
		t_t.setLayout(new BorderLayout());
		t_t.add(total = new JTextField("", JTextField.RIGHT));
		add(t_t);

		JPanel p_t = new JPanel();
		p_t.setBounds(130, 70, w, h);
		p_t.setBorder(new BevelBorder(BevelBorder.RAISED));
		p_t.setLayout(new BorderLayout());
		p_t.add(person = new JTextField("", JTextField.RIGHT));
		add(p_t);

		b.setBounds(130, 120, w, h);
		add(b);

		r.setBounds(10, 120, w, h);
		add(r);

		setVisible(true);
		b.addActionListener(new Listener(this));

	}

	class Listener implements ActionListener {
		JFrame frame;

		public Listener(JFrame f) {
			frame = f;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(b))// ��� ��������
			{
				int total1 = 0;
				int person1 = 0;

				int one = 0, two = 0, three = 0, oneMoney = 0, twoMoney = 0, threeMoney = 0;

				try {
					total1 = Integer.parseInt(total.getText().toString());
					person1 = Integer.parseInt(person.getText().toString());
				} catch (NumberFormatException nfe) {
					System.out.println("Could not parse " + nfe);
				}

				int sum = total1 / person1;
				sum = (sum / 1000) * 1000; // �� �����ϴ� ���� �ο����� ���� ��

				int change;

				if (person1 == 1) {
					one = person1;
					oneMoney = total1;
				}
				if (sum * person1 == total1 && person1 != 1) {
					one = person1;
					oneMoney = sum;
				} else if (sum * person1 != total1 && person1 != 1) {
					int flag = 1;
					change = total1 - (sum * person1);

					if (change >= 1000)// ����������� ������
					{
						int i = 0;
						int j = 0;

						while (true) {
							if (change < 1000) {// õ������ ������ �������� �ʴ� ��
								if (change != 0) {
									flag = 0;
									j++;
								}
								break;
							}
							change = change - 1000;// õ�������� ��������
							i++;
						}
						if (flag == 1) {
							if (person1 - i - j == 0) {
								one = person1 - i - j;
							} else {
								one = person1 - i - j;
								oneMoney = sum;
							}

							two = i;
							int money2 = sum + 1000;
							twoMoney = money2;

						} else // ���¥�� ������
						{
							if (person1 - i - j == 0) {
								one = i;
								int money2 = sum + 1000;
								oneMoney = money2;

								two = j;
								int money3 = sum + change;
								oneMoney = money3;

							} else {
								one = (person1 - i - j);
								oneMoney = sum;
								two = i;
								int money2 = sum + 1000;
								twoMoney = money2;

								three = j;
								int money3 = sum + change;
								threeMoney = money3;
							}
						}

					} else {
						one = (person1 - 1);
						oneMoney = sum;
						two = 1;
						int Money2 = change;
						twoMoney = Money2;

					}
				}
				// ��� �޼���
				JOptionPane.showMessageDialog(frame, one + " �� " + oneMoney + " ����\n" + two + " �� " + twoMoney + " ����\n"
						+ three + " �� " + threeMoney + " ����\n");

			} else {
				total.setText(" ");
				person.setText(" ");
			}

		}

	}
}
