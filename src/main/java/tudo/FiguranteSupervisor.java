package tudo;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FiguranteSupervisor extends AbstractActor implements Serializable {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public AbstractActor.Receive createReceive() {
        return receiveBuilder()
                .match(Mensagem.bate.class, this::printAndReturn)
                .build(
                );
    }

    private void printAndReturn(Mensagem.bate s) {
        log.info("Mensagem recebida: {}", s.getText());
        log.info("Criando figurante para type: " + s.getType());

        Long a = s.getType();
        ActorRef FiguranteActor = getContext().actorOf(Props.create(FiguranteActor.class), "tudo.FiguranteActor" + a);

        FiguranteActor.tell(s, getSelf());
    }


}