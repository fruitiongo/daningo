package com.daningo.service;

import com.google.common.io.ByteStreams;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.crypto.DigestUtils;
import org.restlet.ext.fileupload.RestletFileUpload;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import sun.nio.ch.IOUtil;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.Iterator;

public class UploadService extends ServerResource {
    private int maxFileSize = 2000 * 1024;
    private int maxMemSize = 4 * 1024;
    private String tempUploadDir = "/Users/naing/Documents/temp_upload/";
    private String uploadDir = "/Users/naing/Documents/upload/" ;
    @Post
    public Representation doPost( Representation entity) {
        String responseJson = "{}";
        FileOutputStream fileOutputStream = null;
        //if(entity != null && MediaType.MULTIPART_FORM_DATA.equals(entity.getMediaType(),true)) {
        if(entity != null) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(10000240);

            RestletFileUpload upload = new RestletFileUpload(factory);
            //upload.setFileSizeMax(maxFileSize);
            System.out.println("herE");

            try{
                FileItemIterator fileItemIterator = upload.getItemIterator(entity);

                while (fileItemIterator.hasNext()) {
                    FileItemStream fileItemStream = fileItemIterator.next();
                    InputStream stream = fileItemStream.openStream();
                    if(!fileItemStream.isFormField()){
                        String fieldName = fileItemStream.getFieldName();
                        String contentType = fileItemStream.getContentType();
                        String fileName = fileItemStream.getName();

                        String ext = FilenameUtils.getExtension(fileName);

                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                        String newFileName = fileName+(timestamp.getTime());
                        String md5Hex = DigestUtils.toMd5(newFileName) + "." + ext;

                        System.out.println(fieldName + " " + contentType + " " + fileName  + " "+ ext);
                        System.out.println(md5Hex);
                        fileOutputStream = new FileOutputStream(tempUploadDir + md5Hex);

                        IOUtils.copyLarge(stream, fileOutputStream);
                        fileOutputStream.flush();

                        imageCompress(md5Hex);
                        //byte[] byteArray = ByteStreams.toByteArray(fileItemStream.openStream());
                        responseJson = "{\"filename\": \"md5Hex\" , \"imageURL\": \"http://localhost:8080/images/\"}";
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if(fileOutputStream != null)
                        fileOutputStream.close();
                } catch(IOException ie) {
                    ie.printStackTrace();
                }
            }



        } else {
            setStatus(Status.CLIENT_ERROR_BAD_REQUEST,"Error");
        }


        return new StringRepresentation(responseJson, MediaType.APPLICATION_ALL_JSON);
    }

    public void imageCompress(String fileName) {
        OutputStream outputStream = null;
        ImageOutputStream imageOutputStream = null;
        ImageWriter imageWriter = null;
        try {
            File input = new File(tempUploadDir + fileName);
            BufferedImage image = ImageIO.read(input);

            File compressedImageFile = new File(uploadDir + fileName);
            outputStream = new FileOutputStream(compressedImageFile);

            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(FilenameUtils.getExtension(fileName));
            imageWriter = (ImageWriter) writers.next();

            imageOutputStream = ImageIO.createImageOutputStream(outputStream);
            imageWriter.setOutput(imageOutputStream);

            ImageWriteParam param = imageWriter.getDefaultWriteParam();
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(0.5f);
            imageWriter.write(null, new IIOImage(image, null, null), param);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null)
                    outputStream.close();
                if(imageOutputStream != null)
                    imageOutputStream.close();
                if(imageWriter != null)
                    imageWriter.dispose();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
