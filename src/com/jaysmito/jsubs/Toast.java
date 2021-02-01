package com.jaysmito.jsubs;

public class Toast {
    public static int LENGTH_SHORT = 5000;
    public static int LENGTH_MEDIUM = 10000;
    public static int LENGTH_LONG = 30000;
    public static int LENGTH_VERY_LONG = 60000;

    private String text;
    private int length;

    private Toast(String text, int length){
        this.text = text;
        this.length = length;
    }

    /**
     * Returns a Toast object.
     *
     * <p>
     * This method always returns immediately.
     * This creates a Toasts object which can be used to
     * call the show method.
     *
     * @param toastLength Duration in milliseconds for which the Toast message will be shown.
     * @param toastMessage The message to be shown.
     * @return      The toast object
     * @see         SubsFrame
     */
    public static Toast makeToast(String toastMessage, int toastLength){
        return new Toast(toastMessage, toastLength);
    }

    /**
     * Returns a Toast object.
     *
     * <p>
     * This method always returns immediately.
     * This creates a Toasts object which can be used to
     * call the show method.
     *
     * @param toastMessage The message to be shown.
     * @return      The toast object
     * @see         SubsFrame
     */
    public static Toast makeToast(String toastMessage){
        return new Toast(toastMessage, LENGTH_MEDIUM);
    }


    /**
     * Returns a nothing.
     *
     * <p>
     * This method always completes immediately.
     * This creates a Toasts object which is then used to show
     * the toast message by calling the show method.
     * <br/>
     * The Default Duration is LENGTH_MEDIUM
     *
     * @param text The message to be shown.
     * @see         SubsFrame
     */
    public static void showToast(String text){
        int length = LENGTH_MEDIUM;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SubsFrame.build(JSubsConstants.AUTO_SIZED_FRAME_AUTO_POSITIONED);
                    SubsFrame.setOpacity(0.6);
                    SubsFrame.setText(text);
                    SubsFrame.showFrame();
                    SubsFrame.setIsToastRunning(true);
                    Thread.sleep(length);
                    SubsFrame.destroy();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        }).start();
    }

    /**
     * Shows the Toast message.
     *
     * <p>
     * This method always completes immediately.
     * This creates a Toasts object which is then used to show
     * the toast message by calling the show method.
     *
     * @param length Duration in milliseconds for which the Toast message will be shown.
     * @param text The message to be shown.
     * @see         SubsFrame
     */
    public static void showToast(String text, int length){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SubsFrame.build(JSubsConstants.AUTO_SIZED_FRAME_AUTO_POSITIONED);
                    SubsFrame.setText(text);
                    SubsFrame.setOpacity(0.6);
                    SubsFrame.setIsToastRunning(true);
                    SubsFrame.showFrame();
                    Thread.sleep(length);
                    SubsFrame.destroy();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        }).start();
    }

    /**
     * Shows the toast message.
     *
     * <p>
     * This method always completes immediately.
     * Shows the toast message.
     *
     * @see         SubsFrame
     */
    public void show(){
        String text = this.text;
        int length = this.length;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SubsFrame.build(JSubsConstants.AUTO_SIZED_FRAME_AUTO_POSITIONED);
                    SubsFrame.setText(text);
                    SubsFrame.setOpacity(0.6);
                    SubsFrame.setIsToastRunning(true);
                    SubsFrame.showFrame();
                    Thread.sleep(length);
                    SubsFrame.destroy();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        }).start();
    }

}
