import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FileOrganizer {
    public static void main(String[] args) {
        System.out.println("📂 File Organizer - Organizing files by extension...\n");

        // Set target directory here (or later get from Scanner)
        String targetPath = "D:\\Downloads";  // 你也可以修改为自己电脑上的测试目录
        File targetDir = new File(targetPath);

        if (!targetDir.exists() || !targetDir.isDirectory()) {
            System.out.println("❌ Directory not found: " + targetPath);
            return;
        }

        // Map extension to folder
        Map<String, String> typeMap = new HashMap<>();
        typeMap.put("jpg", "Images");
        typeMap.put("png", "Images");
        typeMap.put("gif", "Images");

        typeMap.put("pdf", "Documents");
        typeMap.put("docx", "Documents");
        typeMap.put("txt", "Documents");

        typeMap.put("mp4", "Videos");
        typeMap.put("avi", "Videos");
        typeMap.put("mov", "Videos");
		typeMap.put("mkv", "Videos");
		
        typeMap.put("rar", "Archives");
        typeMap.put("zip", "Archives");
        typeMap.put("msix", "Installers");


        File[] files = targetDir.listFiles();
        if (files == null) {
            System.out.println("❌ No files found.");
            return;
        }

        for (File file : files) {
            if (file.isFile()) {
                String fileName = file.getName();
                String extension = getExtension(fileName);
                String folderName = typeMap.getOrDefault(extension, "Others");

                File folder = new File(targetDir, folderName);
                if (!folder.exists()) {
                    folder.mkdir();
                }

                File newFile = new File(folder, fileName);
                boolean success = file.renameTo(newFile);

                if (success) {
                    System.out.println("✔ Moved: " + fileName + " → " + folderName);
                } else {
                    System.out.println("⚠ Failed to move: " + fileName);
                }
            }
        }

        System.out.println("\n✅ Organizing completed.");
    }

    private static String getExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }
        return "";
    }
}
