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

package playlistmaker.application;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.*;

import playlistmaker.command.Command;
import playlistmaker.command.ExitCommand;
import playlistmaker.configmgr.SettingConfig;

public class Application extends JFrame {

	private ListBox list;
	private DefaultListModel model = new DefaultListModel();
	private boolean isDirty = false;
	private String NewFileName = "新規プレイリスト";
	private String CurrentFileName = NewFileName;

	private SettingConfig sc;

	public Application() {
		// GUIの外観をWindowsにする．
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		setTitle("PlayListMaker : " + NewFileName);

		Container cp = getContentPane();
		list = new ListBox(this);
		cp.add(list, BorderLayout.CENTER);
		setJMenuBar(new Menubar(this));

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		this.addWindowListener(new WindowAdapter() {
			// 終了時のトラップ
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				ExitExecute();
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO 自動生成されたメソッド・スタブ

			}
		});

		sc = new SettingConfig();
        //setSize(450,520);    			//大きさを変更する

		if (sc.getWindowState().equals("MAX")) {
			this.setExtendedState(MAXIMIZED_BOTH);
		} else if (sc.getWindowState().equals("MIN")) {
			this.setExtendedState(ICONIFIED);
		}

		setBounds(sc.getWinPosX(), sc.getWinPosY(), sc.getWindowSizeWidth(), sc.getWidowSizeHight());

        //setLocationRelativeTo(null);	// 画面の中央に配置する
        //setResizable(false);			// ウィンドウのリサイズを禁止する
        setVisible(true);    			//表示
	}

	public JList getListBox() {
		return list.getListBox();
	}

	public DefaultListModel getdlModel() {
		return model;
	}

	public boolean getIsDirty() {
		return isDirty;
	}

	public void setIsDirty(boolean isDirty) {
		this.isDirty = isDirty;
	}

	public String getCurrentFileName() {
		return CurrentFileName;
	}

	public void setCurrentFileName(String filename) {
		CurrentFileName = filename;
	}

	public String getNewFileName() {
		return NewFileName;
	}

	public SettingConfig getConfig() {
		return sc;
	}

	public void SaveConfig() {
		if (this.getExtendedState() == Frame.NORMAL) {
			sc.addWindowState("NORMAL");
			sc.addWindowSizeWidth(this.getWidth());
			sc.addWindowSizeHight(this.getHeight());
			sc.addWinPosX(this.getX());
			sc.addWinPosY(this.getY());
		} else if (this.getExtendedState() == Frame.ICONIFIED) {
			sc.addWindowState("MIN");
		} else if (this.getExtendedState() == Frame.MAXIMIZED_BOTH) {
			sc.addWindowState("MAX");
		}

		sc.ConfigStore();
	}

	private void ExitExecute() {
		Command c = new ExitCommand(this);
		c.execute();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Application app = new Application();
	}

}
