package com.jaysmito.jsubs;

import java.awt.*;

public class JSubsConstants {
    public static final int DEFAULT_FRAME_Y = Toolkit.getDefaultToolkit().getScreenSize().height - 200;

    private JSubsConstants(){}

    public static final byte AUTO_SIZED_FRAME_AUTO_POSITIONED = 0;
    public static final byte AUTO_SIZED_FRAME_MANUAL_POSITIONED = 1;
    public static final byte MANUAL_SIZED_FRAME_AUTO_POSITIONED = 2;
    public static final byte MANUAL_SIZED_FRAME_MANUAL_POSITIONED = 3;

    protected static boolean isValidMode(long mode) {
        return (mode == AUTO_SIZED_FRAME_AUTO_POSITIONED || mode == AUTO_SIZED_FRAME_MANUAL_POSITIONED || mode == MANUAL_SIZED_FRAME_AUTO_POSITIONED || mode == MANUAL_SIZED_FRAME_MANUAL_POSITIONED);
    }
}
