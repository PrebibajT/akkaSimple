import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class FiguranteActor extends AbstractActor  {

    String msg= "Pong";

    public static Props props() {
        return Props.create(FiguranteActor.class);
    }

  //  private ActorRef protagonistaA = getContext().actorOf(ProtagonistaActor.props(), "protagonistaA");

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, this::printAndReturn)
                .build(
                );
    }

    private void printAndReturn(String s) {
            // croa xml e envia par sefaz e aguarda resposta
            log.info("recebida a mensagem: {}", s);
            log.info( msg +" enviado");
        getSender().tell(new Mensagem.rebate(msg), getSelf());

    }

}
