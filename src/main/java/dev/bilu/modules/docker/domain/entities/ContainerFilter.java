package dev.bilu.modules.docker.domain.entities;

import java.util.List;

public record ContainerFilter(
        List<String> names,
        boolean listAll,
        List<String> ids,
        List<String> volumes,
        List<String> networks,
        Integer exitedCode,
        List<ContainerStatus> status,
        List<String> labels
) {
}
