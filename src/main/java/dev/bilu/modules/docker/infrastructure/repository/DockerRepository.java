package dev.bilu.modules.docker.infrastructure.repository;

import com.github.dockerjava.api.DockerClient;
import dev.bilu.modules.docker.domain.entities.DockerContainer;
import dev.bilu.modules.docker.domain.port.DockerPort;
import dev.bilu.modules.docker.infrastructure.mappers.DockerContainerMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class DockerRepository implements DockerPort {

    @Inject
    DockerClient docker;

    @Override
    public List<DockerContainer> listContainers() {
        return docker.listContainersCmd().exec().stream()
                .map(DockerContainerMapper::from)
                .toList();
    }
}
