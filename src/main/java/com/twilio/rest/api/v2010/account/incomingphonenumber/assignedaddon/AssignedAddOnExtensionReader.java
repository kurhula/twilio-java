/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account.incomingphonenumber.assignedaddon;

import com.twilio.base.Page;
import com.twilio.base.Reader;
import com.twilio.base.ResourceSet;
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
public class AssignedAddOnExtensionReader extends Reader<AssignedAddOnExtension> {
    private String pathAccountSid;
    private final String pathResourceSid;
    private final String pathAssignedAddOnSid;

    /**
     * Construct a new AssignedAddOnExtensionReader.
     *
     * @param pathResourceSid The SID of the Phone Number to which the Add-on is
     *                        assigned
     * @param pathAssignedAddOnSid The SID that uniquely identifies the assigned
     *                             Add-on installation
     */
    public AssignedAddOnExtensionReader(final String pathResourceSid,
                                        final String pathAssignedAddOnSid) {
        this.pathResourceSid = pathResourceSid;
        this.pathAssignedAddOnSid = pathAssignedAddOnSid;
    }

    /**
     * Construct a new AssignedAddOnExtensionReader.
     *
     * @param pathAccountSid The SID of the Account that created the resources to
     *                       read
     * @param pathResourceSid The SID of the Phone Number to which the Add-on is
     *                        assigned
     * @param pathAssignedAddOnSid The SID that uniquely identifies the assigned
     *                             Add-on installation
     */
    public AssignedAddOnExtensionReader(final String pathAccountSid,
                                        final String pathResourceSid,
                                        final String pathAssignedAddOnSid) {
        this.pathAccountSid = pathAccountSid;
        this.pathResourceSid = pathResourceSid;
        this.pathAssignedAddOnSid = pathAssignedAddOnSid;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return AssignedAddOnExtension ResourceSet
     */
    @Override
    public ResourceSet<AssignedAddOnExtension> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return AssignedAddOnExtension ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<AssignedAddOnExtension> firstPage(final TwilioRestClient client) {
        this.pathAccountSid = this.pathAccountSid == null ? client.getAccountSid() : this.pathAccountSid;
        Request request = new Request(
            HttpMethod.GET,
            Domains.API.toString(),
            "/2010-04-01/Accounts/" + this.pathAccountSid + "/IncomingPhoneNumbers/" + this.pathResourceSid + "/AssignedAddOns/" + this.pathAssignedAddOnSid + "/Extensions.json",
            client.getRegion()
        );

        addQueryParams(request);
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the target page from the Twilio API.
     *
     * @param targetUrl API-generated URL for the requested results page
     * @param client TwilioRestClient with which to make the request
     * @return AssignedAddOnExtension ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<AssignedAddOnExtension> getPage(final String targetUrl, final TwilioRestClient client) {
        this.pathAccountSid = this.pathAccountSid == null ? client.getAccountSid() : this.pathAccountSid;
        Request request = new Request(
            HttpMethod.GET,
            targetUrl
        );

        return pageForRequest(client, request);
    }

    /**
     * Retrieve the next page from the Twilio API.
     *
     * @param page current page
     * @param client TwilioRestClient with which to make the request
     * @return Next Page
     */
    @Override
    public Page<AssignedAddOnExtension> nextPage(final Page<AssignedAddOnExtension> page,
                                                 final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(
                Domains.API.toString(),
                client.getRegion()
            )
        );
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the previous page from the Twilio API.
     *
     * @param page current page
     * @param client TwilioRestClient with which to make the request
     * @return Previous Page
     */
    @Override
    public Page<AssignedAddOnExtension> previousPage(final Page<AssignedAddOnExtension> page,
                                                     final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(
                Domains.API.toString(),
                client.getRegion()
            )
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of AssignedAddOnExtension Resources for a given request.
     *
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<AssignedAddOnExtension> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("AssignedAddOnExtension read failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
           throw new ApiException(restException);
        }

        return Page.fromJson(
            "extensions",
            response.getContent(),
            AssignedAddOnExtension.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     *
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}