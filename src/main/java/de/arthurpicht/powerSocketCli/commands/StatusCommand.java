package de.arthurpicht.powerSocketCli.commands;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import de.arthurpicht.powerSocketApi.PowerSocket;
import de.arthurpicht.powerSocketApi.PowerSocketApiException;
import de.arthurpicht.powerSocketApi.Status;
import de.arthurpicht.powerSocketCli.PowerSocketFactory;
import de.arthurpicht.utils.core.collection.Sets;

public class StatusCommand implements CommandExecutor {

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        System.out.println("status called!");

        PowerSocket powerSocket = PowerSocketFactory.create();
        String deviceId = Sets.getSomeElement(powerSocket.getDeviceIds());
        try {
            Status status = powerSocket.getStatus(deviceId);
            for (Status.OutletStatus outletStatus : status.outletStatusList()) {
                System.out.println("[" + outletStatus.outletId() + "][" + outletStatus.outletName() + "]: " + (outletStatus.isPoweredOn() ? "ON" : "OFF"));
            }
        } catch (PowerSocketApiException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
