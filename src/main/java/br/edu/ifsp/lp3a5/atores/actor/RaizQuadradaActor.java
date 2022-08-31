package br.edu.ifsp.lp3a5.atores.actor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;


public class RaizQuadradaActor extends AbstractActor {

	@Override
	public Receive createReceive() {
		// TODO Auto-generated method stub
		return ReceiveBuilder
					.create()
					.match(Double.class, msg->System.out.println(Math.sqrt(msg))  )
					.match(Integer.class, msg->System.out.println(Math.sqrt(msg))  )
					.build();
	}
	
	public static Props props() {
		return Props.create(RaizQuadradaActor.class);
	}

}
