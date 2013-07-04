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

import java.awt.event.*;
import javax.swing.*;

import playlistmaker.command.*;
import playlistmaker.dialog.*;

public class Menubar extends JMenuBar {

	private Application app;

	public Menubar(final Application app) {
		this.app = app;

        //ファイルメニュー
        JMenu fileMenu = new JMenu("ファイル(F)");
        fileMenu.setMnemonic('F');

        //新規ファイルメニュー
        JMenuItem newFileMenuItem = new JMenuItem("新規作成(N)");
        newFileMenuItem.setMnemonic('N');
        newFileMenuItem.setAccelerator(
        	      KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        newFileMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Command c = new NewFileCommand(app);
            	c.execute();
            }
        });
        fileMenu.add(newFileMenuItem);

        //ファイルを開くメニュー
        JMenuItem openFileMenuItem = new JMenuItem("開く(O)...");
        openFileMenuItem.setMnemonic('O');
        openFileMenuItem.setAccelerator(
      	      KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        openFileMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Command c = new OpenFileCommand(app);
                c.execute();
            }
        });
        fileMenu.add(openFileMenuItem);

        fileMenu.addSeparator();

        //上書き保存メニュー
        JMenuItem overWriteMenuItem = new JMenuItem("上書き保存(S)");
        overWriteMenuItem.setMnemonic('S');
        overWriteMenuItem.setAccelerator(
      	      KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        overWriteMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Command c = new FileSaveCommand(app);
                c.execute();
            }
        });
        fileMenu.add(overWriteMenuItem);

        //名前を付けて保存メニュー
        JMenuItem saveFileMenuItem = new JMenuItem("名前を付けて保存(A)...");
        saveFileMenuItem.setMnemonic('A');
        saveFileMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Command c = new FileSaveAsCommand(app);
                c.execute();
            }
        });
        fileMenu.add(saveFileMenuItem);

        fileMenu.addSeparator();

        //終了メニュー
        JMenuItem exitMenuItem = new JMenuItem("PalyListMakerを終了(X)");
        exitMenuItem.setMnemonic('X');
        exitMenuItem.setAccelerator(
      	      KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Command c = new ExitCommand(app);
            	c.execute();
            }
        });
        fileMenu.add(exitMenuItem);
        add(fileMenu);

        //編集メニュー
        JMenu editMenu = new JMenu("編集(E)");
        editMenu.setMnemonic('E');

        //フォルダごと追加
        JMenuItem addFolderMenuItem = new JMenuItem("フォルダごと音楽ファイルを追加(A)...");
        addFolderMenuItem.setMnemonic('A');
        addFolderMenuItem.setAccelerator(
        	      KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        addFolderMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Command c = new AddFolderCommand(app);
            	c.execute();
            }
        });
        editMenu.add(addFolderMenuItem);

        //ファイルを追加
        JMenuItem addFileMenuItem = new JMenuItem("音楽ファイルを追加(M)...");
        addFileMenuItem.setMnemonic('M');
        addFileMenuItem.setAccelerator(
        	      KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK));
        addFileMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Command c = new AddFileCommand(app);
            	c.execute();
            }
        });
        editMenu.add(addFileMenuItem);

        // 削除
        JMenuItem deleteMenuItem = new JMenuItem("削除(D)");
        deleteMenuItem.setMnemonic('D');
        deleteMenuItem.setAccelerator(
        	      KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
        deleteMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Command c = new DeleteCommand(app);
            	c.execute();
            }
        });
        editMenu.add(deleteMenuItem);
        add(editMenu);

        // ヘルプメニュー
        JMenu helpMenu = new JMenu("ヘルプ(H)");
        helpMenu.setMnemonic('H');

        // 	バージョン情報
        JMenuItem aboutMenuItem = new JMenuItem("バージョン情報(A)...");
        aboutMenuItem.setMnemonic('A');
        aboutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// ダイアログ表示処理
            	AboutDialog ab = new AboutDialog(app, true);
            	//ab.show();
            }
        });
        helpMenu.add(aboutMenuItem);
        add(helpMenu);
	}
}
