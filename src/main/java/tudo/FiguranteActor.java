package tudo;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FiguranteActor extends AbstractActor implements Serializable {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    ActorSelection protagonistaSupervisor = getContext().actorSelection("akka.tcp://ProtagonistaSystem@127.0.0.1:2551/user/tudo.ProtagonistaSupervisor");

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Mensagem.bate.class, this::printAndReturn)
                .build(
                );
    }

    private void printAndReturn(Mensagem.bate s) {
        log.info("Pong enviado");

        protagonistaSupervisor.tell(new Mensagem.rebate("Pong", s.getType()), getSelf());

    }
}