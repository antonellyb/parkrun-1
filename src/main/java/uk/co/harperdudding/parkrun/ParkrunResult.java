/**
 * Copyright (C) HM Revenue & Customs 2014. All rights reserved.
 */

package uk.co.harperdudding.parkrun;

import org.codehaus.jackson.annotate.JsonProperty;


/**
 * ParkrunResult.
 * @author Lyndon 'Big Hitter' Dudding <lyndon@atlassian-server>
 */
public class ParkrunResult {

    public String status;
    @JsonProperty("Content-Range")
    public Object ContentRange;
    public Object data;
    public Object links;
    public Object timestamp;
    public Object originalQryTime;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nstatus: " + status);
        sb.append(", ContentRange: " + ContentRange);
        sb.append(", data: " + data);
        sb.append(", links: " + links);
        sb.append(", timestamp: " + timestamp);
        return sb.toString();
    }
}
