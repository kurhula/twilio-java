/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.authy.v1;

import com.twilio.base.Creator;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

/**
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer preview
 * access, please contact help@twilio.com.
 */
public class ServiceCreator extends Creator<Service> {
    private final String friendlyName;
    private String push;

    /**
     * Construct a new ServiceCreator.
     *
     * @param friendlyName A human readable description of this resource.
     */
    public ServiceCreator(final String friendlyName) {
        this.friendlyName = friendlyName;
    }

    /**
     * The optional service level push factors configuration. If present it must be
     * a json string with the following format: {"notify_service_sid":
     * "ISXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "include_date": true}.
     *
     * @param push Optional service level push factors configuration
     * @return this
     */
    public ServiceCreator setPush(final String push) {
        this.push = push;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created Service
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Service create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.AUTHY.toString(),
            "/v1/Services",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Service creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Service.fromJson(response.getStream(), client.getObjectMapper());
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

        if (push != null) {
            request.addPostParam("Push", push);
        }
    }
}