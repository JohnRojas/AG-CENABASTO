package operadores.combinaciones.mutacion;

import java.util.Random;

/**
 * 
 * @author Jonathan Rojas Simón
 */
public class MutacionInversion {

    /**
     * mutacionInversion
     * Contructor vacio
     */
    public MutacionInversion()
    {
        
    }
    
    /**
     * obtenerIdividuosMutados
     * Metodo que obtenemos individuos mutados a traves de un umbral de probabilidad
     * @param objHijos son los individos generados a traves de la cruza
     * @param lEIndiceUmbral establece el inicio del umbral
     * @return los individuos mutados a traves de una lista de arreglos
     */
    public int[] obtenerIndividuosMutados(int[] objHijos, int lEIndiceUmbral)
    {
        Random objRandom = new Random();
        int lEResultadoDado = 0;
        if (lEIndiceUmbral > 0)
        {
            for (int i = 0; i < objHijos.length; i++)
            {
                lEResultadoDado = objRandom.nextInt(1001);
//                System.out.println("El numero generado es: " + lEResultadoDado);
                if (lEResultadoDado >= 0 && lEResultadoDado <= lEIndiceUmbral)
                {
                    if (objHijos[i] == 1)
                    {
                        objHijos[i] = 0;
                    } else if (objHijos[i] == 0)
                    {
                        objHijos[i] = 1;
                    }
//                    System.out.println("El gen " + i + " del hijo uno si muto");
                } else if (lEResultadoDado > lEIndiceUmbral && lEResultadoDado <= 1000)
                {
//                    System.out.println("El gen " + i + " del hijo uno no muto");
                }
            }
        }
        return objHijos;
    }
}
