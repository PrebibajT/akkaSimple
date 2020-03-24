import akka.actor.*;


public class FiguranteMain {
    public static void main (String[] args){

        ActorSystem sistema = ActorSystem.create("ator");
        ActorRef figurante = sistema.actorOf(FiguranteActor.props(), "figurante");

        figurante.tell(new Mensagem.rebate("pong"),ActorRef.noSender());

    }

}
