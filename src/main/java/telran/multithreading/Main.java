package telran.multithreading;
import telran.view.*;
public class Main { 
    private static final int MAX_THREADS = 10;
    private static final int MIN_THREADS = 2;
    private static final int MIN_DISTANCE = 100;
    private static final int MAX_DISTANCE = 3500;
    private static final int MIN_SLEEP = 2;
    private static final int MAX_SLEEP = 5;
    public static void main(String[] args) {
       
            InputOutput io = new StandardInputOutput();
            Item[] items = getItems();
            Menu menu = new Menu("Race Game", items);
            menu.perform(io);
    
        }
    
        private static Item[] getItems() {
            Item[] res = {
                    Item.of("Start new game", Main::startGame),
                    Item.ofExit()
            };
            return res;
        }
        static void startGame(InputOutput io) {
            int nThreads = io.readNumberRange("Enter number of the racers","Wrong number of the racers",
                    MIN_THREADS, MAX_THREADS).intValue();
            int distance = io.readNumberRange("Enter distance", 
            "Wrong Distance",MIN_DISTANCE, MAX_DISTANCE).intValue();
            Race race = new Race(distance, MIN_SLEEP, MAX_SLEEP);
            Racer[] racers = new Racer[nThreads];
            startRacers(racers, race);
            joinRacers(racers);
            displayWinner(race);
        }
    
        private static void displayWinner(Race race) {
            System.out.println("Congratulations to Racer " + race.getWinner());
            
        }
    
        private static void joinRacers(Racer[] racers)  {
            for(int i = 0; i < racers.length; i++) {
                try {
                    racers[i].join();
                } catch (InterruptedException e) {
                    
                }
            }
            
            
        }
    
        private static void startRacers(Racer[] racers, Race race) {
            for(int i = 0; i < racers.length; i++) {
                racers[i] = new Racer(race, i + 1);
                racers[i].start();
            }
            
            
        }
    
}