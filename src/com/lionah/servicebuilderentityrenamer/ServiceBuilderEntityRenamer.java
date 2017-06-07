/**
 * Copyright 2017 Ryan Park
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.lionah.servicebuilderentityrenamer;

import com.lionah.servicebuilderentityrenamer.renamer.ContentRenamer;
import com.lionah.servicebuilderentityrenamer.renamer.FilenameRenamer;

import java.io.File;

import java.util.Map;
import java.util.HashMap;

public class ServiceBuilderEntityRenamer {

	public ServiceBuilderEntityRenamer(File dir, String fromEntityName, String toEntityName) {
		_dir = dir;
		_fromEntityName = fromEntityName;
		_toEntityName = toEntityName;
	}

	public void run() {

		// Files

		if (_renameFilenameEnabled) {
			FilenameRenamer.run(_dir, _fromEntityName, _toEntityName);
			FilenameRenamer.run(_dir, _getJSPEntityName(_fromEntityName), _getJSPEntityName(_toEntityName));
		}

		// Contents

		if (_renameContentEnabled) {
			ContentRenamer.run(_dir, _fromEntityName, _toEntityName);
			ContentRenamer.run(_dir, _getJSPEntityName(_fromEntityName), _getJSPEntityName(_toEntityName));
			ContentRenamer.run(_dir, _getVariableEntityName(_fromEntityName), _getVariableEntityName(_toEntityName));
		}
	}

	public void setRenameFilename(boolean enabled) {
		_renameFilenameEnabled = enabled;
	}

	public void setRenameContent(boolean enabled) {
		_renameContentEnabled = enabled;
	}

	private String _getJSPEntityName(String name) {
		name = name.replaceAll("([A-Z][^A-Z])", "_$1");

		name = name.replaceAll("([^A-Z_])([A-Z])", "$1_$2");

		return name.toLowerCase().substring(1);
	}

	private String _getVariableEntityName(String name) {
		char[] chars = name.toCharArray();

		chars[0] = Character.toLowerCase(chars[0]);

		return new String(chars);
	}

	private final File _dir;
	private final String _fromEntityName;
	private final String _toEntityName;

	private boolean _renameFilenameEnabled = true;
	private boolean _renameContentEnabled = true;

}