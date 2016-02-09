/**
 * Copyright (C) HM Revenue & Customs 2014. All rights reserved.
 */
package uk.co.harperdudding.parkrun.summary;

import java.text.DecimalFormat;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Summary.
 *
 * @author Lyndon 'Big Hitter' Dudding <lyndon.dudding@hmrcaspire.com>
 */
public abstract class Summary implements Comparable<Summary> {

    private static final DateTimeFormatter formatter = DateTimeFormat.forPattern("mm:ss");
    private static final DecimalFormat decimalformatter = new DecimalFormat("#.00");

    protected final String firstName;
    protected final String lastName;
    protected final LocalDate eventDate;
    protected final LocalTime runTime;
    protected final String eventName;
    protected final Double ageGrading;
    protected final String ageCategory;

    public Summary(String pFirstName, String pLastName, LocalDate pEventDate, LocalTime pRunTime, String pEventName, double pAgeGrading, String pAgeCategory) {
        this.firstName = pFirstName;
        this.lastName = pLastName;
        this.eventDate = pEventDate;
        this.runTime = pRunTime;
        this.eventName = pEventName;
        this.ageGrading = pAgeGrading;
        this.ageCategory = pAgeCategory;
    }
    
    @Override
    public String toString() {
        return String.format("%-11s%-13s%-18s%-12s%-8s%-10s%-15s", firstName, lastName, eventName, eventDate, formatter.print(runTime), ageCategory, decimalformatter.format(100 * ageGrading) + "%");
    }

}
