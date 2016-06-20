package Common;

import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.WeakHashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class JInputLocaleIndicator extends JLabel {

    private static class MonitoringThread extends Thread {

        private static final long REFRESH_TIMEOUT = 1000L;

        public MonitoringThread() {
            setPriority(NORM_PRIORITY);
            setDaemon(true);
        }

        public void run() {
            while (!interrupted()) {
                synchronized (indicatorLists) {
                    for (Map.Entry<Window, List<JLabel>> entry : indicatorLists.entrySet()) {
                        Locale locale = entry.getKey().getInputContext().getLocale();
                        for (JLabel label : entry.getValue()) {
                            label.setText(locale.getLanguage());
                            label.setToolTipText(locale.getDisplayLanguage());
                        }
                    }
                }

                try {
                    Thread.sleep(REFRESH_TIMEOUT);
                }
                catch (InterruptedException thrown) {
                    break;
                }
            }
        }

    }

    private static Map<Window, List<JLabel>> indicatorLists = new WeakHashMap<Window, List<JLabel>>();

    static {
        MonitoringThread monitoringThread = new MonitoringThread();
        monitoringThread.start();
    }

    public JInputLocaleIndicator() {
        addHierarchyListener(
                new HierarchyListener() {
                    public void hierarchyChanged(HierarchyEvent event) {
                        if ((event.getChangeFlags() & HierarchyEvent.DISPLAYABILITY_CHANGED) != 0) {
                            Window window = SwingUtilities.getWindowAncestor(JInputLocaleIndicator.this);

                            synchronized (indicatorLists) {
                                List<JLabel> indicators = indicatorLists.get(window);

                                if (isDisplayable()) {
                                    if (indicators == null) {
                                        indicatorLists.put(window, indicators = new ArrayList<JLabel>());
                                    }

                                    indicators.add(JInputLocaleIndicator.this);
                                }
                                else {
                                    if (indicators != null) {
                                        indicators.remove(JInputLocaleIndicator.this);
                                    }
                                }
                            }
                        }
                    }
                }
        );
    }

    public static void main(String... args) {
        JFrame frame = new JFrame();
        JInputLocaleIndicator indicator = new JInputLocaleIndicator();

        frame.setLayout(
                new GridBagLayout()
        );

        frame.add(indicator);
        frame.setSize(512, 384);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}