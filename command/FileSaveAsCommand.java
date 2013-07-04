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

public class FileSaveAsCommand extends Command {

	private Application app;

	public FileSaveAsCommand(Application app) {
		this.app = app;
	}

	@Override
	public void execute() {
		// TODO 自動生成されたメソッド・スタブ

		boolean fileIsNew = false;

		if(app.getCurrentFileName() == app.getNewFileName()) {
			app.setCurrentFileName(app.getNewFileName() + ".m3u");
		}

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("名前を付けて保存");
		// ファイルフィルタの設定
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("テキストファイル","txt"));
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("m3uファイル","m3u"));

		// フィルタ[すべてのファイル]の使用禁止
		fileChooser.setAcceptAllFileFilterUsed(false);

		fileChooser.setSelectedFile(new File(app.getCurrentFileName()));

		DefaultListModel listmodel = app.getdlModel();

       	if(listmodel.getSize() > 0) {
       		int selected = fileChooser.showSaveDialog(app);

	        if(selected == JFileChooser.APPROVE_OPTION) {

	        	if(fileChooser.getSelectedFile().exists()) {
	        		int result = JOptionPane.showConfirmDialog(null, "同じ名前のファイルがあります．上書きしますか？", "PlayListMaker", JOptionPane.YES_NO_OPTION);

	    			if (result == JOptionPane.YES_OPTION) {

	    			} else {
	    				return;
	    		    }
	        	}

        		try {

					BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileChooser.getSelectedFile()), "UTF-8"));

					for (int i = 0; i < listmodel.getSize(); i++) {
						writer.write(listmodel.getElementAt(i).toString() + "\n");
					}

					writer.flush();
			        writer.close();

			        app.setIsDirty(false);
			        app.setCurrentFileName(fileChooser.getSelectedFile().getAbsolutePath());
			        app.setTitle("PlayListMaker : " + fileChooser.getSelectedFile().getName());

				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
	        } else if (selected == JFileChooser.CANCEL_OPTION) {
	        	if (fileIsNew) {
	        		app.setCurrentFileName(app.getNewFileName());
	        	}
	        }
       	} else {
       		JOptionPane.showMessageDialog(null, "プレイリストに音楽が登録されてません．");
       	}
	}

}
