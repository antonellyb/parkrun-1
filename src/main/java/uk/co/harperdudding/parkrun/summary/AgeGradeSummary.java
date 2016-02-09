/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.co.harperdudding.parkrun.summary;

import uk.co.harperdudding.parkrun.summary.Summary;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 * FastestSummary. 
 * @author Lyndon Dudding <lyndon.dudding@capgemini.com>
 */
public class AgeGradeSummary extends Summary {

    public AgeGradeSummary(String pFirstName, String pLastName, LocalDate pEventDate, LocalTime pRunTime, String pEventName, double pAgeGrading, String pAgeCategory) {
        super(pFirstName, pLastName, pEventDate, pRunTime, pEventName, pAgeGrading, pAgeCategory);
    }

    @Override
    public int compareTo(Summary o) {
        return -1 * this.ageGrading.compareTo(o.ageGrading);
    }
}
