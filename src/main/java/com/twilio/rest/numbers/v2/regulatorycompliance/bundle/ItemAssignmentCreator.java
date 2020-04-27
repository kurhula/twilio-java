/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.numbers.v2.regulatorycompliance.bundle;

import com.twilio.base.Creator;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class ItemAssignmentCreator extends Creator<ItemAssignment> {
    private final String pathBundleSid;
    private final String objectSid;

    /**
     * Construct a new ItemAssignmentCreator.
     *
     * @param pathBundleSid The unique string that identifies the resource.
     * @param objectSid The sid of an object bag
     */
    public ItemAssignmentCreator(final String pathBundleSid,
                                 final String objectSid) {
        this.pathBundleSid = pathBundleSid;
        this.objectSid = objectSid;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created ItemAssignment
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public ItemAssignment create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.NUMBERS.toString(),
            "/v2/RegulatoryCompliance/Bundles/" + this.pathBundleSid + "/ItemAssignments",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("ItemAssignment creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return ItemAssignment.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (objectSid != null) {
            request.addPostParam("ObjectSid", objectSid);
        }
    }
}