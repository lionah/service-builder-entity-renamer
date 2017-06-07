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
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;


public class ContentRenamer {

	public static void run(File root, String from, String to) {
		ContentRenamer contentRenamer = new ContentRenamer(from, to);

		contentRenamer._run(root);
	}

	private ContentRenamer(String from, String to) {
		_from = from;
		_to = to;
	}

	private void _run(File root) {
		_processFiles(root);
	}

	private void _processFiles(File dir) {
		File[] files = dir.listFiles(new RenamerFileFilter());

		for (File file : files) {
			if (file.isDirectory()) {
				_processFiles(file);
			}
			else {
				_processFile(file);
			}
		}
	}

	private void _processFile(File file) {
		try {
			String content = _readFile(file);

			String newContent = content.replaceAll(_from, _to);

			if (content.equals(newContent)) {
				return;
			}

			_writeFile(file, newContent);
		}
		catch (IOException ioe) {
		}
	}

	private String _readFile(File file) throws IOException {
		RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");

		byte[] bytes = new byte[(int)randomAccessFile.length()];

		randomAccessFile.readFully(bytes);

		randomAccessFile.close();

		return new String(bytes, "UTF-8");
	}

	private void _writeFile(File file, String contents) throws IOException {
		PrintWriter printWriter = new PrintWriter(file, "UTF-8");

		printWriter.write(contents);

		printWriter.close();
	}

	private final String _from;
	private final String _to;

}