package dev.bilu.modules.docker.infrastructure.mappers;

import com.github.dockerjava.api.model.Container;
import dev.bilu.modules.docker.domain.entities.DockerContainer;
import dev.bilu.modules.docker.domain.entities.DockerContainerPort;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class DockerContainerMapper {

    public static DockerContainer from(Container container) {
        DockerContainer dc = new DockerContainer();
        dc.setId(container.getId());
        dc.setNames(Arrays.asList(container.getNames()));
        dc.setImage(container.getImage());
        dc.setImageId(container.getImageId());
        dc.setStatus(container.getStatus());
        dc.setState(container.getState());
        dc.setCommand(container.getCommand());
        dc.setCreated(container.getCreated());
        dc.setPorts(Arrays.stream(container.getPorts()).map(DockerContainerPortsMapper::from).toList());
        dc.setLabels(container.getLabels());
        dc.setNetworks(
                container.getNetworkSettings().getNetworks().entrySet().stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> DockerContainerNetworkMapper.from(entry.getValue())
                        ))
        );
        dc.setMounts(container.getMounts().stream().map(DockerContainerMountMapper::from).toList());

        return dc;
    }
}
