package curso.gfi.maven;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Goal which touches a timestamp file.
 *
 * @goal file
 * 
 * @phase process-sources
 */
public class MyMojo extends AbstractMojo {
	/**
	 * Location of the file.
	 * 
	 * @parameter expression="${project.build.directory}"
	 * @required
	 */
	private File outputDirectory;

	public void execute() throws MojoExecutionException {
		File f = outputDirectory;

		if (!f.exists()) {
			f.mkdirs();
		}

		File file = new File(f, "application.properties");

		FileWriter w = null;
		try {
			w = new FileWriter(file);

			w.write("driver=com.mysql.jdbc.Driver\n");
			w.write("url=jdbc:mysql://localhost:3306:/curso\n");
			w.write("database=curso\n");
			w.write("username=pepe\n");
			w.write("password=12345\n");
		} catch (IOException e) {
			throw new MojoExecutionException("Error creating file " + file, e);
		} finally {
			if (w != null) {
				try {
					w.close();
				} catch (IOException e) {
					// ignore
				}
			}
		}
	}
}
