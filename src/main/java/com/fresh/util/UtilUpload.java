package com.fresh.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * * Clase para administrar las utilidades relacionadas con la subida de un
 * archivo
 *
 * @author Juan
 */
public class UtilUpload {

    /**
     * Guarda un archivo pdf temporal para la impresion de tickets
     *
     * @param bytes
     * @param nombreArchivo
     * @return
     */
    public static String saveFileTemp(byte[] bytes, String nombreArchivo, String extencion) {

        String ubicacionPdf = null;
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String temporal = "";
        if (servletContext.getRealPath("") == null) {
            temporal = Constantes.PATHSERVER;
        } else {
            temporal = servletContext.getRealPath("");
        }

        String ruta = temporal + File.separatorChar + "resources" + File.separatorChar + "pdf" + File.separatorChar + "temp" + File.separatorChar + nombreArchivo;

        File file = null;
        InputStream inputStream = null;

        try {
            file = new File(ruta + ".pdf");
            int cont = 0;
            while (file.exists()) {
                cont++;

                if (cont > 1) {
                    ruta = ruta.replace("ticketPdf" + (cont - 1), "ticketPdf" + cont);
                    nombreArchivo = nombreArchivo.replace("ticketPdf" + (cont - 1), "ticketPdf" + cont);
                } else {

                    ruta += cont;
                    nombreArchivo += cont;
                }

                file = new File(ruta + ".pdf");
            }

            inputStream = new ByteArrayInputStream(bytes);
            FileOutputStream out = new FileOutputStream(file.getAbsolutePath());
            int c = 0;

            while ((c = inputStream.read()) >= 0) {
                out.write(c);
            }

            out.flush();
            out.close();

            ubicacionPdf = "/resources/pdf/temp/" + nombreArchivo + "." + extencion;

            System.out.println("archivo upload succes");

        } catch (Exception e) {
            System.out.println("error > " + e.getMessage());
            System.out.println("Error al Generar el temporal del PDF");
            e.getStackTrace();
        }
        return ubicacionPdf;
    }

    /**
     * Guarda un archivo pdf temporal para la impresion de tickets
     *
     * @param bytes
     * @param nombreArchivo
     * @return
     */
    public static String saveFileTemp(byte[] bytes, String nombreArchivo, int folio, int idSucursal) {

        long seconds = new Date().getTime();
        nombreArchivo = nombreArchivo + "_" + folio + "_" + idSucursal + "_" + seconds;
        String ubicacionPdf = null;
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String temporal = "";
        if (servletContext.getRealPath("") == null) {
            temporal = Constantes.PATHSERVER;
        } else {
            temporal = servletContext.getRealPath("");
        }

        String ruta = temporal + File.separatorChar + "resources" + File.separatorChar + "pdf" + File.separatorChar + "temp" + File.separatorChar + nombreArchivo;

        File file = null;
        InputStream inputStream = null;

        try {

            file = new File(ruta + ".pdf");
//            nombreArchivo += idSucursal + "_" + folio + "_" + (int)System.currentTimeMillis();
            inputStream = new ByteArrayInputStream(bytes);
            FileOutputStream out = new FileOutputStream(file.getAbsolutePath());
            int c = 0;

            while ((c = inputStream.read()) >= 0) {
                out.write(c);
            }

            out.flush();
            out.close();
            ubicacionPdf = "/Fresh/resources/pdf/temp/" + nombreArchivo + ".pdf";
            System.out.println("archivo upload succes " + ubicacionPdf);

        } catch (Exception e) {
            System.out.println("error > " + e.getMessage());
            System.out.println("Error al Generar el temporal del PDF");
            e.getStackTrace();
        }
        return ubicacionPdf;
    }

    public static boolean deleteFile(String rutaArchivo) {
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

        if (rutaArchivo != null && !rutaArchivo.isEmpty()) {
            String ruta = servletContext.getRealPath("") + rutaArchivo;

            try {
                File file = new File(ruta);

                if (file.exists()) {
                    return file.delete();
                }

            } catch (Exception e) {
                System.out.println("Error > " + e.getMessage());
                return false;
            }
        }

        return false;
    }

    public static String saveFile(byte[] bytes, String nombreArchivo) {
//        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
//        if (servletContext.getRealPath("") == null) {
//            temporal = Constantes.PATHSERVER;
//        } else {
//            temporal = servletContext.getRealPath("");
//        }

        File file = null;
        InputStream inputStream = null;

        try {

            file = new File(nombreArchivo);
            inputStream = new ByteArrayInputStream(bytes);
            FileOutputStream out = new FileOutputStream(file.getAbsolutePath());
            int c = 0;

            while ((c = inputStream.read()) >= 0) {
                out.write(c);
            }

            out.flush();
            out.close();
            System.out.println("archivo guardado " + nombreArchivo);

        } catch (Exception e) {
            System.out.println("error > " + e.getMessage());
            System.out.println("Error al subir archivo");
            e.getStackTrace();
        }
        return nombreArchivo;
    }

}
