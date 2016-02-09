/**
 * Copyright (C) HM Revenue & Customs 2014. All rights reserved.
 */

package uk.co.harperdudding.parkrun;

/**
 * Authorisation.
 * @author Lyndon 'Big Hitter' Dudding <lyndon@atlassian-server>
 */
public class Authorisation {

    public String access_token;
    public long expires_in;
    public String token_type;
    public String scope;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("access_token: ").append(access_token).append("\nexpires_in: ")
                .append(expires_in).append("\ntoken_type: ").append(token_type)
                .append("\nscope: ").append(scope).append('\n');
        return sb.toString();
    }
}
