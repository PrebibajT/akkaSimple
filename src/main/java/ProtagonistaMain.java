import akka.actor.*;


class ProtagonistaMain {
    public static void main (String[] args){

        ActorSystem system = ActorSystem.create("actorSystem");
        ActorRef protagonista = system.actorOf(Props.create(ProtagonistaActor.class), "protagonista");
        
        protagonista.tell(new Mensagem.bate("Ping"),ActorRef.noSender());



    }
}
