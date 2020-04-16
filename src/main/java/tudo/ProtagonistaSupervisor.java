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
public class ProtagonistaSupervisor extends AbstractActor implements Serializable {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    Long tipo;
    String type = " ";
    ActorRef ProtagonistaActorTypeB;

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Mensagem.bate.class, this::inicio)
                .match(Mensagem.rebate.class, this::finale)
                .build();
    }

    private void inicio(Mensagem.bate s) {
        log.info("Criando protagonista para type: " + s.getType());

        Long a = s.getType();
        type = a.toString();

        ProtagonistaActorTypeB = getContext().actorOf(Props.create(ProtagonistaActor.class), "ProtagonistaActorType" + type);

        ProtagonistaActorTypeB.tell(s, getSelf());
    }

    private void finale(Mensagem.rebate s) {
        log.info("Mensagem recebida: " + s.getText());

       tipo = s.setType(s.getType()+1);

       if(tipo<6){
           log.info("Criando novo actor para type: " + tipo);
           ActorRef ProtagonistaActorTypeR = getContext().actorOf(Props.create(ProtagonistaActor.class), "ProtagonistaActorType" + tipo);
           ProtagonistaActorTypeR.tell(new Mensagem.bate("Ping", s.getType()), getSelf());

       }else{
           log.info("Cabôôôôôôôôôôôôôôôôôôôô");

       }
    }
}