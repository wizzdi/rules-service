package com.flexicore.rules.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flexicore.rules.model.ScenarioTriggerType;
import com.wizzdi.flexicore.file.model.FileResource;
import com.wizzdi.flexicore.security.request.BasicPropertiesFilter;
import com.wizzdi.flexicore.security.request.PaginationFilter;
import java.util.List;
import java.util.Set;

public class ScenarioTriggerFilter extends PaginationFilter {

  private Set<String> lastEventId;

  private Set<String> lastActivated;

  private Set<String> validFrom;

  private Set<String> cooldownIntervalMs;

  private Set<String> logFileResourceIds;

  @JsonIgnore private List<ScenarioTriggerType> scenarioTriggerType;

  private Set<String> validTill;

  private Set<String> scenarioTriggerTypeIds;

  private Set<String> evaluatingJSCodeIds;

  private Set<String> activeTill;

  private Set<String> activeMs;

  @JsonIgnore private List<FileResource> logFileResource;

  @JsonIgnore private List<FileResource> evaluatingJSCode;

  private BasicPropertiesFilter basicPropertiesFilter;

  public Set<String> getLastEventId() {
    return this.lastEventId;
  }

  public <T extends ScenarioTriggerFilter> T setLastEventId(Set<String> lastEventId) {
    this.lastEventId = lastEventId;
    return (T) this;
  }

  public Set<String> getLastActivated() {
    return this.lastActivated;
  }

  public <T extends ScenarioTriggerFilter> T setLastActivated(Set<String> lastActivated) {
    this.lastActivated = lastActivated;
    return (T) this;
  }

  public Set<String> getValidFrom() {
    return this.validFrom;
  }

  public <T extends ScenarioTriggerFilter> T setValidFrom(Set<String> validFrom) {
    this.validFrom = validFrom;
    return (T) this;
  }

  public Set<String> getCooldownIntervalMs() {
    return this.cooldownIntervalMs;
  }

  public <T extends ScenarioTriggerFilter> T setCooldownIntervalMs(Set<String> cooldownIntervalMs) {
    this.cooldownIntervalMs = cooldownIntervalMs;
    return (T) this;
  }

  public Set<String> getLogFileResourceIds() {
    return this.logFileResourceIds;
  }

  public <T extends ScenarioTriggerFilter> T setLogFileResourceIds(Set<String> logFileResourceIds) {
    this.logFileResourceIds = logFileResourceIds;
    return (T) this;
  }

  @JsonIgnore
  public List<ScenarioTriggerType> getScenarioTriggerType() {
    return this.scenarioTriggerType;
  }

  public <T extends ScenarioTriggerFilter> T setScenarioTriggerType(
      List<ScenarioTriggerType> scenarioTriggerType) {
    this.scenarioTriggerType = scenarioTriggerType;
    return (T) this;
  }

  public Set<String> getValidTill() {
    return this.validTill;
  }

  public <T extends ScenarioTriggerFilter> T setValidTill(Set<String> validTill) {
    this.validTill = validTill;
    return (T) this;
  }

  public Set<String> getScenarioTriggerTypeIds() {
    return this.scenarioTriggerTypeIds;
  }

  public <T extends ScenarioTriggerFilter> T setScenarioTriggerTypeIds(
      Set<String> scenarioTriggerTypeIds) {
    this.scenarioTriggerTypeIds = scenarioTriggerTypeIds;
    return (T) this;
  }

  public Set<String> getEvaluatingJSCodeIds() {
    return this.evaluatingJSCodeIds;
  }

  public <T extends ScenarioTriggerFilter> T setEvaluatingJSCodeIds(
      Set<String> evaluatingJSCodeIds) {
    this.evaluatingJSCodeIds = evaluatingJSCodeIds;
    return (T) this;
  }

  public Set<String> getActiveTill() {
    return this.activeTill;
  }

  public <T extends ScenarioTriggerFilter> T setActiveTill(Set<String> activeTill) {
    this.activeTill = activeTill;
    return (T) this;
  }

  public Set<String> getActiveMs() {
    return this.activeMs;
  }

  public <T extends ScenarioTriggerFilter> T setActiveMs(Set<String> activeMs) {
    this.activeMs = activeMs;
    return (T) this;
  }

  @JsonIgnore
  public List<FileResource> getLogFileResource() {
    return this.logFileResource;
  }

  public <T extends ScenarioTriggerFilter> T setLogFileResource(
      List<FileResource> logFileResource) {
    this.logFileResource = logFileResource;
    return (T) this;
  }

  @JsonIgnore
  public List<FileResource> getEvaluatingJSCode() {
    return this.evaluatingJSCode;
  }

  public <T extends ScenarioTriggerFilter> T setEvaluatingJSCode(
      List<FileResource> evaluatingJSCode) {
    this.evaluatingJSCode = evaluatingJSCode;
    return (T) this;
  }

  public BasicPropertiesFilter getBasicPropertiesFilter() {
    return this.basicPropertiesFilter;
  }

  public <T extends ScenarioTriggerFilter> T setBasicPropertiesFilter(
      BasicPropertiesFilter basicPropertiesFilter) {
    this.basicPropertiesFilter = basicPropertiesFilter;
    return (T) this;
  }
}