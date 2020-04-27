/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.supersim.v1;

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
public class UsageRecordReader extends Reader<UsageRecord> {
    private String sim;
    private UsageRecord.Granularity granularity;
    private DateTime startTime;
    private DateTime endTime;

    /**
     * SID of a Sim resource. Only show UsageRecords representing usage incurred by
     * this Super SIM..
     *
     * @param sim SID of a Sim resource. Only show UsageRecords representing usage
     *            incurred by this Super SIM.
     * @return this
     */
    public UsageRecordReader setSim(final String sim) {
        this.sim = sim;
        return this;
    }

    /**
     * Time-based grouping that UsageRecords should be aggregated by. Can be:
     * `hour`, `day`, or `all`. Default is `all`. `all` returns one UsageRecord that
     * describes the usage for the entire period..
     *
     * @param granularity Time-based grouping that UsageRecords should be
     *                    aggregated by. Can be: `hour`, `day`, or `all`. Default is
     *                    `all`.
     * @return this
     */
    public UsageRecordReader setGranularity(final UsageRecord.Granularity granularity) {
        this.granularity = granularity;
        return this;
    }

    /**
     * Only include usage that occurred at or after this time, specified in [ISO
     * 8601](https://en.wikipedia.org/wiki/ISO_8601) format. Default is one month
     * before the `end_time`..
     *
     * @param startTime Only include usage that occurred at or after this time.
     * @return this
     */
    public UsageRecordReader setStartTime(final DateTime startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * Only include usage that occurred before this time, specified in [ISO
     * 8601](https://en.wikipedia.org/wiki/ISO_8601) format. Default is the current
     * time..
     *
     * @param endTime Only include usage that occurred before this time.
     * @return this
     */
    public UsageRecordReader setEndTime(final DateTime endTime) {
        this.endTime = endTime;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return UsageRecord ResourceSet
     */
    @Override
    public ResourceSet<UsageRecord> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return UsageRecord ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<UsageRecord> firstPage(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.SUPERSIM.toString(),
            "/v1/UsageRecords",
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
     * @return UsageRecord ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<UsageRecord> getPage(final String targetUrl, final TwilioRestClient client) {
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
    public Page<UsageRecord> nextPage(final Page<UsageRecord> page,
                                      final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(
                Domains.SUPERSIM.toString(),
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
    public Page<UsageRecord> previousPage(final Page<UsageRecord> page,
                                          final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(
                Domains.SUPERSIM.toString(),
                client.getRegion()
            )
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of UsageRecord Resources for a given request.
     *
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<UsageRecord> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("UsageRecord read failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
           throw new ApiException(restException);
        }

        return Page.fromJson(
            "usage_records",
            response.getContent(),
            UsageRecord.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     *
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (sim != null) {
            request.addQueryParam("Sim", sim.toString());
        }

        if (granularity != null) {
            request.addQueryParam("Granularity", granularity.toString());
        }

        if (startTime != null) {
            request.addQueryParam("StartTime", startTime.toString());
        }

        if (endTime != null) {
            request.addQueryParam("EndTime", endTime.toString());
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}