package view;

import forms.*;
import model.FigureListener;
import model.Model;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame implements FigureListener {
 
    private Model model;

    
     private JPanel canvas;
    
    private JPanel infoPanel;
    private JPanel consolePanel;

    private JTextArea figureInfoArea;
    private JScrollPane figureInfoScroll;

    public View(Model model ) {
        this.model = model;
 
        initComponents();
        setupLayout();
        model.addFigureListener(this);

        setTitle("Circulo MVC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(ViewConfig.GRIDWIDTH, ViewConfig.GRIDHEIGHT);
        setLocationRelativeTo(null);

    }

    private void initComponents() {

        canvas = new JPanel();
        canvas.setLayout(null);
        canvas.setBackground(Color.WHITE);
  
        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setPreferredSize(new Dimension(ViewConfig.INFO_PANEL_WIDTH, ViewConfig.GRIDHEIGHT));
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

    @Override
    public void onFigureAdded(Figure figure) {
        System.out.println("onFigureAdded: " + figure); 
        canvas.add(figure);
        canvas.revalidate();
        canvas.repaint(); 
    }
 
    public void render(){ 
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
