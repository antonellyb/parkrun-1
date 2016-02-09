/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.co.harperdudding.parkrun.result;

import java.util.Map;

/**
 * ResultByDate. 
 * @author Lyndon Dudding <lyndon.dudding@capgemini.com>
 */
public class ResultByDate extends Result {

    public ResultByDate(Map<String, String> pItems) {
        super(pItems);
    }

    @Override
    public int compareTo(Result that) {
        if(this.EventDate.isAfter(that.EventDate)) {
            return -1;
        } else if(this.EventDate.isBefore(that.EventDate)) {
            return 1;
        } else {
            return 0;
        }
    }

}
