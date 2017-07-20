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

package com.lionah;

import com.lionah.servicebuilderentityrenamer.ServiceBuilderEntityRenamer;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

public class Runner {

	public static void main(String[] args) {
		String[] arguments = _getArguments(args);
		String[] flags = _getFlags(args);

		if (arguments.length != 3 && arguments.length != 5) {
			_printUsage();

			return;
		}

		if (arguments.length == 5) {
			rename(arguments[0], arguments[3], arguments[4], flags);
		}

		rename(arguments[0], arguments[1], arguments[2], flags);
	}

	public static void rename(String dir, String oldName, String newName, String[] flags) {
		ServiceBuilderEntityRenamer renamer = new ServiceBuilderEntityRenamer(new File(dir), oldName, newName);

		for (String flag : flags) {
			if (flag.equals("--disable-filename-rename")) {
				renamer.setRenameFilename(false);
			}
			else if (flag.equals("--disable-content-rename")) {
				renamer.setRenameContent(false);
			}
			else {
				_printUsage();

				return;
			}
		}

		renamer.run();
	}

	private static String[] _getArguments(String[] args) {
		List<String> arguments = new ArrayList<>(args.length);

		for (String arg : args) {
			if (!arg.startsWith("-")) {
 				arguments.add(arg);
			}
		}

		return arguments.toArray(new String[arguments.size()]);
	}

	private static String[] _getFlags(String[] args) {
		List<String> flags = new ArrayList<>(args.length);

		for (String arg : args) {
			if (arg.startsWith("-")) {
 				flags.add(arg);
			}
		}

		return flags.toArray(new String[flags.size()]);
	}

	private static void _printUsage() {
		System.out.println("usage: ./run directory from_entity to_entity [from_entity_plural to_entity_plural]");
		System.out.println("\t--disable-content-rename          Renamer will skip over contents of files");
		System.out.println("\t--disable-filename-rename         Renamer will not rename files");
	}

}