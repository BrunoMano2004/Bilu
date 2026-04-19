package dev.bilu.modules.docker.infrastructure.mappers;

import com.github.dockerjava.api.model.ContainerPort;
import dev.bilu.modules.docker.domain.entities.DockerContainerPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DockerContainerPortsMapperTest {

    @Mock
    private ContainerPort mockedCp;

    @Test
    @DisplayName("Must return the mapped class")
    void mustReturnTheMappedClass() {
        when(mockedCp.getIp()).thenReturn("123.456.789");
        when(mockedCp.getPublicPort()).thenReturn(5000);
        when(mockedCp.getPrivatePort()).thenReturn(4000);
        when(mockedCp.getType()).thenReturn("type");

        DockerContainerPort mockedDcp = DockerContainerPortsMapper.from(mockedCp);

        assertThat(mockedDcp.getIp()).isEqualTo("123.456.789");
        assertThat(mockedDcp.getPublicPort()).isEqualTo(5000);
        assertThat(mockedDcp.getPrivatePort()).isEqualTo(4000);
        assertThat(mockedDcp.getType()).isEqualTo("type");
    }

    @Test
    @DisplayName("Must return the mapped class with null values")
    void mustReturnTheMappedClassWithNullValues() {
        when(mockedCp.getIp()).thenReturn(null);
        when(mockedCp.getPublicPort()).thenReturn(null);
        when(mockedCp.getPrivatePort()).thenReturn(null);
        when(mockedCp.getType()).thenReturn(null);

        DockerContainerPort mockedDcp = DockerContainerPortsMapper.from(mockedCp);

        assertThat(mockedDcp.getIp()).isNull();
        assertThat(mockedDcp.getPublicPort()).isNull();
        assertThat(mockedDcp.getPrivatePort()).isNull();
        assertThat(mockedDcp.getType()).isNull();
    }
}
