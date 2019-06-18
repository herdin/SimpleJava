package com.harm.unit.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.harm.unit.Unit;

public class IOStudy implements Unit {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void excute(Object[] objects) throws Exception {
		this.logger.debug("");
		File file = new File("");
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(null);
	}//END OF FUNCTION
	
}//END OF CLASS