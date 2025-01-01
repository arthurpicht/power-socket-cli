package de.arthurpicht.powerSocketCli;

import de.arthurpicht.powerSocketApi.PowerSocket;
import de.arthurpicht.powerSocketApi.common.PowerSocketConfig;
import de.arthurpicht.powerSocketApi.infratecPM8.InfratecPM8Config;

import java.util.ArrayList;
import java.util.List;

public class PowerSocketFactory {

    public static PowerSocket create() {
        String deviceId = "infratec-PM8-1";

        PowerSocketConfig powerSocketConfig = new InfratecPM8Config(
                deviceId,
                "192.168.11.5",
                "admin",
                "geheim"
        );

        List<PowerSocketConfig> powerSocketConfigList = new ArrayList<>();
        powerSocketConfigList.add(powerSocketConfig);

        return new PowerSocket(powerSocketConfigList);
    }

}
