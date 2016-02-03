import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by Théo on 03/02/2016.
 */
public class Saviezvous extends JFrame {

    private JPanel panel = new JPanel();
    JTextArea textelem = new JTextArea("    Jeane est cool");

    JTextField he = new JTextField();
    JTextField we = new JTextField();

    JPanel panel2= new JPanel();
    BufferedImage inback = null;
    JPanel panel3 = new JPanel();



    public BufferedImage loadImage(String fileName){

        BufferedImage buff = null;
        try {
            buff = ImageIO.read(getClass().getResourceAsStream(fileName));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return buff;

    }

    public Saviezvous() throws IOException, URISyntaxException {
        this.setTitle("Loto");
        this.setSize(715, 642);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        File fichier;
        BufferedImage in =  loadImage("Katheryn.png");


        inback = in;


        panel2.setLayout(new GridLayout(0,1));

        panel.setLayout(new BorderLayout());

        JButton button1 = new JButton("Generer");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    generer(true);
                } catch (IOException e1) {

                } catch (URISyntaxException e1) {

                }
            }
        });

        textelem.setLineWrap(true);
        textelem.setPreferredSize(new Dimension(20,20));
        textelem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    generer(false);
                } catch (IOException e1) {

                } catch (URISyntaxException e1) {

                }
            }
        });
        panel2.add(textelem );



        JButton chooserbaboung = new JButton("Background");
        chooserbaboung.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File fichier;

                BufferedImage in = loadImage("lesavizevous.png");


                  inback = in;
                JFileChooser dialogue = new JFileChooser(new File("."));
                if (dialogue.showOpenDialog(null)==
                        JFileChooser.APPROVE_OPTION) {
                    fichier = dialogue.getSelectedFile();
                    try {
                        inback = ImageIO.read(fichier);
                    } catch (IOException e1) {

                    }


                }
                try {
                    generer(false);
                } catch (IOException e1) {

                } catch (URISyntaxException e1) {

                }
            }
        });
        panel2.setPreferredSize(new Dimension(170,this.getHeight()));
        panel2.add(chooserbaboung );

        he.setText("0");
        we.setText("0");
        panel2.add(he );
        panel2.add(we );

        panel2.add(button1 );
      /*  panel.add(button2, BorderLayout.SOUTH);
      */

        panel.add(panel2,BorderLayout.EAST);
        panel.add(panel3,BorderLayout.WEST);

       this.setContentPane(panel);
        this.setVisible(true);
        generer(false);
    }

    public void generer(boolean save) throws IOException, URISyntaxException {
        String text =textelem.getText();

        /*
           Because font metrics is based on a graphics context, we need to create
           a small, temporary image so we can ascertain the width and height
           of the final image
         */


      //  File immg = new File(ClassLoader.getSystemResource("lesavizevous.png").toURI());
        BufferedImage in =loadImage("lesavizevous.png");







       // File overlay = new File(ClassLoader.getSystemResource("lesavizevous.png").toURI());
        BufferedImage overlaybfer = loadImage("lesavizevous.png");


        // BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = in.createGraphics();
        Font font = new Font("Arial", Font.PLAIN, 48);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        int width = fm.stringWidth(text);
        int height = fm.getHeight();
        g2d.dispose();

        //img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2d = in.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setFont(font);
        fm = g2d.getFontMetrics();
        g2d.setColor(Color.WHITE);
        g2d.drawImage(inback, Integer.parseInt(he.getText()), Integer.parseInt(we.getText()), null);
        g2d.drawImage(overlaybfer, 0, 0, null);
        drawString(g2d,text,0,300);
        g2d.dispose();

        JFrame parentFrame = new JFrame();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");

        if(save){
            fileChooser.setSelectedFile(new File(System.getProperty("user.home") + "/Desktop"+  "image.png"));
            int userSelection = fileChooser.showSaveDialog(parentFrame);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                ImageIO.write(in, "png", fileToSave);
                JOptionPane.showMessageDialog(parentFrame,"Felicitation");
                System.out.println("Save as file: " + fileToSave.getAbsolutePath());
            }
        }


        JLabel picLabel = new JLabel(new ImageIcon(in));
        panel3.removeAll();
        panel3.add(picLabel,BorderLayout.CENTER);
        panel.repaint();
        this.pack();
    }

    void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }

}
