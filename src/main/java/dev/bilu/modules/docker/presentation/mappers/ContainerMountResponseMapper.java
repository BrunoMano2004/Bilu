package dev.bilu.modules.docker.presentation.mappers;

import dev.bilu.modules.docker.domain.entities.DockerContainerMount;
import dev.bilu.modules.docker.presentation.dto.ContainerMountResponse;

public class ContainerMountResponseMapper {

    public static ContainerMountResponse from(DockerContainerMount dockerContainerMount) {
        return new ContainerMountResponse(
                dockerContainerMount.getName(),
                dockerContainerMount.getSource(),
                dockerContainerMount.getDestination(),
                dockerContainerMount.getDriver(),
                dockerContainerMount.getMode(),
                dockerContainerMount.getRw(),
                dockerContainerMount.getPropagation()
        );
    }
}
