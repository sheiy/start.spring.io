/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.spring.start.site.customizer.contributor.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.project.contributor.ProjectContributor;

public class BeansProjectContributor implements ProjectContributor {

	private final Beans beans;

	private final ProjectDescription projectDescription;

	public BeansProjectContributor(Beans beans, ProjectDescription projectDescription) {
		this.beans = beans;
		this.projectDescription = projectDescription;
	}

	@Override
	public void contribute(Path projectRoot) throws IOException {
		String packageName = this.projectDescription.getPackageName().replaceAll("\\.", "/");
		Path pkgPath = projectRoot.resolve("src/main/java/" + packageName + "/" + "config");
		Path filePath = projectRoot.resolve("src/main/java/" + packageName + "/" + "config/Beans.java");
		Files.createDirectories(pkgPath);
		Path file = Files.createFile(filePath);
		try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(file))) {
			this.beans.write(writer);
		}
	}

}
