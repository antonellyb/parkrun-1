/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.co.harperdudding.parkrun.result;

import java.util.Map;

/**
 * ResultByTime. 
 * @author Lyndon Dudding <lyndon.dudding@capgemini.com>
 */
public class ResultByTime extends Result {

    public ResultByTime(Map<String, String> pItems) {
        super(pItems);
    }

    @Override
    public int compareTo(Result that) {
        if(this.RunTime.isAfter(that.RunTime)) {
            return 1;
        } else if(this.RunTime.isBefore(that.RunTime)) {
            return -1;
        } else {
            return 0;
        }
    }
}
