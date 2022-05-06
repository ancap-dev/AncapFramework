package ru.ancap.framework.plugin.coreplugin;

import ru.ancap.framework.plugin.api.packet.api.packet.Packet;

import java.util.List;

public interface CoreLibraryErrorNotifierSource {


    Packet getInvalidArgsCountPacket(Integer invalidCount, Integer acceptedCount);
    Packet getInvalidArgPacket(String invalid, List<String> accepted);
    Packet getNoPermissionPacket(String perm);

}
