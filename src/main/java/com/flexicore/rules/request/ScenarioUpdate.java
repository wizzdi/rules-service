package com.flexicore.rules.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flexicore.rules.model.Scenario;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Includes all fields from ScenarioCreate container")
public class ScenarioUpdate extends ScenarioCreate {
	private String id;
	@JsonIgnore
	private Scenario scenario;

	public String getId() {
		return id;
	}
	@Schema(description = "The Id of the updated Scenario", nullable = false)
	public <T extends ScenarioUpdate> T setId(String id) {
		this.id = id;
		return (T) this;
	}

	@JsonIgnore
	public Scenario getScenario() {
		return scenario;
	}

	public <T extends ScenarioUpdate> T setScenario(Scenario scenario) {
		this.scenario = scenario;
		return (T) this;
	}
}
