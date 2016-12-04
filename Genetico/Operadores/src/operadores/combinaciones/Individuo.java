package operadores.combinaciones;

import interfazVisualizacion.ExtraccionDatos;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import jxl.read.biff.BiffException;
import operadores.combinaciones.mutacion.MutacionInversion;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Jonathan Rojas Simón
 */
public class Individuo
{
    /**
     * Constante que identifica la capacidad de peso del torton
     */
    public static final int CAM_TORTON = 16000;
    /**
     * Constante que identifica la capacidad de peso del rabon
     */
    public static final int CAM_RABON = 8000;
    /**
     * Constante que identifica la capacidad de peso de la camioneta doble
     */
    public static final int CAM_DOBLE = 4000;
    /**
     * Constante que identifica la capacidad de peso de una camioneta Pick-up
     */
    public static final int PICK_UP = 2000;
    
    private DefaultCategoryDataset pvObjDatosFA;
    private ArrayList<int[]> pvALPoblacionGenerada;
    private ArrayList<Double> pvALpoblacionFA;
    
    /**
     * Individuo 
     * Constructor que asigna los valores principales del problema
     * @param aLPoblacion es la población generada
     * @param aLPoblacionFA es la FA
     * @param objDatos es el conjunto de datos a graficar
     */
    public Individuo(ArrayList<int[]> aLPoblacion, ArrayList<Double> aLPoblacionFA, DefaultCategoryDataset objDatos)
    {
        this.pvALPoblacionGenerada = aLPoblacion;
        this.pvALpoblacionFA = aLPoblacionFA;
        this.pvObjDatosFA = objDatos;
    }

    /**
     * getPvObjDatosFA
     * Método que retorna el conjunto de datos a graficar
     * @return un conjunto de datos obtenidos
     */
    public DefaultCategoryDataset getPvObjDatosFA()
    {
        return pvObjDatosFA;
    }

    /**
     * setPvObjDatosFA
     * Método que establece los valores a graficar
     * @param pvObjDatosFA 
     */
    public void setPvObjDatosFA(DefaultCategoryDataset pvObjDatosFA)
    {
        this.pvObjDatosFA = pvObjDatosFA;
    }

    /**
     * getPvALPoblacionGenerada
     * Método que retorna una lista de los individuos seleccionados como mejores
     * @return un arreglo de individuos
     */
    public ArrayList<int[]> getPvALPoblacionGenerada()
    {
        return pvALPoblacionGenerada;
    }

    /**
     * setPvALPoblacionGenerada
     * Método que establece los valores de la población generada (FA)
     * @param pvALPoblacionGenerada 
     */
    public void setPvALPoblacionGenerada(ArrayList<int[]> pvALPoblacionGenerada)
    {
        this.pvALPoblacionGenerada = pvALPoblacionGenerada;
    }

    /**
     * getPvALpoblacionFA
     * Método que retorna una lista de las FA
     * @return una lista de FA
     */
    public ArrayList<Double> getPvALpoblacionFA()
    {
        return pvALpoblacionFA;
    }

    /**
     * setPvALpoblacionFA
     * Método que establece los valores de la FA
     * @param pvALpoblacionFA 
     */
    public void setPvALpoblacionFA(ArrayList<Double> pvALpoblacionFA)
    {
        this.pvALpoblacionFA = pvALpoblacionFA;
    }
    

    /**
     * generaPoblacionInicial 
     * Genera el numero de individuos de forma aleatoria
     *
     * @param lEIndividuos es el numero de individuos
     * @param lEGenes es el numero de genes definido
     * @return ArrayList de arreglos de enteros
     */
    public ArrayList<int[]> generaPoblacionInicial(long lEIndividuos, int lEGenes)
    {
        ArrayList<int[]> objArrayListPoblacion = new ArrayList<>();

        for (long i = 0; i < lEIndividuos; i++)
        {
            objArrayListPoblacion.add(llenaGenes(lEGenes));
        }

        return objArrayListPoblacion;
    }

