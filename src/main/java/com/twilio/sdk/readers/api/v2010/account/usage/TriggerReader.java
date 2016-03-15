package com.twilio.sdk.readers.api.v2010.account.usage;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.Reader;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.ResourceSet;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.api.v2010.account.usage.Trigger;

public class TriggerReader extends Reader<Trigger> {
    private final String accountSid;
    private Trigger.Recurring recurring;
    private Trigger.TriggerField triggerBy;
    private Trigger.UsageCategory usageCategory;

    /**
     * Construct a new TriggerReader.
     * 
     * @param accountSid The account_sid
     */
    public TriggerReader(final String accountSid) {
        this.accountSid = accountSid;
    }

    /**
     * Only show UsageTriggers that count over this interval. One of daily, monthly,
     * or yearly.
     * 
     * @param recurring Filter by recurring
     * @return this
     */
    public TriggerReader byRecurring(final Trigger.Recurring recurring) {
        this.recurring = recurring;
        return this;
    }

    /**
     * Only show UsageTriggers that trigger by this field in the UsagRecord.
     * 
     * @param triggerBy Filter by trigger by
     * @return this
     */
    public TriggerReader byTriggerBy(final Trigger.TriggerField triggerBy) {
        this.triggerBy = triggerBy;
        return this;
    }

    /**
     * Only show UsageTriggers that watch this usage category.
     * 
     * @param usageCategory Filter by Usage Category
     * @return this
     */
    public TriggerReader byUsageCategory(final Trigger.UsageCategory usageCategory) {
        this.usageCategory = usageCategory;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Trigger ResourceSet
     */
    @Override
    public ResourceSet<Trigger> execute(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage());
    }

    /**
     * Make the request to the Twilio API to perform the read.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Trigger ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Trigger> firstPage(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            TwilioRestClient.Domains.API,
            "/2010-04-01/Accounts/" + this.accountSid + "/Usage/Triggers.json",
            client.getAccountSid()
        );
        
        addQueryParams(request);
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
    public Page<Trigger> nextPage(final Page<Trigger> page, 
                                  final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUri(),
            client.getAccountSid()
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of Trigger Resources for a given request.
     * 
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<Trigger> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Trigger read failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
        
            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }
        
        return Page.fromJson(
            "usage_triggers",
            response.getContent(),
            Trigger.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     * 
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (recurring != null) {
            request.addQueryParam("Recurring", recurring.toString());
        }
        
        if (triggerBy != null) {
            request.addQueryParam("TriggerBy", triggerBy.toString());
        }
        
        if (usageCategory != null) {
            request.addQueryParam("UsageCategory", usageCategory.toString());
        }
        
        request.addQueryParam("PageSize", Integer.toString(getPageSize()));
    }
}