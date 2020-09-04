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
import io.spring.initializr.generator.condition.ConditionalOnRequestedDependency;
import io.spring.initializr.generator.io.template.MustacheTemplateRenderer;
import io.spring.initializr.generator.language.java.JavaLanguage;
import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.project.ProjectGenerationConfiguration;
import io.spring.start.site.customizer.contributor.annotation.WithOutLogin;
import io.spring.start.site.customizer.contributor.annotation.WithOutLoginProjectContributor;
import io.spring.start.site.customizer.contributor.exception.Lang3GradleBuildCustomizer;
import io.spring.start.site.customizer.contributor.exception.Lang3MavenBuildCustomizer;
import io.spring.start.site.customizer.contributor.security.AuthenticationFilter;
import io.spring.start.site.customizer.contributor.security.AuthenticationFilterProjectContributor;
import io.spring.start.site.customizer.contributor.security.AuthenticationProvider;
import io.spring.start.site.customizer.contributor.security.AuthenticationProviderProjectContributor;
import io.spring.start.site.customizer.contributor.security.Beans;
import io.spring.start.site.customizer.contributor.security.BeansProjectContributor;
import io.spring.start.site.customizer.contributor.security.CustomAccessDeniedHandler;
import io.spring.start.site.customizer.contributor.security.CustomAccessDeniedHandlerProjectContributor;
import io.spring.start.site.customizer.contributor.security.CustomAuthenticationFailureHandler;
import io.spring.start.site.customizer.contributor.security.CustomAuthenticationFailureHandlerProjectContributor;
import io.spring.start.site.customizer.contributor.security.SecurityConfig;
import io.spring.start.site.customizer.contributor.security.SecurityConfigProjectContributor;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;

@ProjectGenerationConfiguration
public class SecurityGenerationConfiguration {

	@Bean
	@ConditionalOnRequestedDependency("security")
	@ConditionalOnBuildSystem(MavenBuildSystem.ID)
	public Lang3MavenBuildCustomizer lang3MavenBuildCustomizer() {
		return new Lang3MavenBuildCustomizer();
	}

	@Bean
	@ConditionalOnRequestedDependency("security")
	@ConditionalOnBuildSystem(GradleBuildSystem.ID)
	public Lang3GradleBuildCustomizer lang3GradleBuildCustomizer() {
		return new Lang3GradleBuildCustomizer();
	}

	@Bean
	@ConditionalOnLanguage(JavaLanguage.ID)
	@ConditionalOnRequestedDependency("security")
	public Beans beans(ObjectProvider<MustacheTemplateRenderer> templateRenderer,
			ProjectDescription projectDescription) {
		return new Beans(templateRenderer.getIfAvailable(() -> new MustacheTemplateRenderer("classpath:/templates")),
				projectDescription);
	}

	@Bean
	@ConditionalOnLanguage(JavaLanguage.ID)
	@ConditionalOnRequestedDependency("security")
	public BeansProjectContributor beansProjectContributor(Beans beans, ProjectDescription projectDescription) {
		return new BeansProjectContributor(beans, projectDescription);
	}

	@Bean
	@ConditionalOnLanguage(JavaLanguage.ID)
	@ConditionalOnRequestedDependency("security")
	public AuthenticationFilter authenticationFilter(ObjectProvider<MustacheTemplateRenderer> templateRenderer,
			ProjectDescription projectDescription) {
		return new AuthenticationFilter(
				templateRenderer.getIfAvailable(() -> new MustacheTemplateRenderer("classpath:/templates")),
				projectDescription);
	}

	@Bean
	@ConditionalOnLanguage(JavaLanguage.ID)
	@ConditionalOnRequestedDependency("security")
	public AuthenticationFilterProjectContributor authenticationFilterProjectContributor(
			AuthenticationFilter authenticationFilter, ProjectDescription projectDescription) {
		return new AuthenticationFilterProjectContributor(authenticationFilter, projectDescription);
	}

	@Bean
	@ConditionalOnLanguage(JavaLanguage.ID)
	@ConditionalOnRequestedDependency("security")
	public AuthenticationProvider authenticationProvider(ObjectProvider<MustacheTemplateRenderer> templateRenderer,
			ProjectDescription projectDescription) {
		return new AuthenticationProvider(
				templateRenderer.getIfAvailable(() -> new MustacheTemplateRenderer("classpath:/templates")),
				projectDescription);
	}

