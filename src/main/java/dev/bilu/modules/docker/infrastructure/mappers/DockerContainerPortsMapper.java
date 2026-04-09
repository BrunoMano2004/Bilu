package dev.bilu.modules.docker.infrastructure.mappers;

import com.github.dockerjava.api.model.ContainerPort;
import dev.bilu.modules.docker.domain.entities.DockerContainerPort;

public class DockerContainerPortsMapper {

    public static DockerContainerPort from(ContainerPort containerPort) {
        DockerContainerPort dcp = new DockerContainerPort();

        dcp.setIp(containerPort.getIp());
        dcp.setPublicPort(containerPort.getPublicPort());
        dcp.setPrivatePort(containerPort.getPrivatePort());
        dcp.setType(containerPort.getType());

        return dcp;
    }
}
