/**
 * Copyright 2010-2014 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.amazonaws.cognito.sync.demo;

import android.content.Context;

import com.amazonaws.android.auth.CognitoCredentialsProvider;
import com.amazonaws.android.cognito.CognitoSyncClient;
import com.amazonaws.android.cognito.DefaultCognitoSyncClient;

import java.util.HashMap;
import java.util.Map;

public class CognitoSyncClientManager {
    /**
     * account id and pool id associated with the app
     */
    private static final String AWS_ACCOUNT_ID = "AWS_ACCOUNT_ID";
    private static final String IDENTITY_POOL_ID = "IDENTITY_POOL_ID";
    /**
     * the role arn to be assumed. You can provide a role arn for unauthorized
     * user and one for authorized.
     */
    private static final String UNAUTH_ROLE_ARN = "";
    private static final String AUTH_ROLE_ARN = "";

    private static CognitoSyncClient client;
    private static CognitoCredentialsProvider provider;

    /**
     * Initializes the CognitoClient. This must be called before getInstance().
     * 
     * @param context a context of the app
     */
    public static void init(Context context) {
        // TODO: let customer specify region
        provider = new CognitoCredentialsProvider(context,
                AWS_ACCOUNT_ID, IDENTITY_POOL_ID, UNAUTH_ROLE_ARN, AUTH_ROLE_ARN);

        client = new DefaultCognitoSyncClient(context, IDENTITY_POOL_ID, provider);
    }

    /**
     * Sets the login so that you can use authorized identity. This requires a
     * network request. Please call it in a background thread.
     * 
     * @param providerName the name of 3rd identity provider
     * @param token openId token
     */
    public static void addLogins(String providerName, String token) {
        if (client == null) {
            throw new IllegalStateException("client not initialized yet");
        }

        Map<String, String> logins = provider.getLogins();
        if (logins == null) {
            logins = new HashMap<String, String>();
        }
        logins.put(providerName, token);
        provider.setLogins(logins);
    }

    /**
     * Gets the singleton instance of the CognitoClient. init() must be call
     * prior to this.
     * 
     * @return an instance of CognitoClient
     */
    public static CognitoSyncClient getInstance() {
        if (client == null) {
            throw new IllegalStateException("client not initialized yet");
        }
        return client;
    }
}
