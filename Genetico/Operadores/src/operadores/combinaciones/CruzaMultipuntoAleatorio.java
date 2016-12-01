package operadores.combinaciones;

import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author Jonathan Rojas Simón
 */
public class CruzaMultipuntoAleatorio
{

    public CruzaMultipuntoAleatorio()
    {
        
    }
    
    public ArrayList<int[]> obtenerHijosCruza(int[] arrP1, int[] arrP2,int lENoPuntos)
    {
        ArrayList<int[]> objArrayListHijos = new ArrayList<>();
        Random objRandom = new Random();
        int[] arrPuntos = new int[lENoPuntos];
        int[] arrPuntosCorte = new int[arrP1.length];
        int[] arrH1 = new int[arrP1.length];
        int[] arrH2 = new int[arrP2.length];
        
//        System.out.print("[");
        for (int i = 0; i < arrPuntos.length; i++)
        {
            arrPuntos[i] = objRandom.nextInt(arrP1.length - 2) + 1;
//            System.out.print(arrPuntos[i] + ", ");
        }
//        System.out.println("]");
        
        for (int i = 0; i < arrPuntosCorte.length; i++)
        {
            for (int j = 0; j < arrPuntos.length; j++)
            {
                if (arrPuntos[j] == i)
                {
                    arrPuntosCorte[i] = 1;
                }
            }
        }
        
//        System.out.print("[");
//        for (int i = 0; i < arrPuntosCorte.length; i++)
//        {
//            System.out.print(arrPuntosCorte[i] + ", ");
//        }
//        System.out.println("]");
        
        int control = 1;
        
        for (int i = 0; i < arrH1.length; i++)
        {
            if (control == 1)
            {
                arrH1[i] = arrP1[i];
                if (i < arrH1.length)
                {
                    if (arrPuntosCorte[i] == 1)
                    {
                        control = 2;
                    }
                }
            }else if(control == 2)
            {
                arrH1[i] = arrP2[i];
                if (i < arrH1.length)
                {
                    if (arrPuntosCorte[i] == 1)
                    {
                        control = 1;
                    }
                }
            }
        }
        
        control = 2;
        
        for (int i = 0; i < arrH2.length; i++)
        {
            if (control == 2)
            {
                arrH2[i] = arrP2[i];
                if (i < arrH2.length)
                {
                    if (arrPuntosCorte[i] == 1)
                    {
                        control = 1;
                    }
                }
            }else if(control == 1)
            {
                arrH2[i] = arrP1[i];
                if (i < arrH2.length)
                {
                    if (arrPuntosCorte[i] == 1)
                    {
                        control = 2;
                    }
                }
            }
        }
//        System.out.print("[");
//        for (int i = 0; i < arrH1.length; i++)
//        {
//            System.out.print(arrH1[i] + ", ");
//        }
//        System.out.println("]");
//        System.out.print("[");
//        for (int i = 0; i < arrH2.length; i++)
//        {
//            System.out.print(arrH2[i] + ", ");
//        }
//        System.out.println("]");
        
        objArrayListHijos.add(arrH1);
        objArrayListHijos.add(arrH2);
        
        return objArrayListHijos;
    }
}