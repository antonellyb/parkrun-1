/**
 * Copyright (C) HM Revenue & Customs 2014. All rights reserved.
 */
package uk.co.harperdudding.parkrun;

import uk.co.harperdudding.parkrun.summary.Summary;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.representation.Form;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * NewParkrun.
 * Shrewsbury: Event 628
 * Telford: Event 460
 *
 * @author Lyndon 'Big Hitter' Dudding <lyndon@atlassian-server>
 */
public class Parkrun {

    private static final String BASE_URL = "https://test-api.parkrun.com/";
    private static Properties runners;

    public static void main(String[] args) throws Exception {
        Parkrun instance = new Parkrun();
        Authorisation auth = instance.authenticate();
        loadRunners();
        String[] ids = runners.stringPropertyNames().toArray(new String[]{});
//        pIds = new String[]{"563235"};
        instance.doStuff(ids, auth.access_token);
    }

    private void doStuff(String[] pIds, String pToken) throws Exception {
        List<Summary> summaries = new ArrayList();
        
        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        List<Future<Summary>> futures = new ArrayList();
        
        System.out.print("Processing ");
        for (String id : pIds) {
            Future<Summary> future = threadPool.submit(new Workable(id, pToken));
            futures.add(future);
        }
        
        for(Future<Summary> f : futures) {
            summaries.add(f.get());
        }
        
        threadPool.shutdown();
        
        System.out.println("");
        Collections.sort(summaries);
        
        summaries.stream().forEach(System.out::println);
    }

    private Authorisation authenticate() throws Exception {
        Authorisation auth;
        long now = System.currentTimeMillis();
        Properties props = new Properties();
        props.load(new FileInputStream("token.properties"));
        Long expires = Long.parseLong((String) props.get("expires"));
        String token = (String) props.get("token");
        if (expires > (now + 10 * 1000)) {
            auth = new Authorisation();
            auth.access_token = token;
        } else {
            auth = postAuthenticate("USERNAME", "SECRET");
            props.put("token", auth.access_token);
            props.put("expires", Long.toString(now + (1000 * auth.expires_in)));
            OutputStream os = new FileOutputStream("token.properties");
            props.store(os, null);
        }

        return auth;
    }

    private Authorisation postAuthenticate(String pUser, String pSecret) {
        Form form = new Form();
        form.add("grant_type", "client_credentials");
        form.add("scope", "core");
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client c = Client.create(clientConfig);
        c.addFilter(new HTTPBasicAuthFilter(pUser, pSecret));
        WebResource webResource = c.resource(BASE_URL + "token.php");
        ClientResponse response = webResource
                .accept("application/json").post(ClientResponse.class, form);
        if (response.getStatus() == 200) {
            return response.getEntity(Authorisation.class);
        }
        return null;
    }

    private static void loadRunners() {
        runners = new Properties();
        try {
            runners.load(new FileInputStream("src/main/resources/runners.properties"));
        } catch (IOException ioe) {
            System.err.println("Could not load runner properties: " + ioe.getMessage());
            System.exit(-1);
        }
    }
}
