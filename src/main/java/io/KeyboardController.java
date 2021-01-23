package io;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.logging.Level;
import java.util.logging.Logger;


public class KeyboardController implements NativeKeyListener {


    public KeyboardController() throws NativeHookException {
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(this);
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        switch (nativeKeyEvent.getKeyCode()) {
            case NativeKeyEvent.VC_F1:
            case NativeKeyEvent.VC_F3:
            case NativeKeyEvent.VC_F2:
            case NativeKeyEvent.VC_F5:
            case NativeKeyEvent.VC_F4:
                break;
            case NativeKeyEvent.VC_ESCAPE: {
                if (nativeKeyEvent.getModifiers() == NativeKeyEvent.CTRL_L_MASK)
                    close();
                break;
            }
            default:
                System.out.println(nativeKeyEvent.getKeyCode());
                break;
        }


    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
    }

    public void close() {
        try {
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException nativeHookException) {
            nativeHookException.printStackTrace();
        }
    }
}
