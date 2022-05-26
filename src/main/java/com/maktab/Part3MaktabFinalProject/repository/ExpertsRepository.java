package com.maktab.Part3MaktabFinalProject.repository;


import com.maktab.Part3MaktabFinalProject.entity.Experts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Repository
public interface ExpertsRepository extends JpaRepository<Experts,Long> {
    Experts findExpertsByUsernameAndPassword(String username, String password);

    List<Experts> findExpertsByFirstnameOrLastnameOrEmailOrUsername(
            String firstname
            , String lastname
            , String email
            , String username);

    static byte[] getImage(String address) {     //for saving image of expert
        File file = new File(address);
        if (file.exists()) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                var byteOutStream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "jpg", byteOutStream);
                return byteOutStream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
