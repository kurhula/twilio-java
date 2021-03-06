/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.serverless.v1.service.environment;

import com.twilio.base.Page;
import com.twilio.base.Reader;
import com.twilio.base.ResourceSet;
import com.twilio.converter.DateConverter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import org.joda.time.DateTime;

/**
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer preview
 * access, please contact help@twilio.com.
 */
public class LogReader extends Reader<Log> {
    private final String pathServiceSid;
    private final String pathEnvironmentSid;
    private String functionSid;
    private DateTime startDate;
    private DateTime endDate;

    /**
     * Construct a new LogReader.
     *
     * @param pathServiceSid The SID of the Service to read the Log resource from
     * @param pathEnvironmentSid The SID of the environment with the Log resources
     *                           to read
     */
    public LogReader(final String pathServiceSid,
                     final String pathEnvironmentSid) {
        this.pathServiceSid = pathServiceSid;
        this.pathEnvironmentSid = pathEnvironmentSid;
    }

    /**
     * The SID of the function whose invocation produced the Log resources to read..
     *
     * @param functionSid The SID of the function whose invocation produced the Log
     *                    resources to read
     * @return this
     */
    public LogReader setFunctionSid(final String functionSid) {
        this.functionSid = functionSid;
        return this;
    }

    /**
     * The date/time (in GMT, ISO 8601) after which the Log resources must have been
     * created. Defaults to 1 day prior to current date/time..
     *
     * @param startDate The date and time after which the Log resources must have
     *                  been created.
     * @return this
     */
    public LogReader setStartDate(final DateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * The date/time (in GMT, ISO 8601) before which the Log resources must have
     * been created. Defaults to current date/time..
     *
     * @param endDate The date and time before which the Log resource must have
     *                been created.
     * @return this
     */
    public LogReader setEndDate(final DateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Log ResourceSet
     */
    @Override
    public ResourceSet<Log> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Log ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Log> firstPage(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.SERVERLESS.toString(),
            "/v1/Services/" + this.pathServiceSid + "/Environments/" + this.pathEnvironmentSid + "/Logs",
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
     * @return Log ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Log> getPage(final String targetUrl, final TwilioRestClient client) {
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
    public Page<Log> nextPage(final Page<Log> page,
                              final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(
                Domains.SERVERLESS.toString(),
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
    public Page<Log> previousPage(final Page<Log> page,
                                  final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(
                Domains.SERVERLESS.toString(),
                client.getRegion()
            )
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of Log Resources for a given request.
     *
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<Log> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Log read failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
           throw new ApiException(restException);
        }

        return Page.fromJson(
            "logs",
            response.getContent(),
            Log.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     *
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (functionSid != null) {
            request.addQueryParam("FunctionSid", functionSid);
        }

        if (startDate != null) {
            request.addQueryParam("StartDate", startDate.toString());
        }

        if (endDate != null) {
            request.addQueryParam("EndDate", endDate.toString());
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}