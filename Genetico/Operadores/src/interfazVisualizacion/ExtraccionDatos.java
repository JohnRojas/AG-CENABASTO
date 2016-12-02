package interfazVisualizacion;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 *
 * @author Jonathan Rojas Simón
 */
public class ExtraccionDatos
{

    public ExtraccionDatos()
    {
        
    }
    
    public ArrayList<Object[][]> extraccionFechas(String lSArchivo) throws IOException, BiffException
    {
        ArrayList<Object[][]> objectses = new ArrayList<>();
        Workbook workbook = Workbook.getWorkbook(new File(lSArchivo)); //Pasamos el excel que vamos a leer
        Sheet sheet = workbook.getSheet(0); //Seleccionamos la hoja que vamos a leer
        String[][] objectsFechas = new String[sheet.getRows()-1][2];
        for (int fila = 0; fila < sheet.getRows()-1; fila++)
        { //recorremos las filas
            objectsFechas[fila][0] = sheet.getCell(1 , (fila+1)).getContents();
            objectsFechas[fila][1] = sheet.getCell(3 , (fila+1)).getContents();
        }
        objectses.add(objectsFechas);
        
        sheet = workbook.getSheet(2); //Seleccionamos la hoja que vamos a leer
        Double[][] fletes = new Double[sheet.getRows()-1][sheet.getColumns()-1];
        for (int i = 0; i < sheet.getRows()-1; i++)
        {
            for (int j = 0; j < sheet.getColumns()-1; j++)
            {
                fletes[i][j] = Double.parseDouble(sheet.getCell((j + 1), (i + 1)).getContents());
            }
        }
        
        objectses.add(fletes);
        
        sheet = workbook.getSheet(1);
        String[][] diaSemana = new String[sheet.getRows()-1][sheet.getColumns()-1];
         for (int i = 0; i < sheet.getRows()-1; i++)
        {
            for (int j = 0; j < sheet.getColumns()-1; j++)
            {
                diaSemana[i][j] = sheet.getCell((j + 1), (i + 1)).getContents();
            }
        }
         objectses.add(diaSemana);
         
         sheet = workbook.getSheet(3);
        String[][] preciosEnvase = new String[sheet.getRows()-1][sheet.getColumns()-1];
         for (int i = 0; i < sheet.getRows()-1; i++)
        {
            for (int j = 0; j < sheet.getColumns()-1; j++)
            {
                preciosEnvase[i][j] = sheet.getCell( j , (i + 1)).getContents();
            }
        }
         objectses.add(preciosEnvase);
         
         sheet = workbook.getSheet(4);
         String[][] preciosMercado = new String[sheet.getRows()-1][sheet.getColumns()];
         for (int i = 0; i < sheet.getRows()-1; i++)
        {
            for (int j = 0; j < sheet.getColumns(); j++)
            {
                preciosMercado[i][j] = sheet.getCell(j,(i + 1)).getContents();
            }
        }
         objectses.add(preciosMercado);
         
         sheet = workbook.getSheet(5);
         Double[][] preciosCampo = new Double[sheet.getRows()-1][sheet.getColumns()-1];
         for (int i = 0; i < sheet.getRows()-1; i++)
        {
            for (int j = 0; j < sheet.getColumns()-1; j++)
            {
                preciosCampo[i][j] = Double.parseDouble(sheet.getCell((j+1),(i+1)).getContents());
            }
        }
         objectses.add(preciosCampo);
        
        return objectses;
    }
    
    public String imprimirLista(ArrayList<Object[][]> objectses)
    {
        String salida = "";
         for (int i = 0; i < objectses.size(); i++)
        {
            switch (i)
            {
                case 0:
                    salida += "Días festivos \n\n";
                    break;
                case 1:
                    salida += "Fletes \n\n";
                    break;
                case 2:
                    salida += "Día de la semana \n\n";
                    break;
                case 3:
                    salida += "Envases \n\n";
                    break;
                case 4:
                    salida += "Precios del mercado \n\n";
                    break;
                case 5:
                    salida += "Precios del campo \n\n";
            }
            for (int j = 0; j < objectses.get(i).length; j++)
            {
                for (int k = 0; k < objectses.get(i)[0].length; k++)
                {
                    salida += objectses.get(i)[j][k] + "\t";
                }
                salida += "\n";
            }
            salida += "\n";
        }
         return salida;
    }
    
}
