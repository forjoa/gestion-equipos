package org.example;

import org.example.components.app.App;
import org.example.components.app.SplashScreen;

public class Main {
    public static void main(String[] args) {
        SplashScreen splashScreen = new SplashScreen();
        splashScreen.setVisible(true);

        for (int i = 0; i <= 100; i++) {
            try {
                Thread.sleep(20);
                splashScreen
                        .updateProgress(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        splashScreen.dispose();
        new App();
    }
}