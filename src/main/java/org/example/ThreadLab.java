package org.example;

import org.example.controller.Controller;
import org.example.model.MyModel;
import org.example.view.MyView;

import javax.swing.SwingUtilities;

public class ThreadLab {
    public static void main(String[] arcgs) {
        SwingUtilities.invokeLater(() -> {
            MyModel model = new MyModel();
            MyView view = new  MyView();
            Controller controller = new Controller(model, view);
            view.setVisible(true);
        });
    }
}
