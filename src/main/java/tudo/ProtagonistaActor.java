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
public class ProtagonistaActor extends AbstractActor implements Serializable {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public ProtagonistaActor() {

    }

    ActorSelection FiguranteSupervisor = getContext().actorSelection("akka.tcp://FiguranteSystem@127.0.0.1:2553/user/tudo.FiguranteSupervisor");

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Mensagem.bate.class, this::inicio)
            //  .match(Mensagem.rebate.class, this::reinicio)

                .build();
    }

    private void inicio(Mensagem.bate s) {
        log.info(s.getText() + " enviado");
        FiguranteSupervisor.tell(s, getSelf());
    }

//    private void reinicio(Mensagem.rebate s) {
//        log.info(s.getText() + " enviado");
//        FiguranteSupervisor.tell(s, getSelf());
//    }

}

