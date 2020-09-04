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
import io.spring.start.site.customizer.contributor.exception.BusinessException;
import io.spring.start.site.customizer.contributor.exception.BusinessExceptionProjectContributor;
import io.spring.start.site.customizer.contributor.exception.SystemException;
import io.spring.start.site.customizer.contributor.exception.SystemExceptionProjectContributor;
import io.spring.start.site.customizer.contributor.exception.WarnException;
import io.spring.start.site.customizer.contributor.exception.WarnExceptionProjectContributor;
import io.spring.start.site.customizer.contributor.exception.handler.ErrorResult;
import io.spring.start.site.customizer.contributor.exception.handler.ErrorResultProjectContributor;
import io.spring.start.site.customizer.contributor.exception.handler.ExceptionHandlers;
import io.spring.start.site.customizer.contributor.exception.handler.ExceptionHandlersProjectContributor;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;

@ProjectGenerationConfiguration
public class ExceptionGenerationConfiguration {

	@Bean
	@ConditionalOnLanguage(JavaLanguage.ID)
	@ConditionalOnRequestedDependency("web")
	public ErrorResult errorResult(ObjectProvider<MustacheTemplateRenderer> templateRenderer,
			ProjectDescription projectDescription) {
		return new ErrorResult(
				templateRenderer.getIfAvailable(() -> new MustacheTemplateRenderer("classpath:/templates")),
				projectDescription);
	}

	@Bean
	@ConditionalOnLanguage(JavaLanguage.ID)
	@ConditionalOnRequestedDependency("web")
	public ErrorResultProjectContributor errorResultProjectContributor(ErrorResult errorResult,
			ProjectDescription projectDescription) {
		return new ErrorResultProjectContributor(errorResult, projectDescription);
	}

	@Bean
	@ConditionalOnLanguage(JavaLanguage.ID)
	@ConditionalOnRequestedDependency("web")
	public ExceptionHandlers exceptionHandlers(ObjectProvider<MustacheTemplateRenderer> templateRenderer,
			ProjectDescription projectDescription) {
		return new ExceptionHandlers(
				templateRenderer.getIfAvailable(() -> new MustacheTemplateRenderer("classpath:/templates")),
				projectDescription);
	}

	@Bean
	@ConditionalOnLanguage(JavaLanguage.ID)
	@ConditionalOnRequestedDependency("web")
	public ExceptionHandlersProjectContributor exceptionHandlersProjectContributor(ExceptionHandlers exceptionHandlers,
			ProjectDescription projectDescription) {
		return new ExceptionHandlersProjectContributor(exceptionHandlers, projectDescription);
	}

	@Bean
	@ConditionalOnLanguage(JavaLanguage.ID)
	public BusinessException businessException(ObjectProvider<MustacheTemplateRenderer> templateRenderer,
			ProjectDescription projectDescription) {
		return new BusinessException(
				templateRenderer.getIfAvailable(() -> new MustacheTemplateRenderer("classpath:/templates")),
				projectDescription);
	}

	@Bean
	@ConditionalOnLanguage(JavaLanguage.ID)
	public BusinessExceptionProjectContributor businessExceptionProjectContributor(BusinessException businessException,
			ProjectDescription projectDescription) {
		return new BusinessExceptionProjectContributor(businessException, projectDescription);
	}

	@Bean
	@ConditionalOnLanguage(JavaLanguage.ID)
	public SystemException systemException(ObjectProvider<MustacheTemplateRenderer> templateRenderer,
			ProjectDescription projectDescription) {
		return new SystemException(
				templateRenderer.getIfAvailable(() -> new MustacheTemplateRenderer("classpath:/templates")),
				projectDescription);
	}

	@Bean
	@ConditionalOnLanguage(JavaLanguage.ID)
	public SystemExceptionProjectContributor systemExceptionProjectContributor(SystemException systemException,
			ProjectDescription projectDescription) {
		return new SystemExceptionProjectContributor(systemException, projectDescription);
	}

	@Bean
	@ConditionalOnLanguage(JavaLanguage.ID)
	public WarnException warnException(ObjectProvider<MustacheTemplateRenderer> templateRenderer,
			ProjectDescription projectDescription) {
		return new WarnException(
				templateRenderer.getIfAvailable(() -> new MustacheTemplateRenderer("classpath:/templates")),
				projectDescription);
	}

	@Bean
	@ConditionalOnLanguage(JavaLanguage.ID)
	public WarnExceptionProjectContributor warnExceptionProjectContributor(WarnException warnException,
			ProjectDescription projectDescription) {
		return new WarnExceptionProjectContributor(warnException, projectDescription);
	}

}
