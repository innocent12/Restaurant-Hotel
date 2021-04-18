package com.manage.hotel.tools;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class ImageUtil {

    public ImageUtil(){}

    public void fileSave(MultipartFile multipartFile,String fileName){
//        String fileName =  multipartFile.getOriginalFilename();
        String path1 = System.getProperty("user.dir");
        path1 = path1 + "/src/main/webapp/static/images";
        File direct = new File(path1 + "/" + fileName);
        if(!direct.exists()){
            direct.mkdirs();
        }
        try {
            multipartFile.transferTo(direct);
            System.out.println(direct);
            System.out.println("image is ok");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void byteToFile(byte[] bfile, String filePath,String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath+"\\"+fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

}
