package dev.bilu.modules.docker.infrastructure.mappers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DockerContainerMapperTest {

    @InjectMocks
    private DockerContainerMapper mapper;

    @Mock
    private DockerContainerPortsMapper mockedDockerContainerPortsMapper;

    @Mock
    private DockerContainerNetworkMapper mockedDockerContainerNetworkMapper;

    @Mock
    private DockerContainerMountMapper mockedDockerContainerMountMapper;

    @Test
    @DisplayName("Must return a mapped type")
    void mustReturnAMappedType() {

    }
}
