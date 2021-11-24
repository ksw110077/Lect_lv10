package gui;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class WorkerExample extends JFrame {
    private static final long serialVersionUID = 6230291564719983347L;
    private JLabel label;

    public WorkerExample() {
        super("");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new FlowLayout());

        SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {

            @Override
            protected Void doInBackground() throws Exception {
                publish("Started....");
                Thread.sleep(1500);
                for (int i = 0; i < 5; i++) {
                    publish("Number of iterations: " + i);
                    //Do something
                    Thread.sleep(3500);
                }
                return null;
            }

            @Override
            protected void process(List<String> chunks) {
                String chunk = chunks.get(0);
                label.setText(chunk);
            }

            @Override
            protected void done() {
                label.setText("Done.");
            }
        };

        JButton button = new JButton("Start");
        button.addActionListener(e -> worker.execute());
        add(button);

        label = new JLabel("Nothing yet.");
        add(label);

        setSize(400, 400);
        setLocationByPlatform(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new WorkerExample().setVisible(true);
        });
    }
}