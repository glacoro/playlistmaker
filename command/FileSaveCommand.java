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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.swing.DefaultListModel;

import playlistmaker.application.Application;

public class FileSaveCommand extends Command {

	private Application app;

	public FileSaveCommand(Application app) {
		this.app = app;
	}

	@Override
	public void execute() {
		// TODO 自動生成されたメソッド・スタブ
		if(app.getCurrentFileName()  == app.getNewFileName()) {
			Command c = new FileSaveAsCommand(app);
			c.execute();
		} else {
			filesave();
		}
	}

	private void filesave() {
		DefaultListModel listmodel = app.getdlModel();

		try {

			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(app.getCurrentFileName())), "UTF-8"));

			for (int i = 0; i < listmodel.getSize(); i++) {
				writer.write(listmodel.getElementAt(i).toString() + "\n");
			}

			writer.flush();
	        writer.close();

	        app.setIsDirty(false);
	        app.setTitle("PlayListMaker : " + new File(app.getCurrentFileName()).getName());

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
