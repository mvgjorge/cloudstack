package com.globo.networkapi.commands;

import com.cloud.agent.api.Command;

public class CreateNewVlanInNetworkAPICommand extends Command {
	
	private String vlanName;
	
	private String vlanDescription;

	private Long networkAPIEnvironmentId;

	@Override
	public boolean executeInSequence() {
		return false;
	}

	public String getVlanName() {
		return vlanName;
	}

	public void setVlanName(String vlanName) {
		this.vlanName = vlanName;
	}

	public String getVlanDescription() {
		return vlanDescription;
	}

	public void setVlanDescription(String vlanDescription) {
		this.vlanDescription = vlanDescription;
	}

	public Long getNetworkAPIEnvironmentId() {
		return networkAPIEnvironmentId;
	}

	public void setNetworkAPIEnvironmentId(Long networkAPIEnvironmentId) {
		this.networkAPIEnvironmentId = networkAPIEnvironmentId;
	}

}