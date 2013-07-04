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

package playlistmaker.path;

public class PathChanger {

	public String Change(String OriginalPath) {
		String filepath = OriginalPath;
		String path1 = filepath.substring(2, filepath.length());
		String path2 = "/mnt/sdcard" + path1;
		path2 = path2.replace("\\", "/");

		return path2;
	}

	/*************************************************************************

	D:\Music\モーツァルト全集
			↓
	/mnt/sdcard/Music/モーツァルト全集

	step1.	D:		->	/mnt/sdcard			/mnt/sdcard\Music\モーツァルト全集
			3文字目から文末まで[2:end]を取り出す．
			先頭に/mnt/sdcardをくっ付ける．

	step2.	\ 		->	/					/mnt/sdcard/Music/モーツァルト全集
			片っ端から，\を探し/に変更する．

	***************************************************************************/
}
