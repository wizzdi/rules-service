package com.flexicore.rules.service;

import com.flexicore.annotations.plugins.PluginInfo;
import com.flexicore.data.jsoncontainers.PaginationResponse;
import com.flexicore.interfaces.ServicePlugin;
import com.flexicore.model.Baseclass;
import com.flexicore.rules.model.*;
import com.flexicore.rules.repository.ScenarioToActionRepository;
import com.flexicore.rules.request.ScenarioToActionCreate;
import com.flexicore.rules.request.ScenarioToActionFilter;
import com.flexicore.rules.request.ScenarioToActionUpdate;
import com.flexicore.security.SecurityContext;
import com.flexicore.service.BaseclassNewService;
import com.flexicore.service.DynamicInvokersService;

import javax.ws.rs.BadRequestException;
import java.util.*;
import java.util.stream.Collectors;
import org.pf4j.Extension;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@PluginInfo(version = 1)
@Extension
@Component
public class ScenarioToActionService implements ServicePlugin {

	@PluginInfo(version = 1)
	@Autowired
	private ScenarioToActionRepository repository;

	@Autowired
	private BaseclassNewService baseclassNewService;

	public <T extends Baseclass> List<T> listByIds(Class<T> c, Set<String> ids,
			SecurityContext securityContext) {
		return repository.listByIds(c, ids, securityContext);
	}

	public void validate(ScenarioToActionFilter scenarioToActionFilter,
			SecurityContext securityContext) {

		Set<String> actionsIds = scenarioToActionFilter.getActionsIds();
		Map<String, ScenarioAction> actionMap = actionsIds.isEmpty()
				? new HashMap<>()
				: listByIds(ScenarioAction.class, actionsIds, securityContext)
						.parallelStream().collect(
								Collectors.toMap(f -> f.getId(), f -> f));
		actionsIds.removeAll(actionMap.keySet());
		if (!actionsIds.isEmpty()) {
			throw new BadRequestException("No Scenario Action with ids "
					+ actionsIds);
		}
		scenarioToActionFilter.setScenarioActions(new ArrayList<>(actionMap
				.values()));

		Set<String> scenarioIds = scenarioToActionFilter.getScenarioIds();
		Map<String, Scenario> scenarioMap = scenarioIds.isEmpty()
				? new HashMap<>()
				: listByIds(Scenario.class, scenarioIds, securityContext)
						.parallelStream().collect(
								Collectors.toMap(f -> f.getId(), f -> f));
		scenarioIds.removeAll(scenarioMap.keySet());
		if (!scenarioIds.isEmpty()) {
			throw new BadRequestException("No Scenarios with ids "
					+ scenarioIds);
		}
		scenarioToActionFilter.setScenarios(new ArrayList<>(scenarioMap
				.values()));
	}

	public void validate(ScenarioToActionCreate creationContainer,
			SecurityContext securityContext) {
		String scenarioId = creationContainer.getScenarioId();
		Scenario scenario = scenarioId != null ? getByIdOrNull(scenarioId,
				Scenario.class, null, securityContext) : null;
		if (scenario == null && scenarioId != null) {
			throw new BadRequestException("No Scenario with id " + scenarioId);
		}
		creationContainer.setScenario(scenario);

		String actionId = creationContainer.getActionId();
		ScenarioAction action = actionId != null ? getByIdOrNull(actionId,
				ScenarioAction.class, null, securityContext) : null;
		if (action == null && actionId != null) {
			throw new BadRequestException("No ScenarioAction with id "
					+ actionId);
		}
		creationContainer.setScenarioAction(action);

	}

	public <T extends Baseclass> T getByIdOrNull(String id, Class<T> c,
			List<String> batchString, SecurityContext securityContext) {
		return repository.getByIdOrNull(id, c, batchString, securityContext);
	}

	public ScenarioToAction createScenarioToAction(
			ScenarioToActionCreate creationContainer,
			SecurityContext securityContext) {
		ScenarioToAction scenarioToAction = createScenarioToActionNoMerge(
				creationContainer, securityContext);
		repository.merge(scenarioToAction);
		return scenarioToAction;

	}

	public ScenarioToAction updateScenarioToAction(
			ScenarioToActionUpdate creationContainer,
			SecurityContext securityContext) {
		ScenarioToAction scenarioToAction = creationContainer
				.getScenarioToAction();
		if (updateScenarioToActionNoMerge(scenarioToAction, creationContainer)) {
			repository.merge(scenarioToAction);

		}
		return scenarioToAction;

	}

	private ScenarioToAction createScenarioToActionNoMerge(
			ScenarioToActionCreate creationContainer,
			SecurityContext securityContext) {
		ScenarioToAction scenarioToAction = new ScenarioToAction(creationContainer.getName(), securityContext);
		updateScenarioToActionNoMerge(scenarioToAction, creationContainer);
		return scenarioToAction;
	}

	private boolean updateScenarioToActionNoMerge(
			ScenarioToAction scenarioToAction,
			ScenarioToActionCreate scenarioToActionCreate) {
		boolean update = baseclassNewService.updateBaseclassNoMerge(scenarioToActionCreate,scenarioToAction);
		if (scenarioToActionCreate.getScenario() != null && (scenarioToAction.getScenario() == null || !scenarioToActionCreate.getScenario().getId().equals(scenarioToAction.getScenario().getId()))) {
			scenarioToAction.setScenario(scenarioToActionCreate.getScenario());
			update = true;
		}

		if (scenarioToActionCreate.getScenarioAction() != null && (scenarioToAction.getScenarioAction() == null || !scenarioToActionCreate.getScenarioAction().getId().equals(scenarioToAction.getScenarioAction().getId()))) {
			scenarioToAction.setScenarioAction(scenarioToActionCreate
					.getScenarioAction());
			update = true;
		}

		if (scenarioToActionCreate.getEnabled() != null && scenarioToActionCreate.getEnabled() != scenarioToAction.isEnabled()) {
			scenarioToAction.setEnabled(scenarioToActionCreate.getEnabled());
			update = true;
		}

		return update;

	}

	public PaginationResponse<ScenarioToAction> getAllScenarioToActions(
			ScenarioToActionFilter filter, SecurityContext securityContext) {
		List<ScenarioToAction> list = listAllScenarioToAction(filter,
				securityContext);
		long count = repository.countAllScenarioToActions(filter,
				securityContext);
		return new PaginationResponse<>(list, filter, count);
	}

	public List<ScenarioToAction> listAllScenarioToAction(
			ScenarioToActionFilter filter, SecurityContext securityContext) {
		return repository.listAllScenarioToActions(filter, securityContext);
	}

	public <T> T findByIdOrNull(Class<T> type, String id) {
		return repository.findByIdOrNull(type, id);
	}
}