    /**
     * llenaGenes Metodo que asigna los numeros dada la cantidad de los genes
     * del individuo
     *
     * @param lEGenes es la longitud de los genes dados y se crea un arreglo de
     * enteros
     * @return un arreglo de entero de los genes NOTA: Los numeros aleatorios
     * asignados pertenecen al rango de 0 a 1
     */
    public int[] llenaGenes(int lEGenes)
    {
        int[] lEARGenes = new int[lEGenes];
        Random objRandom = new Random();

        for (int i = 0; i < lEARGenes.length; i++)
        {
            lEARGenes[i] = objRandom.nextInt(2);
        }

        String op1 = "", op2 = "";

        op1 += String.valueOf(lEARGenes[0]) + String.valueOf(lEARGenes[1]);
        op2 += String.valueOf(lEARGenes[5]) + String.valueOf(lEARGenes[6]);

        while ((op1.equals("11") && op2.equals("01"))
                || (op1.equals("11") && op2.equals("10"))
                || (op1.equals("11") && op2.equals("11"))
                || (op1.equals("01") && op2.equals("11"))
                || (op1.equals("10") && op2.equals("11"))
                || (op1.equals("10") && op2.equals("10")))
        {
            op1 = "";
            op2 = "";

            lEARGenes[0] = objRandom.nextInt(2);
            lEARGenes[1] = objRandom.nextInt(2);
            lEARGenes[5] = objRandom.nextInt(2);
            lEARGenes[6] = objRandom.nextInt(2);

            op1 += String.valueOf(lEARGenes[0]) + String.valueOf(lEARGenes[1]);
            op2 += String.valueOf(lEARGenes[5]) + String.valueOf(lEARGenes[6]);
        }

        return lEARGenes;
    }

    /**
     * llenarGenesGeneral Metodo que asigna de forma aleatoria los numeros de
     * genes dada su propia longitud
     *
     * @param lEGenes
     * @return el individuo generado
     */
    public int[] llenarGenesGeneral(int lEGenes)
    {
        int[] lEARGenes = new int[lEGenes];
        Random objRandom = new Random();

        for (int i = 0; i < lEARGenes.length; i++)
        {
            lEARGenes[i] = objRandom.nextInt(2);
        }
        return lEARGenes;
    }

