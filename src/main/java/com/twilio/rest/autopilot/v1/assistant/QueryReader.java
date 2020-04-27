/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.autopilot.v1.assistant;

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
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer preview
 * access, please contact help@twilio.com.
 */
public class QueryReader extends Reader<Query> {
    private final String pathAssistantSid;
    private String language;
    private String modelBuild;
    private String status;

    /**
     * Construct a new QueryReader.
     *
     * @param pathAssistantSid The SID of the Assistant that is the parent of the
     *                         resources to read
     */
    public QueryReader(final String pathAssistantSid) {
        this.pathAssistantSid = pathAssistantSid;
    }

    /**
     * The [ISO
     * language-country](https://docs.oracle.com/cd/E13214_01/wli/docs92/xref/xqisocodes.html) string that specifies the language used by the Query resources to read. For example: `en-US`..
     *
     * @param language The ISO language-country string that specifies the language
     *                 used by the Query resources to read
     * @return this
     */
    public QueryReader setLanguage(final String language) {
        this.language = language;
        return this;
    }

    /**
     * The SID or unique name of the [Model
     * Build](https://www.twilio.com/docs/autopilot/api/model-build) to be queried..
     *
     * @param modelBuild The SID or unique name of the Model Build to be queried
     * @return this
     */
    public QueryReader setModelBuild(final String modelBuild) {
        this.modelBuild = modelBuild;
        return this;
    }

    /**
     * The status of the resources to read. Can be: `pending-review`, `reviewed`, or
     * `discarded`.
     *
     * @param status The status of the resources to read
     * @return this
     */
    public QueryReader setStatus(final String status) {
        this.status = status;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Query ResourceSet
     */
    @Override
    public ResourceSet<Query> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Query ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Query> firstPage(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.AUTOPILOT.toString(),
            "/v1/Assistants/" + this.pathAssistantSid + "/Queries",
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
     * @return Query ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Query> getPage(final String targetUrl, final TwilioRestClient client) {
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
    public Page<Query> nextPage(final Page<Query> page,
                                final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(
                Domains.AUTOPILOT.toString(),
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
    public Page<Query> previousPage(final Page<Query> page,
                                    final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(
                Domains.AUTOPILOT.toString(),
                client.getRegion()
            )
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of Query Resources for a given request.
     *
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<Query> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Query read failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
           throw new ApiException(restException);
        }

        return Page.fromJson(
            "queries",
            response.getContent(),
            Query.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     *
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (language != null) {
            request.addQueryParam("Language", language);
        }

        if (modelBuild != null) {
            request.addQueryParam("ModelBuild", modelBuild.toString());
        }

        if (status != null) {
            request.addQueryParam("Status", status);
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}