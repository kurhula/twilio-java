/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.flexapi.v1;

import com.twilio.base.Creator;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

import java.net.URI;

public class FlexFlowCreator extends Creator<FlexFlow> {
    private final String friendlyName;
    private final String chatServiceSid;
    private final FlexFlow.ChannelType channelType;
    private String contactIdentity;
    private Boolean enabled;
    private FlexFlow.IntegrationType integrationType;
    private String integrationFlowSid;
    private URI integrationUrl;
    private String integrationWorkspaceSid;
    private String integrationWorkflowSid;
    private String integrationChannel;
    private Integer integrationTimeout;
    private Integer integrationPriority;
    private Boolean integrationCreationOnMessage;
    private Boolean longLived;
    private Boolean janitorEnabled;

    /**
     * Construct a new FlexFlowCreator.
     *
     * @param friendlyName A string to describe the resource
     * @param chatServiceSid The SID of the chat service
     * @param channelType The channel type
     */
    public FlexFlowCreator(final String friendlyName,
                           final String chatServiceSid,
                           final FlexFlow.ChannelType channelType) {
        this.friendlyName = friendlyName;
        this.chatServiceSid = chatServiceSid;
        this.channelType = channelType;
    }

    /**
     * The channel contact's Identity..
     *
     * @param contactIdentity The channel contact's Identity
     * @return this
     */
    public FlexFlowCreator setContactIdentity(final String contactIdentity) {
        this.contactIdentity = contactIdentity;
        return this;
    }

    /**
     * Whether the new FlexFlow is enabled..
     *
     * @param enabled Whether the new FlexFlow is enabled
     * @return this
     */
    public FlexFlowCreator setEnabled(final Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     * The integration type. Can be: `studio`, `external`, or `task`..
     *
     * @param integrationType The integration type
     * @return this
     */
    public FlexFlowCreator setIntegrationType(final FlexFlow.IntegrationType integrationType) {
        this.integrationType = integrationType;
        return this;
    }

    /**
     * The SID of the Flow when `integration_type` is `studio`..
     *
     * @param integrationFlowSid The SID of the Flow
     * @return this
     */
    public FlexFlowCreator setIntegrationFlowSid(final String integrationFlowSid) {
        this.integrationFlowSid = integrationFlowSid;
        return this;
    }

    /**
     * The External Webhook URL when `integration_type` is `external`..
     *
     * @param integrationUrl The External Webhook URL
     * @return this
     */
    public FlexFlowCreator setIntegrationUrl(final URI integrationUrl) {
        this.integrationUrl = integrationUrl;
        return this;
    }

    /**
     * The External Webhook URL when `integration_type` is `external`..
     *
     * @param integrationUrl The External Webhook URL
     * @return this
     */
    public FlexFlowCreator setIntegrationUrl(final String integrationUrl) {
        return setIntegrationUrl(Promoter.uriFromString(integrationUrl));
    }

    /**
     * The Workspace SID for a new task for Task `integration_type`..
     *
     * @param integrationWorkspaceSid The Workspace SID for a new task
     * @return this
     */
    public FlexFlowCreator setIntegrationWorkspaceSid(final String integrationWorkspaceSid) {
        this.integrationWorkspaceSid = integrationWorkspaceSid;
        return this;
    }

    /**
     * The Workflow SID for a new task when `integration_type` is `task`..
     *
     * @param integrationWorkflowSid The Workflow SID for a new task
     * @return this
     */
    public FlexFlowCreator setIntegrationWorkflowSid(final String integrationWorkflowSid) {
        this.integrationWorkflowSid = integrationWorkflowSid;
        return this;
    }

    /**
     * The task channel for a new task when `integration_type` is `task`. The
     * default is `default`..
     *
     * @param integrationChannel The task channel for a new task
     * @return this
     */
    public FlexFlowCreator setIntegrationChannel(final String integrationChannel) {
        this.integrationChannel = integrationChannel;
        return this;
    }

    /**
     * The task timeout in seconds for a new task when `integration_type` is `task`.
     * The default is `86,400` seconds (24 hours)..
     *
     * @param integrationTimeout The task timeout in seconds for a new task
     * @return this
     */
    public FlexFlowCreator setIntegrationTimeout(final Integer integrationTimeout) {
        this.integrationTimeout = integrationTimeout;
        return this;
    }

    /**
     * The task priority of a new task when `integration_type` is `task`. The
     * default priority is `0`..
     *
     * @param integrationPriority The task priority of a new task
     * @return this
     */
    public FlexFlowCreator setIntegrationPriority(final Integer integrationPriority) {
        this.integrationPriority = integrationPriority;
        return this;
    }

    /**
     * Whether to create a task when the first message arrives when
     * `integration_type` is `task`. If `false`, the task is created with the
     * channel..
     *
     * @param integrationCreationOnMessage Whether to create a task when the first
     *                                     message arrives
     * @return this
     */
    public FlexFlowCreator setIntegrationCreationOnMessage(final Boolean integrationCreationOnMessage) {
        this.integrationCreationOnMessage = integrationCreationOnMessage;
        return this;
    }

    /**
     * Whether new channels are long-lived..
     *
     * @param longLived Whether new channels are long-lived
     * @return this
     */
    public FlexFlowCreator setLongLived(final Boolean longLived) {
        this.longLived = longLived;
        return this;
    }

    /**
     * Boolean flag for enabling or disabling the Janitor.
     *
     * @param janitorEnabled Boolean flag for enabling or disabling the Janitor
     * @return this
     */
    public FlexFlowCreator setJanitorEnabled(final Boolean janitorEnabled) {
        this.janitorEnabled = janitorEnabled;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created FlexFlow
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public FlexFlow create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.FLEXAPI.toString(),
            "/v1/FlexFlows",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("FlexFlow creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }

            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }

        return FlexFlow.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }

        if (chatServiceSid != null) {
            request.addPostParam("ChatServiceSid", chatServiceSid);
        }

        if (channelType != null) {
            request.addPostParam("ChannelType", channelType.toString());
        }

        if (contactIdentity != null) {
            request.addPostParam("ContactIdentity", contactIdentity);
        }

        if (enabled != null) {
            request.addPostParam("Enabled", enabled.toString());
        }

        if (integrationType != null) {
            request.addPostParam("IntegrationType", integrationType.toString());
        }

        if (integrationFlowSid != null) {
            request.addPostParam("Integration.FlowSid", integrationFlowSid);
        }

        if (integrationUrl != null) {
            request.addPostParam("Integration.Url", integrationUrl.toString());
        }

        if (integrationWorkspaceSid != null) {
            request.addPostParam("Integration.WorkspaceSid", integrationWorkspaceSid);
        }

        if (integrationWorkflowSid != null) {
            request.addPostParam("Integration.WorkflowSid", integrationWorkflowSid);
        }

        if (integrationChannel != null) {
            request.addPostParam("Integration.Channel", integrationChannel);
        }

        if (integrationTimeout != null) {
            request.addPostParam("Integration.Timeout", integrationTimeout.toString());
        }

        if (integrationPriority != null) {
            request.addPostParam("Integration.Priority", integrationPriority.toString());
        }

        if (integrationCreationOnMessage != null) {
            request.addPostParam("Integration.CreationOnMessage", integrationCreationOnMessage.toString());
        }

        if (longLived != null) {
            request.addPostParam("LongLived", longLived.toString());
        }

        if (janitorEnabled != null) {
            request.addPostParam("JanitorEnabled", janitorEnabled.toString());
        }
    }
}