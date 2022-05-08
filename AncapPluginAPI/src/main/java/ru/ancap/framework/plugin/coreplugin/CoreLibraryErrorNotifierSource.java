package ru.ancap.framework.plugin.coreplugin;

import ru.ancap.framework.plugin.api.packet.api.packet.Sendable;

import java.util.List;

public interface CoreLibraryErrorNotifierSource {


    Sendable getInvalidArgsCountPacket(Integer invalidCount, Integer acceptedCount);
    Sendable getInvalidArgPacket(String invalid, List<String> accepted);
    Sendable getNoPermissionPacket(String perm);

}
