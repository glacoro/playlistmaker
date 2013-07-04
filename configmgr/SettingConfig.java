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

package playlistmaker.configmgr;

import java.io.*;

public class SettingConfig extends Configuration {

	public SettingConfig() {
		File SetConfig = new File(System.getProperty( "user.dir" ) + "\\Config.properties");

		// 構成設定ファイルが存在しない場合は，新しく生成する．
		if (!SetConfig.exists()) {
			try {
				SetConfig.createNewFile();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}

		// 読み込み処理を実行
		ConfigRead(System.getProperty( "user.dir" ) + "\\Config.properties");
	}

	public void ConfigStore() {
		store(System.getProperty( "user.dir" ) + "\\Config.properties", "SettingFile");
	}

	public int getWindowSizeWidth() {
		if (getProperty("SizeWidth") == "") {
			return 450;
		} else {
			return Integer.valueOf(getProperty("SizeWidth"));
		}
	}

	public void addWindowSizeWidth(int value) {
		addProperty("SizeWidth", String.valueOf(value));
	}

	public int getWidowSizeHight() {
		if (getProperty("SizeHight") == "") {
			return 520;
		} else {
			return Integer.valueOf(getProperty("SizeHight"));
		}
	}

	public void addWindowSizeHight(int value) {
		addProperty("SizeHight", String.valueOf(value));
	}

	public int getWinPosX() {
		if (getProperty("PosX") == "") {
			return 0;
		} else {
			return Integer.valueOf(getProperty("PosX"));
		}
	}

	public void addWinPosX(int value) {
		addProperty("PosX", String.valueOf(value));
	}

	public int getWinPosY() {
		if (getProperty("PosY") == "") {
			return 0;
		} else {
			return Integer.valueOf(getProperty("PosY"));
		}
	}

	public void addWinPosY(int value) {
		addProperty("PosY", String.valueOf(value));
	}

	public String getWindowState() {
		if (getProperty("State") == "") {
			return "NORMAL";
		} else {
			return getProperty("State");
		}
	}

	public void addWindowState(String state) {
		addProperty("State", state);
	}

}
