package emu.nes;

import emu.nes.cartridge.Cartridge;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private static final int BORDER_INSET = 5;

    public GUI() throws InterruptedException {
        final NES nes = new NES();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final int posx = 100;
        final int posy = 100;
        final int width = 450;
        final int height = 300;
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
        content.add(new JPanel(), BorderLayout.CENTER);
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
                nes.toggleOn();
            }
        });
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nes.reset();
            }
        });
        insert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    nes.insert(
                        new Cartridge(
                            Paths.get(
                                ClassLoader.getSystemResource("testing/classic/nestest.nes").toURI()
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
}
