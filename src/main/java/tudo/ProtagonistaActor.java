package tudo;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import proto.Message;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ProtagonistaActor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public ProtagonistaActor() {

    }

    ActorSelection FiguranteSupervisor = getContext().actorSelection("akka.tcp://FiguranteSystem@127.0.0.1:2553/user/tudo.FiguranteSupervisor");

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Message.Mensagem.Ping.class, this::inicio)
                .build();
    }

    private void inicio(Message.Mensagem.Ping s) {
        log.info(s.getMessage() + " enviado");
        FiguranteSupervisor.tell(s, getSelf());
    }

}

