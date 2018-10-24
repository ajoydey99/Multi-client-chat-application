import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class Client extends javax.swing.JFrame 
{
    Socket s;
    private int port;
    private String IP;
    private boolean isLogin;
    private boolean isConnected;
    String username;
    DataInputStream dis;
    DataOutputStream dos;
    
    public Client() {
        super("FlyChat v1.2 - Client Section");
        initComponents();
        isLogin = true;
        isConnected = false;
        this.setLocation(720,50);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        DefaultCaret caret = (DefaultCaret)jTextArea1.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        jTextArea3.setEditable(false);
        ImageIcon icon = new ImageIcon("img/icon.png");
        setIconImage(icon.getImage());
        
        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                int i=JOptionPane.showConfirmDialog(null, "Do you really want to exit?");
                try
                {
                    if(i==0)
                    {
                        if(s!=null && !s.isClosed())
                        {
                            dos = new DataOutputStream(s.getOutputStream());
                            dos.writeUTF(Home.discMessage);
                            s.close();
                        }
                        Home.removeUser(username);
                        dispose();
                    }
                    else
                        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                }
                catch(Exception ex){ ex.printStackTrace(); }
            }
        });
    }

    public void setData(String IP,int port,String username) 
    {
       this.IP = IP;
       this.port = port;
       this.username = username;
    }
    
    public class ClientThread implements Runnable
    {
        DataInputStream dis;
        
        public ClientThread(DataInputStream dis)
        {
            this.dis = dis;
        }        
        
        @Override
        public void run()
        {
            String line="";
            try
            {
                while(true)
                {
                    line = dis.readUTF();
                    
                    if(line.startsWith(Home.addFriend))
                    {
                        line = line.replace(Home.addFriend, "");
                        jTextArea2.append(" "+line+"\n");
                    }
                    else if(line.startsWith(Home.removeFriend))
                    {
                        line = line.replace(Home.removeFriend,"");
                        line = line.replace("[", "");
                        line = line.replace("]", "");
                        StringTokenizer st = new StringTokenizer(line,",");
                        
                        jTextArea2.setText("");
                        jLabel7.setText("");
                        while(st.hasMoreTokens())
                        {
                            String name = st.nextToken().trim();
                            if(!name.equalsIgnoreCase(username))
                            {
                                jTextArea2.append(" "+name+"\n");
                            }
                        }
                    }
                    else if(line.startsWith(Home.serverStatus))
                    {
                        line = line.replace(Home.serverStatus,"");
                        System.out.println(line);
                        if(line.equalsIgnoreCase("true"))
                            JOptionPane.showMessageDialog(null,"Server is running smoothly");
                        else
                            JOptionPane.showMessageDialog(null,"Server is interrupted by external source.");
                    }
                    else if(line.equals(Home.warningMessage))
                    {
                        if(!s.isClosed())
                            s.close();
                        isConnected = false;
                        jLabel5.setText("WARNING:");
                        jLabel6.setForeground(java.awt.Color.RED);
                        jLabel6.setText("Server is currently closed for maintainance.");
                        jTextArea1.setText("");
                        jTextArea2.setText("");
                        jTextArea3.setText("");
                        jTextArea3.setEditable(false);
                        break;
                    }    
                    else if(line.equals(Home.discMessage))
                    {
                        break;
                    }
                    else if(line.startsWith(Home.typeUser))
                    {
                        line = line.replace(Home.typeUser, "");
                        jLabel7.setText(line+" is typing...");
                    }
                    else if(line.startsWith(Home.onlineList))
                    {
                        line = line.replace(Home.onlineList,"");
                        line = line.replace("[", "");
                        line = line.replace("]", "");
                        StringTokenizer st = new StringTokenizer(line,",");
                        
                        jTextArea2.setText("");
                        while(st.hasMoreTokens())
                        {
                            String name = st.nextToken().trim();
                            if(!name.equalsIgnoreCase(username))
                                jTextArea2.append(" "+name+"\n");
                        }
                    }
                    else if(line.startsWith(Home.myMessage))
                    {
                        line = "Me:  "+line.replace(Home.myMessage,"");
                        jTextArea1.append(line+"\n");
                    }
                    else
                    {
                        jLabel7.setText("");
                        jTextArea1.append(line+"\n");
                    }
                }
            }
            catch(Exception e){}
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jPanel3.setLayout(null);

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 0, 26)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 121, 0));
        jLabel8.setText("Group Chat View");
        jPanel3.add(jLabel8);
        jLabel8.setBounds(80, 20, 380, 30);

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 9)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 121, 0));
        jLabel3.setText("FlyChat");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(30, 14, 34, 20);

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Games\\FlyChat\\img\\minichat.png")); // NOI18N
        jPanel3.add(jLabel2);
        jLabel2.setBounds(10, 0, 60, 70);

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Games\\FlyChat\\img\\miniscreen.png")); // NOI18N
        jPanel3.add(jLabel1);
        jLabel1.setBounds(0, 0, 630, 70);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 204), 1, true), "Active Friends", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(51, 153, 0))); // NOI18N

        jTextArea2.setEditable(false);
        jTextArea2.setBackground(new java.awt.Color(225, 228, 64));
        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Comic Sans MS", 1, 13)); // NOI18N
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jTextArea2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 228, 64)));
        jScrollPane2.setViewportView(jTextArea2);

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(55, 71, 79));
        jButton3.setText("View");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 0), 1, true), "Actions", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(51, 153, 0))); // NOI18N

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(55, 71, 79));
        jButton7.setText("Logout");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(55, 71, 79));
        jButton6.setText("Server Status");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(55, 71, 79));
        jButton1.setText("Disconnect");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(55, 71, 79));
        jButton5.setText("Connect");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 255), 1, true), "Chat Area", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(51, 153, 0))); // NOI18N

        jTextArea3.setColumns(20);
        jTextArea3.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jTextArea3.setLineWrap(true);
        jTextArea3.setRows(5);
        jTextArea3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextArea3MouseClicked(evt);
            }
        });
        jTextArea3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextArea3KeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jTextArea3);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 153, 204));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Not Connected to the server");

        jLabel5.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jLabel5.setText("Client Status: ");

        jButton4.setIcon(new javax.swing.ImageIcon("C:\\Games\\FlyChat\\img\\send.png")); // NOI18N
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(0, 102, 102));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try
        {
            dos = new DataOutputStream(this.s.getOutputStream());
            String sendMsg = jTextArea3.getText();
            if(sendMsg.length()>0)
            {
                dos.writeUTF(sendMsg);
                dos.flush();
            }
            jTextArea3.setText("");
            jLabel7.setText("");
        }
        catch(Exception e)
        {
            jTextArea3.setText("");
            JOptionPane.showMessageDialog(null,"Please connect to the server");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(isConnected)
        {
            try
            {
                isConnected = false;
                dos = new DataOutputStream(s.getOutputStream());
                dos.writeUTF(Home.discMessage);
                s.close();
                jTextArea1.setText("");
                jTextArea2.setText("");
                jTextArea3.setEditable(false);
                jLabel6.setForeground(java.awt.Color.BLUE);
                jLabel6.setText("Successfully disconnected from server");
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else
            JOptionPane.showMessageDialog(null,"Please connect to the server first.");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try 
        {
            if(isLogin)
            {
                if(!isConnected)
                {
                    s = new Socket(IP,port);
                    jLabel6.setForeground(java.awt.Color.BLUE);
                    jLabel6.setText("Connected to the server successfully");
                    isConnected = true;
                    jTextArea3.setEditable(true);
                    dos = new DataOutputStream(s.getOutputStream());
                    dos.writeUTF(Home.userName+username);
                    dis = new DataInputStream(s.getInputStream());
            
                    ClientThread ct = new ClientThread(dis);
                    Thread t = new Thread(ct);
                    t.start();
                }
                else
                    JOptionPane.showMessageDialog(null,"You are already connected.");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Your username has been expired.Try login again");
                this.dispose();
            }
        } 
        catch (IOException ex) 
        {
            jLabel6.setForeground(java.awt.Color.red);
            jLabel6.setText("Server connection error");
        }      
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try
        {
            if(isConnected)
            {
                dos = new DataOutputStream(s.getOutputStream());
                dos.writeUTF(Home.onlineList);
                dos.flush();
            }
            else
            {
                jTextArea2.setText("");
                JOptionPane.showMessageDialog(null,"Please Connect to the server first");
            }
        }
        catch(Exception e){ e.printStackTrace(); }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextArea3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea3MouseClicked
        // TODO add your handling code here:
        jTextArea3.setText("");
    }//GEN-LAST:event_jTextArea3MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        try
        {
            if(isConnected)
            {
                dos = new DataOutputStream(this.s.getOutputStream());
                dos.writeUTF(Home.serverStatus);
                dos.flush();
            }
            else
                JOptionPane.showMessageDialog(null,"Please Connect to the server first");
        }
        catch(Exception e){}
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        if(!isConnected)
        {
            isLogin = false;
            boolean con = Home.removeUser(this.username);
            if(con)
            {
                int val = JOptionPane.showConfirmDialog(null,"You are successfully logged out. Do you want to exit?","Exit",JOptionPane.YES_NO_OPTION);
                if(val==0)
                    this.dispose();
            }
            else
                JOptionPane.showMessageDialog(null, "Please Login first.");
        }
        else
            JOptionPane.showMessageDialog(null,"Disconnect to the server first.");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextArea3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea3KeyReleased
        // TODO add your handling code here:
        try
        {
            dos = new DataOutputStream(this.s.getOutputStream());
            jTextArea3 = (JTextArea)evt.getSource();
            if(jTextArea3.getText().length()>0 && isConnected)
                dos.writeUTF(Home.typeUser+this.username);
        }
        catch(Exception e){}
    }//GEN-LAST:event_jTextArea3KeyReleased

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                   new Client().setVisible(true);
             }  
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    // End of variables declaration//GEN-END:variables
}
