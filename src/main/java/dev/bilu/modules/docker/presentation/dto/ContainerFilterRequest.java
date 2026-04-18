package dev.bilu.modules.docker.presentation.dto;

import dev.bilu.modules.docker.domain.entities.ContainerStatus;
import jakarta.validation.constraints.Min;
import jakarta.ws.rs.QueryParam;

import java.util.List;

public class ContainerFilterRequest {

    @QueryParam("names")
    public List<String> names;

    @QueryParam("listAll")
    public boolean listAll = true;

    @QueryParam("volumes")
    public List<String> volumes;

    @QueryParam("networks")
    public List<String> networks;

    @QueryParam("ids")
    public List<String> ids;

    @QueryParam("status")
    public List<ContainerStatus> status;

    @QueryParam("exitCode")
    @Min(value = 0, message = "Exit code must be greater than or equal to 0")
    public Integer exitCode;

    @QueryParam("labels")
    public List<String> labels;
}
