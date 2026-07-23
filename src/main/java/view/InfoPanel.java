package view;

import forms.Figure;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.util.List;

public class InfoPanel extends JPanel {

    private final JTextArea area;
    private final JScrollPane scroll;

    public InfoPanel() {

        setLayout(new BorderLayout());

        setPreferredSize(new Dimension(
                ViewConfig.INFO_PANEL_WIDTH,
                ViewConfig.GRIDHEIGHT));

        setBackground(new Color(240, 240, 240));

        area = new JTextArea();

        area.setEditable(false);
        area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));

        ((DefaultCaret) area.getCaret())
                .setUpdatePolicy(DefaultCaret.NEVER_UPDATE);

        scroll = new JScrollPane(area);

        add(scroll, BorderLayout.CENTER);

    }

    public void update(List<Figure> figures) {

        Point p = scroll.getViewport().getViewPosition();

        StringBuilder sb = new StringBuilder();

        for (Figure f : figures) {

            sb.append(f).append("\n");

        }

        area.setText(sb.toString());

        SwingUtilities.invokeLater(
                () -> scroll.getViewport().setViewPosition(p));

    }

}