
import com.jtattoo.plaf.DecorationHelper;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;


public class Welcome extends javax.swing.JFrame implements Runnable
{

    public Welcome() {
        initComponents();
        ImageIcon icon = new ImageIcon("img/icon.png");
        setIconImage(icon.getImage());
        Thread t = new Thread((Runnable)this);
        t.start();
    }
    
    @Override
    public void run()
    {
        try
        {
            Home obj = new Home();
            for(int i=0;i<=101;i++)
            {
                Thread.sleep(50);
            }
            obj.setVisible(true);
            DecorationHelper.decorateWindows(true);
            this.dispose();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setLayout(null);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/animated-bird.gif"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(90, 160, 300, 160);

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Loading...");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(330, 320, 60, 14);

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 23)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 121, 0));
        jLabel3.setText("FlyChat");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(70, 20, 90, 70);

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 121, 0));
        jLabel6.setText("Version 1.2");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(100, 130, 80, 20);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/chat-bubble.png"))); // NOI18N
        jPanel1.add(jLabel5);
        jLabel5.setBounds(0, 0, 190, 160);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/screen.png"))); // NOI18N
        jPanel1.add(jLabel2);
        jLabel2.setBounds(0, 0, 400, 350);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
                DecorationHelper.decorateWindows(false);
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Welcome().setVisible(true);
                DecorationHelper.decorateWindows(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
