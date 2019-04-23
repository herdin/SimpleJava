package com.harm.utils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {
	private static Logger logger = LoggerFactory.getLogger(FileUtils.class);
	public static String getTextFromFile(String fileFullPathStr) {
		String output = "";
		FileChannel fileChannel = null;
		try {
			StringBuffer stringBuffer = new StringBuffer();
			Path fileFullPath = Paths.get(fileFullPathStr);
			fileChannel = FileChannel.open(fileFullPath, StandardOpenOption.READ);
			ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
			byte[] bytes = new byte[byteBuffer.capacity()];
			while(fileChannel.position() < fileChannel.size()) {
				fileChannel.read(byteBuffer);
				byteBuffer.flip();
				while(byteBuffer.hasRemaining()) {
					int remaining = byteBuffer.remaining();
					byteBuffer.get(bytes, 0, remaining);
					stringBuffer.append(new String(bytes, 0, remaining));
				}
				byteBuffer.clear();
			}
			output = stringBuffer.toString();
		} catch (IOException e) {
			FileUtils.logger.error(e.getMessage());
		} finally {
			if(fileChannel != null && fileChannel.isOpen())
				try {
					fileChannel.close();
				} catch (IOException e) {
					FileUtils.logger.error(e.getMessage());
				}
		}
		return output;
	}//END OF FUNCTION
}//END OF CLASS
