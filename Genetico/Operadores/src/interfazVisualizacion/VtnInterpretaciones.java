/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazVisualizacion;

import java.util.Arrays;
import operadores.combinaciones.Individuo;

/**
 *
 * @author Jonathan Rojas Simón
 */
public class VtnInterpretaciones extends javax.swing.JFrame
{

    /**
     * Creates new form VtnInterpreciaciones
     */
    public VtnInterpretaciones()
    {
        initComponents();
    }
    
    /**
     * Creates new form VtnInterpreciaciones con un nuevo informe
     * @param objIndividuo
     */
    public VtnInterpretaciones(Individuo objIndividuo)
    {
        initComponents();
        String informe = this.interpretacionDatos(objIndividuo);
        
        jTextArea1.setText(informe);
    }

    public String interpretacionDatos(Individuo datos)
    {
        String salida = "";
        String [] lSValores =
        {
            "","","","","",""
        };
        
        int [] valores = new int[6];
        
//        for (int i = 0; i < datos.getPvALPoblacionGenerada().size(); i++)
//        {
        salida += "El individuo ";
        salida += Arrays.toString(datos.getPvALPoblacionGenerada().get(datos.getPvALPoblacionGenerada().size() - 1)) + " \n";
        salida += "Tiene como FA [" + String.valueOf(datos.getPvALpoblacionFA().get(datos.getPvALPoblacionGenerada().size() - 1).shortValue()) + "] ";
        salida += "\n";
        
        for (int i = 0; i < datos.getPvALPoblacionGenerada().get(datos.getPvALPoblacionGenerada().size()-1).length; i++)
        {
            if (i < 2)
            {
                lSValores[0] += String.valueOf(datos.getPvALPoblacionGenerada().get(datos.getPvALPoblacionGenerada().size()-1)[i]);
            }else if(i < 5)
            {
                lSValores[1] += String.valueOf(datos.getPvALPoblacionGenerada().get(datos.getPvALPoblacionGenerada().size()-1)[i]);
            }else if (i < 7)
            {
                lSValores[2] += String.valueOf(datos.getPvALPoblacionGenerada().get(datos.getPvALPoblacionGenerada().size()-1)[i]);
            }else if(i < 10)
            {
                lSValores[3] += String.valueOf(datos.getPvALPoblacionGenerada().get(datos.getPvALPoblacionGenerada().size()-1)[i]);
            }else if(i < 12)
            {
                lSValores[4] += String.valueOf(datos.getPvALPoblacionGenerada().get(datos.getPvALPoblacionGenerada().size()-1)[i]);
            }else if(i < 15)
            {
                lSValores[5] += String.valueOf(datos.getPvALPoblacionGenerada().get(datos.getPvALPoblacionGenerada().size()-1)[i]);
            }
        }
        
        for (int i = 0; i < lSValores.length; i++)
        {
            valores[i] = Integer.parseInt(lSValores[i], 2);
        }
        
        salida += interpretaProductos(valores[0], valores[1]);
        salida += interpretaProductos(valores[2], valores[3]);
        
        switch (valores[4])
        {
            case 0:
                salida += "\nTienes que llevar una camioneta pick-up a ";
                break;
            case 1:
                salida += "\nTienes que llevar una camioneta doble a ";
                break;
            case 2:
                salida += "\nTienes que llevar un rabón a ";
                break;
            case 3:
                salida += "\nTienes que llevar un torton a ";
                break;
        }
        
        switch (valores[5])
        {
            case 0:
                salida += "Cuautla Morelos";
                break;
            case 1:
                salida += "Sonora";
                break;
            case 2:
                salida += "Hidalgo";
                break;
            case 3:
                salida += "Sinaloa";
                break;
            case 4:
                salida += "Zacatecas";
                break;
            case 5:
                salida += "Nayarit";
                break;
            case 6:
                salida += "Michoacán";
                break;
            case 7:
                salida += "Guanajuato";
                break;
        }

//        }

        return salida;
    }
    
    public String interpretaProductos(int proporcion, int producto)
    {
        String salida = "";
        switch (proporcion)
        {
            case 0:
                salida += "\nOcupa un 0% de ";
                break;
            case 1:
                salida += "\nOcupa un 33% de ";
                break;
            case 2: 
                salida += "\nOcupa un 66% de ";
                break;
            case 3:
                salida += "\nOcupa un 100% de ";
                break;
        }
        
        switch (producto)
        {
            case 0:
                salida += "Tomate rojo";
                break;
            case 1:
                salida += "Tomate verde";
                break;
            case 2:
                salida += "Tomate manzano verde";
                break;
            case 3:
                salida += "Tomate manzano amarillo";
                break;
            case 4:
                salida += "Cebolla blanca";
                break;
            case 5:
                salida += "Chile jalapeño";
                break;
            case 6:
                salida += "Chile poblano";
                break;
            case 7:
                salida += "Chile serrano";
                break;
        }
        
        return salida;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Interpretación");
        setMaximumSize(new java.awt.Dimension(525, 230));
        setMinimumSize(new java.awt.Dimension(525, 230));
        setResizable(false);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Interpretación de resultados");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(70, 70, 70))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(VtnInterpretaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(VtnInterpretaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(VtnInterpretaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(VtnInterpretaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new VtnInterpretaciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
