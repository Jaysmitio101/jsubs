package com.jaysmito.jsubs;


import javax.swing.*;
import java.awt.*;

public class SubsFrame extends JFrame {
    public JLabel label;
    private byte mode;

    protected static boolean isIsToastRunning() {
        return isToastRunning;
    }

    protected static void setIsToastRunning(boolean isToastRunning) {
        SubsFrame.isToastRunning = isToastRunning;
    }

    private static boolean isToastRunning;

    private static SubsFrame instance;

    /**
     * <p>
     * This method always returns immediately.
     * Builds a SubFrame in the memory which can be
     * used to show onscreen subtitles.
     *
     * @param mode Sets the mode of the SubFrame.
     * @see         SubsFrame
     */
    public static void build(long mode) throws Exception{
        if(instance == null) {
            instance = new SubsFrame(mode);
        }else{
            throw new Exception("Another instance already exists!");
        }
    }

    /**
     * <p>
     * Shows the Subtitle Frame
     *
     * @see         SubsFrame
     */
    public static void showFrame(){
        instance.setVisible(true);
    }

    /**
     * <p>
     * Hides the frame.
     *
     * @see         SubsFrame
     */
    public static void hideFrame(){
        instance.setVisible(false);
    }

    /**
     * <p>
     * Destroys the SubFrame
     *
     * @see         SubsFrame
     */
    public static void destroy() {
        setIsToastRunning(false);
        instance.dispose();
        instance = null;
    }

    /**
     * <p>
     * Sets the Opacity of the background of the Frame.
     * Also sets the color to dark gray.
     *
     * @see         SubsFrame
     */
    public static void setOpacity(double opacity){
        instance.setBackground(new Color(0.3f,0.3f,0.3f,(float)opacity));
    }

    /**
     * <p>
     * Sets Text Color.
     *
     * @see         SubsFrame
     */
    public static void setTextColor(Color textColor){
        instance.label.setForeground(textColor);
    }

    /**
     * <p>
     * Sets background color.
     *
     * @see         SubsFrame
     */
    public static  void setBackgroundColor(Color backgroundColor){
        instance.setBackground(backgroundColor);
    }

    /**
     * Sets Text of the Label
     *
     * <p>
     * This method always returns immediately.
     * This sets the Text of the label.
     * The text should be smaller than max length
     * supported by the screen.
     *
     * @see         SubsFrame
     */
    public static void setText(String text) throws Exception{
        if(isIsToastRunning()){
            throw new Exception("A Toast is already running.");
        }
        if(text.length() == 0){
            instance.setVisible(false);
        }
        if(text.length() * 15 > (Toolkit.getDefaultToolkit().getScreenSize().width - 200)){
            throw new Exception("Max Safe Width Exceeded!");
        }
        instance.label.setText(text);
        instance.handleFrameSize();
    }

    /**
     * <p>
     * Centers the Frame Horizontally
     *
     * @see         SubsFrame
     */
    public static void centerWindow(){
        instance.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - instance.getWidth())/2, JSubsConstants.DEFAULT_FRAME_Y);
    }

    /**
     * <p>
     * Sets the Y of the Frame.
     *
     * @see         SubsFrame
     */
    public static void setY(int y){
        instance.setLocation(instance.getLocation().x, y);
    }

    private SubsFrame(long mode) throws Exception{
        if(JSubsConstants.isValidMode(mode))
            this.mode = (byte)mode;
        else
            throw new Exception("Invalid Mode Selected!");
        if(!this.isAlwaysOnTopSupported()){
            throw new Exception("Always on top is not supported!");
        }
        label = new JLabel("");
        label.setFont(new Font("Consolas", Font.PLAIN, 25));
        label.setForeground(Color.WHITE);
        this.setLayout(new FlowLayout());
        this.add(label);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setAlwaysOnTop(true);
        this.setUndecorated(true);
        if(this.mode == JSubsConstants.AUTO_SIZED_FRAME_AUTO_POSITIONED){
            handleFrameSize();
            handleFrameLocation();
        }
        else if(this.mode == JSubsConstants.AUTO_SIZED_FRAME_MANUAL_POSITIONED){
            handleFrameSize();
        }

    }

    private void handleFrameLocation() {
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - this.getWidth())/2, this.getLocation().y);
    }

    private void handleFrameSize() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int w = instance.label.getText().length() * 15;
                int h = 40;
                instance.setSize(instance.getWidth(), h);
                if(instance.getWidth() > w){
                    while(instance.getWidth() > w){
                        SubsFrame.centerWindow();
                        instance.setSize(instance.getWidth() - 1, instance.getHeight());
                        try {
                            if(instance.getWidth()%2==0)
                                Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }else if(instance.getWidth() < w ){
                    while(instance.getWidth() < w){
                        SubsFrame.centerWindow();
                        instance.setSize(instance.getWidth() + 1, instance.getHeight());
                        try {
                            if(instance.getWidth()%2==0)
                                Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
}
