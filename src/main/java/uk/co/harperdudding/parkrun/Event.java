/**
 * Copyright (C) HM Revenue & Customs 2014. All rights reserved.
 */

package uk.co.harperdudding.parkrun;

import java.util.Map;

/**
 * Event.
 * @author Lyndon 'Big Hitter' Dudding <lyndon@atlassian-server>
 */
public class Event {

    public int EventNumber;
    public String EventShortName;
    
    public Event(Map<String,String> pItems) {
        this.EventNumber = Integer.parseInt(pItems.get("EventNumber"));
        this.EventShortName = pItems.get("EventShortName");
    }

    @Override
    public String toString() {
        return EventNumber + " " + EventShortName;
    }
    
    
}
