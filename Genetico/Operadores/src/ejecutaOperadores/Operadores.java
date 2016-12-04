package ejecutaOperadores;

import interfazVisualizacion.ExtraccionDatos;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
//import java.util.Arrays;
import javax.swing.JFrame;
import jxl.read.biff.BiffException;
import operadores.combinaciones.Individuo;
import operadores.combinaciones.CruzaMultipuntoAleatorio;
import operadores.combinaciones.SeleccionRuleta;
import operadores.combinaciones.mutacion.MutacionInversion;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Jonathan Rojas Simón
 */
public class Operadores
{
    

    /**
     * @param args the command line arguments
     * @throws IOException
     * @throws BiffException
     * @throws ParseException
     */
    public static void main(String[] args) throws IOException, BiffException, ParseException
    {
        
        DefaultCategoryDataset objCategoryDataset = new DefaultCategoryDataset();
        
        
        JFreeChart objChart;
        int generaciones = 30;
        int individuos = 30;
        Individuo objIndividuo = new Individuo(null,null,null);
        SeleccionRuleta objRuleta = new SeleccionRuleta();
        CruzaMultipuntoAleatorio objMultipuntoAleatorio = new CruzaMultipuntoAleatorio();
        MutacionInversion objMutacionInversion = new MutacionInversion();
        ExtraccionDatos objExtraccionDatos = new ExtraccionDatos();

        ArrayList<Object[][]> objectses = objExtraccionDatos.extraccionFechas("C:\\Users\\IDS_J\\Desktop\\AG-CENABASTO\\Genetico\\Banco_de_datos_integrado.xls");
        ArrayList<int[]> objArrayListPoblacion = new ArrayList<>();
        ArrayList<Double> objArrayListFA = new ArrayList<>();
        ArrayList<int[]> objListIndividuosSeleccionados = new ArrayList<>();
        ArrayList<int[]> objListIndividuosCruzados = new ArrayList<>();
        ArrayList<int[]> objListIndividuosMutados = new ArrayList<>();
        ArrayList<int[]> objListIndices = new ArrayList<>();

        for (int i = 0; i < generaciones; i++)
        {
            System.out.println("*******Generacion " + i + " ***********");

            if (i == 0)
            {
                objArrayListPoblacion = objIndividuo.generaPoblacionInicial(individuos, 15);
            } else
            {
                objArrayListFA.clear();
            }
       
            objArrayListFA = objIndividuo.funcionAptitud(objArrayListPoblacion, objectses, "Viernes", "02/11/16");
           
            double mejor = 0, sumaFA = 0, promedioGen = 0;
            for (int j = 0; j < objArrayListFA.size(); j++)
            {
                sumaFA = sumaFA + objArrayListFA.get(j);
                if (mejor < objArrayListFA.get(j))
                {
                    mejor = objArrayListFA.get(j);
                }
            }
            
            promedioGen = sumaFA / individuos;
            
            System.out.println("El mejor individuo tiene: " + mejor + " de utilidades");
            System.out.println("El promedio de la generación es de: " + promedioGen);
            
            objCategoryDataset.addValue(mejor, "Mejor individuo", String.valueOf(i));
            objCategoryDataset.addValue(promedioGen, "Promedio población", String.valueOf(i));
            
            objListIndividuosSeleccionados.clear();

            objListIndices.clear();
            for (int j = 0; j < (individuos / 2); j++)
            {
                objListIndices.add(objRuleta.seleccionaRuleta(objArrayListFA,i));
                objListIndividuosSeleccionados.add(objArrayListPoblacion.get(objListIndices.get(j)[0]));
                objListIndividuosSeleccionados.add(objArrayListPoblacion.get(objListIndices.get(j)[1]));
            }

            objListIndividuosCruzados.clear();
            ArrayList<int[]> bandera = new ArrayList<>();
            for (int j = 0; j < (individuos / 2); j++)
            {
                bandera.clear();
                bandera = objMultipuntoAleatorio.obtenerHijosCruza(objListIndividuosSeleccionados.get(j), objListIndividuosSeleccionados.get(j), 4);
                objListIndividuosCruzados.add(bandera.get(0));
                objListIndividuosCruzados.add(bandera.get(1));
            }

            objListIndividuosMutados.clear();
            for (int j = 0; j < objListIndividuosCruzados.size(); j++)
            {
                objListIndividuosMutados.add(objMutacionInversion.obtenerIndividuosMutados(objListIndividuosCruzados.get(j), 0));
            }
       
            for (int j = 0; j < objListIndividuosMutados.size(); j++)
            {
                objArrayListPoblacion.set(j, objListIndividuosMutados.get(j));
            }
            
        }
        
        objChart = ChartFactory.createLineChart("Gráfico de utilidades", "Generaciones", "Utilidades", objCategoryDataset, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel Panel = new ChartPanel(objChart);
        JFrame Ventana = new JFrame("JFreeChart");
        Ventana.getContentPane().add(Panel);
        Ventana.pack();
        Ventana.setVisible(true);
        Ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
