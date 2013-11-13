package akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import java.util.Scanner;


public class Akkatutorial {

    
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("Hola");
        ActorRef helloActor = system.actorOf(new Props(HelloActor.class), "hola");
        
        Scanner sc =new Scanner (System.in);
    
    int operacionesA = sc.nextInt();
    int operacionesB = sc.nextInt();
    
    
    int arregloOpA[] = new int[operacionesA];
    int arregloOpB[] = new int[operacionesB];
    
    for (int i=0;i<operacionesA;i++){
        arregloOpA[i] = sc.nextInt();
    }
    
    for (int j=0;j<operacionesB;j++){
        arregloOpB[j] = sc.nextInt();
    }
    
    
        helloActor.tell(arregloOpA, null);
        helloActor.tell(arregloOpB, null);
        system.shutdown();
        system.awaitTermination();//JOIN
    }
}

class HelloActor extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof int[]) {
            int arreglo[] = (int[])message;
            for (int i=0;i<arreglo.length;i++){
                cuenta.movimiento(arreglo[i]);
            }
            System.out.println(cuenta.tot());
        } else {
            unhandled(message);
        }
    }
}

 class cuenta {

   static int total, temp;
   
    public static void movimiento(int cantidad){
        temp = cantidad;
        temp = temp + total;
        total = temp;
    }
    
    public static int tot(){
        return total;
    }
    
}
