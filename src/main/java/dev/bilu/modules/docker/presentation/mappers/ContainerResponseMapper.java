package dev.bilu.modules.docker.presentation.mappers;

import dev.bilu.modules.docker.domain.entities.DockerContainer;
import dev.bilu.modules.docker.presentation.dto.ContainerResponse;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.stream.Collectors;

public class ContainerResponseMapper {

    public static ContainerResponse from(DockerContainer dockerContainer) {
        return new ContainerResponse(
                dockerContainer.getId(),
                dockerContainer.getNames(),
                dockerContainer.getImage(),
                dockerContainer.getImageId(),
                dockerContainer.getStatus(),
                dockerContainer.getState(),
                dockerContainer.getCommand(),
                LocalDateTime.ofInstant(Instant.ofEpochSecond(dockerContainer.getCreated()), ZoneId.systemDefault()),
                dockerContainer.getPorts().stream()
                        .map(ContainerPortResponseMapper::from)
                        .toList(),
                dockerContainer.getLabels(),
                dockerContainer.getNetworks().entrySet().stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> ContainerNetworkResponseMapper.from(entry.getValue())
                        )),
                dockerContainer.getMounts().stream()
                        .map(ContainerMountResponseMapper::from)
                        .toList()
        );
    }
}
