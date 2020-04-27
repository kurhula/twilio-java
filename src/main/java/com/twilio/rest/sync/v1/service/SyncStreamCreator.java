/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.sync.v1.service;

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
 * PLEASE NOTE that this class contains beta products that are subject to
 * change. Use them with caution.
 */
public class SyncStreamCreator extends Creator<SyncStream> {
    private final String pathServiceSid;
    private String uniqueName;
    private Integer ttl;

    /**
     * Construct a new SyncStreamCreator.
     *
     * @param pathServiceSid The SID of the Sync Service to create the new Stream in
     */
    public SyncStreamCreator(final String pathServiceSid) {
        this.pathServiceSid = pathServiceSid;
    }

    /**
     * An application-defined string that uniquely identifies the resource. This
     * value must be unique within its Service and it can be up to 320 characters
     * long. The `unique_name` value can be used as an alternative to the `sid` in
     * the URL path to address the resource..
     *
     * @param uniqueName An application-defined string that uniquely identifies the
     *                   resource
     * @return this
     */
    public SyncStreamCreator setUniqueName(final String uniqueName) {
        this.uniqueName = uniqueName;
        return this;
    }

    /**
     * How long, in seconds, before the Stream expires and is deleted
     * (time-to-live). Can be an integer from 0 to 31,536,000 (1 year). The default
     * value is `0`, which means the Stream does not expire. The Stream will be
     * deleted automatically after it expires, but there can be a delay between the
     * expiration time and the resources's deletion..
     *
     * @param ttl How long, in seconds, before the Stream expires and is deleted
     * @return this
     */
    public SyncStreamCreator setTtl(final Integer ttl) {
        this.ttl = ttl;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created SyncStream
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public SyncStream create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.SYNC.toString(),
            "/v1/Services/" + this.pathServiceSid + "/Streams",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("SyncStream creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return SyncStream.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (uniqueName != null) {
            request.addPostParam("UniqueName", uniqueName);
        }

        if (ttl != null) {
            request.addPostParam("Ttl", ttl.toString());
        }
    }
}