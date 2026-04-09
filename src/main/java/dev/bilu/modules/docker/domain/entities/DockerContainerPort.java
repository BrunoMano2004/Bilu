package dev.bilu.modules.docker.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DockerContainerPort {

    private Integer privatePort;
    private Integer publicPort;
    private String type;
    private String ip;
}
