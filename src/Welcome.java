/**
 * @author Reynaldo
 */
public class Welcome extends javax.swing.JFrame {
    /**
     * Crea nuevo Formulario Welcome
     * @param userInfo data del usuario
     */
    public Welcome() {
        setResizable(false);
        initComponents();
        this.setLocationRelativeTo(null); // Centrar el Formulario
    }

    /**
     * Este método se llama desde el constructor para inicializar el formulario.
     * ADVERTENCIA: NO modifique este código. El contenido de este método siempre es
     * regenerada por el editor de formularios.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelName1 = new javax.swing.JLabel();
        jLabelName2 = new javax.swing.JLabel();
        jLabelName3 = new javax.swing.JLabel();
        jLabelName4 = new javax.swing.JLabel();
        jLabelName5 = new javax.swing.JLabel();
        jLabelName6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(44, 62, 80));

        jLabelName1.setFont(new java.awt.Font("Tekton Pro", 0, 24)); // NOI18N
        jLabelName1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelName1.setText("Nombre");

        jLabelName2.setFont(new java.awt.Font("Tekton Pro", 0, 24)); // NOI18N
        jLabelName2.setForeground(new java.awt.Color(255, 255, 255));
        jLabelName2.setText("Apellido");

        jLabelName3.setFont(new java.awt.Font("Tekton Pro", 0, 24)); // NOI18N
        jLabelName3.setForeground(new java.awt.Color(255, 255, 255));
        jLabelName3.setText("Usuario");

        jLabelName4.setFont(new java.awt.Font("Tekton Pro", 0, 24)); // NOI18N
        jLabelName4.setForeground(new java.awt.Color(255, 255, 255));
        jLabelName4.setText("Pswd");

        jLabelName5.setFont(new java.awt.Font("Tekton Pro", 0, 24)); // NOI18N
        jLabelName5.setForeground(new java.awt.Color(255, 255, 255));
        jLabelName5.setText("Fecha");

        jLabelName6.setFont(new java.awt.Font("Tekton Pro", 0, 24)); // NOI18N
        jLabelName6.setForeground(new java.awt.Color(255, 255, 255));
        jLabelName6.setText("Lugar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(148, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelName1)
                    .addComponent(jLabelName2)
                    .addComponent(jLabelName3)
                    .addComponent(jLabelName4)
                    .addComponent(jLabelName5)
                    .addComponent(jLabelName6))
                .addGap(83, 83, 83))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabelName1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelName2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelName3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelName4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelName5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelName6)
                .addContainerGap())
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

    /**
     * @param args argumentos de linea de comandos
     */
    public static void main(String args[]) {
        /* Establecer como se ve y siente el Nimbus */
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
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Crear y visualizar el formulario */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Welcome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel jLabelName1;
    public javax.swing.JLabel jLabelName2;
    public javax.swing.JLabel jLabelName3;
    public javax.swing.JLabel jLabelName4;
    public javax.swing.JLabel jLabelName5;
    public javax.swing.JLabel jLabelName6;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}