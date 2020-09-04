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

package io.spring.start.site.customizer.contributor.exception.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.spring.initializr.generator.io.template.MustacheTemplateRenderer;
import io.spring.initializr.generator.io.text.MustacheSection;
import io.spring.initializr.generator.io.text.Section;
import io.spring.initializr.generator.project.ProjectDescription;

public class ExceptionHandlers {

	private final MustacheSection section;

	public ExceptionHandlers(MustacheTemplateRenderer templateRenderer, ProjectDescription projectDescription) {
		String packageName = projectDescription.getPackageName();
		Map<String, Object> model = new HashMap<>(1, 1);
		model.put("package", packageName);
		if (projectDescription.getRequestedDependencies().containsKey("security")) {
			model.put("havSecurity", true);
		}
		projectDescription.getRequestedDependencies().forEach((k, v) -> {
			if (v.getArtifactId().contains("spring-boot-starter-data") || v.getArtifactId().contains("mybatis")) {
				model.put("havTx", true);
			}
		});
		if (projectDescription.getRequestedDependencies().containsKey("validation")) {
			model.put("havValidation", true);
		}
		this.section = new MustacheSection(templateRenderer, "exception/handler/exception-handlers", model);
	}

	public void write(PrintWriter writer) throws IOException {
		List<Section> allSections = new ArrayList<>();
		allSections.add(this.section);
		for (Section section : allSections) {
			section.write(writer);
		}
	}

}
