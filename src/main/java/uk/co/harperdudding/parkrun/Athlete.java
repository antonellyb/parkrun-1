/**
 * Copyright (C) HM Revenue & Customs 2014. All rights reserved.
 */

package uk.co.harperdudding.parkrun;

import java.util.Map;

/**
 * Athlete.
 * @author Lyndon 'Big Hitter' Dudding <lyndon@atlassian-server>
 */
public class Athlete implements Comparable<Athlete> {
    
    public int AthleteID;
    public String FirstName;
    public String LastName;
    public String HomeRunID;
    public String Sex;
    public String CountryCode;

    public Athlete(Map<String,String> pItems) {
        AthleteID = Integer.parseInt(pItems.get("AthleteID"));
        FirstName = pItems.get("FirstName");
        LastName = pItems.get("LastName");
        HomeRunID = pItems.get("HomeRunID");
        Sex = pItems.get("Sex");
        CountryCode = pItems.get("CountryCode");
    }

    @Override
    public int compareTo(Athlete o) {
        if(this.AthleteID > o.AthleteID) {
            return 1;
        } else if(this.AthleteID < o.AthleteID) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return FirstName + " " + LastName;
    }
    
    

}
