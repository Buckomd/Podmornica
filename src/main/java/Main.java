import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.Arrays;
import java.util.Random;

public class Main {

    private static int height = 500, width = 500;
    private static int brojacPlus = 0, brojacMinus = 24;
    private static JButton[][] matricaJbtn = new JButton[6][6];
    private static JFrame window;

    /**
     * Class MyFrame
     */
    private static class MyFrame extends JFrame {
        private int x;
        private int y;
        private int brojacMinus;
        private int brojacPlus;
        private String tekst;

        /**
         * Constructor class MyFrame
         * @param tekst
         * @param x
         * @param y
         * @param brojacMinus
         * @param brojacPlus
         */
        public MyFrame(String tekst, int x, int y, int brojacMinus, int brojacPlus) {
            super(tekst);
            this.tekst = tekst;
            this.x = x;
            this.y = y;
            this.brojacMinus = brojacMinus;
            this.brojacPlus = brojacPlus;

            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setBounds(x, y, width, height);
            setLayout(new GridLayout(6, 6));

        }

    }

    /**
     * Checking win or lose
     */
    public static void checkingWinOrLose() {
        if (brojacMinus == 0 && brojacPlus < 12) {
            JOptionPane.showMessageDialog(window, "Kraj igre. Pobedili ste!");
        } else if (brojacPlus == 12 && brojacMinus > 0) {
            JOptionPane.showMessageDialog(window, "Kraj igre. Izgubili ste!");
        }
    }

    /**
     * Checking to see if I guessed
     *
     * @param matrica
     * @param myButton
     */
    public static void checkingShot(int[][] matrica, MyButton myButton, JFrame window) {
        if (matrica[myButton.getXPos()][myButton.getYPos()] != 0) {
            brojacMinus--;
            myButton.setText("*");
        } else {
            myButton.setText("x");
            window.dispose();
            brojacPlus++;
        }
    }


    /**
     * Generate random matrix
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
    public static void createMatrx(JFrame window) {

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
                        checkingShot(matrica, myButton,window);
                        checkingWinOrLose();

                    }
                });
                window.add(matricaJbtn[r][c]);
                window.setVisible(true);
            }
        }
    }




    public static void main(String[] args) {

        MyFrame myFrame = new MyFrame("Submarine-Player1", 300, 200, brojacMinus, brojacPlus);

        MyFrame myFrame1 = new MyFrame("Submarine-Player2", 900, 200, brojacMinus, brojacPlus);
        createMatrx(myFrame1);
        createMatrx(myFrame);



    }
}
