package com.rh.handler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;
import org.kie.api.task.model.Status;
import org.kie.api.task.model.TaskSummary;
import org.kie.internal.query.QueryFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rh.config.ProcessHandler;

@Component("Allocate")
public class AllocateHandler implements WorkItemHandler {
	
	@Autowired 
	ProcessHandler handler;

    private static final Logger LOGGER = LoggerFactory.getLogger(AllocateHandler.class);

    private static final String INPUT = "input";

    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager workItemManager) {
    	LOGGER.info("Allocate workitem : {}", workItem.getParameter(INPUT));
    	
    	Map<String, String> headerMap = Arrays.stream(((String)workItem.getParameter(INPUT)).split(","))
                .map(s -> s.split(":"))
                .collect(Collectors.toMap(s -> s[0], s -> s[1]));
    	
    	String userId = headerMap.get("userId");
    	
    	LOGGER.info("Allocate userid : {}", userId);
    	
    	QueryFilter filter = new QueryFilter();
    	
    	List<TaskSummary> taskInstances = handler.getRuntimeDataService().getTasksByStatusByProcessInstanceId(workItem.getProcessInstanceId(), 
    													Arrays.asList(Status.Created, Status.Ready, Status.Reserved), filter);
    	TaskSummary task = taskInstances.get(0);
    	
    	handler.getUserTaskService().delegate(task.getId(), "rhpamAdmin", userId);
        workItemManager.completeWorkItem(workItem.getId(), new HashMap<>());
    }

    @Override
    public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {}
}