	@Bean
	@ConditionalOnLanguage(JavaLanguage.ID)
	@ConditionalOnRequestedDependency("security")
	public AuthenticationProviderProjectContributor authenticationProviderProjectContributor(
			AuthenticationProvider authenticationProvider, ProjectDescription projectDescription) {
		return new AuthenticationProviderProjectContributor(authenticationProvider, projectDescription);
	}

	@Bean
	@ConditionalOnLanguage(JavaLanguage.ID)
	@ConditionalOnRequestedDependency("security")
	public CustomAccessDeniedHandler customAccessDeniedHandler(
			ObjectProvider<MustacheTemplateRenderer> templateRenderer, ProjectDescription projectDescription) {
		return new CustomAccessDeniedHandler(
				templateRenderer.getIfAvailable(() -> new MustacheTemplateRenderer("classpath:/templates")),
				projectDescription);
	}

	@Bean
	@ConditionalOnLanguage(JavaLanguage.ID)
	@ConditionalOnRequestedDependency("security")
	public CustomAccessDeniedHandlerProjectContributor customAccessDeniedHandlerProjectContributor(
			CustomAccessDeniedHandler customAccessDeniedHandler, ProjectDescription projectDescription) {
		return new CustomAccessDeniedHandlerProjectContributor(customAccessDeniedHandler, projectDescription);
	}

	@Bean
	@ConditionalOnLanguage(JavaLanguage.ID)
	@ConditionalOnRequestedDependency("security")
	public CustomAuthenticationFailureHandler customAuthenticationFailureHandler(
			ObjectProvider<MustacheTemplateRenderer> templateRenderer, ProjectDescription projectDescription) {
		return new CustomAuthenticationFailureHandler(
				templateRenderer.getIfAvailable(() -> new MustacheTemplateRenderer("classpath:/templates")),
				projectDescription);
	}

	@Bean
	@ConditionalOnLanguage(JavaLanguage.ID)
	@ConditionalOnRequestedDependency("security")
	public CustomAuthenticationFailureHandlerProjectContributor customAuthenticationFailureHandlerProjectContributor(
			CustomAuthenticationFailureHandler customAuthenticationFailureHandler,
			ProjectDescription projectDescription) {
		return new CustomAuthenticationFailureHandlerProjectContributor(customAuthenticationFailureHandler,
				projectDescription);
	}

	@Bean
	@ConditionalOnLanguage(JavaLanguage.ID)
	@ConditionalOnRequestedDependency("security")
	public SecurityConfig securityConfig(ObjectProvider<MustacheTemplateRenderer> templateRenderer,
			ProjectDescription projectDescription) {
		return new SecurityConfig(
				templateRenderer.getIfAvailable(() -> new MustacheTemplateRenderer("classpath:/templates")),
				projectDescription);
	}

	@Bean
	@ConditionalOnLanguage(JavaLanguage.ID)
	@ConditionalOnRequestedDependency("security")
	public SecurityConfigProjectContributor securityConfigProjectContributor(SecurityConfig securityConfig,
			ProjectDescription projectDescription) {
		return new SecurityConfigProjectContributor(securityConfig, projectDescription);
	}

	@Bean
	@ConditionalOnLanguage(JavaLanguage.ID)
	@ConditionalOnRequestedDependency("security")
	public WithOutLogin withOutLogin(ObjectProvider<MustacheTemplateRenderer> templateRenderer,
			ProjectDescription projectDescription) {
		return new WithOutLogin(
				templateRenderer.getIfAvailable(() -> new MustacheTemplateRenderer("classpath:/templates")),
				projectDescription);
	}

	@Bean
	@ConditionalOnLanguage(JavaLanguage.ID)
	@ConditionalOnRequestedDependency("security")
	public WithOutLoginProjectContributor withOutLoginProjectContributor(WithOutLogin withOutLogin,
			ProjectDescription projectDescription) {
		return new WithOutLoginProjectContributor(withOutLogin, projectDescription);
	}

}
