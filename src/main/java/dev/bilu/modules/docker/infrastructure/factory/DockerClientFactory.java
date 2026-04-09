package dev.bilu.modules.docker.infrastructure.factory;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Produces;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.time.Duration;

@ApplicationScoped
public class DockerClientFactory {

    @ConfigProperty(name = "docker.host", defaultValue = "unix:///var/run/docker.sock")
    String dockerHost;

    @ConfigProperty(name = "registry.url", defaultValue = "https://index.docker.io/v1/")
    String registryUrl;

    @ConfigProperty(name = "registry.username")
    String registryUsername;

    @ConfigProperty(name = "registry.password")
    String registryPassword;

    @ConfigProperty(name = "registry.email")
    String registryEmail;

    @Produces
    @Singleton
    public DockerClient dockerClient() {
        DockerClientConfig dockerConfig = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost(dockerHost)
                .withDockerTlsVerify(false)
                .withRegistryEmail(registryEmail)
                .withRegistryUsername(registryUsername)
                .withRegistryPassword(registryPassword)
                .withRegistryUrl(registryUrl)
                .build();

        DockerHttpClient dockerHttp = new ApacheDockerHttpClient.Builder()
                .dockerHost(dockerConfig.getDockerHost())
                .sslConfig(dockerConfig.getSSLConfig())
                .maxConnections(100)
                .connectionTimeout(Duration.ofSeconds(30))
                .responseTimeout(Duration.ofSeconds((45)))
                .build();

        return DockerClientImpl.getInstance(dockerConfig, dockerHttp);
    }
}
