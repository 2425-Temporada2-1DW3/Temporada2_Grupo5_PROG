package com.structure;
import com.logic.Log;

public class TestGeneradorLog {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Log log = new Log();
		
		log.Log("buenos dias amigo",0);
		log.Log("buenos dias amig2o",0);
		log.clearLog();
		log.Log("buenos dias amigo 3",0);

	}

}
