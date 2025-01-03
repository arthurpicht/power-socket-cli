package de.arthurpicht.powerSocketCli.commands;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import de.arthurpicht.console.Console;
import de.arthurpicht.powerSocketApi.IllegalOperationException;
import de.arthurpicht.powerSocketApi.PowerSocket;
import de.arthurpicht.powerSocketApi.PowerSocketApiException;
import de.arthurpicht.powerSocketApi.common.OutletIds;
import de.arthurpicht.powerSocketCli.PowerSocketFactory;
import de.arthurpicht.utils.core.collection.Sets;

public class OnCommand implements CommandExecutor {

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        PowerSocket powerSocket = PowerSocketFactory.create();
        String deviceId = Sets.getSomeElement(powerSocket.getDeviceIds());
        String outletId = cliCall.getParameterList().getFirst();

        try {
            OutletIds outletIds = powerSocket.getOutletIds(deviceId);
            if (!outletIds.hasId(outletId))
                throw new CommandExecutorException("Unknown outletId: [" + outletId + "].2");
            try {
                powerSocket.switchOn(deviceId, outletId);
                System.out.println("[OK] Outlet [" + outletId + "] is switched to on.");
            } catch (IllegalOperationException e) {
                Console.println("[Skipped] Outlet [" + outletId + "] is already powered on.");
            }

        } catch (PowerSocketApiException e) {
            throw new CommandExecutorException(e.getMessage(), e);
        }
    }

}
