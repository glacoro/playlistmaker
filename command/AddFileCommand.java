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

import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import playlistmaker.application.Application;
import playlistmaker.path.PathChanger;

public class AddFileCommand extends Command {

	Application app;

	public AddFileCommand(Application app) {
		this.app = app;
	}

	@Override
	public void execute() {
		// TODO 自動生成されたメソッド・スタブ
		JList list = app.getListBox();
		DefaultListModel model = app.getdlModel();

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("音楽ファイルを追加");
		// ファイルの複数選択を可能にする
		fileChooser.setMultiSelectionEnabled(true);

		// ファイルフィルタの設定
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("mp3ファイル","mp3"));
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("m4aファイル","m4a"));
		// フィルタ[すべてのファイル]の使用禁止
		fileChooser.setAcceptAllFileFilterUsed(false);

		// ApproveButtonの表示名とヒントをカスタム
		fileChooser.setApproveButtonText("追加");
		fileChooser.setApproveButtonToolTipText("プレイリストに追加します");

		int selected = fileChooser.showOpenDialog(app);

		boolean isDouble = false;

		if (selected == JFileChooser.APPROVE_OPTION) {
			File[] files = fileChooser.getSelectedFiles();

			for (int i = 0; i < files.length; i++) {
				PathChanger ch = new PathChanger();
				String path = ch.Change(files[i].getAbsolutePath());

				// 同じ音楽ファイルが既に追加されていたら，追加しない．
				for (int j = 0; j < model.getSize(); j++) {
					if (path.equals(model.getElementAt(j).toString())) {
						isDouble = true;
					}
				}

				if (isDouble) {
					isDouble = false;
				} else {
					/* 現在リストで選択されている項目のIndexを調べる */
					int index = list.getSelectedIndex();
					if (index == -1){
						model.add(0, path);
					}else{
						model.add(index + 1, path);
					}

					list.ensureIndexIsVisible(index + 1);

					if(app.getIsDirty() == false) {
						app.setIsDirty(true);
						app.setTitle(app.getTitle() + "*");
					}
				}
			}
		}
	}

}
