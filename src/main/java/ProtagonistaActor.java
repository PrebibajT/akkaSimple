import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.io.Serializable;

public class ProtagonistaActor extends AbstractActor implements Serializable {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    ActorSelection figurante = getContext().actorSelection("akka.tcp://FiguranteSystem@127.0.0.1:2552/user/FiguranteActor");

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Mensagem.bate.class, this::inicio)
                .match(Mensagem.rebate.class, this::print)
                .build();
    }

    private void print(Mensagem.rebate s) {
        log.info("recebida a mensagem: {}", s.getText());
    }

    private void inicio(Mensagem.bate s) {
        log.info(s.getText() + " enviado");
        figurante.tell(s.getText(), getSelf());
        // do some magic
        // enviar para outro actor system

    }
}