    /**
     * funcionAptitud Metodo que retorna el valor de la función de aptitud de
     * tods los individuos
     *
     * @param objArrayListPoblacion es toda la poblacion
     * @param objValores es una lista de matrices de objetos cargados de un
     * archivo de excel
     * @param diaSemana Es la cadena del día de la semana (Lunes - Domingo)
     * @param diaFestivo Es la fecha del año mostrada en el formato (dd/MM/aa)
     * @return un arreglo dinamico de valores reales que representan las
     * ganancias obtenidas de los individuos
     * @throws ParseException
     */
    public ArrayList<Double> funcionAptitud(ArrayList<int[]> objArrayListPoblacion, ArrayList<Object[][]> objValores, String diaSemana, String diaFestivo) throws ParseException
    {
        SimpleDateFormat objSimpleDateFormat = new SimpleDateFormat("dd/MM/yy");
        Date objDate1 = objSimpleDateFormat.parse(diaFestivo), objDate2 = null;
        String cadena = "";
        double incDiaSemana = 0, incDiaFestivo = 0;

        for (int i = 0; i < objValores.get(2).length; i++)
        {
            if (String.valueOf(objValores.get(2)[i][0]).equals(diaSemana))
            {
                incDiaSemana = Double.parseDouble(String.valueOf(objValores.get(2)[i][1]));
            }
        }

        System.out.println("Es " + diaSemana + " con un incremeneto de: " + incDiaSemana);

        for (int i = 0; i < objValores.get(0).length; i++)
        {
            objDate2 = (Date) objSimpleDateFormat.parse(String.valueOf(objValores.get(0)[i][0]));
            if (objDate2.compareTo(objDate1) == 0)
            {
                incDiaFestivo = Double.parseDouble(String.valueOf(objValores.get(0)[i][1]));
                cadena = String.valueOf(objValores.get(0)[i][0]);
            }
        }

        if (incDiaFestivo == 0)
        {
            System.out.println("La fecha ingresada no es un día festivo y tiene un incremento de: " + incDiaFestivo);
        } else
        {
            System.out.println("Es " + cadena + " y tiene un incremento/decremento de: " + incDiaFestivo);
        }

        ArrayList<Double> objArrayListValoresFA = new ArrayList<>();

        for (int p = 0; p < objArrayListPoblacion.size(); p++)
        {
//            System.out.println("Individuo: " + p);
            /**
             * indicadores[] Es un arreglo de enteros que almacena los valores
             * relacionados para determinar la FA
             *
             * La constitucion del individuo esta determinada de la siguiente
             * forma:
             *
             * Ocupación del producto uno {1,2} Producto uno {3,4,5} Precio del
             * campo para el producto uno {6,…,13} Precio del mercado para el
             * producto uno {14,…,21} Ocupación del producto dos {22,23}
             * Producto dos {24,25,26} Precio del campo para el producto dos
             * {27,…,34} Precio del mercado para el producto dos {35,…,42}
             * Vehículo utilizado {43,44} Localización del producto {45,46,47}
             */
            String[] binarios =
            {
                "", "", "", "", "", ""
            };
            int[] indicadores = new int[6];

            String[] lugares =
            {
                "Morelos", "Sonora", "Hidalgo", "Sinaloa", "Zacatecas", "Nayarit", "Michoacan", "Guanajuato"
            };
            String[] vehiculo =
            {
                "Pick-up", "Camioneta doble", "Rabon", "Torton"
            };
            String[] productos =
            {
                "Tomate rojo", "Tomate verde", "Tomate manzano verde", "Tomate manzano amarillo", "Cebolla blanca", "Chile Jalapeño", "Chile Poblano", "Chile Serrano"
            };
            double[] porcentajes =
            {
                0, 33, 66, 100
            };
//            String[] indicadoresCadenas = new String[4];
//            double[] indicadoresNumericos = new double[6];

            double oPUno = 0, oPDos = 0, pCPUno = 0, pMPUno = 0, pCPDos = 0, pMPDos = 0;
            String pUno = "", pDos = "", vUtilizado = "", lProductos;

            for (int i = 0; i < objArrayListPoblacion.get(p).length; i++)
            {
                if (i < 2)
                {
                    binarios[0] += String.valueOf(objArrayListPoblacion.get(p)[i]);
                } else if (i < 5)
                {
                    binarios[1] += String.valueOf(objArrayListPoblacion.get(p)[i]);
                } else if (i < 7)
                {
                    binarios[2] += String.valueOf(objArrayListPoblacion.get(p)[i]);
                } else if (i < 10)
                {
                    binarios[3] += String.valueOf(objArrayListPoblacion.get(p)[i]);
                } else if (i < 12)
                {
                    binarios[4] += String.valueOf(objArrayListPoblacion.get(p)[i]);
                } else if (i < 15)
                {
                    binarios[5] += String.valueOf(objArrayListPoblacion.get(p)[i]);
                }
            }

            for (int i = 0; i < binarios.length; i++)
            {
                indicadores[i] = Integer.parseInt(binarios[i], 2);
                switch (i)
                {
                    case 0:
                        oPUno = porcentajes[indicadores[0]];
//                        System.out.println("El porcentaje asociado al producto uno es: " + oPUno);
                        break;
                    case 1:
                        pUno = productos[indicadores[1]];
//                        System.out.println("El producto asociado es: " + pUno);
                        break;
                    case 2:
                        oPDos = porcentajes[indicadores[2]];
//                        System.out.println("El porcentaje asociado al producto dos es: " + oPDos);
                        break;
                    case 3:
                        pDos = productos[indicadores[3]];
//                        System.out.println("El producto asociado es: " + pDos);
                        break;
                    case 4:
                        vUtilizado = vehiculo[indicadores[4]];
//                        System.out.println("El vehiculo utilizado es de: " + vUtilizado);
                        break;
                    case 5:
                        lProductos = lugares[indicadores[5]];
//                        System.out.println("El lugar a donde se va es de: " + lProductos);
                        break;
                }
            }

            /*
         * Una vez que se tengan los valores de cada undividuo, debemos obtener de él la FA correspondiente
         * 
             */
            double valor = 0, flete = 0, capacidadCarga = 0;
            double envaseUno = 0, envaseDos = 0, precioEnvaseUno = 0, precioEnvaseDos = 0;

            switch (vUtilizado)
            {
                case "Torton":
                    capacidadCarga = CAM_TORTON;
                    break;
                case "Rabon":
                    capacidadCarga = CAM_RABON;
                    break;
                case "Camioneta doble":
                    capacidadCarga = CAM_DOBLE;
                    break;
                case "Pick-up":
                    capacidadCarga = PICK_UP;
                    break;
            }

            switch (pUno)
            {
                case "Tomate rojo":
                case "Tomate verde":
                case "Tomate manzano verde":
                case "Tomate manzano amarillo":
                    envaseUno = Double.parseDouble(String.valueOf(objValores.get(3)[0][2]));
                    precioEnvaseUno = Double.parseDouble(String.valueOf(objValores.get(3)[0][1]));
                    break;
                case "Cebolla blanca":
                    envaseUno = Double.parseDouble(String.valueOf(objValores.get(3)[4][2]));
                    precioEnvaseUno = Double.parseDouble(String.valueOf(objValores.get(3)[4][1]));
                    break;
                case "Chile Jalapeño":
                case "Chile Serrano":
                    envaseUno = Double.parseDouble(String.valueOf(objValores.get(3)[3][2]));
                    precioEnvaseUno = Double.parseDouble(String.valueOf(objValores.get(3)[3][1]));
                    break;
                case "Chile Poblano":
                    envaseUno = Double.parseDouble(String.valueOf(objValores.get(3)[5][2]));
                    precioEnvaseUno = Double.parseDouble(String.valueOf(objValores.get(3)[5][1]));
                    break;
            }

            switch (pDos)
            {
                case "Tomate rojo":
                case "Tomate verde":
                case "Tomate manzano verde":
                case "Tomate manzano amarillo":
                    envaseDos = Double.parseDouble(String.valueOf(objValores.get(3)[0][2]));
                    precioEnvaseDos = Double.parseDouble(String.valueOf(objValores.get(3)[0][1]));
                    break;
                case "Cebolla blanca":
                    envaseDos = Double.parseDouble(String.valueOf(objValores.get(3)[4][2]));
                    precioEnvaseDos = Double.parseDouble(String.valueOf(objValores.get(3)[4][1]));
                    break;
                case "Chile Jalapeño":
                case "Chile Serrano":
                    envaseDos = Double.parseDouble(String.valueOf(objValores.get(3)[3][2]));
                    precioEnvaseDos = Double.parseDouble(String.valueOf(objValores.get(3)[3][1]));
                    break;
                case "Chile Poblano":
                    envaseDos = Double.parseDouble(String.valueOf(objValores.get(3)[5][2]));
                    precioEnvaseDos = Double.parseDouble(String.valueOf(objValores.get(3)[5][1]));
                    break;
            }
            
            pMPUno = Double.parseDouble(String.valueOf(objValores.get(4)[indicadores[1]][1]));
//            System.out.println("El precio del mercado del producto uno es: " + pMPUno);
            pCPUno = Double.parseDouble(String.valueOf(objValores.get(5)[indicadores[1]][indicadores[5]]));
//            System.out.println("El precio del campo del producto uno es: " + pCPUno);
            pMPDos = Double.parseDouble(String.valueOf(objValores.get(4)[indicadores[3]][1]));
//            System.out.println("El precio del mercado del producto dos es: " + pMPDos);
            pCPDos = Double.parseDouble(String.valueOf(objValores.get(5)[indicadores[3]][indicadores[5]]));
//            System.out.println("El precio del campo del producto dos es: " + pCPDos);
            
            flete = Double.parseDouble(String.valueOf(objValores.get(1)[indicadores[4]][indicadores[5]]));

////            System.out.println("El flete es de: " + flete);
////            System.out.println("Su capacidad es de: " + capacidadCarga);
////            System.out.println("El peso para el producto uno es: " + envaseUno);
////            System.out.println("El precio del envase uno es: " + precioEnvaseUno);
////            System.out.println("El peso para el producto dos es: " + envaseDos);
////            System.out.println("El precio del envase dos es: " + precioEnvaseDos);

//        oPUno = 66;
//        capacidadCarga = 16000;
//        pMPUno = 11.1;
//        pCPUno = 5.9;
//        oPDos = 0;
//        pMPDos = 7.3;
//        pCPDos = 13.2;
//        flete = 7500;
//        envaseUno = 28;
//        envaseDos = 28;
//        precioEnvaseUno = 10;
//        precioEnvaseDos = 10;
            valor = (((oPUno * capacidadCarga) / 100) * ((pMPUno + incDiaSemana + incDiaFestivo) - pCPUno)) + (((oPDos * capacidadCarga) / 100) * ((pMPDos + incDiaSemana + incDiaFestivo) - pCPDos)) - flete - ((((oPUno * capacidadCarga) / 100) / envaseUno) * precioEnvaseUno) - ((((oPDos * capacidadCarga) / 100) / envaseDos) * precioEnvaseDos);

            if (valor < 0)
            {
                valor = 0;
            }else if((oPUno + oPDos) > 100)
            {
                valor = 10;
            }
            objArrayListValoresFA.add(valor);

        }
        
        for (int i = 0; i < objArrayListValoresFA.size(); i++)
        {
            System.out.print(Arrays.toString(objArrayListPoblacion.get(i)));
            System.out.println(" ["+String.valueOf(objArrayListValoresFA.get(i)) + "]");
        }

        return objArrayListValoresFA;
    }
    
