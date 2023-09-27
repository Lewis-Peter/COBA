package GameCtl;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;


public class CodeUploadHandler {
    public static void handleUploadCode() {
        JFileChooser fileChooser = new JFileChooser();
        // 创建文件过滤器，只接受.java文件
        FileFilter javaFileFilter = new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory() || file.getName().toLowerCase().endsWith(".java");
            }

            @Override
            public String getDescription() {
                return "Java文件 (*.java)";
            }
        };
        fileChooser.setFileFilter(javaFileFilter);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
            // 处理代码上传和集成逻辑
            // 提示用户上传成功或失败
        }
    }
}

