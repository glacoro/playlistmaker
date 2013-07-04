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

import playlistmaker.application.Application;
import playlistmaker.path.PathChanger;

// OpenFolderCommand
public class AddFolderCommand extends Command {

	Application app;

	public AddFolderCommand(Application app) {
		this.app = app;
	}

	@Override
	public void execute() {
		// TODO 自動生成されたメソッド・スタブ
		 JFileChooser fileChooser = new JFileChooser();
		 fileChooser.setDialogTitle("フォルダを選択");
		 // ApproveButtonの表示名とヒントをカスタム
		 fileChooser.setApproveButtonText("追加");
		 fileChooser.setApproveButtonToolTipText("プレイリストに追加します");
		 fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

	        if(fileChooser.showOpenDialog(app)==JFileChooser.APPROVE_OPTION) {
	        	File file = fileChooser.getSelectedFile();
	        	//app.getTextBox().setText(file.getAbsolutePath());

	    		//String path = app.getTextBox().getText();
	    		File dir = new File(file.getAbsolutePath());
	    		File[] files = dir.listFiles();

	    		JList listbox = app.getListBox();
	    		DefaultListModel model = app.getdlModel();

	    		for (int i = 0; i < files.length; i++) {
	    			if (files[i].isFile()) {
	    				PathChanger changer = new PathChanger();
	    				model.addElement(changer.Change(files[i].getAbsolutePath()));

						if(app.getIsDirty() == false) {
							app.setIsDirty(true);
							app.setTitle(app.getTitle() + "*");
						}
	    			}
	    		}

	        }
	}

}
