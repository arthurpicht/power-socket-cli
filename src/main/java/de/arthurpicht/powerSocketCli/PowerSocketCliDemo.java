package de.arthurpicht.powerSocketCli;

import de.arthurpicht.powerSocketApi.IllegalOperationException;
import de.arthurpicht.powerSocketApi.PowerSocket;
import de.arthurpicht.powerSocketApi.PowerSocketApiException;
import de.arthurpicht.powerSocketApi.Status;
import de.arthurpicht.powerSocketApi.infratecPM8.InfratecPM8OutletIds;
import de.arthurpicht.utils.core.collection.Sets;

public class PowerSocketCliDemo {

    public static void main(String[] args) {
        PowerSocket powerSocket = PowerSocketFactory.create();
        String deviceId = Sets.getSomeElement(powerSocket.getDeviceIds());

        try {
            Status status = powerSocket.getStatus(deviceId);

            for (Status.OutletStatus outletStatus : status.outletStatusList()) {
                System.out.println(outletStatus.outletName() + ": " + (outletStatus.isPoweredOn() ? "ON" : "OFF"));
            }

            String outletId = new InfratecPM8OutletIds().getIds().getFirst();
            Status.OutletStatus outletStatus = status.getOutletStatus(outletId);
            if (outletStatus.isPoweredOn()) {
                System.out.println("Schalte " + outletId + " aus.");
                powerSocket.switchOff(deviceId, outletId);
            } else {
                System.out.println("Schalte " + outletId + " ein.");
                powerSocket.switchOn(deviceId, outletId);
            }

        } catch (PowerSocketApiException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalOperationException e) {
            System.out.println("Illegal operation: " + e.getMessage());
            e.printStackTrace();
        }

    }

}
