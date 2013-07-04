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

import javax.swing.*;

import playlistmaker.application.Application;

public class DeleteCommand extends Command {

	Application app;

	public DeleteCommand(Application app) {
		this.app = app;
	}

	@Override
	public void execute() {
		// TODO 自動生成されたメソッド・スタブ
		DefaultListModel model = app.getdlModel();
		JList list = app.getListBox();

		if (!list.isSelectionEmpty()){
			int minIndex = list.getMinSelectionIndex();
			int maxIndex = list.getMaxSelectionIndex();
			model.removeRange(minIndex, maxIndex);

			if(app.getIsDirty() == false) {
				app.setIsDirty(true);
				app.setTitle(app.getTitle() + "*");
			}
		}
	}

}
