import akka.actor.*;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class FiguranteMain {
    public static void main (String[] args){

        Config akkaConfiguration = ConfigFactory.load("aplicacao").getConfig("FiguranteSystem");

        ActorSystem sistema = ActorSystem.create("FiguranteSystem", akkaConfiguration);
        ActorRef figurante = sistema.actorOf(Props.create(FiguranteActor.class), "FiguranteActor");

      //  figurante.tell(new Mensagem.rebate("pong"),ActorRef.noSender());

    }
}