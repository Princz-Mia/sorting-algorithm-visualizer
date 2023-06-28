package javaswingdev;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class PanelGlowingGradient extends JComponent {
    private static int shadowSize = 20;
    private static int borderSize = 1;


    private static Color gradientColor1 = new Color(220, 180, 240);
    private static Color gradientColor2 = new Color(200, 140, 190);
    private static Color backgroundLight  = new Color(35,35,35);
    private static BufferedImage imageRender;


    public PanelGlowingGradient() {
        setBorder(new EmptyBorder(shadowSize, shadowSize, shadowSize, shadowSize));
        setBackground(new Color(20,20,20));

    }

    @Override
    protected void paintComponent(Graphics graphics) {
        if (imageRender != null) {
            Graphics2D graphics2D = (Graphics2D) graphics.create();
            graphics2D.drawImage(imageRender,0 , 0, null);
            graphics2D.dispose();
        }
        super.paintComponent(graphics);
    }

    private void createImageRender() {
        int width = getWidth();
        int height = getHeight();
        if (width > 0 && height > 0) {
            Insets inset = getInsets();
            imageRender = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics2D = imageRender.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // Draw shadow left
            graphics2D.drawImage(createShadow(width, height, inset, gradientColor1, true), 0, 0, null);
            // Draw shadow right
            graphics2D.drawImage(createShadow(width, height, inset, gradientColor1, false), 0, 0, null);
            Area area = new Area(new Rectangle2D.Double(inset.left, inset.top, width - inset.left - inset.right, height - inset.top - inset.bottom));

            graphics2D.setColor(getBackground());
            graphics2D.fill(area);
            area.subtract(new Area(new Rectangle2D.Double(width / 2, 0, width / 2, height)));
            graphics2D.setColor(backgroundLight);
            graphics2D.fill(area);
            graphics2D.drawImage(createBorder(width, height, inset), 0,0,null);
            graphics2D.dispose();
        }
    }

    private BufferedImage createShadow(int width, int height, Insets inset, Color color, boolean leftShadow) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = img.createGraphics();
        Path2D path2D = new Path2D.Double();

        double w = width - inset.left - inset.right;
        double h = height - inset.top - inset.bottom;

        if (leftShadow) {
            path2D.moveTo(0,0);
            path2D.lineTo(w, h);
            path2D.lineTo(0, h);
        } else {
            path2D.moveTo(0,0);
            path2D.lineTo(w, 0);
            path2D.lineTo(w,h);
        }

        graphics2D.fill(path2D);
        graphics2D.dispose();
        return new ShadowRenderer(shadowSize, 0.6f, color).createShadow(img);
    }

    private BufferedImage createBorder(int width, int height, Insets inset) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = img.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        double w = width - inset.left - inset.right;
        double h = height - inset.top - inset.bottom;
        Color transColor1 = toTranslucentColor(gradientColor1);
        Color transColor2 = toTranslucentColor(gradientColor2);
        // Draw Left
        graphics2D.setPaint(new GradientPaint((float) 0, (float) (h * 0.1f), transColor1, (float) 0, (float) h, gradientColor1));
        graphics2D.fill(new Rectangle2D.Double(inset.left, inset.top, borderSize, h));
        // Draw Bottom
        graphics2D.setPaint(new GradientPaint((float) 0, (float) 0, gradientColor1, (float) (w * 0.9f), (float) 0, transColor1));
        graphics2D.fill(new Rectangle2D.Double(inset.left, height - inset.bottom - borderSize, w, borderSize));
        // Draw Top
        graphics2D.setPaint(new GradientPaint((float) (w * 0.1f), (float) 0, transColor2, (float) w, (float) 0, gradientColor2));
        graphics2D.fill(new Rectangle2D.Double(inset.left, height - inset.top, w, borderSize));
        // Draw Right
        graphics2D.setPaint(new GradientPaint((float) 0, (float) 0, gradientColor2, (float) 0, (float) (h * 2.5f), transColor2));
        graphics2D.fill(new Rectangle2D.Double(width - inset.right - borderSize, inset.top, borderSize, h));

        graphics2D.dispose();
        return img;
    }

    private Color toTranslucentColor(Color color) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), 0);
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        createImageRender();
        // Update or create new image render when component has resize or change size
    }
}
