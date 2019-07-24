package com.art2join.authentication;

import com.herb2sy.utils.EncryptionUtils;
import com.herb2sy.utils.GetFDatas;
import com.herb2sy.utils.StringUtils;

import java.util.Date;

public class ArtOffice2Joinitem {


    /**
     * 根据个人uuid 和要添加的机构获取uuid
     * @param uuid
     * @param orgId
     */
    public static RequestData encodeSessionKey(String uuid, String orgId){

        Date date = new Date();

        uuid = encode(uuid);//加密uuid
        String md5 = EncryptionUtils.hMd5(StringUtils.obj2Str(date.getTime()) + orgId + uuid);
        md5 = md5.substring(8, 24);
        String uid = EncryptionUtils.hAESEncode(md5,uuid);
        String timeStamp = StringUtils.obj2Str(date.getTime());

        return new RequestData(EncryptionUtils.hBase64Encoder(md5),EncryptionUtils.hBase64Encoder(uid),timeStamp,EncryptionUtils.hBase64Encoder(encode(orgId)));

    }



    /**
     * 解密数据
     * @param data
     * @return
     */
    public static DecodeRequestData decodeSessionKey(RequestData data){

        Long aLong = StringUtils.obj2Long(data.getTimeStamp());
        Date date = new Date();
        //如果超时,就取消资格
        System.out.println("秒是什么意思??"+data.getTimeStamp());
        if (date.getTime() - aLong > 60*1000){
            return null;
        }

        String orgMd5 = EncryptionUtils.hBase64Decoder(data.getMd5());
        String orUid = EncryptionUtils.hBase64Decoder(data.getUuid());
        String orOrgId = EncryptionUtils.hBase64Decoder(data.getOrgUid());

        String orgUid = decode(orOrgId);

        String orUUID = EncryptionUtils.hAESDecode(orgMd5, orUid);
        String uuid = decode(orUUID);

        String sbMd5 = EncryptionUtils.hMd5(data.getTimeStamp() + orgUid + orUUID).substring(8, 24);
        if (!orgMd5.equals(sbMd5)){
            return null;
        }
        return new DecodeRequestData(uuid,orgUid);

    }


    public static String encodeHeader(String sessionKey,String uid){
        StringBuilder sb = new StringBuilder();
        sb.append(sessionKey);
        sb.append(";");
        uid = EncryptionUtils.hMd5(uid).substring(8,24);
        String s = EncryptionUtils.hAESEncode(uid, sessionKey);
        sb.append(s);

        return EncryptionUtils.hBase64Encoder(sb.toString());
    }

    public static String decodeHeader(String token){
        String s = EncryptionUtils.hBase64Decoder(token);
        return s.split(";")[0];
    }

    public static boolean isHeader(String uuid, String token){
        String s = EncryptionUtils.hBase64Decoder(token);
        String[] split = s.split(";");
        uuid = EncryptionUtils.hMd5(uuid).substring(8,24);

        String newSessionKey = EncryptionUtils.hAESDecode(uuid, split[1]);
        if (split[0].equals(newSessionKey)){
            return true;
        }
        return false;
    }





    private static String encode(String key){
        String uuid2 = GetFDatas.UUID().substring(4,10);
        String keys = "anyi"+uuid2;
        if (key.length() <= 10){
            return keys+key;
        }

        StringBuilder sb = new StringBuilder();
        String sb1 = key.substring(0, 10);
        String sb2 = key.substring(10);
        char[] chars = sb1.toCharArray();
        char[] chars1 = keys.toCharArray();
        for (int i = 0; i< 10; i++){
            sb.append(chars[i]);
            sb.append(chars1[i]);
        }

        sb.append(sb2);
        return sb.toString();
    }


    private static String decode(String key){
        if (key.length() <= 20){
            return key.substring(10);
        }
        StringBuilder sb = new StringBuilder();
        String sb1 = key.substring(0, 20);
        String sb2 = key.substring(20);
        char[] chars = sb1.toCharArray();
        for (int i = 0; i < 20; i++){
            if (i%2 == 0){
                sb.append(chars[i]);
            }

        }

        sb.append(sb2);
        return sb.toString();
    }

}
