package com.appkart.Utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class FileUtil {

	/**
	 * Use getFilesDir()
	 * 
	 * @param context
	 * @param fileName
	 * @param byteArr
	 * @throws IOException
	 */
	public static void writeFileInInternalStorage(Context context,
			String fileName, byte[] byteArr) throws IOException {
		writeFile(context, context.getFilesDir(), fileName, byteArr);
	}

	/**
	 * Use getFilesDir()
	 * 
	 * @param context
	 * @param fileName
	 * @param byteArr
	 * @throws IOException
	 */
	public static void writeFileInCacheStorage(Context context,
			String fileName, byte[] byteArr) throws IOException {
		writeFile(context, context.getCacheDir(), fileName, byteArr);
	}

	private static void writeFile(Context context, File fileDir,
			String fileName, byte[] byteArr) throws IOException {
		File file = new File(fileDir, fileName);
		FileOutputStream outputStream = new FileOutputStream(file);
		outputStream.write(byteArr);
		outputStream.close();
	}

	public static byte[] readFileFromInternalStorage(Context context,
			String fileName) throws IOException {
		return readFile(context, context.getFilesDir(), fileName);
	}

	public static byte[] readFileFromCacheStorage(Context context,
			String fileName) throws IOException {
		return readFile(context, context.getCacheDir(), fileName);
	}

	private static byte[] readFile(Context context, File dirName,
			String fileName) throws IOException {
		File file = new File(dirName, fileName);
		int size = (int) file.length();
		byte byteArr[] = new byte[size];

		FileInputStream inputStream = new FileInputStream(file);
		BufferedInputStream bufferedInputStream = new BufferedInputStream(
				inputStream);
		bufferedInputStream.read(byteArr, 0, byteArr.length);
		bufferedInputStream.close();

		return byteArr;
	}

	public static void writeFileInExternalStorage(String fileName, byte byteArr[])
			throws ExternalStorageWriteException, IOException {
		if (isExternalStorageWritable()) {
			File externalDirectory = Environment.getExternalStorageDirectory();
			File file = new File(externalDirectory + "/" + fileName);
			Log.d("ZZZ ", "ZZZ : "+file.getAbsolutePath());
			if (!file.exists()) {
				boolean b = file.createNewFile();
				Log.d("", "ZZZZ be : "+b);
			}
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(byteArr);
			fileOutputStream.close();
		} else {
			throw new ExternalStorageWriteException(
					"External storage is not writable!");
		}
	}

	public static byte[] readFileFromExternalStorage(String fileName)
			throws IOException, ExternalStorageReadException {
		byte[] byteArr;
		if (isExternalStorageReadable()) {
			File file = new File(Environment.getExternalStorageDirectory()
					+ "/" + fileName);
			int size = (int) file.length();
			byteArr = new byte[size];
			FileInputStream fileInputStream = new FileInputStream(file);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(
					fileInputStream);
			bufferedInputStream.read(byteArr, 0, byteArr.length);
			bufferedInputStream.close();
		} else {
			throw new ExternalStorageReadException();
		}
		
		return byteArr;
	}

	private static boolean isExternalStorageWritable() {
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean isExternalStorageReadable() {
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)
				|| state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
			return true;
		} else {
			return false;
		}
	}
}
