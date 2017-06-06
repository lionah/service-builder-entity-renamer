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

import com.lionah.servicebuilderentityrenamer.ServiceBuilderEntityRenamer;

import java.io.File;

public class FileRenamer {

	public FileRenamer(ServiceBuilderEntityRenamer serviceBuilderEntityRenamer) {
		_renamer = serviceBuilderEntityRenamer;
	}

	public void run() {
		_rename(_renamer.dir);
	}

	private void _rename(File dir) {
		File[] files = dir.listFiles();

		for (File file : files) {
			if (file.isDirectory()) {
				_rename(file);
			}
			else {
				String fileName = file.getName();

				String newFileName = fileName.replace(_renamer.fromEntityName, _renamer.toEntityName);

				if (fileName.equals(newFileName)) {
					System.out.println("[file] _: " + fileName);

					continue;
				}

				System.out.println("[file] renamed: " + file.getAbsolutePath() + " -> " + newFileName);

				File newFile = new File(dir, newFileName);

				file.renameTo(newFile);
			}
		}
	}

	private ServiceBuilderEntityRenamer _renamer;

}