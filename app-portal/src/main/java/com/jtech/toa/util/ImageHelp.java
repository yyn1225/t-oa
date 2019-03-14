/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.util;

import org.apache.shiro.codec.Base64;

import java.io.File;

public class ImageHelp {
    public static String getImgStr(File file) {
        if(null==file || !file.exists()){
            return null;
        }
        final byte[] fileBytes = FileIOUtils.readFile2BytesByChannel(file);
        if(null==fileBytes || fileBytes.length==0){
            return null;
        }
        return Base64.encodeToString(fileBytes);
    }
}
