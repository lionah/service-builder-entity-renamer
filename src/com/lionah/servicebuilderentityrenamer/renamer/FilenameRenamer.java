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

package com.lionah.servicebuilderentityrenamer.renamer;

import java.io.File;

public class FilenameRenamer {

	public static void run(File root, String from, String to) {
		FilenameRenamer filenameRenamer = new FilenameRenamer(from, to);

		filenameRenamer._run(root);
	}

	private FilenameRenamer(String from, String to) {
		_from = from;
		_to = to;
	}

	private void _run(File root) {
		_rename(root);
	}

	private void _rename(File dir) {
		File[] files = dir.listFiles(new RenamerFileFilter());

		for (File file : files) {
			if (file.isDirectory()) {
				_rename(file);
			}
			else {
				String fileName = file.getName();

				String newFileName = fileName.replace(_from, _to);

				if (fileName.equals(newFileName)) {
					continue;
				}

				File newFile = new File(dir, newFileName);

				file.renameTo(newFile);
			}
		}
	}

	private final String _from;
	private final String _to;

}