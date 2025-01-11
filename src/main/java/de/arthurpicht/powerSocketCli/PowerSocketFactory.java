package de.arthurpicht.powerSocketCli;

import de.arthurpicht.powerSocketApi.PowerSocket;
import de.arthurpicht.powerSocketApi.common.PowerSocketConfig;
import de.arthurpicht.powerSocketApi.dummy.DummyConfig;
import de.arthurpicht.powerSocketApi.infratecPM8.InfratecPM8Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PowerSocketFactory {

//    public static PowerSocket create() {
//        String deviceId = "infratec-PM8-1";
//
//        PowerSocketConfig powerSocketConfig = new InfratecPM8Config(
//                deviceId,
//                "192.168.11.5",
//                "admin",
//                "geheim"
//        );
//
//        List<PowerSocketConfig> powerSocketConfigList = new ArrayList<>();
//        powerSocketConfigList.add(powerSocketConfig);
//
//        return new PowerSocket(powerSocketConfigList);
//    }

    public static PowerSocket create() {
        String deviceId = "dummy-power-module";

        PowerSocketConfig powerSocketConfig = new DummyConfig(deviceId, Arrays.asList("out-1", "out-2", "out-3", "out-4"));

        List<PowerSocketConfig> powerSocketConfigList = new ArrayList<>();
        powerSocketConfigList.add(powerSocketConfig);

        return new PowerSocket(powerSocketConfigList);
    }

}
