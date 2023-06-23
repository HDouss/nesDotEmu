/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2021-2022, Hamdi Douss
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom
 * the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
 * OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 */
package emu.nes.nestest;

import emu.nes.GUI;
import emu.nes.NES;
import emu.nes.cartridge.Cartridge;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Runs Nestest and compare only CPU results.
 * @since 0.1
 */
public final class NesTestCPU {

    /**
     * Daemon thread.
     */
    private static Thread daemon;

    @Test
    public void testsCPU()
        throws IOException, URISyntaxException, InterruptedException {
        final NES nes = new NES();
        nes.insert(
            new Cartridge(
                Paths.get(ClassLoader.getSystemResource("classic/nestest.nes").toURI())
            )
        );
        List<String> expected = Files.readAllLines(
            Paths.get(ClassLoader.getSystemResource("classic/expected-nesdotemu.log").toURI())
        );
        nes.toggleOn(new GUI());
        boolean valid = true;
        int read = 0;
        while (valid && read < expected.size()) {
            Thread.sleep(500);
            String check = nes.cpu().getOutput().toString();
            String[] lines = check.split(System.getProperty("line.separator"));
            int total = lines.length - 1;
            total = total > expected.size() ? expected.size() : total;
            for (int idx = read; idx < total; ++idx) {
                Assert.assertEquals(expected.get(idx), lines[idx]);
            }
            read = total;
        }
        nes.toggleOn(null);
    }

    @BeforeClass
    public static void setUpDaemon() {
        daemon = new Thread(
            () -> {
                try {
                    Thread.sleep(Long.MAX_VALUE);
                } catch (InterruptedException e) {
                }
            }
        );
        daemon.setDaemon(true);
        daemon.start();
    }

    @SuppressWarnings("deprecation")
    @AfterClass
    public static void killDaemon() {
        daemon.stop();
    }

}
