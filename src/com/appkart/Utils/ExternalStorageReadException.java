package com.appkart.Utils;

import android.content.res.Resources.Theme;

public class ExternalStorageReadException extends Exception {

	private String message;

	public ExternalStorageReadException() {
		super();
	}

	public ExternalStorageReadException(String message) {
		super(message);
		this.message = message;
	}

	public ExternalStorageReadException(Throwable throwable) {
		super(throwable);
	}

	@Override
	public String toString() {
		return message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
