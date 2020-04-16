package tudo;

import akka.actor.*;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FiguranteMain {
    public static void main(String[] args) {

        Config akkaConfiguration = ConfigFactory.load("aplicacao").getConfig("FiguranteSystem");

        ActorSystem sistema = ActorSystem.create("FiguranteSystem", akkaConfiguration);

        ActorRef FiguranteSupervisor = sistema.actorOf(Props.create(FiguranteSupervisor.class), "tudo.FiguranteSupervisor");

    }
}