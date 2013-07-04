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
import java.util.*;

public class Configuration {
	private Properties conf;

	public Configuration() {}

	public void ConfigRead(String filename) {
		conf = new Properties();
        try {
            conf.load(new FileInputStream(filename));
        } catch (IOException e) {
            System.err.println("Cannot open " + filename + ".");
            e.printStackTrace();
        }
	}

	public String getProperty(String key) {
        if(conf.containsKey(key))
            return conf.getProperty(key);
        else {
            System.err.println("Key not found: " + key);
            return "";
        }
    }

    public int addProperty(String key, String value) {
    	conf.setProperty(key, value);
        return 0;
    }

    public void store(String filename, String comments) {
        try {
            conf.store(new FileOutputStream(filename), comments);
        } catch (IOException e) {
            System.err.println("Cannot open " + filename + ".");
            e.printStackTrace();
        }
    }

}
