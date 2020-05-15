/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.voice.v1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.base.Resource;
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

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectionPolicy extends Resource {
    private static final long serialVersionUID = 84226976776172L;

    /**
     * Create a ConnectionPolicyCreator to execute create.
     *
     * @return ConnectionPolicyCreator capable of executing the create
     */
    public static ConnectionPolicyCreator creator() {
        return new ConnectionPolicyCreator();
    }

    /**
     * Create a ConnectionPolicyFetcher to execute fetch.
     *
     * @param pathSid The unique string that identifies the resource
     * @return ConnectionPolicyFetcher capable of executing the fetch
     */
    public static ConnectionPolicyFetcher fetcher(final String pathSid) {
        return new ConnectionPolicyFetcher(pathSid);
    }

    /**
     * Create a ConnectionPolicyReader to execute read.
     *
     * @return ConnectionPolicyReader capable of executing the read
     */
    public static ConnectionPolicyReader reader() {
        return new ConnectionPolicyReader();
    }

    /**
     * Create a ConnectionPolicyUpdater to execute update.
     *
     * @param pathSid The unique string that identifies the resource
     * @return ConnectionPolicyUpdater capable of executing the update
     */
    public static ConnectionPolicyUpdater updater(final String pathSid) {
        return new ConnectionPolicyUpdater(pathSid);
    }

    /**
     * Create a ConnectionPolicyDeleter to execute delete.
     *
     * @param pathSid The unique string that identifies the resource
     * @return ConnectionPolicyDeleter capable of executing the delete
     */
    public static ConnectionPolicyDeleter deleter(final String pathSid) {
        return new ConnectionPolicyDeleter(pathSid);
    }

    /**
     * Converts a JSON String into a ConnectionPolicy object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return ConnectionPolicy object represented by the provided JSON
     */
    public static ConnectionPolicy fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, ConnectionPolicy.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a ConnectionPolicy object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return ConnectionPolicy object represented by the provided JSON
     */
    public static ConnectionPolicy fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, ConnectionPolicy.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final String sid;
    private final String friendlyName;
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final URI url;
    private final Map<String, String> links;

    @JsonCreator
    private ConnectionPolicy(@JsonProperty("account_sid")
                             final String accountSid,
                             @JsonProperty("sid")
                             final String sid,
                             @JsonProperty("friendly_name")
                             final String friendlyName,
                             @JsonProperty("date_created")
                             final String dateCreated,
                             @JsonProperty("date_updated")
                             final String dateUpdated,
                             @JsonProperty("url")
                             final URI url,
                             @JsonProperty("links")
                             final Map<String, String> links) {
        this.accountSid = accountSid;
        this.sid = sid;
        this.friendlyName = friendlyName;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.url = url;
        this.links = links;
    }

    /**
     * Returns The SID of the Account that created the resource.
     *
     * @return The SID of the Account that created the resource
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The unique string that identifies the resource.
     *
     * @return The unique string that identifies the resource
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns The string that you assigned to describe the resource.
     *
     * @return The string that you assigned to describe the resource
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * Returns The RFC 2822 date and time in GMT when the resource was created.
     *
     * @return The RFC 2822 date and time in GMT when the resource was created
     */
    public final DateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The RFC 2822 date and time in GMT when the resource was last updated.
     *
     * @return The RFC 2822 date and time in GMT when the resource was last updated
     */
    public final DateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns The absolute URL of the resource.
     *
     * @return The absolute URL of the resource
     */
    public final URI getUrl() {
        return this.url;
    }

    /**
     * Returns The URLs of related resources.
     *
     * @return The URLs of related resources
     */
    public final Map<String, String> getLinks() {
        return this.links;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ConnectionPolicy other = (ConnectionPolicy) o;

        return Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(sid, other.sid) &&
               Objects.equals(friendlyName, other.friendlyName) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(url, other.url) &&
               Objects.equals(links, other.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            sid,
                            friendlyName,
                            dateCreated,
                            dateUpdated,
                            url,
                            links);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("sid", sid)
                          .add("friendlyName", friendlyName)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("url", url)
                          .add("links", links)
                          .toString();
    }
}