package dev.bilu.modules.docker.presentation.dto;

import java.util.List;

public record ContainerNetworkResponse(
        String networkId,
        String endpointId,
        String gateway,
        String ipAddress,
        Integer ipPrefixLen,
        String macAddress,
        List<String> aliases,
        List<String> links
) {
}
