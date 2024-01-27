/*****************************************
 * Test class for Virtual Poker
 ****************************************/ 

public class PokerTest {

    //this class must remain unchanged
    //your code must work with this test class
 
    public static void main(String[] args){
        // If there are no arguments, call the default constructor
        if (args.length<1){
            Game g = new Game();
            g.play();
        }
        // If there are arguments, call the testing constructor
        else{
            Game g = new Game(args);
            g.play();
        }
    }

}


