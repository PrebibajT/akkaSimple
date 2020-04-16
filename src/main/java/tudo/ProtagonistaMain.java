package tudo;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProtagonistaMain {

  public static void main (String[] args) {
        SpringApplication.run(ProtagonistaMain.class, args);

        Config akkaConfiguration = ConfigFactory.load("application").getConfig("ProtagonistaSystem");

        ActorSystem system = ActorSystem.create("ProtagonistaSystem", akkaConfiguration);
        ActorRef protagonista = system.actorOf(Props.create(ProtagonistaSupervisor.class), "tudo.ProtagonistaSupervisor");

        protagonista.tell(new Mensagem.bate("Ping", (long) 1), ActorRef.noSender());

    }
}