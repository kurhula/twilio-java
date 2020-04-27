/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.chat.v1.service;

import com.twilio.base.Creator;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class ChannelCreator extends Creator<Channel> {
    private final String pathServiceSid;
    private String friendlyName;
    private String uniqueName;
    private String attributes;
    private Channel.ChannelType type;

    /**
     * Construct a new ChannelCreator.
     *
     * @param pathServiceSid The SID of the Service to create the resource under
     */
    public ChannelCreator(final String pathServiceSid) {
        this.pathServiceSid = pathServiceSid;
    }

    /**
     * A descriptive string that you create to describe the new resource. It can be
     * up to 64 characters long..
     *
     * @param friendlyName A string to describe the new resource
     * @return this
     */
    public ChannelCreator setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * An application-defined string that uniquely identifies the resource. It can
     * be used to address the resource in place of the resource's `sid` in the URL.
     * This value must be 64 characters or less in length and be unique within the
     * Service..
     *
     * @param uniqueName An application-defined string that uniquely identifies the
     *                   resource
     * @return this
     */
    public ChannelCreator setUniqueName(final String uniqueName) {
        this.uniqueName = uniqueName;
        return this;
    }

    /**
     * A valid JSON string that contains application-specific data..
     *
     * @param attributes A valid JSON string that contains application-specific data
     * @return this
     */
    public ChannelCreator setAttributes(final String attributes) {
        this.attributes = attributes;
        return this;
    }

    /**
     * The visibility of the channel. Can be: `public` or `private` and defaults to
     * `public`..
     *
     * @param type The visibility of the channel
     * @return this
     */
    public ChannelCreator setType(final Channel.ChannelType type) {
        this.type = type;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created Channel
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Channel create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.CHAT.toString(),
            "/v1/Services/" + this.pathServiceSid + "/Channels",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Channel creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Channel.fromJson(response.getStream(), client.getObjectMapper());
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

        if (uniqueName != null) {
            request.addPostParam("UniqueName", uniqueName);
        }

        if (attributes != null) {
            request.addPostParam("Attributes", attributes);
        }

        if (type != null) {
            request.addPostParam("Type", type.toString());
        }
    }
}