package com.account.replenishment.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @version 1.0
 * @autor a2driano
 * @project: AccountReplenishment
 * @since 05.07.2016
 */
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatusDTO {
    @JsonProperty("status")
    private Status status;

    public Status getStatus() {
        return status;
    }

    public Status setStatus(Status status) {
        this.status = status;
        return status;
    }
}
