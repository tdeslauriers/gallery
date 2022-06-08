package world.deslauriers;

import io.dekorate.docker.annotation.DockerBuild;
import io.dekorate.kubernetes.annotation.*;
import io.micronaut.runtime.Micronaut;

@KubernetesApplication(
        name = "gallery",
        serviceType = ServiceType.NodePort,
        expose = true,
        host = "localhost",
        replicas = 3,
        imagePullPolicy = ImagePullPolicy.Always,
        labels = @Label(key = "app", value = "gallery"),
        ports = @Port(name = "http", containerPort = 8080),
        envVars = {
            @Env(name = "GALLERY_JDBC_URL", configmap = "gallery-svc-config", value = "jdbc_url"),
            @Env(name = "GALLERY_JDBC_USER", configmap = "gallery-svc-config", value = "jdbc_username"),
            @Env(name = "GALLERY_JDBC_DRIVER", configmap = "gallery-svc-config", value = "jdbc_driver"),
            @Env(name = "GALLERY_JDBC_DIALECT", configmap = "gallery-svc-config", value = "jdbc_dialect"),
            @Env(name = "CORS_URLS", configmap = "gallery-svc-config", value = "cors_urls"),
            @Env(name = "GALLERY_JDBC_PASSWORD", secret = "gallery-mariadb-galera", value = "mariadb-password"),
            @Env(name = "JWT_GENERATOR_SIGNATURE_SECRET", secret = "jwt", value = "signature-pw"),
        }
)
@DockerBuild(group = "tdeslauriers", name = "gallery")
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
