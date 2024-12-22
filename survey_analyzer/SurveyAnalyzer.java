import java.io.*;
import java.util.*;

public class SurveyAnalyzer {
    public static void main(String[] args) {
        String fileName = "responses.csv";
        String targetColumn = "Age"; // ← 可修改为其他列，如 "Gender", "Classical music" 等

        Map<String, Integer> countMap = new LinkedHashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String headerLine = reader.readLine(); // 读取标题行
			System.out.println("列名调试：[" + headerLine + "]");  // ← 查看整行列名
			System.out.println("目标列名：" + targetColumn);
            if (headerLine == null) {
                System.out.println("❌ File is empty.");
                return;
            }

            String[] headers = headerLine.replaceAll("\"", "").split(",", -1);
            int targetIndex = -1;

            // 找到目标列的索引
            for (int i = 0; i < headers.length; i++) {
				System.out.println("找到列名：" + headers[i]);
                if (headers[i].trim().equalsIgnoreCase(targetColumn)) {
                    targetIndex = i;
                    break;
                }
            }

            if (targetIndex == -1) {
                System.out.println("❌ Column \"" + targetColumn + "\" not found.");
                return;
            }

            // 逐行读取并计数
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.replaceAll("\"", "").split(",", -1);
                if (targetIndex < values.length) {
                    String answer = values[targetIndex].trim();
                    if (!answer.isEmpty()) {
						System.out.println("读取值：" + answer);
                        countMap.put(answer, countMap.getOrDefault(answer, 0) + 1);
                    }
                }
            }

            // 输出结果
            System.out.println("📊 Frequency count for: " + targetColumn);
            System.out.println("------------------------------");
            for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
                System.out.printf("%-30s : %d\n", entry.getKey(), entry.getValue());
            }

        } catch (IOException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
