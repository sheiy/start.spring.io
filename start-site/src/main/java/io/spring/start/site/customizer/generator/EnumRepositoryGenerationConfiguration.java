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

package io.spring.start.site.customizer.generator;

import io.spring.initializr.generator.buildsystem.gradle.GradleBuildSystem;
import io.spring.initializr.generator.buildsystem.maven.MavenBuildSystem;
import io.spring.initializr.generator.condition.ConditionalOnBuildSystem;
import io.spring.initializr.generator.condition.ConditionalOnLanguage;
import io.spring.initializr.generator.io.template.MustacheTemplateRenderer;
import io.spring.initializr.generator.language.java.JavaLanguage;
import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.project.ProjectGenerationConfiguration;
import io.spring.start.site.customizer.contributor.enumrepository.EnumRepository;
import io.spring.start.site.customizer.contributor.enumrepository.EnumRepositoryProjectContributor;
import io.spring.start.site.customizer.contributor.exception.ReflectionsGradleBuildCustomizer;
import io.spring.start.site.customizer.contributor.exception.ReflectionsMavenBuildCustomizer;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;

@ProjectGenerationConfiguration
public class EnumRepositoryGenerationConfiguration {

	@Bean
	@ConditionalOnLanguage(JavaLanguage.ID)
	public EnumRepository enumRepository(ObjectProvider<MustacheTemplateRenderer> templateRenderer,
			ProjectDescription projectDescription) {
		return new EnumRepository(
				templateRenderer.getIfAvailable(() -> new MustacheTemplateRenderer("classpath:/templates")),
				projectDescription);
	}

	@Bean
	@ConditionalOnLanguage(JavaLanguage.ID)
	public EnumRepositoryProjectContributor enumRepositoryProjectContributor(EnumRepository enumRepository,
			ProjectDescription projectDescription) {
		return new EnumRepositoryProjectContributor(enumRepository, projectDescription);
	}

	@Bean
	@ConditionalOnBuildSystem(MavenBuildSystem.ID)
	public ReflectionsMavenBuildCustomizer reflectionsMavenBuildCustomizer() {
		return new ReflectionsMavenBuildCustomizer();
	}

	@Bean
	@ConditionalOnBuildSystem(GradleBuildSystem.ID)
	public ReflectionsGradleBuildCustomizer reflectionsGradleBuildCustomizer() {
		return new ReflectionsGradleBuildCustomizer();
	}

}
