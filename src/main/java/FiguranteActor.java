import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class FiguranteActor extends AbstractActor{

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
//    String msg = "Pong";

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, this::printAndReturn)
                .build(
                );
    }

    private void printAndReturn(String s)  {
        log.info("recebida a mensagem: {}", s);
        log.info( "Pong enviado");

        getSender().tell(new Mensagem.rebate("Pong"),getSelf());

    }
}
