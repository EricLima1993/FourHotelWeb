package br.com.fourHotel.Entities.bo;

import java.util.Calendar;

import br.com.fourHotel.Entities.models.QuartoModel;

public class QuartoBo {
	
	public static QuartoModel datas(QuartoModel quarto) {
		int num = quarto.getEstadia();
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.DAY_OF_MONTH, num);
		quarto.setCheckIn(ca.getTime());
		
		return quarto;
	}

}
