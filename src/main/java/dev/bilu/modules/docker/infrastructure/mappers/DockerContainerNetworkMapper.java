package dev.bilu.modules.docker.infrastructure.mappers;

import com.github.dockerjava.api.model.ContainerNetwork;
import com.github.dockerjava.api.model.ContainerNetworkSettings;
import dev.bilu.modules.docker.domain.entities.DockerContainerNetwork;

import java.util.Arrays;
import java.util.Objects;

public class DockerContainerNetworkMapper {

    public static DockerContainerNetwork from(ContainerNetwork containerNetwork) {
        DockerContainerNetwork dcn = new DockerContainerNetwork();

        dcn.setNetworkId(containerNetwork.getNetworkID());
        dcn.setEndpointId(containerNetwork.getEndpointId());
        dcn.setGateway(containerNetwork.getGateway());
        dcn.setIpAddress(containerNetwork.getIpAddress());
        dcn.setIpPrefixLen(containerNetwork.getIpPrefixLen());
        dcn.setMacAddress(containerNetwork.getMacAddress());
        dcn.setAliases(containerNetwork.getAliases());
        dcn.setLinks(Arrays.stream(Objects.requireNonNull(containerNetwork.getLinks())).map(link -> link.getName() + "/" + link.getAlias()).toList());

        return dcn;
    }
}
