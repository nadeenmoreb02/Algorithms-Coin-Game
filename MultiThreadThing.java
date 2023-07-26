public class MultiThreadThing implements Runnable{
    private int threadNumber;
    private int coinNumber;
    public MultiThreadThing (int threadNumber, int coinNumber){
        this.threadNumber = threadNumber;
        this.coinNumber = coinNumber;
    }
    @override
    public void run(){
        for(int i=1;i<=coinNumber; i++){
            //CREATE A CIRCLE
                    try{
thread.sleep(1000);
                    }catch(InterruptedException e){

                    };
        }
    }
}
//IN MAIN CLASS
/*
MultiThreadThing myThing = new MultiThreadThing
Thred myThread = n
* */
