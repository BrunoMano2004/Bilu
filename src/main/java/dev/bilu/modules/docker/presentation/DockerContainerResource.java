package dev.bilu.modules.docker.presentation;

import dev.bilu.modules.docker.application.ListContainersUseCase;
import dev.bilu.modules.docker.presentation.dto.ContainerSummaryResponse;
import dev.bilu.modules.docker.presentation.mappers.ContainerSummaryResponseMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/docker/container")
public class DockerContainerResource {

    @Inject
    ListContainersUseCase listContainersUseCase;

    @GET
    @Path("/list")
    public List<ContainerSummaryResponse> list() {
        return listContainersUseCase.execute().stream()
                .map(ContainerSummaryResponseMapper::from)
                .toList();
    }
}
