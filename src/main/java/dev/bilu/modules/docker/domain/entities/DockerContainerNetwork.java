package dev.bilu.modules.docker.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DockerContainerNetwork {

    private String networkId;
    private String endpointId;
    private String gateway;
    private String ipAddress;
    private Integer ipPrefixLen;
    private String macAddress;
    private List<String> aliases;
    private List<String> links;
}
