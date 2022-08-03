package com.vohizy.images;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Grayscale {
    @GetMapping("/")
    public String HelloWorld(){
        return "Hello World" ;
    }

    @PostMapping(
            value = "/image" ,
            produces = {MediaType.IMAGE_JPEG_VALUE , MediaType.IMAGE_PNG_VALUE} )

    public @ResponseBody byte[] postTest(@RequestBody byte[] image)throws IOException {
        ByteArrayInputStream img = new ByteArrayInputStream(image);
        return GrayScale(ImageIO.read(img));
    }

    public static byte[] GrayScale(BufferedImage img) throws IOException {
        byte[] bytes = new byte[0];

        //get image width and height
        int width = img.getWidth();
        int height= img.getHeight();

        //convert to grayscale
        for (int y=0; y< height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = img.getRGB(x, y);

                int a = (rgb >> 24) & 0xff;
                int r = (rgb >> 16) & 0xff;
                int g = (rgb >> 8) & 0xff;
                int b = rgb & 0xff;

                //calculate average
                int avg = (r + g + b) / 3;

                //replace RGB value with avg
                rgb = (a << 24) | (avg << 16) | (avg << 8) | avg;

                img.setRGB(x, y, rgb);
                ByteArrayOutputStream byteImage = new ByteArrayOutputStream();
                ImageIO.write(img, "jpg", byteImage);
                bytes = byteImage.toByteArray();
            }
        }
        return bytes;
    }
}
