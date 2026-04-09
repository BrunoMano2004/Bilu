package dev.bilu.modules.docker.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DockerContainer {

    private String id;
    private List<String> names;
    private String image;
    private String imageId;
    private String status;
    private String state;
    private String command;
    private Long created;
    private List<DockerContainerPort> ports;
    private Map<String, String> labels;
    private Map<String, DockerContainerNetwork> networks;
    private List<DockerContainerMount> mounts;
}
