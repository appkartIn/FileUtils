# FileUtils

FileUtil is library Project which is used for read and write files(byteArray) in or from Internal and External Storage.

How this library is used inside the code -
For read and write string inside file in/from internal Storage

    try {
        FileUtil.writeFileInInternalStorage(MainActivity.this, "test", data.getBytes("UFF-8"));
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

    try {
        byte[] byteArr = FileUtil.readFileFromInternalStorage(MainActivity.this, "test");
        String str = new String(byteArr, "UTF-8");
        Log.d(TAG, "ZZZ file content"+str);
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

For read and write byteArray in/from external Storage

    try {
        FileUtil.writeFileInExternalStorage("test.png", image);
    } catch (UnsupportedEncodingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (ExternalStorageWriteException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

    try {
        byte[] byteArr = FileUtil.readFileFromExternalStorage("test");
        //String str = new String(byteArr, "UTF-8");
        Log.d(TAG, "ZZZ byteArr : "+byteArr.toString());
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (ExternalStorageReadException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }