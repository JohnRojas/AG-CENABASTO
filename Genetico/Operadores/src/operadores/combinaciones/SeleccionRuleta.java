package operadores.combinaciones;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Jonathan Rojas Simón
 */
public class SeleccionRuleta
{

    /**
     * SeleccionRuleta
     * Constructor vacio
     */
    public SeleccionRuleta()
    {
        
    }
    
    /**
     * seleccionaRuleta
     * Metodo que se realiza la seleccion de individuos de forma aleatoria
     * 
     * @param objArrayListAptitudes 
     * @return el numero de individuo seleccionado
     */
    public int[] seleccionaRuleta(ArrayList<Double> objArrayListAptitudes, int o)
    {
        int[] individuos = 
        {
            0 , 0
        };
        ArrayList<Double> objArrayListFAcumulado = new ArrayList<>();
        double incrementos = 0;
        
        for (int i = 0; i < objArrayListAptitudes.size(); i++)
        {
            incrementos += objArrayListAptitudes.get(i);
            objArrayListFAcumulado.add(incrementos);
//            System.out.println("El individuo " + i + " tiene: " + objArrayListFAcumulado.get(i));
        }
        
//        System.out.println("La suma acumulada de todos los individuos es de : " + incrementos);
        
        individuos[0] = seleccionaIndividuo(objArrayListFAcumulado, incrementos, o);
        individuos[1] = seleccionaIndividuo(objArrayListFAcumulado, incrementos, o);
        
        return individuos;
    }
    
    public int seleccionaIndividuo(ArrayList<Double> objArrayListFAcumulado, double incrementos, int o)
    {
        Random objRandom = new Random();
        int aleatorioSeleccionado = 0, numeroIndividuo = 0;
//        System.out.println("La suma acumulada es: " + incrementos);
        try
        {

            aleatorioSeleccionado = objRandom.nextInt((int) incrementos);
        } catch (Exception e)
        {
            System.err.println("ERROR en la generacion "+o+" : " + e.getMessage());
        }
        
//        System.out.println("El numero acumulado seleccionado es: " + aleatorioSeleccionado);
        double temporalNumero = 0;
        
        for (int i = 0; i < objArrayListFAcumulado.size(); i++)
        {
            if (i == 0)
            {
                if (aleatorioSeleccionado <= objArrayListFAcumulado.get(0))
                {
                    temporalNumero = objArrayListFAcumulado.get(0);
                    numeroIndividuo = i;
                }
            } else if (aleatorioSeleccionado > objArrayListFAcumulado.get(i - 1) && aleatorioSeleccionado <= objArrayListFAcumulado.get(i))
            {
                temporalNumero = objArrayListFAcumulado.get(i);
                numeroIndividuo = i;
            }
        }
        
//        System.out.println("El numero mas cercano a la suma acumulada es de: " + temporalNumero);
//        System.out.println("El indiviuo mas cercano al numero es de: " + numeroIndividuo);
        
        return numeroIndividuo;
        
    }

}
