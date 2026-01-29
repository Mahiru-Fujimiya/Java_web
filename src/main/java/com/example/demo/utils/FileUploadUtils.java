package com.example.demo.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class FileUploadUtils {

    // Thư mục mặc định để lưu file (ví dụ: src/main/resources/uploads)
    public static final String UPLOAD_DIR = "uploads/";

    /**
     * Lưu file upload vào thư mục UPLOAD_DIR
     * @param file File upload
     * @return Tên file mới đã lưu
     */
    public static String saveFile(MultipartFile file) {

        if (file.isEmpty()) {
            return null;
        }

        try {
            // Tạo thư mục nếu chưa tồn tại
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Tạo tên file mới (tránh trùng)
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);

            // Lưu file vào thư mục
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return fileName;

        } catch (IOException e) {
            throw new RuntimeException("Không thể upload file: " + e.getMessage());
        }
    }

    /**
     * Xóa file trong thư mục upload
     */
    public static void deleteFile(String filename) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR + filename);
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Không thể xóa file: " + e.getMessage());
        }
    }
}
