package org.launchcode.PetLife.models;

import org.springframework.web.multipart.MultipartFile;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {

    public static void saveFile(String uploadDir, String fileName,
                                byte[] image) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        InputStream is = new ByteArrayInputStream(image);
        BufferedImage newBi = ImageIO.read(is);

        File outputfile = new File(uploadDir + fileName);
        ImageIO.write(newBi, "jpg", outputfile);
    }
}
