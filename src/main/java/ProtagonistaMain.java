import akka.actor.*;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ProtagonistaMain {
    public static void main (String[] args){

        Config akkaConfiguration = ConfigFactory.load("application").getConfig("ProtagonistaSystem");

        ActorSystem system = ActorSystem.create("ProtagonistaSystem", akkaConfiguration);
        ActorRef protagonista = system.actorOf(Props.create(ProtagonistaActor.class), "ProtagonistaActor");

        protagonista.tell(new Mensagem.bate("Ping"),ActorRef.noSender());

    }
}