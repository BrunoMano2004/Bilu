package dev.bilu.modules.docker.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DockerContainerMount {

    private String name;
    private String source;
    private String destination;
    private String driver;
    private String mode;
    private Boolean rw;
    private String propagation;
}
