import java.awt.Color;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class Home extends javax.swing.JFrame 
{
    static ArrayList<Socket> socList;       //for client socket
    static ArrayList<String> userList;      //for logged client
    static ArrayList<String> onList;        //for connected to the server client
    static boolean status;
    public static final int port = 4669;
    public static final String userName = "@@user@@";
    public static final String discMessage = "@@disc@@";
    public static final String warningMessage = "@@WarNing@@";
    public static final String myMessage = "@@me@@";
    public static final String typeUser = "@@type@@";
    public static final String onlineList = "@@onList@@";
    public static final String addFriend = "@@addthis@@";
    public static final String removeFriend = "@@removethis@@";
    public static final String serverStatus = "@@status@@";
    ServerSocket ss;  
    Socket s;
    
    public Home() {
        super("FlyChat v1.2");
        this.socList = new ArrayList<>();
        this.userList = new ArrayList<>();
        this.onList = new ArrayList<>();
        status = false;
        initComponents();
        setLocation(100,150);
        ImageIcon icon = new ImageIcon("img/icon.png");
        setIconImage(icon.getImage());
    }
    
    public class StartServer implements Runnable 
    {
        public void run()
        {
            try
            {
                while(true)
                {
                    s = ss.accept();
                    jLabel11.setText("Client "+s+" connected");
                    synchronized(this){
                        socList.add(s);
                    }
                    ClientHandler ch = new ClientHandler(s);
                    Thread t = new Thread(ch);
                    t.start();
                }  
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, "All Clients are now offline");
            }
        }
    }
    
    public class ClientHandler implements Runnable
    {
        Socket s;
        String username;
        DataOutputStream dos;
        DataInputStream dis;
        boolean privStatus;
        
        public ClientHandler(Socket s)
        {
            this.s = s;
            privStatus = false;
            try 
            {
                dos = new DataOutputStream(this.s.getOutputStream());
                dis = new DataInputStream(this.s.getInputStream());
                String clientName = dis.readUTF();
                if(clientName.startsWith(Home.userName))
                {
                    this.username = clientName.replace(Home.userName,"");
                    synchronized(this)
                    {
                        Home.addUserOnline(username);
                    }
                }
                dos.writeUTF("You're logged in at "+new Date());
                dos.writeUTF("--------------------------------------------------------");
                dos.flush();
                String msg = Home.addFriend+this.username;
                tellEveryone(msg);
            } 
            catch (IOException ex){}
        }
        
        @Override
        public void run()    
        {
            synchronized(this)
            {
                String line="";
                try
                {
                    dis = new DataInputStream(this.s.getInputStream());
                    while(true)
                    {
                        line = dis.readUTF();
                        if(line.equals(Home.discMessage))
                            break;
                        else if(line.equals(Home.onlineList))
                        {
                            dos = new DataOutputStream(this.s.getOutputStream());
                            dos.writeUTF(line+Home.getOnlineUser());
                            dos.flush();
                        }
                        else if(line.startsWith(Home.serverStatus))
                        {
                            dos = new DataOutputStream(this.s.getOutputStream());
                            dos.writeUTF(Home.serverStatus+Home.getServerStatus());
                            dos.flush();
                        }
                        else
                            tellEveryone(line);
                    }
                    dos = new DataOutputStream(this.s.getOutputStream());
                    dos.writeUTF(Home.discMessage);
                    dos.flush();
                    socList.remove(this.s);
                    onList.remove(username);
                    String msg =Home.removeFriend+getOnlineUser();
                    tellEveryone(msg);
                    s.close();
                    jLabel11.setText(this.s + " is disconnected.");
                }
                catch(Exception e){}
            }
        }
        
        public void tellEveryone(String msg)
        {
            Iterator all = socList.iterator();
            try
            {
                while(true)
                {
                    Socket temp = (Socket)all.next();
                    dos = new DataOutputStream(temp.getOutputStream());
                    if(temp==this.s) {
                        if(!msg.startsWith(Home.typeUser) && !msg.startsWith(Home.addFriend) &&!msg.startsWith(Home.removeFriend))
                        {
                            dos.writeUTF(myMessage+msg);
                            dos.flush();
                        }
                    }
                    else 
                    {
                        if(msg.startsWith(Home.typeUser) || msg.startsWith(Home.addFriend) || msg.startsWith(Home.removeFriend))
                        {
                            dos.writeUTF(msg);
                            dos.flush();
                        }
                        else
                        {
                            dos.writeUTF(this.username+":  "+msg);
                            dos.flush();
                        }
                    }
                }
            }
            catch(Exception e){}
        }
    }
    
    
    public void warnEveryone(String warn)
    {
        Iterator all = socList.iterator();
        try
        {
            while(all.hasNext())
            {
                Socket temp = (Socket)all.next();
                DataOutputStream dos = new DataOutputStream(temp.getOutputStream());
                dos.writeUTF(warningMessage);
                dos.flush();
            }
        }
        catch(Exception e){}
    }
   
    /* userList operation for logged in user */
    public static void addUser(String name)
    {
        userList.add(name);
    }
    
    public static boolean isUserExist(String name)
    {
        if(userList.contains(name))
            return true;
        else
            return false;
    }
    
    public static boolean removeUser(String name)
    {
        if(userList.contains(name))
            return userList.remove(name);
        else
            return false;
    }
    
    /* onList operations for online user */
    public static void addUserOnline(String name)
    {
        onList.add(name);
    }
    
    public static String getOnlineUser()
    {
        return onList.toString();
    }
    
    public static boolean getServerStatus()
    {
        return status;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 121, 0));
        jLabel5.setText("Welcome to FlyChat");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(80, 10, 240, 40);

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 9)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 121, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("FlyChat");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(20, 20, 50, 10);

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Games\\FlyChat\\img\\minichat.png")); // NOI18N
        jPanel1.add(jLabel2);
        jLabel2.setBounds(10, 0, 60, 70);

        jLabel12.setIcon(new javax.swing.ImageIcon("C:\\Games\\FlyChat\\img\\miniscreen.png")); // NOI18N
        jPanel1.add(jLabel12);
        jLabel12.setBounds(0, 0, 620, 80);

        jPanel2.setBackground(new java.awt.Color(34, 39, 43));
        jPanel2.setLayout(null);

        jLabel8.setBackground(new java.awt.Color(255, 255, 221));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 221));
        jLabel8.setText("All rights reserved 2018-19");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(230, 20, 170, 15);

        jLabel9.setIcon(new javax.swing.ImageIcon("C:\\Games\\FlyChat\\img\\copyright.png")); // NOI18N
        jPanel2.add(jLabel9);
        jLabel9.setBounds(210, 20, 20, 16);

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true));

        jButton1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(55, 71, 79));
        jButton1.setText("Start Server");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon("C:\\Games\\FlyChat\\img\\client.png")); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Games\\FlyChat\\img\\server.png")); // NOI18N

        jLabel7.setIcon(new javax.swing.ImageIcon("C:\\Games\\FlyChat\\img\\about.png")); // NOI18N

        jButton2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(55, 71, 79));
        jButton2.setText("Client Login");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(55, 71, 79));
        jButton3.setText("About");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon("C:\\Games\\FlyChat\\img\\shutdown.png")); // NOI18N

        jButton4.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(55, 71, 79));
        jButton4.setText("Stop Server");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Server Status :");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel11.setText("Server not started yet");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(18, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try
        {
            ss = new ServerSocket(port);
            jLabel11.setForeground(Color.BLUE);
            jLabel11.setText("Server Started. Waiting for the client to connect...");
            status = true;
            Thread t = new Thread(new StartServer());
            t.start();  
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Server already started or try again later");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Login log = new Login();
        log.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try
        {
            status = false;
            warnEveryone(warningMessage);
            socList.removeAll(socList);
            onList.removeAll(onList);
            if(s!=null)
                this.s.close();
            
            if(ss!=null)
            {
                ss.close();
                jLabel11.setText("Server stopped successfully");
            }
            else
                JOptionPane.showMessageDialog(null, "Please Start the Server");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Server interrupted");
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        About obj = new About();
        obj.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
