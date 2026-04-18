package dev.bilu.modules.docker.infrastructure.filters;

import com.github.dockerjava.api.command.ListContainersCmd;
import dev.bilu.modules.docker.domain.entities.ContainerFilter;

public interface ListContainerStrategy {

    void apply(ListContainersCmd cmd, ContainerFilter filters);
}
