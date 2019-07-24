package com.art2join;

import com.art2join.authentication.ArtOffice2Joinitem;

public class Test {

    public static void main(String [] arg){
        String key= ArtOffice2Joinitem.encodeHeader("woshishei", "nihaome");
        System.out.println("加密后的数据是"+key);

        String decodeRequestData = ArtOffice2Joinitem.decodeHeader(key);
        System.out.println("解密后的数据是"+decodeRequestData);
        boolean nihaome = ArtOffice2Joinitem.isHeader("nihaome", key);
        System.out.println("解密后的数据是不是???"+nihaome);



    }
}
