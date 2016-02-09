/**
 * Copyright (C) HM Revenue & Customs 2014. All rights reserved.
 */

package uk.co.harperdudding.parkrun.result;

import java.util.Map;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 * Result.
 * @author Lyndon 'Big Hitter' Dudding <lyndon@atlassian-server>
 */
public abstract class Result implements Comparable<Result> {

    public int SeriesID;
    public int EventNumber;
    public int RunId;
    public int FinishPosition;
    public int GenderPosition;
    public LocalDate EventDate;
    public int AthleteID;
    public LocalTime RunTime;
    public int PB;
    public int Points;
    public double AgeGrading;
    public String AgeCategory;
    public int FirstTimer;
    public int GenuinePB;
    public String Updated;
    public int HandicapRunTime;
    public int Assisted;
    
    public Result(Map<String,String> pItems) {
        EventNumber = Integer.parseInt(pItems.get("EventNumber"));
        RunId = Integer.parseInt(pItems.get("RunId"));
        SeriesID = Integer.parseInt(pItems.get("SeriesID"));
        EventDate = new LocalDate(pItems.get("EventDate"));
        AthleteID = Integer.parseInt(pItems.get("AthleteID"));
        RunTime = new LocalTime(pItems.get("RunTime"));
        AgeGrading = Double.parseDouble(pItems.get("AgeGrading"));
        AgeCategory =pItems.get("AgeCategory");
    }

    @Override
    public String toString() {
        return AthleteID + " " + EventDate + " " + RunTime;
    }

}
