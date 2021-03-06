package com.flexicore.rules.service;

import com.flexicore.annotations.plugins.PluginInfo;
import com.flexicore.data.jsoncontainers.PaginationResponse;
import com.flexicore.interfaces.ServicePlugin;
import com.flexicore.model.Baseclass;
import com.flexicore.rules.model.ScenarioTriggerType;
import com.flexicore.rules.repository.ScenarioTriggerTypeRepository;
import com.flexicore.rules.request.ScenarioTriggerTypeCreate;
import com.flexicore.rules.request.ScenarioTriggerTypeFilter;
import com.flexicore.rules.request.ScenarioTriggerTypeUpdate;
import com.flexicore.security.SecurityContext;
import com.flexicore.service.BaseclassNewService;
import org.pf4j.Extension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@PluginInfo(version = 1)
@Extension
@Component
public class ScenarioTriggerTypeService implements ServicePlugin {

	@PluginInfo(version = 1)
	@Autowired
	private ScenarioTriggerTypeRepository repository;


	@Autowired
	private BaseclassNewService baseclassNewService;

	
	public void validate(ScenarioTriggerTypeFilter scenarioTriggerTypeArgumentFilter, SecurityContext securityContext) {
		baseclassNewService.validateFilter(scenarioTriggerTypeArgumentFilter,securityContext);

	}

	
	
	public void validate(ScenarioTriggerTypeCreate creationContainer, SecurityContext securityContext) {
		baseclassNewService.validateCreate(creationContainer,securityContext);
	}

	public <T extends Baseclass> T getByIdOrNull(String id, Class<T> c,
			List<String> batchString, SecurityContext securityContext) {
		return repository.getByIdOrNull(id, c, batchString, securityContext);
	}

	
	public ScenarioTriggerType createScenarioTriggerType(ScenarioTriggerTypeCreate creationContainer, SecurityContext securityContext) {
		ScenarioTriggerType scenarioTriggerType = createScenarioTriggerTypeNoMerge(
				creationContainer, securityContext);
		repository.merge(scenarioTriggerType);
		return scenarioTriggerType;
	}

	
	public ScenarioTriggerType updateScenarioTriggerType(ScenarioTriggerTypeUpdate creationContainer, SecurityContext securityContext) {
		ScenarioTriggerType scenarioTriggerType = creationContainer.getScenarioTriggerType();
		if (updateScenarioTriggerTypeNoMerge(scenarioTriggerType, creationContainer)) {
			repository.merge(scenarioTriggerType);
		}
		return scenarioTriggerType;
	}

	
	
	public ScenarioTriggerType createScenarioTriggerTypeNoMerge(ScenarioTriggerTypeCreate creationContainer, SecurityContext securityContext) {
		ScenarioTriggerType scenarioTriggerType = new ScenarioTriggerType(creationContainer.getName(), securityContext);
		updateScenarioTriggerTypeNoMerge(scenarioTriggerType, creationContainer);
		return scenarioTriggerType;
	}

	
	
	public boolean updateScenarioTriggerTypeNoMerge(ScenarioTriggerType scenarioTriggerType, ScenarioTriggerTypeCreate creationContainer) {
		boolean update = baseclassNewService.updateBaseclassNoMerge(creationContainer,scenarioTriggerType);
		if (creationContainer.getEventCanonicalName() != null && !creationContainer.getEventCanonicalName().equals(scenarioTriggerType.getEventCanonicalName())) {
			scenarioTriggerType.setEventCanonicalName(creationContainer.getEventCanonicalName());
			update = true;
		}
		return update;
	}

	
	public PaginationResponse<ScenarioTriggerType> getAllScenarioTriggerTypes(ScenarioTriggerTypeFilter filter, SecurityContext securityContext) {
		List<ScenarioTriggerType> list = listAllTriggerTypes(filter, securityContext);
		long count = repository.countAllScenarioTriggerTypes(filter, securityContext);
		return new PaginationResponse<>(list, filter, count);
	}

	public List<ScenarioTriggerType> listAllTriggerTypes(ScenarioTriggerTypeFilter filter, SecurityContext securityContext) {
		return repository.listAllScenarioTriggerTypes(filter, securityContext);
	}

	public <T> T findByIdOrNull(Class<T> type, String id) {
		return repository.findByIdOrNull(type, id);
	}

	public void merge(Object base) {
		repository.merge(base);
	}

	public void massMerge(List<?> toMerge) {
		repository.massMerge(toMerge);
	}
}
