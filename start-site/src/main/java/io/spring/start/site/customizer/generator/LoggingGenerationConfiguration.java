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

import io.spring.initializr.generator.condition.ConditionalOnLanguage;
import io.spring.initializr.generator.condition.ConditionalOnRequestedDependency;
import io.spring.initializr.generator.io.template.MustacheTemplateRenderer;
import io.spring.initializr.generator.language.java.JavaLanguage;
import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.project.ProjectGenerationConfiguration;
import io.spring.start.site.customizer.contributor.annotation.IgnoreLogging;
import io.spring.start.site.customizer.contributor.annotation.IgnoreLoggingProjectContributor;
import io.spring.start.site.customizer.contributor.logging.ApplicationToolProjectContributor;
import io.spring.start.site.customizer.contributor.logging.ApplicationTools;
import io.spring.start.site.customizer.contributor.logging.HealthController;
import io.spring.start.site.customizer.contributor.logging.HealthControllerProjectContributor;
import io.spring.start.site.customizer.contributor.logging.WebMvcRegistration;
import io.spring.start.site.customizer.contributor.logging.WebMvcRegistrationProjectContributor;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;

@ProjectGenerationConfiguration
public class LoggingGenerationConfiguration {

	@Bean
	@ConditionalOnRequestedDependency("web")
	@ConditionalOnLanguage(JavaLanguage.ID)
	public WebMvcRegistration webMvcRegistration(ObjectProvider<MustacheTemplateRenderer> templateRenderer,
			ProjectDescription projectDescription) {
		return new WebMvcRegistration(
				templateRenderer.getIfAvailable(() -> new MustacheTemplateRenderer("classpath:/templates")),
				projectDescription);
	}

	@Bean
	@ConditionalOnRequestedDependency("web")
	@ConditionalOnLanguage(JavaLanguage.ID)
	public WebMvcRegistrationProjectContributor webMvcRegistrationProjectContributor(
			WebMvcRegistration webMvcRegistration, ProjectDescription projectDescription) {
		return new WebMvcRegistrationProjectContributor(webMvcRegistration, projectDescription);
	}

	@Bean
	@ConditionalOnRequestedDependency("web")
	@ConditionalOnLanguage(JavaLanguage.ID)
	public ApplicationTools applicationTools(ObjectProvider<MustacheTemplateRenderer> templateRenderer,
			ProjectDescription projectDescription) {
		return new ApplicationTools(
				templateRenderer.getIfAvailable(() -> new MustacheTemplateRenderer("classpath:/templates")),
				projectDescription);
	}

	@Bean
	@ConditionalOnRequestedDependency("web")
	@ConditionalOnLanguage(JavaLanguage.ID)
	public ApplicationToolProjectContributor applicationToolProjectContributor(ApplicationTools applicationTools,
			ProjectDescription projectDescription) {
		return new ApplicationToolProjectContributor(applicationTools, projectDescription);
	}

	@Bean
	@ConditionalOnRequestedDependency("web")
	@ConditionalOnLanguage(JavaLanguage.ID)
	public HealthController healthController(ObjectProvider<MustacheTemplateRenderer> templateRenderer,
			ProjectDescription projectDescription) {
		return new HealthController(
				templateRenderer.getIfAvailable(() -> new MustacheTemplateRenderer("classpath:/templates")),
				projectDescription);
	}

	@Bean
	@ConditionalOnRequestedDependency("web")
	@ConditionalOnLanguage(JavaLanguage.ID)
	public HealthControllerProjectContributor healthControllerProjectContributor(HealthController healthController,
			ProjectDescription projectDescription) {
		return new HealthControllerProjectContributor(healthController, projectDescription);
	}

	@Bean
	@ConditionalOnRequestedDependency("web")
	@ConditionalOnLanguage(JavaLanguage.ID)
	public IgnoreLogging ignoreLogging(ObjectProvider<MustacheTemplateRenderer> templateRenderer,
			ProjectDescription projectDescription) {
		return new IgnoreLogging(
				templateRenderer.getIfAvailable(() -> new MustacheTemplateRenderer("classpath:/templates")),
				projectDescription);
	}

	@Bean
	@ConditionalOnRequestedDependency("web")
	@ConditionalOnLanguage(JavaLanguage.ID)
	public IgnoreLoggingProjectContributor ignoreLoggingProjectContributor(IgnoreLogging ignoreLogging,
			ProjectDescription projectDescription) {
		return new IgnoreLoggingProjectContributor(ignoreLogging, projectDescription);
	}

}
