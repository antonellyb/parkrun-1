/**
 * Copyright (C) HM Revenue & Customs 2014. All rights reserved.
 */

package uk.co.harperdudding.parkrun;

import uk.co.harperdudding.parkrun.result.Result;
import uk.co.harperdudding.parkrun.result.ResultByDate;
import uk.co.harperdudding.parkrun.summary.Summary;
import uk.co.harperdudding.parkrun.summary.RecentFastestSummary;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Workable.
 * @author Lyndon 'Big Hitter' Dudding <lyndon.dudding@hmrcaspire.com>
 */
public class Workable implements Callable<Summary> {

    private static final String BASE_URL = "https://test-api.parkrun.com/";

    private final String id;
    private final String token;
    
    public Workable(String pId, String pToken) {
        this.id = pId;
        this.token = pToken;
    }
    
    @Override
    public Summary call() {
        System.out.print('.');
        Athlete a = getAthlete(id, token);
        List<Result> results = getAllAthleteResults(id, token);
        Collections.sort(results);
        Event event = getEvent(results.get(0).EventNumber, token);
        return new RecentFastestSummary(a.FirstName, a.LastName, results.get(0).EventDate, results.get(0).RunTime, event.EventShortName, results.get(0).AgeGrading, results.get(0).AgeCategory);
    }

    private Athlete getAthlete(String pId, String pToken) {
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client c = Client.create(clientConfig);
        WebResource webResource = c.resource(BASE_URL + "/v1/athletes/" + pId);
        ClientResponse response = webResource
                .header("Authorization", "Bearer " + pToken)
                .accept("application/json").get(ClientResponse.class);
        if (response.getStatus() == 200) {
            ParkrunResult prr = response.getEntity(ParkrunResult.class);
            Map<String, Map<String, String>> lhm = (Map<String, Map<String, String>>) prr.data;
            List<Map<String, String>> results = (List<Map<String, String>>) lhm.get("Athletes");
            return new Athlete(results.get(0));
        }
        System.out.println(response.getStatus());
        System.out.println(response.getEntity(String.class));
        return null;
    }

    private Event getEvent(int pEventId, String pToken) {
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client c = Client.create(clientConfig);
        WebResource webResource = c.resource(BASE_URL + "/v1/events/" + pEventId);
        ClientResponse response = webResource
                .header("Authorization", "Bearer " + pToken)
                .accept("application/json").get(ClientResponse.class);
        if (response.getStatus() == 200) {
            ParkrunResult prr = response.getEntity(ParkrunResult.class);
            Map<String, Map<String, String>> lhm = (Map<String, Map<String, String>>) prr.data;
            List<Map<String, String>> results = (List<Map<String, String>>) lhm.get("Events");
            return new Event(results.get(0));
        }
        System.out.println(response.getStatus());
        System.out.println(response.getEntity(String.class));
        return null;
    }

    private List<Result> getAllAthleteResults(String pId, String pToken) {
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client c = Client.create(clientConfig);
        WebResource webResource = c.resource(BASE_URL + "v1/athletes/" + pId + "/results");
        ClientResponse response = webResource
                .header("Authorization", "Bearer " + pToken)
                .accept("application/json").get(ClientResponse.class);
        if (response.getStatus() == 200) {
            ParkrunResult prr = response.getEntity(ParkrunResult.class);
            Map<String, Map<String, String>> lhm = (Map<String, Map<String, String>>) prr.data;
            List<Map<String, String>> results = (List<Map<String, String>>) lhm.get("Results");
            List<Result> wibble = new ArrayList<>();
            results.stream().forEach(m -> wibble.add(new ResultByDate(m)));
            return wibble;
        }
        System.out.println(response.getStatus());
        System.out.println(response.getEntity(ParkrunResult.class));
        return null;
    }
}
