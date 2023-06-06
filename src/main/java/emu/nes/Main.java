package emu.nes;

/**
 * Main entry point that fires the GUI.
 * @author hdouss
 *
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        
        Thread t = new Thread(
            () -> {
                try {
                    Thread.sleep(Long.MAX_VALUE);
                } catch (InterruptedException e) {
                }
            }
        );
        t.setDaemon(true);
        t.start();
         
        new GUI().setVisible(true);
    }

}
