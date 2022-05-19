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
        ports = @Port(name = "http", containerPort = 8081)
)
@DockerBuild(group = "tdeslauriers", name = "gallery")
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
