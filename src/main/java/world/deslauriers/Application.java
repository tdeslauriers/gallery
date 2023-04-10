package world.deslauriers;

import io.dekorate.docker.annotation.DockerBuild;
import io.dekorate.kubernetes.annotation.*;
import io.micronaut.runtime.Micronaut;

@KubernetesApplication(
        name = "gallery",
        serviceType = ServiceType.ClusterIP,
        replicas = 3,
        imagePullPolicy = ImagePullPolicy.Always,
        labels = @Label(key = "app", value = "gallery"),
        ports = @Port(name = "http", hostPort = 8080, containerPort = 8081),
        envVars = {
            @Env(name = "GALLERY_R2DBC_URL", configmap = "gallery-svc-config", value = "r2dbc_url"),
            @Env(name = "GALLERY_JDBC_URL", configmap = "gallery-svc-config", value = "jdbc_url"),
            @Env(name = "GALLERY_JDBC_USER", configmap = "gallery-svc-config", value = "jdbc_username"),
            @Env(name = "GALLERY_JDBC_PASSWORD", secret = "gallery-mariadb", value = "mariadb-password"),
            @Env(name = "JWT_GENERATOR_SIGNATURE_SECRET", secret = "jwt", value = "signature-pw"),
        }
)
@DockerBuild(group = "tdeslauriers", name = "gallery")
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
