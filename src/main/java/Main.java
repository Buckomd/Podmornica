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


    public static class MyButton extends JButton {
        private int xPos;
        private int yPos;

        public MyButton(int xPos, int yPos){
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

    public static void createMatrx(JFrame window){

        JButton[][] matricaJbtn = new JButton[6][6];
        int[][] matrica = new int[6][6];
        randomMAtrica(matrica);

        for(int r = 0; r < 6; r++) {
            for(int c = 0; c < 6; c++) {
                MyButton myButton = new MyButton(r,c);
                myButton.setBackground(Color.white);
                myButton.setForeground(Color.black);
                matricaJbtn[r][c] = myButton;
                myButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        myButton.setFont(new Font("Arial", Font.PLAIN, 25));

                        if (matrica[myButton.getXPos()][myButton.getYPos()] != 0) {
                            brojacMinus--;
                            myButton.setText("*");
                        } else {
                            myButton.setText("x");
                            brojacPlus++;
                        }

                        if (brojacMinus == 0 && brojacPlus < 12) {
                            JDialog jDialog = new JDialog();
                            JPanel p = new JPanel();

                            JLabel l = new JLabel("Pobedili ste!");
                            JButton button = new JButton("Kraj");

                            button.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    window.dispose();
                                    jDialog.dispose();
                                }
                            });

                            p.add(l);
                            p.add(button);
                            jDialog.add(p);

                            jDialog.setSize(200, 100);
                            jDialog.setVisible(true);
                            jDialog.setLocation(window.getX() + window.getWidth() / 3, window.getY() + window.getHeight() / 3);
                        } else if (brojacPlus == 12 && brojacMinus > 0) {
                            JDialog jDialog = new JDialog();
                            JPanel p = new JPanel();

                            JLabel l = new JLabel("Izgubili ste!");
                            JButton button = new JButton("Kraj");

                            button.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    window.dispose();
                                    jDialog.dispose();
                                }
                            });

                            p.add(l);
                            p.add(button);
                            jDialog.add(p);

                            jDialog.setSize(200, 100);
                            jDialog.setVisible(true);
                            jDialog.setLocation(window.getX() + window.getWidth() / 3, window.getY() + window.getHeight() / 3);
                        }
                    }
                });

                window.add(matricaJbtn[r][c]);
                window.setVisible(true);
            }
        }
    }

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



    public static void main(String[] args) {

        JFrame prozor = new JFrame("Submarine");
        prozor.setDefaultCloseOperation(prozor.EXIT_ON_CLOSE);
        prozor.setBounds(100,200,width,height);
        prozor.setLayout(new GridLayout(6,6));

        JFrame prozor2 = new JFrame("Submarine");
        prozor2.setDefaultCloseOperation(prozor.EXIT_ON_CLOSE);
        prozor2.setBounds(700,200,width,height);
        prozor2.setLayout(new GridLayout(6,6));



        createMatrx(prozor2);
        createMatrx(prozor);






    }
}
