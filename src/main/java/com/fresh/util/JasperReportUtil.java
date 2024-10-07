package com.fresh.util;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Juan de la Cruz
 */
public class JasperReportUtil {

    public static ByteArrayOutputStream getOutputStreamFromReport(Map map, String pathJasper) throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
      
//
        JasperPrint jp = JasperFillManager.fillReport(pathJasper, map, new JREmptyDataSource());

        JasperExportManager.exportReportToPdfStream(jp, os);
        os.flush();
        os.close();
        return os;
    }

    public static StreamedContent getStreamContentFromOutputStream(ByteArrayOutputStream os, String contentType, String nameFile) throws Exception {
        StreamedContent file = null;
        file = DefaultStreamedContent.builder().contentType(contentType).name(nameFile).stream(() -> new ByteArrayInputStream(os.toByteArray())).build();
        return file;
    }

    public static StreamedContent getStreamContentReport(List list, Map map, String pathJasper, String nameFilePdf) throws Exception {
        StreamedContent pdf = null;
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);

        JasperPrint jp = JasperFillManager.fillReport(pathJasper, map, dataSource);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jp, os);
        os.flush();
        os.close();

        InputStream is = new ByteArrayInputStream(os.toByteArray());
        pdf = DefaultStreamedContent.builder().contentType("application/pdf").name(nameFilePdf).stream(() -> new ByteArrayInputStream(os.toByteArray())).build();
        return pdf;
    }
}