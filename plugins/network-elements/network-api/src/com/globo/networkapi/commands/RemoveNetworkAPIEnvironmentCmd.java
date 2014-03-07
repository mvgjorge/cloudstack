//Licensed to the Apache Software Foundation (ASF) under one
//or more contributor license agreements.  See the NOTICE file
//distributed with this work for additional information
//regarding copyright ownership.  The ASF licenses this file
//to you under the Apache License, Version 2.0 (the
//"License"); you may not use this file except in compliance
//with the License.  You may obtain a copy of the License at
//
//http://www.apache.org/licenses/LICENSE-2.0
//
//Unless required by applicable law or agreed to in writing,
//software distributed under the License is distributed on an
//"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
//KIND, either express or implied.  See the License for the
//specific language governing permissions and limitations
//under the License.
package com.globo.networkapi.commands;

import javax.inject.Inject;

import org.apache.cloudstack.api.APICommand;
import org.apache.cloudstack.api.ApiConstants;
import org.apache.cloudstack.api.ApiErrorCode;
import org.apache.cloudstack.api.BaseAsyncCmd;
import org.apache.cloudstack.api.Parameter;
import org.apache.cloudstack.api.ServerApiException;
import org.apache.cloudstack.api.response.PhysicalNetworkResponse;
import org.apache.cloudstack.api.response.SuccessResponse;

import com.cloud.event.EventTypes;
import com.cloud.exception.ConcurrentOperationException;
import com.cloud.exception.InsufficientCapacityException;
import com.cloud.exception.InvalidParameterValueException;
import com.cloud.exception.ResourceAllocationException;
import com.cloud.exception.ResourceUnavailableException;
import com.cloud.user.UserContext;
import com.cloud.utils.exception.CloudRuntimeException;
import com.globo.networkapi.element.NetworkAPIService;

@APICommand(name = "removeNetworkAPIEnvironment", responseObject = SuccessResponse.class, description = "Removes a NetworkAPI environment from a zone")
public class RemoveNetworkAPIEnvironmentCmd extends BaseAsyncCmd {

	private static final String s_name = "removenetworkapiresponse";
	@Inject
	NetworkAPIService _napiManager;

	// ///////////////////////////////////////////////////
	// ////////////// API parameters /////////////////////
	// ///////////////////////////////////////////////////

	@Parameter(name = ApiConstants.PHYSICAL_NETWORK_ID, type = CommandType.UUID, entityType = PhysicalNetworkResponse.class, required = true, description = "the Physical Network ID")
	private Long physicalNetworkId;

	@Parameter(name = "napienvironmentid", type = CommandType.LONG, required = true, description = "the Id of environment in NetworkAPI")
	private Long napiEnvironmentId;

	// ///////////////////////////////////////////////////
	// ///////////////// Accessors ///////////////////////
	// ///////////////////////////////////////////////////

	public Long getPhysicalNetworkId() {
		return physicalNetworkId;
	}

	public Long getNapiEnvironmentId() {
		return napiEnvironmentId;
	}

	// ///////////////////////////////////////////////////
	// ///////////// API Implementation///////////////////
	// ///////////////////////////////////////////////////

	@Override
	public void execute() throws ResourceUnavailableException,
			InsufficientCapacityException, ServerApiException,
			ConcurrentOperationException, ResourceAllocationException {
		try {
			// FIXME Implement this functionality
			// boolean result = _napiManager.removeNetworkAPIEnvironment(physicalNetworkId, napiEnvironmentId);
			boolean result = true;

			if (result) {
				SuccessResponse response = new SuccessResponse(getCommandName());
				this.setResponseObject(response);
			} else {
                throw new ServerApiException(ApiErrorCode.INTERNAL_ERROR, "Failed to remove the environment.");
			}	
		} catch (InvalidParameterValueException invalidParamExcp) {
			throw new ServerApiException(ApiErrorCode.PARAM_ERROR,
					invalidParamExcp.getMessage());
		} catch (CloudRuntimeException runtimeExcp) {
			throw new ServerApiException(ApiErrorCode.INTERNAL_ERROR,
					runtimeExcp.getMessage());
		}
	}

    @Override
	public String getCommandName() {
		return s_name;
	}

	@Override
	public long getEntityOwnerId() {
		return UserContext.current().getCaller().getId();
	}

	@Override
	public String getEventType() {
		return EventTypes.EVENT_SERVICE_PROVIDER_DELETE;
	}

	@Override
	public String getEventDescription() {
		return "Removing a NetworkAPI Environment from a zone";
	}

}