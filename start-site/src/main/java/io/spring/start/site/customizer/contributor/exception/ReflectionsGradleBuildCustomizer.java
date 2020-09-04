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

package io.spring.start.site.customizer.contributor.exception;

import io.spring.initializr.generator.buildsystem.Dependency;
import io.spring.initializr.generator.buildsystem.gradle.GradleBuild;
import io.spring.initializr.generator.buildsystem.gradle.GradleDependency;
import io.spring.initializr.generator.spring.build.BuildCustomizer;
import io.spring.initializr.generator.version.VersionReference;

public class ReflectionsGradleBuildCustomizer implements BuildCustomizer<GradleBuild> {

	@Override
	public void customize(GradleBuild build) {
		GradleDependency reflections = GradleDependency.withCoordinates("org.reflections", "reflections")
				.version(VersionReference.ofValue("0.9.12"))
				.exclusions(new Dependency.Exclusion("com.google.guava", "guava")).build();
		build.dependencies().add("reflections", reflections);
	}

}
