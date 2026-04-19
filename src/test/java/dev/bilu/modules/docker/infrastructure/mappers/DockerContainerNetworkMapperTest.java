package dev.bilu.modules.docker.infrastructure.mappers;

import com.github.dockerjava.api.model.ContainerNetwork;
import com.github.dockerjava.api.model.Link;
import dev.bilu.modules.docker.domain.entities.DockerContainerNetwork;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DockerContainerNetworkMapperTest {

    @Mock
    private ContainerNetwork mockedCn;

    @Test
    @DisplayName("Must return the mapped class")
    void mustReturnTheMappedClass() {
        Link[] mockedLinkArray = {new Link("name1", "alias1"), new Link("name2", "alias2")};
        when(mockedCn.getNetworkID()).thenReturn("networkId");
        when(mockedCn.getEndpointId()).thenReturn("endpointId");
        when(mockedCn.getGateway()).thenReturn("gateway");
        when(mockedCn.getIpAddress()).thenReturn("123.456.789");
        when(mockedCn.getIpPrefixLen()).thenReturn(9);
        when(mockedCn.getMacAddress()).thenReturn("macAddress");
        when(mockedCn.getAliases()).thenReturn(List.of("alias1", "alias2", "alias3"));
        when(mockedCn.getLinks()).thenReturn(mockedLinkArray);

        DockerContainerNetwork dcn = DockerContainerNetworkMapper.from(mockedCn);

        assertThat(dcn.getNetworkId()).isEqualTo("networkId");
        assertThat(dcn.getEndpointId()).isEqualTo("endpointId");
        assertThat(dcn.getGateway()).isEqualTo("gateway");
        assertThat(dcn.getIpAddress()).isEqualTo("123.456.789");
        assertThat(dcn.getIpPrefixLen()).isEqualTo(9);
        assertThat(dcn.getMacAddress()).isEqualTo("macAddress");
        assertThat(dcn.getAliases()).isEqualTo(List.of("alias1", "alias2", "alias3"));
        assertThat(dcn.getLinks()).isEqualTo(List.of("name1/alias1", "name2/alias2"));
    }

    @Test
    @DisplayName("Must return the mapped class with null values")
    void mustReturnTheMappedClassWithNullValues() {
        when(mockedCn.getNetworkID()).thenReturn(null);
        when(mockedCn.getEndpointId()).thenReturn(null);
        when(mockedCn.getGateway()).thenReturn(null);
        when(mockedCn.getIpAddress()).thenReturn(null);
        when(mockedCn.getIpPrefixLen()).thenReturn(null);
        when(mockedCn.getMacAddress()).thenReturn(null);
        when(mockedCn.getAliases()).thenReturn(null);
        when(mockedCn.getLinks()).thenReturn(null);

        DockerContainerNetwork dcn = DockerContainerNetworkMapper.from(mockedCn);

        assertThat(dcn.getNetworkId()).isNull();
        assertThat(dcn.getEndpointId()).isNull();
        assertThat(dcn.getGateway()).isNull();
        assertThat(dcn.getIpAddress()).isNull();
        assertThat(dcn.getIpPrefixLen()).isNull();
        assertThat(dcn.getMacAddress()).isNull();
        assertThat(dcn.getAliases()).isNull();
        assertThat(dcn.getLinks()).isNull();
    }
}
