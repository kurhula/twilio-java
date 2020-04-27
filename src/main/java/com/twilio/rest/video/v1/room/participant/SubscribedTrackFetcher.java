/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.video.v1.room.participant;

import com.twilio.base.Fetcher;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class SubscribedTrackFetcher extends Fetcher<SubscribedTrack> {
    private final String pathRoomSid;
    private final String pathParticipantSid;
    private final String pathSid;

    /**
     * Construct a new SubscribedTrackFetcher.
     *
     * @param pathRoomSid The SID of the Room where the Track resource to fetch is
     *                    subscribed
     * @param pathParticipantSid The SID of the participant that subscribes to the
     *                           Track resource to fetch
     * @param pathSid The SID that identifies the resource to fetch
     */
    public SubscribedTrackFetcher(final String pathRoomSid,
                                  final String pathParticipantSid,
                                  final String pathSid) {
        this.pathRoomSid = pathRoomSid;
        this.pathParticipantSid = pathParticipantSid;
        this.pathSid = pathSid;
    }

    /**
     * Make the request to the Twilio API to perform the fetch.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Fetched SubscribedTrack
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public SubscribedTrack fetch(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.VIDEO.toString(),
            "/v1/Rooms/" + this.pathRoomSid + "/Participants/" + this.pathParticipantSid + "/SubscribedTracks/" + this.pathSid + "",
            client.getRegion()
        );

        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("SubscribedTrack fetch failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return SubscribedTrack.fromJson(response.getStream(), client.getObjectMapper());
    }
}