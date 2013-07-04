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
import java.awt.event.*;

import javax.swing.*;

public class ListBox extends JPanel {

	Application app;
	JList list;

	public ListBox(final Application app) {
		this.app = app;

		this.setLayout(new BorderLayout());

		list = new JList();
		list.setModel(app.getdlModel());
		list.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setTransferHandler(new ListItemTransferHandler());
		list.setDropMode(DropMode.INSERT);
		list.setDragEnabled(true);

		JScrollPane sp = new JScrollPane();
		sp.getViewport().setView(list);
		this.add(sp, BorderLayout.CENTER);
	}

	public JList getListBox() {
		return list;
	}

}
