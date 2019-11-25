package persistence;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class UtilsBanco {
	
	public static GregorianCalendar converterData(String data){
		String novaData[] = data.split("/");
		String dia = novaData[0];
		String mes = novaData[1];
		String ano = novaData[2];
		GregorianCalendar dt = new GregorianCalendar(new Integer(ano), 
				new Integer(mes) - 1, new Integer(dia));
		return dt; //cal.getTime() == DATE;
	}	
	
	public static String converterDataToBanco(GregorianCalendar data){
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		String dtNascFormatada = dt.format(data.getTime());
		return dtNascFormatada;
	}
	
	public static GregorianCalendar converterDataBancoToGC(String data){
		String novaData[] = data.split("-");
		String ano = novaData[0];
		String mes = novaData[1];
		String dia = novaData[2];
		GregorianCalendar dt = new GregorianCalendar(new Integer(ano), 
				new Integer(mes) - 1, new Integer(dia));
		return dt;
	}	
	
	public static GregorianCalendar converterTSBancoToGC(Timestamp data){
		Date d = new Date(data.getTime());
		GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(d);
		return gc;
	}
	
	public static String getDataHoraCorrente() { 
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date date = new Date(); 
		return dateFormat.format(date); 
	}
	
	public static String ConverterDataToTela(GregorianCalendar data){
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
		String dtNascFormatada = dt.format(data.getTime());
		return dtNascFormatada;
	}
	
	public static String prepararDouble(String valor) { 
		valor = valor.replace( "." , ""); //tira ponto
		valor = valor.replace( "R$ " , ""); //tira R$ 
		valor = valor.replace( "%" , ""); //tira R$ 
		valor = valor.replace( "," , "."); //trocar v�rgula por ponto
		
		return valor;
	}
	
}
