package controller;

import java.awt.Color;

public interface ToolPanelListener {

    void onCreateCircle(
            int radius,
            double mass,
            Color color
    );
    
}