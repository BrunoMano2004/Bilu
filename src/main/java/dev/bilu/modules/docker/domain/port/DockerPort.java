package dev.bilu.modules.docker.domain.port;

import dev.bilu.modules.docker.domain.entities.DockerContainer;

import java.util.List;
import java.util.Optional;

public interface DockerPort {
    List<DockerContainer> listContainers();

    Optional<DockerContainer> getContainerById(String id);
}
