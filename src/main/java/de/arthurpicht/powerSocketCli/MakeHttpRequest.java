package de.arthurpicht.powerSocketCli;

import de.arthurpicht.powerSocketApi.common.HttpHelper;

public class MakeHttpRequest {

    public static void main(String[] args) throws Exception {

        String response = HttpHelper.sendGetRequest("http://192.168.11.5/sw?s=0");
        System.out.println(response);

    }

}
