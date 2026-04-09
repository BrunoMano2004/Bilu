package dev.bilu.modules.docker.infrastructure.mappers;

import com.github.dockerjava.api.model.ContainerMount;
import dev.bilu.modules.docker.domain.entities.DockerContainerMount;

public class DockerContainerMountMapper {
    public static DockerContainerMount from(ContainerMount mount) {
        DockerContainerMount dcm = new DockerContainerMount();
        dcm.setName(mount.getName());
        dcm.setSource(mount.getSource());
        dcm.setDestination(mount.getDestination());
        dcm.setDriver(mount.getDriver());
        dcm.setMode(mount.getMode());
        dcm.setRw(mount.getRw());
        dcm.setPropagation(mount.getPropagation());
        return dcm;
    }
}
