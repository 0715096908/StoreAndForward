/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package osero;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author BLAIRMAN SUPER
 */
public class server extends javax.swing.JFrame {

        static long start_time;
        static long end_time;
         PrintWriter outPw;
         public boolean stopped = false;
         ServerSocket welcomeSocket;
         
    /**
     * Creates new form server
     */
    public server() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton2stop = new javax.swing.JButton();
        jButton3exit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1state = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(java.awt.Color.pink);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(java.awt.Color.blue);
        jLabel1.setText("SAN FILE SYSTEM SERVER PANEL");

        jButton2stop.setText("STOP SERVER");
        jButton2stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2stopActionPerformed(evt);
            }
        });

        jButton3exit.setText("EXIT");
        jButton3exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3exitActionPerformed(evt);
            }
        });

        jTextArea1state.setEditable(false);
        jTextArea1state.setColumns(20);
        jTextArea1state.setRows(5);
        jTextArea1state.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jTextArea1state);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(jButton2stop))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(jButton3exit)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 159, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(237, 237, 237)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2stop)
                .addGap(170, 170, 170)
                .addComponent(jButton3exit)
                .addGap(86, 86, 86))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3exitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton3exitActionPerformed

    private void jButton2stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2stopActionPerformed
         stopserver();
    }//GEN-LAST:event_jButton2stopActionPerformed

     public void stopserver(){
      JOptionPane.showMessageDialog(null, "Server Stopped.");
       stopped = true;
       welcomeSocket = null;
       System.exit(-1); 
    }
    
     public void osero()
     {
        int id=1;
        
           jTextArea1state.append("----- SERVER---- ----- \n");
 
           jTextArea1state.append("****************************\n");
           jTextArea1state.append("Server Started...\n");
           jTextArea1state.append("Waiting for connections...\n");
           jTextArea1state.append("-\n");
           
          try
          {
 
              welcomeSocket = new ServerSocket(3333);
               for(;;)
               {
                  Socket connectionSocket = welcomeSocket.accept();
                  jTextArea1state.append("New Client Connected with id "+ id +" from "+connectionSocket.getInetAddress()+"... \n" );
                    Thread server = new ThreadedServer(connectionSocket,id);
                    id++;                   
                    server.start();
                  
                  
                    
                     
                     

                     

                  
               }
          }
          catch(Exception e)
          {
               jTextArea1state.append("Error: " + e);
               jPanel1.revalidate();
          }
     
         
         
        
          
     }
     
     
     class ThreadedServer extends Thread {
	int n;
	int m;
	String name, f, ch, fileData;
	String filename;
	Socket connectionSocket;
	int counter;
        int current;
	String dirName="C:/SERVER/";

	public ThreadedServer(Socket s, int c) {
		connectionSocket = s;
		counter = c;

		
	}

	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			InputStream inFromClient = connectionSocket.getInputStream();
                        PrintWriter outPw = new PrintWriter(connectionSocket.getOutputStream());
			OutputStream output = connectionSocket.getOutputStream();

			ObjectOutputStream oout = new ObjectOutputStream(output);
			oout.writeObject("WELCOME DEAR CLIENT \n    AVAILABLE FILES FOR DOWNLOAD ARE: \n");
                        
			File ff = new File(dirName);
			ArrayList<String> names = new ArrayList<String>(Arrays.asList(ff.list()));
			int len = names.size();
			oout.writeObject(String.valueOf(names.size()));

			for(String name: names) {
				oout.writeObject(name);
			}

			name = in.readLine();
			ch = name.substring(0, 1);

			if (ch.equals("*")) {
				n = name.lastIndexOf("*");
				filename = name.substring(1, n);
				FileInputStream file = null;
				BufferedInputStream bis = null;
				boolean fileExists = true;
				jTextArea1state.append("Request to download file " + filename + " recieved from " + connectionSocket.getInetAddress() + "...\n");
				filename = dirName + filename;
				//System.out.println(filename);
				try {
					file = new FileInputStream(filename);
					bis = new BufferedInputStream(file);
				} 
				catch (FileNotFoundException excep) {
					fileExists = false;
					jTextArea1state.append("FileNotFoundException: \n" + excep.getMessage());
                                        jPanel1.revalidate();
				}
				if (fileExists) {
                                        
					oout = new ObjectOutputStream(output);
					oout.writeObject("Success");
                                        start_time = System.currentTimeMillis();
					jTextArea1state.append("Download begins \n");
                                       
                                        
				        sendBytes(bis, output);
                                        end_time=System.currentTimeMillis(); 
					jTextArea1state.append("Completed \n");
                                        long elapsed_time = end_time - start_time;
                                        jTextArea1state.append("Elapsed time is: "+elapsed_time+ " ms \n");
                                        jPanel1.revalidate();
					bis.close();
					file.close();
					oout.close();
					output.close();
				}
				else {
					oout = new ObjectOutputStream(output);
					oout.writeObject("FileNotFound \n");
                                        jPanel1.revalidate();
					bis.close();
					file.close();
					oout.close();
					output.close();
				}
			} 
			
			
		} 
		catch (Exception ex) {
			jTextArea1state.append(ex.getMessage());
		}
        }

  public void sendBytes(BufferedInputStream in , OutputStream out) throws Exception {
		/*int size =6022386;
		byte[] data = new byte[size];
		//int bytes = 0;
		int c = in.read(data, 0, data.length);
		out.write(data, 0, c);
		out.flush();*/
      
                int size =1024*1024;
		byte[] data = new byte[size];
		int bytes = 0;
                while((bytes=in.read(data))>0)
                {
                    out.write(data, 0, bytes);
		    out.flush();
                }
		//int c = in.read(data, 0, data.length);
		//out.write(data, 0, c);
		//out.flush();
           
			
            
                
              
	}
     }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        
        
        server sv = new server();
        sv.setVisible(true);
        JOptionPane.showMessageDialog(null, "The Server Is Automatically Stared After This. \n Thanks.");
        sv.osero();
           
        
        
        
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
            java.util.logging.Logger.getLogger(server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new server().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2stop;
    private javax.swing.JButton jButton3exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextArea jTextArea1state;
    // End of variables declaration//GEN-END:variables
}
