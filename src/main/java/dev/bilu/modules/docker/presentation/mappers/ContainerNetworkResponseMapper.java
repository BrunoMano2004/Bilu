package dev.bilu.modules.docker.presentation.mappers;

import dev.bilu.modules.docker.domain.entities.DockerContainerNetwork;
import dev.bilu.modules.docker.presentation.dto.ContainerNetworkResponse;

public class ContainerNetworkResponseMapper {

    public static ContainerNetworkResponse from(DockerContainerNetwork dockerContainerNetwork) {
        return new ContainerNetworkResponse(
                dockerContainerNetwork.getNetworkId(),
                dockerContainerNetwork.getEndpointId(),
                dockerContainerNetwork.getGateway(),
                dockerContainerNetwork.getIpAddress(),
                dockerContainerNetwork.getIpPrefixLen(),
                dockerContainerNetwork.getMacAddress(),
                dockerContainerNetwork.getAliases(),
                dockerContainerNetwork.getLinks()
        );
    }
}
