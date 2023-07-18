package emu.nes;

import emu.nes.cartridge.Cartridge;
import emu.nes.graphics.Picture;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Simple GUI for basic testing.
 * @author hdouss
 *
 */
public class GUI extends JFrame {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -4646410431880625186L;

    /**
     * Border Inset.
     */
    private static final int BORDER_INSET = 5;

    /**
     * NES screen output.
     */
    private BufferedImage frame;

    /**
     * Constructor. Builds the general GUI.
     * @throws InterruptedException
     */
    public GUI() throws InterruptedException {
        final NES nes = new NES();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final int posx = 100;
        final int posy = 100;
        final int width = Picture.SCREEN_WIDTH + 250;
        final int height = Picture.SCREEN_HEIGHT + 250;
        this.setBounds(posx, posy, width, height);
        final JPanel content = new JPanel();
        content.setBorder(
            new EmptyBorder(
                GUI.BORDER_INSET, GUI.BORDER_INSET,
                GUI.BORDER_INSET, GUI.BORDER_INSET
            )
        );
        this.setContentPane(content);
        content.setLayout(new BorderLayout(0, 0));
        final JPanel panel = new JPanel() {
            /**
             * Default serial version UID.
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                graphics.drawImage(GUI.this.frame, 2, 2, null);
            }
        };
        content.add(panel, BorderLayout.CENTER);
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        JButton onoff = new JButton("ON/OFF");
        JButton reset = new JButton("Reset");
        JButton insert = new JButton("Insert");
        JButton eject = new JButton("Eject");
        buttons.add(onoff);
        buttons.add(reset);
        buttons.add(insert);
        buttons.add(eject);
        onoff.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nes.toggleOn(GUI.this);
            }
        });
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nes.reset(GUI.this);
            }
        });
        insert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    nes.insert(
                        new Cartridge(
                            Paths.get(
                                //ClassLoader.getSystemResource("testing/classic/nestest.nes").toURI()
                                //ClassLoader.getSystemResource("testing/games/chipndale.nes").toURI()
                                //ClassLoader.getSystemResource("testing/games/contra.nes").toURI()
                                ClassLoader.getSystemResource("testing/games/mario.nes").toURI()
                                //ClassLoader.getSystemResource("testing/games/mario2.nes").toURI()
                                //ClassLoader.getSystemResource("testing/games/mario3.nes").toURI()
                                //ClassLoader.getSystemResource("testing/games/smurfs.nes").toURI()
                                //ClassLoader.getSystemResource("testing/games/zelda.nes").toURI()
                                //ClassLoader.getSystemResource("testing/games/zelda2.nes").toURI()
                                
                            )
                        )
                    );
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (URISyntaxException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        eject.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nes.eject();
            }
        });
        content.add(buttons, BorderLayout.EAST);
    }

    public void setFrame(BufferedImage frame) {
        this.frame = frame;
    }
}
