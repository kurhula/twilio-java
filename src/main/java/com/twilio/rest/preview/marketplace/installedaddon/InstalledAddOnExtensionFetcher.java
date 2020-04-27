/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.preview.marketplace.installedaddon;

import com.twilio.base.Fetcher;
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
public class InstalledAddOnExtensionFetcher extends Fetcher<InstalledAddOnExtension> {
    private final String pathInstalledAddOnSid;
    private final String pathSid;

    /**
     * Construct a new InstalledAddOnExtensionFetcher.
     *
     * @param pathInstalledAddOnSid The SID of the InstalledAddOn resource with the
     *                              extension to fetch
     * @param pathSid The SID of the InstalledAddOn Extension resource to fetch
     */
    public InstalledAddOnExtensionFetcher(final String pathInstalledAddOnSid,
                                          final String pathSid) {
        this.pathInstalledAddOnSid = pathInstalledAddOnSid;
        this.pathSid = pathSid;
    }

    /**
     * Make the request to the Twilio API to perform the fetch.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Fetched InstalledAddOnExtension
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public InstalledAddOnExtension fetch(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.PREVIEW.toString(),
            "/marketplace/InstalledAddOns/" + this.pathInstalledAddOnSid + "/Extensions/" + this.pathSid + "",
            client.getRegion()
        );

        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("InstalledAddOnExtension fetch failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return InstalledAddOnExtension.fromJson(response.getStream(), client.getObjectMapper());
    }
}