package br.edu.ifsp.lp3a5.atores;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import br.edu.ifsp.lp3a5.atores.actor.RaizQuadradaActor;

public class AkkaClassicoApp {
	public static void main(String[] args) {
		System.out.println("app rodando!");
		ActorSystem akkaSystem = ActorSystem.create("lp3a5");
		ActorRef raizActor = akkaSystem.actorOf(RaizQuadradaActor.props(), "raizActor");
		raizActor.tell( 9, ActorRef.noSender());
		raizActor.tell( 64, ActorRef.noSender());
		
	}
}
