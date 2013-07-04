/**
	PlayListMaker Software to create a playlist for Android devices.
	Copyright (C) 2013 KuroBlack.

	This program is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.

	You should have received a copy of the GNU General Public License
	along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package playlistmaker.dialog;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AboutDialog extends JDialog implements ActionListener{

	public AboutDialog(JFrame parent, boolean modal) {
		super(parent, modal);

		Container cp = getContentPane();
		cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));

		JLabel app = new JLabel();
		app.setAlignmentX(CENTER_ALIGNMENT);
		app.setText("PlayListMaker");
		app.setFont(new Font(getFont().getFontName(), Font.BOLD, 24));
		cp.add(app);

		JLabel author = new JLabel();
		author.setAlignmentX(CENTER_ALIGNMENT);
		author.setText("author : KuroBlack");
		author.setFont(new Font(getFont().getFontName(), Font.PLAIN, 14));
		cp.add(author);

		JLabel cr = new JLabel();
		cr.setAlignmentX(CENTER_ALIGNMENT);
		cr.setText("Copyright(c) 2013 KuroBlack.");
		cr.setFont(new Font(getFont().getFontName(), Font.PLAIN, 14));
		cp.add(cr);

		JLabel li1 = new JLabel();
		li1.setAlignmentX(CENTER_ALIGNMENT);
		li1.setText("PlayListMakerの著作権は，日本国の著作権法および");
		li1.setFont(new Font(getFont().getFontName(), Font.PLAIN, 12));
		cp.add(li1);

		JLabel li2 = new JLabel();
		li2.setAlignmentX(CENTER_ALIGNMENT);
		li2.setText("国際条約により保護されています．");
		li2.setFont(new Font(getFont().getFontName(), Font.PLAIN, 12));
		cp.add(li2);

		cp.add(new JLabel("  "));

		JButton ok_btn = new JButton("OK(O)");
		ok_btn.setMnemonic('O');
		ok_btn.setFont(new Font(getFont().getFontName(), Font.PLAIN, 12));
		ok_btn.addActionListener(this);
		ok_btn.setActionCommand("OK");
		ok_btn.setAlignmentX(CENTER_ALIGNMENT);
		cp.add(ok_btn);

		setTitle("バージョン情報");
		setSize(400, 220);
        setLocationRelativeTo(null);	// 画面の中央に配置する
        setResizable(false);			// ウィンドウのリサイズを禁止する
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		String cmd = e.getActionCommand();

		if (cmd.equals("OK")) {
			this.dispose();
		}
	}

}
