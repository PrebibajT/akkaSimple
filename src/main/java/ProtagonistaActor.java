import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class ProtagonistaActor extends AbstractActor {

    public static Props props() {
        return Props.create(ProtagonistaActor.class);
    }

    private ActorRef figuranteA = getContext().actorOf(FiguranteActor.props(), "figuranteR");

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Mensagem.bate.class, this::inicio)
                .match(Mensagem.rebate.class, this::print)
                .build();
    }

    private void print(Mensagem.rebate s) {
        log.info("recebida a mensagem: {}", s.taker());
        // grava no banco de dados e notifica ERP
    }

    private void inicio(Mensagem.bate s) {
        // cria doc
        log.info(s + "enviado");
        figuranteA.tell(s.taker(), getSelf());
        // do some magic
        // enviar para outro actor system

    }
}