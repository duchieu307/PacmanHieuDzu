package pacmanhv;

public class PacmanHV {

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true); // main menu 
                
            }
        });
    }

}
