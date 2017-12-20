package JavaServerP;

import javax.swing.*;

import java.io.*;

public class ChatClient {
	public static int count;

	public static String getLogonID() {
		String logonID = "";
		try {
			while (logonID.equals("")) {
				logonID = JOptionPane.showInputDialog("�г����� ���ּ���(�ٸ� ����� ��� ���� �г��� ���� �Ұ�)");
			}
		} catch (NullPointerException e) {
			System.exit(0);
		}
		return logonID;
	}

	public static void main(String args[]) {
		member[] gachon = new member[100];
		String SubN = null;
		String Cre;
		member temp = null;
		count = 0;
		try {
			BufferedReader in = new BufferedReader(new FileReader("Gachon_info.txt"));
			String s;
			while ((s = in.readLine()) != null) {
				SubN = s.substring(0, 9);
				Cre = s.substring(10);

				temp = new member(SubN, Cre);
				gachon[count] = temp;
				count++;
			}
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
		}

		int i;

		String id = getLogonID();
		String credit1;

		for (i = 0; i < count; i++) {
			if ((gachon[i].student_n.equals(id))) {
				credit1 = gachon[i].credit;
				break;
			}

		}

		if (i == count) {
			System.out.println("��ġ�ϴ� id ����!");
			JOptionPane.showMessageDialog(null, "��ġ�ϴ� ȸ�������� �����ϴ�.");

			System.exit(0);
		}

		try {
			if (args.length == 0) {
				ClientThread thread = new ClientThread();
				thread.start();
				///
				thread.requestLogon(id);
			} else if (args.length == 1) {
				ClientThread thread = new ClientThread(args[0]);
				thread.start();
				//
				thread.requestLogon(id);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}

class member {

	public final String student_n;
	public final String credit;

	public member(String n, String cr) {
		this.student_n = n;
		this.credit = cr;
	}

}
