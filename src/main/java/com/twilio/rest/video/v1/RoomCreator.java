/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.video.v1;

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
import java.util.List;

public class RoomCreator extends Creator<Room> {
    private Boolean enableTurn;
    private Room.RoomType type;
    private String uniqueName;
    private URI statusCallback;
    private HttpMethod statusCallbackMethod;
    private Integer maxParticipants;
    private Boolean recordParticipantsOnConnect;
    private List<Room.VideoCodec> videoCodecs;
    private String mediaRegion;

    /**
     * Deprecated. Whether to enable [Twilio's Network Traversal TURN
     * service](https://www.twilio.com/stun-turn). TURN service is used when direct
     * peer-to-peer media connections cannot be established due to firewall
     * restrictions. This setting only applies to rooms with type `peer-to-peer`..
     *
     * @param enableTurn Enable Twilio's Network Traversal TURN service
     * @return this
     */
    public RoomCreator setEnableTurn(final Boolean enableTurn) {
        this.enableTurn = enableTurn;
        return this;
    }

    /**
     * The type of room. Can be: `peer-to-peer`, `group-small`, or `group`. The
     * default value is `group`..
     *
     * @param type The type of room
     * @return this
     */
    public RoomCreator setType(final Room.RoomType type) {
        this.type = type;
        return this;
    }

    /**
     * An application-defined string that uniquely identifies the resource. It can
     * be used as a `room_sid` in place of the resource's `sid` in the URL to
     * address the resource. This value is unique for `in-progress` rooms. SDK
     * clients can use this name to connect to the room. REST API clients can use
     * this name in place of the Room SID to interact with the room as long as the
     * room is `in-progress`..
     *
     * @param uniqueName An application-defined string that uniquely identifies the
     *                   resource
     * @return this
     */
    public RoomCreator setUniqueName(final String uniqueName) {
        this.uniqueName = uniqueName;
        return this;
    }

    /**
     * The URL we should call using the `status_callback_method` to send status
     * information to your application on every room event. See [Status
     * Callbacks](https://www.twilio.com/docs/video/api/status-callbacks) for more
     * info..
     *
     * @param statusCallback The URL to send status information to your application
     * @return this
     */
    public RoomCreator setStatusCallback(final URI statusCallback) {
        this.statusCallback = statusCallback;
        return this;
    }

    /**
     * The URL we should call using the `status_callback_method` to send status
     * information to your application on every room event. See [Status
     * Callbacks](https://www.twilio.com/docs/video/api/status-callbacks) for more
     * info..
     *
     * @param statusCallback The URL to send status information to your application
     * @return this
     */
    public RoomCreator setStatusCallback(final String statusCallback) {
        return setStatusCallback(Promoter.uriFromString(statusCallback));
    }

    /**
     * The HTTP method we should use to call `status_callback`. Can be `POST` or
     * `GET`..
     *
     * @param statusCallbackMethod The HTTP method we should use to call
     *                             status_callback
     * @return this
     */
    public RoomCreator setStatusCallbackMethod(final HttpMethod statusCallbackMethod) {
        this.statusCallbackMethod = statusCallbackMethod;
        return this;
    }

    /**
     * The maximum number of concurrent Participants allowed in the room.
     * Peer-to-peer rooms can have up to 10 Participants. Small Group rooms can have
     * up to 4 Participants. Group rooms can have up to 50 Participants..
     *
     * @param maxParticipants The maximum number of concurrent Participants allowed
     *                        in the room
     * @return this
     */
    public RoomCreator setMaxParticipants(final Integer maxParticipants) {
        this.maxParticipants = maxParticipants;
        return this;
    }

    /**
     * Whether to start recording when Participants connect. ***This feature is not
     * available in `peer-to-peer` rooms.***.
     *
     * @param recordParticipantsOnConnect Whether to start recording when
     *                                    Participants connect
     * @return this
     */
    public RoomCreator setRecordParticipantsOnConnect(final Boolean recordParticipantsOnConnect) {
        this.recordParticipantsOnConnect = recordParticipantsOnConnect;
        return this;
    }

    /**
     * An array of the video codecs that are supported when publishing a track in
     * the room.  Can be: `VP8` and `H264`.  ***This feature is not available in
     * `peer-to-peer` rooms***.
     *
     * @param videoCodecs An array of the video codecs that are supported when
     *                    publishing a track in the room
     * @return this
     */
    public RoomCreator setVideoCodecs(final List<Room.VideoCodec> videoCodecs) {
        this.videoCodecs = videoCodecs;
        return this;
    }

    /**
     * An array of the video codecs that are supported when publishing a track in
     * the room.  Can be: `VP8` and `H264`.  ***This feature is not available in
     * `peer-to-peer` rooms***.
     *
     * @param videoCodecs An array of the video codecs that are supported when
     *                    publishing a track in the room
     * @return this
     */
    public RoomCreator setVideoCodecs(final Room.VideoCodec videoCodecs) {
        return setVideoCodecs(Promoter.listOfOne(videoCodecs));
    }

    /**
     * The region for the media server in Group Rooms.  Can be: one of the
     * [available Media
     * Regions](https://www.twilio.com/docs/video/ip-address-whitelisting#group-rooms-media-servers). ***This feature is not available in `peer-to-peer` rooms.***.
     *
     * @param mediaRegion The region for the media server in Group Rooms
     * @return this
     */
    public RoomCreator setMediaRegion(final String mediaRegion) {
        this.mediaRegion = mediaRegion;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created Room
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Room create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.VIDEO.toString(),
            "/v1/Rooms",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Room creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Room.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (enableTurn != null) {
            request.addPostParam("EnableTurn", enableTurn.toString());
        }

        if (type != null) {
            request.addPostParam("Type", type.toString());
        }

        if (uniqueName != null) {
            request.addPostParam("UniqueName", uniqueName);
        }

        if (statusCallback != null) {
            request.addPostParam("StatusCallback", statusCallback.toString());
        }

        if (statusCallbackMethod != null) {
            request.addPostParam("StatusCallbackMethod", statusCallbackMethod.toString());
        }

        if (maxParticipants != null) {
            request.addPostParam("MaxParticipants", maxParticipants.toString());
        }

        if (recordParticipantsOnConnect != null) {
            request.addPostParam("RecordParticipantsOnConnect", recordParticipantsOnConnect.toString());
        }

        if (videoCodecs != null) {
            for (Room.VideoCodec prop : videoCodecs) {
                request.addPostParam("VideoCodecs", prop.toString());
            }
        }

        if (mediaRegion != null) {
            request.addPostParam("MediaRegion", mediaRegion);
        }
    }
}