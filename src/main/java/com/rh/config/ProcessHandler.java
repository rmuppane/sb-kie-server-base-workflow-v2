package com.rh.config;

import org.jbpm.services.api.ProcessService;
import org.jbpm.services.api.RuntimeDataService;
import org.jbpm.services.api.UserTaskService;
import org.jbpm.services.api.admin.UserTaskAdminService;
import org.springframework.stereotype.Component;

@Component
public class ProcessHandler {

	private ProcessService processService;
	private RuntimeDataService runtimeDataService;
	private UserTaskService userTaskService;
	
	private UserTaskAdminService userTaskAdminService;
	

	public ProcessHandler(ProcessService processService, RuntimeDataService runtimeDataService, UserTaskService userTaskService, UserTaskAdminService userTaskAdminService) {
		this.processService = processService;
		this.runtimeDataService = runtimeDataService;
		this.userTaskService = userTaskService;
		
		this.userTaskAdminService = userTaskAdminService;
		
	}

	public ProcessService getProcessService() {
		return processService;
	}

	public RuntimeDataService getRuntimeDataService() {
		return runtimeDataService;
	}

	public UserTaskService getUserTaskService() {
		return userTaskService;
	}

	public UserTaskAdminService getUserTaskAdminService() {
		return userTaskAdminService;
	}
}