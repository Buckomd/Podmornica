import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static int height = 500, width = 500;
    private static JButton[][] matricaJbtn = new JButton[6][6];
    private static int pl1Tacni = 24, pl1Netacni = 0, pl2Tacni = 24, pl2Netacni = 0;
    private static JFrame window;


    /**
     * Class MyButton
     */
    public static class MyButton extends JButton {
        private int xPos;
        private int yPos;

        public MyButton(int xPos, int yPos) {
            this.xPos = xPos;
            this.yPos = yPos;
        }

        public int getXPos() {
            return xPos;
        }

        public int getYPos() {
            return yPos;
        }
    }


    /**
     * Class MyFrame
     */
    private static class MyFrame extends JFrame {
        private int x;
        private int y;
        private int br;
        private String tekst;
        private String names;

        /**
         * Constructor class MyFrame
         *
         * @param tekst
         * @param x
         * @param y
         */
        public MyFrame(String tekst,String names, int x, int y, int br) {
            super(tekst);
            this.tekst = tekst;
            this.names = names;
            this.x = x;
            this.y = y;
            this.br = br;

            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setBounds(x, y, width, height);
            setLayout(new GridLayout(6, 6));
        }

        public String getTekst() {
            return tekst;
        }
        public String getNames() {
            return names;
        }
        public int getBr() {
            return br;
        }
        public void setBr(int br) {
            this.br = br;
        }
    }

    /**
     * Checking win or lose
     */
    public static void checkingWinOrLose(int tacniOdgovori, int netacniOdgovori, String names) {
                if (tacniOdgovori == 0 && netacniOdgovori < 12) {
                    JOptionPane.showMessageDialog(window, "Kraj igre. " + names + " je pobedio!");
                } else if (netacniOdgovori == 12 && tacniOdgovori > 0) {
                    JOptionPane.showMessageDialog(window, "Kraj igre. " + names + " je izgubio!" );
                }
    }

    /**
     * Checking to see if I guessed
     * @param matrica
     * @param myButton
     */
    public static void checkingShot(int[][] matrica, MyButton myButton, JFrame window, JFrame window1, int br, String names) {

        switch (br) {
            case 1:
                if (matrica[myButton.getXPos()][myButton.getYPos()] != 0) {
                    myButton.setText("*");
                    pl1Tacni--;
                    System.out.println("Player1 tacni " + pl1Tacni);
                } else {
                    myButton.setText("x");
                    pl1Netacni++;
                    System.out.println("Player1 promasaj " + pl1Netacni);
                    window.setVisible(false);
                    window1.setVisible(true);
                }
                checkingWinOrLose(pl1Tacni, pl1Netacni, names);
                break;

            case 2:
                if (matrica[myButton.getXPos()][myButton.getYPos()] != 0) {
                    myButton.setText("*");
                    pl2Tacni--;
                    System.out.println("Player2 tacni " + pl2Tacni);
                } else {
                    myButton.setText("x");
                    pl2Netacni++;
                    System.out.println("Player2 promasaj " + pl2Netacni);
                    window.setVisible(false);
                    window1.setVisible(true);
                }
                checkingWinOrLose(pl2Tacni, pl2Netacni,names);
                break;
        }
    }


    /**
     * Generate random matrix
     *
     * @param matrica
     */
    public static void randomMAtrica(int[][] matrica) {

        Random rand = new Random();
        for (int i = 0; i < matrica.length; i++) {
            for (int j = 0; j < 4; j++) {
                int x = rand.nextInt(59) + 1;
                while (matrica[i][x / 10] != 0) {
                    x = rand.nextInt(59) + 1;
                }
                matrica[i][x / 10] = x;
            }
        }
    }

    /**
     * Create matrix of JButtons and add matrix on JFrame
     *
     * @param window
     */
    public static void createMatrx(JFrame window, JFrame window1, int br, String name) {

        int[][] matrica = new int[6][6];
        randomMAtrica(matrica);
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 6; c++) {
                MyButton myButton = new MyButton(r, c);
                matricaJbtn[r][c] = myButton;
                myButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        myButton.setFont(new Font("Arial", Font.PLAIN, 25));
                        checkingShot(matrica, myButton, window, window1,br, name);
                    }
                });
                window.add(matricaJbtn[r][c]);
                window.setVisible(true);
            }
        }
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String player1Name = "Submarine-Player1: " + sc.next();
        String player2Name = "Submarine-Player2" +  sc.next();

        MyFrame myFrame = new MyFrame(player1Name, "Aleksandar",300, 200,1);
        MyFrame myFrame1 = new MyFrame(player2Name,"Kristina", 900, 200,2);
        createMatrx(myFrame, myFrame1, myFrame.getBr(),myFrame.getNames());
        createMatrx(myFrame1, myFrame, myFrame1.getBr(), myFrame1.getNames());
        myFrame1.setVisible(false);
        
        System.out.println("Kraj Igre!");
    }
}
