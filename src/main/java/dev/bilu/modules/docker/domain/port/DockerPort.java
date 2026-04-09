package dev.bilu.modules.docker.domain.port;

import dev.bilu.modules.docker.domain.entities.DockerContainer;

import java.util.List;

public interface DockerPort {
    List<DockerContainer> listContainers();
}
