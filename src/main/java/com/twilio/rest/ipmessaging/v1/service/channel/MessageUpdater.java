/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.ipmessaging.v1.service.channel;

import com.twilio.base.Updater;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class MessageUpdater extends Updater<Message> {
    private final String pathServiceSid;
    private final String pathChannelSid;
    private final String pathSid;
    private String body;
    private String attributes;

    /**
     * Construct a new MessageUpdater.
     *
     * @param pathServiceSid The SID of the Service to update the resource from
     * @param pathChannelSid he unique ID of the Channel the message belongs to
     * @param pathSid The unique string that identifies the resource
     */
    public MessageUpdater(final String pathServiceSid,
                          final String pathChannelSid,
                          final String pathSid) {
        this.pathServiceSid = pathServiceSid;
        this.pathChannelSid = pathChannelSid;
        this.pathSid = pathSid;
    }

    /**
     * The message to send to the channel. Can also be an empty string or `null`,
     * which sets the value as an empty string. You can send structured data in the
     * body by serializing it as a string..
     *
     * @param body The message to send to the channel
     * @return this
     */
    public MessageUpdater setBody(final String body) {
        this.body = body;
        return this;
    }

    /**
     * A valid JSON string that contains application-specific data..
     *
     * @param attributes A valid JSON string that contains application-specific data
     * @return this
     */
    public MessageUpdater setAttributes(final String attributes) {
        this.attributes = attributes;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated Message
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Message update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.IPMESSAGING.toString(),
            "/v1/Services/" + this.pathServiceSid + "/Channels/" + this.pathChannelSid + "/Messages/" + this.pathSid + "",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Message update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Message.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (body != null) {
            request.addPostParam("Body", body);
        }

        if (attributes != null) {
            request.addPostParam("Attributes", attributes);
        }
    }
}