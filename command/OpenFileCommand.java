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

package playlistmaker.command;

import java.io.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import playlistmaker.application.Application;

// OpenListCommand
public class OpenFileCommand extends Command {

	private Application app;

	public OpenFileCommand(Application app) {
		this.app = app;
	}

	@Override
	public void execute() {
		// TODO 自動生成されたメソッド・スタブ
		if (DirtyCheck()) {
			openFile();
		}
	}

	private void openFile() {
		DefaultListModel model = app.getdlModel();

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("プレイリストを開く");

		// ファイルフィルタの設定
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("テキストファイル","txt"));
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("m3uファイル","m3u"));
		// フィルタ[すべてのファイル]の使用禁止
		fileChooser.setAcceptAllFileFilterUsed(false);

		int selected = fileChooser.showOpenDialog(app);

		if (selected == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			model.clear();

			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
				for(String line;(line=reader.readLine())!=null;) {
					model.addElement(line);
				}
				reader.close();
				app.setIsDirty(false);
				app.setCurrentFileName(file.getAbsolutePath());
				app.setTitle("PlayListMaker : " + file.getName());

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean DirtyCheck() {
		if (app.getIsDirty()) {
			int result = JOptionPane.showConfirmDialog(null, "編集中のプレイリストを保存せずに閉じますか？", "PlayListMaker", JOptionPane.YES_NO_OPTION);

			if (result == JOptionPane.YES_OPTION) {
				return true;
			} else {
				return false;
		    }
		} else {
			return true;
		}
	}

}
