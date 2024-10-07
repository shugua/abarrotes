package com.fresh.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class TiempoUtil {
  

  
  private static DateFormat formatAnioMesDia = new SimpleDateFormat("yyyy-MM-dd");
  
  private static DateFormat formatoFechaDiaMesAnioHoraMinuto = new SimpleDateFormat("dd/MM/yyyy HH:mm");
  
  private static DateFormat formatoFechaDiaMesAnioHoraMinutoSegundo = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
  
  private static DateFormat formatoFechaDiaUnoMesAnio = new SimpleDateFormat("01/MM/yyyy");
  
  private static DateFormat formatoFechaDiaMesAnio = new SimpleDateFormat("dd/MM/yyyy");
  
  private static DateFormat formatoFechaDiaMesAnioDosDigitos = new SimpleDateFormat("dd/MM/yy");
  
  private static DateFormat formatFull = new SimpleDateFormat("EEEE dd 'de' MMMM 'del' yyyy HH:mm:ss");
  
  private static DateFormat formatoEneroDiaUno = new SimpleDateFormat("01/01/yyyy");
  
  private static DateFormat formatoFechaDiaMesAnioDosDigitosGuion = new SimpleDateFormat("dd-MM-yy");
  
  private static String[] diasEspanol = new String[] { "Dom", "Lun", "Mar", "Mie", "Jue", "Vie", "Sab" };
  
  public static String rellenaEspacios(int value) {
    int espacios = 4 - String.valueOf(value).length();
    String strValue = "";
    for (int i = 0; i < espacios; i++)
      strValue = strValue + "0"; 
    return strValue = strValue + value;
  }
  
  public static String getFechaDDMMYYYYHHMM(Date fecha) {
    if (fecha == null)
      return ""; 
    String convertido = formatoFechaDiaMesAnioHoraMinuto.format(fecha);
    return convertido;
  }
  
  
  public static Date getFechaDDMMYYYYHHMM(String fecha) {
    if (fecha == null)
      return null; 
    try {
      return formatoFechaDiaMesAnioHoraMinutoSegundo.parse(fecha);
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    } 
  }
  
  public static Date getDayOneOfMonth(Date fecha) {
    if (fecha == null)
      return null; 
    String convertido = formatoFechaDiaUnoMesAnio.format(fecha);
    return getFechaDDMMYYYY(convertido);
  }
  
  public static Date getFechaDDMMYYYY(String fechaString) {
    try {
      return formatoFechaDiaMesAnio.parse(fechaString);
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    } 
  }
  
  public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
    return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
  }
  
  public static Date getDayEndOfMonth(Date fecha) {
    if (fecha == null)
      return null; 
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(fecha);
    calendar.add(2, 1);
    fecha = getDayOneOfMonth(calendar.getTime());
    calendar.setTime(fecha);
    calendar.add(6, -1);
    return calendar.getTime();
  }
  
  public static Date getDayOneYear(Date fecha) {
    if (fecha == null)
      return null; 
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(fecha);
    calendar.set(6, 1);
    return calendar.getTime();
  }
  
  public static Date getDayEndYear(Date fecha) {
    if (fecha == null)
      return null; 
    fecha = sumarRestarAnios(fecha, 1);
    fecha = getDayOneYear(fecha);
    fecha = sumarRestarDias(fecha, -1);
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(fecha);
    return calendar.getTime();
  }
  
  public static LocalDateTime getDayOneYear(LocalDateTime fecha) {
    if (fecha == null)
      return null; 
    return fecha.with(TemporalAdjusters.firstDayOfYear());
  }
  
  public static LocalDateTime getDayEndYear(LocalDateTime fecha) {
    if (fecha == null)
      return null; 
    return fecha.with(TemporalAdjusters.lastDayOfYear());
  }
  
  public static String getFechaDDMMYYYY(Date fecha) {
    if (fecha == null)
      return ""; 
    String convertido = formatoFechaDiaMesAnio.format(fecha);
    return convertido;
  }
  
  
  public static String getFechaYYYYMMDD(Date fecha) {
    if (fecha == null)
      return ""; 
    String convertido = formatAnioMesDia.format(fecha);
    return convertido;
  }
  
  private static List<Date> getListDatesForDate(Date date,int diaInicio) {
    List<Date> lstDateResult = new ArrayList<>(7);
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    while (c.get(7) != diaInicio)///para decir en que dia inicia la semana
      c.add(5, -1); 
    for (int i = 0; i < 7; i++) {
      lstDateResult.add(c.getTime());
      c.add(5, 1);
    } 
    return lstDateResult;
  }
  
    private static List<Date> getListDatesForDate(Date date) {
    List<Date> lstDateResult = new ArrayList<>(7);
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    while (c.get(7) != 2)///para decir en que dia inicia la semana
      c.add(5, -1); 
    for (int i = 0; i < 7; i++) {
      lstDateResult.add(c.getTime());
      c.add(5, 1);
    } 
    return lstDateResult;
  }
  
  private static List<Date> getListDatesForDay(Date date, int days) {
    List<Date> lstDateResult = new ArrayList<>(days);
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    for (int i = 0; i < days; i++) {
      lstDateResult.add(c.getTime());
      c.add(5, 1);
    } 
    return lstDateResult;
  }
  
  public static List<String> getintervalWeekDDMMYYYYbyDay(Date fecha,int diaInicio) {
    List<String> lstWeek = new ArrayList<>(7);
    List<Date> lstDates = getListDatesForDate(fecha,diaInicio);
    for (Date day : lstDates)
      lstWeek.add(getFechaDDMMYYYY(day)); 
    return lstWeek;
  }
  
    
  public static List<String> getintervalWeekDDMMYYYYbyDay(Date fecha) {
    List<String> lstWeek = new ArrayList<>(7);
    List<Date> lstDates = getListDatesForDate(fecha);
    for (Date day : lstDates)
      lstWeek.add(getFechaDDMMYYYY(day)); 
    return lstWeek;
  }
  
  public static List<String> getintervalDDMMYYYYbyDay(Date fecha, int days) {
    List<String> lstDays = new ArrayList<>(days);
    List<Date> lstDates = getListDatesForDay(fecha, days);
    for (Date day : lstDates)
      lstDays.add(getFechaDDMMYYYY(day)); 
    return lstDays;
  }
  
  public static Date sumarRestarDias(Date fecha, int dias) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(fecha);
    cal.add(6, dias);
    return cal.getTime();
  }
  
  public static Date sumarRestarMeses(Date fecha, int meses) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(fecha);
    cal.add(2, meses);
    return cal.getTime();
  }
  
  public static Date sumarRestarAnios(Date fecha, int anios) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(fecha);
    cal.add(1, anios);
    return cal.getTime();
  }
  
  public static int getYear(Date fecha) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(fecha);
    int anio = cal.get(1);
    return anio;
  }
  
  public static int getDayOFMonth(Date fecha) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(fecha);
    int anio = cal.get(5);
    return anio;
  }
  
  public static String getMonthYear(Date fecha) {
    int year = 0;
    String month = "";
    Calendar cal = Calendar.getInstance();
    cal.setTime(fecha);
    year = cal.get(1);
    month = getNameMonth(cal.get(2));
    return month + "/" + year;
  }
  
  public static String getNameMonth(int month) {
    String nameMonth = "Enero";
    switch (month) {
      case 0:
        nameMonth = "Enero";
        break;
      case 1:
        nameMonth = "Febrero";
        break;
      case 2:
        nameMonth = "Marzo";
        break;
      case 3:
        nameMonth = "Abril";
        break;
      case 4:
        nameMonth = "Mayo";
        break;
      case 5:
        nameMonth = "Junio";
        break;
      case 6:
        nameMonth = "Julio";
        break;
      case 7:
        nameMonth = "Agosto";
        break;
      case 8:
        nameMonth = "Septiembre";
        break;
      case 9:
        nameMonth = "Octubre";
        break;
      case 10:
        nameMonth = "Noviembre";
        break;
      case 11:
        nameMonth = "Diciembre";
        break;
    } 
    return nameMonth;
  }
  
  public static String nombreDia(Date fecha) {
    GregorianCalendar cal = new GregorianCalendar();
    cal.setTime(fecha);
    int numeroDia = cal.get(7);
    return diasEspanol[numeroDia - 1];
  }
  
  public static Date getFechaDDMMYYYYDate(Date fecha) {
    if (fecha == null)
      return new Date(); 
    String convertido = formatoFechaDiaMesAnio.format(fecha);
    return getFechaDDMMYYYY(convertido);
  }
  
  public static int getYearTwoDigitos(Date fecha) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(fecha);
    String anioStr = Integer.toString(cal.get(1));
    anioStr = anioStr.substring(2, anioStr.length());
    int anio = Integer.parseInt(anioStr);
    return anio;
  }
  
  public static int getNumberMonthYear(Date fecha) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(fecha);
    return cal.get(3);
  }
  
  public static String getFechaDDMMYY(Date fecha) {
    if (fecha == null)
      return ""; 
    String convertido = formatoFechaDiaMesAnioDosDigitos.format(fecha);
    return convertido;
  }
  
  public static String getFechaFull(Date fecha) {
    if (fecha == null)
      return ""; 
    String dateStr = formatFull.format(fecha);
    return dateStr;
  }
  
  public static synchronized int diferenciasDeFechas(Date fechaInicial, Date fechaFinal) {
    DateFormat df = DateFormat.getDateInstance(2);
    String fechaInicioString = df.format(fechaInicial);
    try {
      fechaInicial = df.parse(fechaInicioString);
    } catch (ParseException parseException) {}
    String fechaFinalString = df.format(fechaFinal);
    try {
      fechaFinal = df.parse(fechaFinalString);
    } catch (ParseException parseException) {}
    long fechaInicialMs = fechaInicial.getTime();
    long fechaFinalMs = fechaFinal.getTime();
    long diferencia = fechaFinalMs - fechaInicialMs;
    double dias = Math.floor((diferencia / 86400000L));
    return (int)dias;
  }
  
  public static synchronized double diferenciasDeFechasInSeconds(Date fechaInicial, Date fechaFinal) {
    long fechaInicialMs = fechaInicial.getTime();
    long fechaFinalMs = fechaFinal.getTime();
    long diferencia = fechaFinalMs - fechaInicialMs;
    double dias = Math.floor((diferencia / 1000L));
    return dias;
  }
  
  ///regresa una lista de string con el dia uno del mes y el dia final del mes
  public static ArrayList<String> obtenerFechaInicioMesFinMesPorMes(int month, Date fecha) {
    ArrayList<String> fechas = new ArrayList<>();
    month++;
    DateFormat formatoEneroDiaUno = new SimpleDateFormat("01/" + month + "/yyyy");
    fechas.add(formatoEneroDiaUno.format(fecha));
    fechas.add(getFechaDDMMYYYY(sumarRestarDias(sumarRestarMeses(getFechaDDMMYYYY(formatoEneroDiaUno.format(fecha)), 1), -1)));
    return fechas;
  }
  
  public static Date obtenerUltimoDomingoMes(Date fecha) {
    Calendar cal = GregorianCalendar.getInstance();
    cal.set(fecha.getYear(), fecha.getMonth(), fecha.getDay());
    cal.set(7, 1);
    cal.set(8, -1);
    return cal.getTime();
  }
  
  public static BigDecimal obtenerUltimoDiaMes(Date fecha) {
    Calendar cal = GregorianCalendar.getInstance();
    cal.set(fecha.getYear(), fecha.getMonth() + 1, 0);
    return new BigDecimal(cal.getActualMaximum(5));
  }
  
  public static String nombreDia(int numeroDiaSemana) {
    String dia = "Lunes";
    switch (numeroDiaSemana) {
      case 0:
        dia = "Domingo";
        break;
      case 1:
        dia = "Lunes";
        break;
      case 2:
        dia = "Martes";
        break;
      case 3:
        dia = "Miercoles";
        break;
      case 4:
        dia = "Jueves";
        break;
      case 5:
        dia = "Viernes";
        break;
      case 6:
        dia = "Sabado";
        break;
    } 
    return dia;
  }
  
  public static int getNumberDayForWeek(Date fecha) {
    GregorianCalendar cal = new GregorianCalendar();
    cal.setTime(fecha);
    int numeroDia = cal.get(7);
    return numeroDia - 1;
  }
  
  public static int getMinutesBetweenTwoHour(String hhmm, String hhmmTwo) {
    if (hhmm == null || hhmm.equals("--"))
      hhmm = "00:00"; 
    if (hhmmTwo == null || hhmmTwo.equals("--"))
      hhmmTwo = "00:00"; 
    String[] hhmmSplit = hhmm.split(":");
    String[] hhmmTwoSplit = hhmmTwo.split(":");
    int hora = Integer.parseInt(hhmmSplit[0]);
    int minuto = Integer.parseInt(hhmmSplit[1]);
    int horaTwo = Integer.parseInt(hhmmTwoSplit[0]);
    int minutoTwo = Integer.parseInt(hhmmTwoSplit[1]);
    minuto += hora * 60;
    minutoTwo += horaTwo * 60;
    return minuto - minutoTwo;
  }
  
  public static String gethhmm(int hora, int minuto) {
    String strHora = "";
    String strMinutos = "";
    if (hora < 10) {
      strHora = "0" + hora;
    } else {
      strHora = Integer.toString(hora);
    } 
    if (minuto < 10) {
      strMinutos = "0" + minuto;
    } else {
      strMinutos = Integer.toString(minuto);
    } 
    return strHora + ":" + strMinutos;
  }
  
  public static String sumarHorasFormatohhmm(String hhmm, String hhmmTwo) {
    String strHora = "00";
    String strMinuto = "00";
    if (hhmm == null || hhmm.equals("--"))
      hhmm = "00:00"; 
    if (hhmmTwo == null || hhmmTwo.equals("--"))
      hhmmTwo = "00:00"; 
    String[] hhmmSplit = hhmm.split(":");
    String[] hhmmTwoSplit = hhmmTwo.split(":");
    int hora = Integer.parseInt(hhmmSplit[0]);
    int minuto = Integer.parseInt(hhmmSplit[1]);
    int horaTwo = Integer.parseInt(hhmmTwoSplit[0]);
    int minutoTwo = Integer.parseInt(hhmmTwoSplit[1]);
    hora += horaTwo;
    minuto += minutoTwo;
    hora += minuto / 60;
    minuto %= 60;
    if (hora < 10) {
      strHora = "0" + hora;
    } else {
      strHora = Integer.toString(hora);
    } 
    if (minuto < 10) {
      strMinuto = "0" + minuto;
    } else {
      strMinuto = Integer.toString(minuto);
    } 
    return strHora + ":" + strMinuto;
  }
  
  public static String getFechaTitulosDDMMYY(Date fecha) {
    if (fecha == null)
      return ""; 
    String convertido = formatoFechaDiaMesAnioDosDigitosGuion.format(fecha);
    return convertido;
  }
  
  public static Date setYearDate(int year, Date fecha) {
    DateFormat formatoSetYearDate = new SimpleDateFormat("dd/MM/" + year);
    String convertido = formatoSetYearDate.format(fecha);
    return getFechaDDMMYYYY(convertido);
  }
  
  public static int getMonth(Date fecha) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(fecha);
    int mes = cal.get(2);
    return mes;
  }
  
  public static String getMonthString(Date fecha) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(fecha);
    int mes = cal.get(2) + 1;
    return (mes < 10) ? ("0" + mes) : String.valueOf(mes);
  }
  
  public static Date setMonthDate(int month, Date fecha) {
    DateFormat formatoSetYearDate = new SimpleDateFormat("dd/" + month + "/yyyy");
    String convertido = formatoSetYearDate.format(fecha);
    return getFechaDDMMYYYY(convertido);
  }
}
