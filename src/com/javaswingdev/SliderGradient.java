package com.javaswingdev;

import java.awt.Color;
import javax.swing.JSlider;

public class SliderGradient extends JSlider {

    public Color getTicksColor() {
        return ticksColor;
    }

    public void setTicksColor(Color ticksColor) {
        this.ticksColor = ticksColor;
    }

    public int getTrackSize() {
        return trackSize;
    }

    public void setTrackSize(int trackSize) {
        this.trackSize = trackSize;
    }

    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    private Color color1 = new Color(186, 148, 245);
    private Color color2 = new Color(155, 80, 206);
    private Color ticksColor = new Color(200, 200, 200);
    private int trackSize = 3;

    public SliderGradient() {
        setUI(new SliderGradientUI(this));
    }
}
