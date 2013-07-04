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

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import playlistmaker.application.Application;

public class NewFileCommand extends Command {

	private Application app;

	public NewFileCommand(Application app) {
		this.app = app;
	}

	@Override
	public void execute() {
		// TODO 自動生成されたメソッド・スタブ
		if(DirtyCheck()) {
			DefaultListModel model = app.getdlModel();
			model.clear();
			app.setIsDirty(false);
			app.setTitle("PlayListMaker : " + app.getNewFileName());
			app.setCurrentFileName(app.getNewFileName());
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
