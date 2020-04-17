package tudo;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import proto.Message;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ProtagonistaSupervisor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    Long tipo;
    String type = " ";
    ActorRef ProtagonistaActorTypeB;

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Message.Mensagem.Ping.class, this::inicio)
                .match(Message.Mensagem.Pong.class, this::finale)
                .build();
    }

    private void inicio(Message.Mensagem.Ping s) {
        log.info("Criando protagonista para type: " + s.getType());

        long a = s.getType();
        type = Long.toString(a);

        ProtagonistaActorTypeB = getContext().actorOf(Props.create(ProtagonistaActor.class), "ProtagonistaActorType" + type);

        ProtagonistaActorTypeB.tell(s, getSelf());
    }

    private void finale(Message.Mensagem.Pong s) {
        log.info("Mensagem recebida: " + s.getMessage());

        tipo = (s.getType() + 1);


        if (tipo < 6) {
            log.info("Criando novo actor para type: " + tipo);
            ActorRef ProtagonistaActorTypeR = getContext().actorOf(Props.create(ProtagonistaActor.class), "ProtagonistaActorType" + tipo);
            ProtagonistaActorTypeR.tell(Message.Mensagem.Ping.newBuilder().setMessage("Ping").setType(tipo).build(), getSelf());

        } else {
            log.info("Cabôôôôôôôôôôôôôôôôôôôô");

        }
    }
}