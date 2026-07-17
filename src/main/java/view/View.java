package view;

import forms.*;
import model.Model;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {

    private static final int INFO_PANEL_WIDTH = 450;
    private static final int CONSOLE_HEIGHT = 180;

    private int gridWidth;
    private int gridHeight;
    private Model model;

    
     private JPanel canvas;
    
    private JPanel infoPanel;
    private JPanel consolePanel;

    private JTextArea figureInfoArea;
    private JScrollPane figureInfoScroll;

    public View(Model model, int gridWidth, int gridHeight) {
        this.model = model;
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        int delay = 16; //60 FPS

        initComponents();
        setupLayout();

        setTitle("Circulo MVC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(gridWidth, gridHeight);
        setLocationRelativeTo(null);

    }

    private void initComponents() {

        canvas = new JPanel();
        canvas.setLayout(null);
        canvas.setBackground(Color.WHITE);
  
        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setPreferredSize(new Dimension(INFO_PANEL_WIDTH, gridHeight));
        infoPanel.setBackground(new Color(240, 240, 240));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        figureInfoArea = new JTextArea(10, 35);
        figureInfoArea.setEditable(false);
        figureInfoArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
        figureInfoArea.setBackground(Color.WHITE);
        figureInfoArea.setForeground(Color.BLACK);
        figureInfoArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        ((javax.swing.text.DefaultCaret) figureInfoArea.getCaret())
                .setUpdatePolicy(javax.swing.text.DefaultCaret.NEVER_UPDATE);
        figureInfoScroll = new JScrollPane(figureInfoArea);
        figureInfoScroll.setAlignmentX(Component.LEFT_ALIGNMENT);
        figureInfoScroll.setPreferredSize(new Dimension(430, 180));

    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        add(canvas, BorderLayout.CENTER);
        infoPanel.add(figureInfoScroll);
        add(infoPanel, BorderLayout.EAST);

    }

    public void mostrar() {
        setVisible(true);
    }

    private void drawAll() {
        for (Figure f : model.getFigures()) {
            canvas.add(f);
        }
    }

    public void repaint(){
        drawAll();
        updateInfo();  
    }
    // -------------------------------------------------------------------------
    // ACTUALIZACIÓN DEL PANEL DE INFO
    // -------------------------------------------------------------------------
    private void updateInfo() {
        Point statsPos = figureInfoScroll.getViewport().getViewPosition();

        StringBuilder FigureInfoText = new StringBuilder();
        for (Figure f : model.getFigures()) {
            FigureInfoText.append(f).append("\n");
        }
        
         figureInfoArea.setText(FigureInfoText.toString());
        SwingUtilities.invokeLater(() -> {
            figureInfoScroll.getViewport().setViewPosition(statsPos);
        });
        
    }


    /*
    
    public void logMessage(String message) {
        SwingUtilities.invokeLater(() -> {
            String timestamp = String.format("[%tT] ", System.currentTimeMillis());
            consoleArea.append(timestamp + message + "\n");
            consoleArea.setCaretPosition(consoleArea.getDocument().getLength());

            // Recortar: obtener líneas, eliminar las más antiguas si pasan de 500
            String text = consoleArea.getText();
            int count = 0;
            int pos = text.length();
            // Contar líneas desde el final
            while (pos > 0 && count < 500) {
                int nl = text.lastIndexOf('\n', pos - 1);
                if (nl < 0) break;
                count++;
                pos = nl;
            }
            // Si hay más de 500 líneas, eliminar el texto anterior a 'pos'
            if (count == 500 && pos > 0) {
                consoleArea.replaceRange("", 0, pos + 1);
            }
        });
    }
     */
}