    public Individuo procesoGenético(int numeroGeneraciones, int tamanioPoblacion, int numeroCortes, int probMutacion, String fecha, String diaSemana, String path) throws IOException, BiffException, ParseException
    {
        DefaultCategoryDataset objCategoryDataset = new DefaultCategoryDataset(); //Creamos el objeto para graficar
        
        int generaciones = numeroGeneraciones; //Asignamos el número de generaciones
        int individuos = tamanioPoblacion; //Establecemos el tamaño de la población de cada generación
        
        /**
         * Generamos el conjunto de los siguientes objetos para el proceso del genético
         *          objRuleta: Para realizar la ruleta
         *          objMultipuntoAleatorio: Para realizar la cruza
         *          objMutacionInversion: Para realizar las mutaciones
         *          objExtraccionDatos: Para extraer los datos de una BD (Excel)
         */
        SeleccionRuleta objRuleta = new SeleccionRuleta();
        CruzaMultipuntoAleatorio objMultipuntoAleatorio = new CruzaMultipuntoAleatorio();
        MutacionInversion objMutacionInversion = new MutacionInversion();
        ExtraccionDatos objExtraccionDatos = new ExtraccionDatos();
        int[] caractMejor = null; //Creamos un arreglo estático para determinar el mejor de cada generación

        /**
         * Cargamos los datos de la BD en una Lista de Matrices de datos (de tipo Object) y mandamos la ubicación del archivo de datos
         * Generamos la lista de la población
         * Generamos una lista de las FA de la población
         * Generamos una lista de los mejores individuos de cada generación
         * Generamos una lista de las FA del mejor individuo de cada generación
         */
        ArrayList<Object[][]> objectses = objExtraccionDatos.extraccionFechas(path);
        ArrayList<int[]> objArrayListPoblacion = new ArrayList<>();
        ArrayList<Double> objArrayListFA = new ArrayList<>();
        ArrayList<int[]> individuosMejores = new ArrayList<>();
        ArrayList<Double> objMejoresFA = new ArrayList<>();
        
        /**
         * GENERAMOS EL OBJETO DE TIPO INDIVIDUO QUE TENDRÁ LOS VALORES NECESARIOS PARA GRAFICAR
         */
        Individuo objIndividuo = new Individuo(objArrayListPoblacion, objArrayListFA, objCategoryDataset);

        /**
         * Generamos el siguiente grupo de listas para realizar el proceso del algoritmo genético
         *      objListIndices: Son los índices que maneja la seleción
         *      objListIndividuosSeleccionados: Es para los individuos seleccionados
         *      objListIndividuosCruzados: Es para los individuos cruzados
         *      objListIndividuosMutados: Es para los individuos mutados
         */
        ArrayList<int[]> objListIndividuosSeleccionados = new ArrayList<>();
        ArrayList<int[]> objListIndividuosCruzados = new ArrayList<>();
        ArrayList<int[]> objListIndividuosMutados = new ArrayList<>();
        ArrayList<int[]> objListIndices = new ArrayList<>();

        /**
         * Realizamos el proceso de cada generación
         */
        for (int i = 0; i < generaciones; i++)
        {
            System.out.println("***********Generacion " + (i+1) + " ***********");

            if (i == 0) //Cuando es la primera generación
            {
                objArrayListPoblacion = objIndividuo.generaPoblacionInicial(individuos, 15); //Se genera una población con números aleatorios
            } else
            {
                objArrayListFA.clear(); //Cuando se trate de la segunda generación se tiene que limpiar los datos de la lista
            }
       
            //Le asignamos una FA a cada individuo de la población de cualquier generación considerando el día de la semana y la fecha
            objArrayListFA = objIndividuo.funcionAptitud(objArrayListPoblacion, objectses, diaSemana, fecha);
           
            /**
             * A través del siguiente bloque de sentencias obtenemos el promedio de cada generación
             * y también determinamos el mejor individuo de cada generación
             */
            double mejor = 0, sumaFA = 0, promedioGen = 0;
            for (int j = 0; j < objArrayListFA.size(); j++)
            {
                sumaFA = sumaFA + objArrayListFA.get(j);
                if (mejor < objArrayListFA.get(j))
                {
                    mejor = objArrayListFA.get(j); //Actualizamos el mejor cada que encuentre un valor mayor
                    caractMejor = objArrayListPoblacion.get(j); //Obtenemos las características del mejor individuo
                }
            }
            
            promedioGen = sumaFA / individuos; //El promedio de cada generación se almacena en una variable
            
            /**
             * Imprimimos los valores correspondientes para determinar la FA y características del mejor individuo de
             * cada generación
             *      mejor ---- Cantidad
             *      caractMejor ---- El individuo
             *      promedioGen ---- Es el promedio de la generación
             */
            System.out.println("El mejor individuo tiene: " + mejor + " de utilidades");
            System.out.println("El mejor individuo tiene: " + Arrays.toString(caractMejor));
            System.out.println("El promedio de la generación es de: " + promedioGen);
            
            objMejoresFA.add(mejor); //Agregamos a una lista el valor del mejor individuo de la población
            individuosMejores.add(caractMejor); //Agregamos a una lista las características del mejor individuo de la población
            
            //Insertamos las FA de los mejores individuos de cada genración
            objCategoryDataset.addValue(mejor, "Mejor individuo", String.valueOf(i+1));
            //Insertamos la FA promedio de cada genración
            objCategoryDataset.addValue(promedioGen, "Promedio población", String.valueOf(i+1));
            
            //Limpiamos la lista de individuos seleccionados
            objListIndividuosSeleccionados.clear();
            //Limpiamos la lista de los índices (números de individuos) que fueron seleccionados
            objListIndices.clear();
            
            /**
             * Realizamos selección por ruleta a partir de la población generada
             * las iteraciones serán dada por la siguiente expresión
             * 
             *          iteraciones = individuos/2
             */
            for (int j = 0; j < (individuos / 2); j++)
            {
                objListIndices.add(objRuleta.seleccionaRuleta(objArrayListFA,i));
                objListIndividuosSeleccionados.add(objArrayListPoblacion.get(objListIndices.get(j)[0]));
                objListIndividuosSeleccionados.add(objArrayListPoblacion.get(objListIndices.get(j)[1]));
            }

            
            objListIndividuosCruzados.clear(); //Limpiamos la lista de los individuos cruzados
            ArrayList<int[]> bandera = new ArrayList<>(); //Generamos una lista de tipo bandera
            /**
             * El siguiente ciclo presenta las iteraciones para realizar la cruza necesaria, tomando en cuenta a la población
             * seleccionada por ruleta y son enviados al método "obtenerHijosCruza" para que puedan cruzarse
             * La lista bandera es para que pueda recibir los los arreglos para posteriormente ingresarlos a la lista de individuos cruzados
             */
            for (int j = 0; j < (individuos / 2); j++)
            {
                bandera.clear();
                bandera = objMultipuntoAleatorio.obtenerHijosCruza(objListIndividuosSeleccionados.get(j), objListIndividuosSeleccionados.get(j), numeroCortes);
                objListIndividuosCruzados.add(bandera.get(0)); //Insertamos el primer hijo
                objListIndividuosCruzados.add(bandera.get(1)); //Insertamos el segundo hijo
            }

            objListIndividuosMutados.clear(); //Limpiamos la lista de los individuos mutados
            
            /**
             * Realicé el diguiente ciclo para meter a cada individuo de la población cruzada al proceso de mutación
             * tomando en cuenta la probabilidad de mutación
             */
            for (int j = 0; j < objListIndividuosCruzados.size(); j++)
            {
                objListIndividuosMutados.add(objMutacionInversion.obtenerIndividuosMutados(objListIndividuosCruzados.get(j), probMutacion));
            }
       
            /**
             * Asignamos a cada individuo de la población a la siguiente generación
             */
            for (int j = 0; j < objListIndividuosMutados.size(); j++)
            {
                objArrayListPoblacion.set(j, objListIndividuosMutados.get(j));
            }
            
        } //Termina el proceso de iteración

        /**
         * Insertamos los siguientes valores para la graficación e interpretación de los resultados
         */
        objIndividuo.setPvALpoblacionFA(objMejoresFA); //Insertamos al objeto la lista de las mejores FA de cada generación
        objIndividuo.setPvObjDatosFA(objCategoryDataset); //Insertamos los datos a graficar de cada generación al objeto
        objIndividuo.setPvALPoblacionGenerada(individuosMejores); //Insertamos las características de los mejores individuos en cada generación 

        return objIndividuo; //Retornamos el objeto
    }

}
